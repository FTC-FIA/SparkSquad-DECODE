package org.firstinspires.ftc.teamcode.opmode.auton.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_CloseUp_Intake1;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Close 6 Ball PPG", group="Main")
public class Auton_CloseUp_Intake1_Red extends Auton_CloseUp_Intake1 {

    public void init() {
        setColor(Alliance.RED);
        super.init();
    }
}

