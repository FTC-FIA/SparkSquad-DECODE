package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_6Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Long (Old)", group="Main")
@Disabled
public class Auton_LongShot_6Ball_Blue extends Auton_LongShot_6Ball {

    public void init() {
        setColor( Alliance.BLUE );
        super.init();
    }
}

