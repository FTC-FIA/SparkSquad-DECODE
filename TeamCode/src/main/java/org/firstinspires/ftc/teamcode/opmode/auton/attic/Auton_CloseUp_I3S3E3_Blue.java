package org.firstinspires.ftc.teamcode.opmode.auton.attic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close 3 Ball", group="Main")
@Disabled
public class Auton_CloseUp_I3S3E3_Blue extends Auton_CloseUp_I3S3E3 {

    public void init() {
        setColor(Alliance.BLUE);
        super.init();
    }
}

