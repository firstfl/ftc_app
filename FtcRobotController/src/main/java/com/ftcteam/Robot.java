package com.ftcteam;

import com.firstfl.BasicRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by chris on 10/12/15.
 */
public class Robot extends BasicRobot{
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    public Robot(OpMode opMode) {
        super(opMode);
        leftDrive = getHardwareMap().dcMotor.get("leftDrive");
        rightDrive = getHardwareMap().dcMotor.get("rightDrive");
    }

    public void setDriveMotors(double left, double right){
        leftDrive.setPower(left);
        rightDrive.setPower(right);
    }
}
