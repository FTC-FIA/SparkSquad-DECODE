package org.firstinspires.ftc.teamcode.opmode.auton.attic.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_LongShot_6Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Long (Old)", group="Main")
public class Auton_LongShot_6Ball_Red extends Auton_LongShot_6Ball {

    public void init() {
        setColor( Alliance.RED );
        super.init();
    }
}

