package org.firstinspires.ftc.teamcode.opmode.auton.attic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Close 9 Ball", group="Main")
@Disabled
public class Auton_CloseUp_9Ball_Red_Original extends Auton_CloseUp_9Ball_Original {

    public void init() {
        setColor(Alliance.RED);
        super.init();
    }
}

