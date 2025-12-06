package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_CloseUp_9Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close 9 Ball", group="Main")
public class Auton_CloseUp_Intake1_Blue extends Auton_CloseUp_9Ball {

    public void init() {
        setColor(Alliance.BLUE);
        super.init();
    }
}

