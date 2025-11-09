package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_CloseUp_I3S3E3_Blue", group="Main")
public class Auton_CloseUp_I3S3E3_Blue extends Auton_CloseUp_I3S3E3 {

    public void init() {
        setColor(AllianceColor.BLUE);
        super.init();
    }
}

