package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_I3S3E2_Blue", group="Test")
public class Auton_I3S3E2_Blue extends Auton_I3S3E2 {

    public void init() {
        setColor(AllianceColor.BLUE);
        super.init();
    }
}

