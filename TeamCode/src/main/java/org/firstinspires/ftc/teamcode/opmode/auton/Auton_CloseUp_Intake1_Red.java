package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_CloseUp_Intake1_Red", group="Main")
@Disabled
public class Auton_CloseUp_Intake1_Red extends Auton_CloseUp_Intake1 {

    public void init() {
        setColor(AllianceColor.RED);
        super.init();
    }
}

