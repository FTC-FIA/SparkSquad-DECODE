package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_I1S2E2_Red", group="Test")
public class Auton_I1S2E2_Red extends Auton_I1S2E2 {

    public void init() {
        setColor(AllianceColor.RED);
        super.init();
    }
}

