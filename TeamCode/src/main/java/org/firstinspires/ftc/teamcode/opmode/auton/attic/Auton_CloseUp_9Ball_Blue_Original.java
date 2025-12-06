package org.firstinspires.ftc.teamcode.opmode.auton.attic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Close 9Ball", group="Main")
@Disabled
public class Auton_CloseUp_9Ball_Blue_Original extends Auton_CloseUp_9Ball_Original {

    public void init() {
        setColor(Alliance.BLUE);
        super.init();
    }
}

