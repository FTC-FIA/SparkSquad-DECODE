package org.firstinspires.ftc.teamcode.opmode.auton.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_CloseUp_I3S3E3;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Close 3 Ball ", group="Main")
@Disabled
public class Auton_CloseUp_I3S3E3_Red extends Auton_CloseUp_I3S3E3 {

    public void init() {
        setColor(Alliance.RED);
        super.init();
    }
}

