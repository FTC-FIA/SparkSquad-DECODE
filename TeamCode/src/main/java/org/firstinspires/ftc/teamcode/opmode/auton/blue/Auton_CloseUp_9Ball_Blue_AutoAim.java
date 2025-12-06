package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_CloseUp_9Ball_AutoAim;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close (AutoAim)", group="Main")
public class Auton_CloseUp_9Ball_Blue_AutoAim extends Auton_CloseUp_9Ball_AutoAim {

    public void init() {
        setAlliance(Alliance.BLUE);
        super.init();
    }
}

