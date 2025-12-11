package org.firstinspires.ftc.teamcode.opmode.auton.attic.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_CloseUp_9Ball_AutoAim;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Close (Auto Aim)", group="Main")
public class Auton_CloseUp_9Ball_Red_AutoAim extends Auton_CloseUp_9Ball_AutoAim {

    public void init() {
        setAlliance(Alliance.RED);
        super.init();
    }
}

