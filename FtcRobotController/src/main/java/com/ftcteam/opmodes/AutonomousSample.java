package com.ftcteam.opmodes;

import com.firstfl.SafeLinearOpMode;
import com.ftcteam.Robot;

/**
 * Created by chris on 10/12/15.
 */
public class AutonomousSample extends SafeLinearOpMode {
    private Robot robot;

    @Override
    public void initOpMode() throws InterruptedException {
        robot = new Robot(this);
    }

    @Override
    public void runOpMode() throws InterruptedException {

    }

    @Override
    public void stopOpMode() {

    }
}
