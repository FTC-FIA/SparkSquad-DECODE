package org.firstinspires.ftc.teamcode.opmode.auton.attic.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_CloseUp_9Ball_AutoAim;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close (AutoAim)", group="Main")
@Disabled
public class Auton_CloseUp_9Ball_Blue_AutoAim extends Auton_CloseUp_9Ball_AutoAim {

    public void init() {
        setAlliance(Alliance.BLUE);
        super.init();
    }
}

