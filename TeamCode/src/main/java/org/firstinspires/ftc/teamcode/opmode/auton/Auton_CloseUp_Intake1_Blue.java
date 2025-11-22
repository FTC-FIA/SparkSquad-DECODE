package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_CloseUp_Intake1_Blue", group="Main")
public class Auton_CloseUp_Intake1_Blue extends Auton_CloseUp_Intake1 {

    public void init() {
        setColor(AllianceColor.BLUE);
        super.init();
    }
}

