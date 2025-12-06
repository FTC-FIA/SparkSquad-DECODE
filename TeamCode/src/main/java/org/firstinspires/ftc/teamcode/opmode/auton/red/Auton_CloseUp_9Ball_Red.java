package org.firstinspires.ftc.teamcode.opmode.auton.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_CloseUp_9Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Close (Old)", group="Main")
@Disabled
public class Auton_CloseUp_9Ball_Red extends Auton_CloseUp_9Ball {

    public void init() {
        setColor(Alliance.RED);
        super.init();
    }
}

