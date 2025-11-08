package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_I1S2E2_Blue", group="Test")
public class Auton_I1S2E2_Blue extends Auton_I1S2E2 {

    public void init() {
        setColor(AllianceColor.BLUE);
        super.init();
    }
}

