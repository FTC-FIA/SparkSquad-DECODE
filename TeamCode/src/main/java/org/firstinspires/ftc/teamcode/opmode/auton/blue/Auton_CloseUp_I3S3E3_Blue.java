package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_CloseUp_I3S3E3;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close 3 Ball", group="Main")
public class Auton_CloseUp_I3S3E3_Blue extends Auton_CloseUp_I3S3E3 {

    public void init() {
        setColor(Alliance.BLUE);
        super.init();
    }
}

