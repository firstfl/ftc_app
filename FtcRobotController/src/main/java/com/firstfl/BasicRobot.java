package com.firstfl;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by chris on 10/12/15.
 */
public class BasicRobot {
    private final OpMode opMode;

    public BasicRobot(OpMode opMode){
        this.opMode = opMode;
    }

    /**
     * get the OpMode that is using this class
     * @return the current OpMode that is using this class
     */
    public OpMode getOpMode(){
        return opMode;
    }

    /**
     * get the LinearOpMode that is using this class if the OpMode is a LinearOpMode is using a linearOpMode
     * WARNING: ONLY USE THIS METHOD FROM A LinearOpMode, otherwise your OpMode will crash.
     * @return the current LinearOpMode that is using this class
     * @throws ClassCastException if the OpMode is not a linearOpMode
     */
    public LinearOpMode getLinearOpMode(){
        return (LinearOpMode) opMode;
    }

    public HardwareMap getHardwareMap(){
        return opMode.hardwareMap;
    }

    public Context getAppContext(){
        return getHardwareMap().appContext;
    }
}
