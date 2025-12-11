package org.firstinspires.ftc.teamcode.opmode.auton.attic.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_CloseUp_9Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close (Old)", group="Main")
@Disabled
public class Auton_CloseUp_9Ball_Blue extends Auton_CloseUp_9Ball {

    public void init() {
        setColor(Alliance.BLUE);
        super.init();
    }
}

