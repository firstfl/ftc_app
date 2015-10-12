package com.firstfl;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Created by chris on 10/9/15.
 */
public abstract class SafeLinearOpMode extends OpMode implements Runnable {
    Thread thread;
    boolean opModeActive = false;
    ElapsedTime timer;

    public synchronized void waitForStart() throws InterruptedException {
        while(!opModeActive) {
            synchronized(this) {
                this.wait();
            }
        }

    }

    public void waitOneFullHardwareCycle() throws InterruptedException {
        this.waitForNextHardwareCycle();
        Thread.sleep(1L);
        this.waitForNextHardwareCycle();
    }

    public void waitForNextHardwareCycle() throws InterruptedException {
        synchronized(this) {
            this.wait();
        }
    }

    public boolean opModeIsActive(){
        return opModeActive;
    }

    @Override
    public void init() {
        thread = new Thread(this);
        thread.start();
        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        opModeActive = true;
        synchronized (this){
            notifyAll();
        }
    }

    @Override
    public void loop() {
        synchronized(this) {
            this.notifyAll();
        }
    }

    @Override
    public void stop() {
        opModeActive = false;
        if(thread.isAlive()){
            thread.interrupt();
        }
        timer.reset();
        while(thread.isAlive() && timer.time() < 0.5){
            Thread.yield();
        }
        if(thread.isAlive()){
            RobotLog.e("*****************************************************************");
            RobotLog.e("User Safe Linear Op Mode took too long to exit; emergency killing app.");
            RobotLog.e("all code within RunOpMode must end within 0.5 Seconds");
            RobotLog.e("*****************************************************************");
            System.exit(-1);
        }
    }

    /**
     * Called before the start of the match on the same thread as runOpMode
     * @throws InterruptedException to allow sleeping
     */
    public abstract void initOpMode() throws InterruptedException;
    /**
     * runs in separate thread
     * @throws InterruptedException to allow sleeping
     */
    public abstract void runOpMode() throws InterruptedException;

    /**
     * cleanup OpMode,runs on the same thread as runOpMode
     */
    public abstract void stopOpMode();

    @Override
    public void run() {
        try {
            timer.reset();
            initOpMode();
            waitForStart();
            runOpMode();
        } catch (InterruptedException e) {
            RobotLog.w("Interrupt triggered, this is probably because the game was ended forcefully");
            e.printStackTrace();
        }finally {
            stopOpMode();
            RobotLog.d("thread finished");
        }
    }
}
