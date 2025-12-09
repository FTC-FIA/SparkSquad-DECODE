package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_LongShot_6Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Long (Old)", group="Main")
public class Auton_LongShot_6Ball_Blue extends Auton_LongShot_6Ball {

    public void init() {
        setColor( Alliance.BLUE );
        super.init();
    }
}

