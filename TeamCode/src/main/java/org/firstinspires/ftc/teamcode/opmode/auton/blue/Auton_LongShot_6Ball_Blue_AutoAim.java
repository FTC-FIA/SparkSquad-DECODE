package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_6Ball_AutoAim;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Long (AutoAim)", group="Main")
public class Auton_LongShot_6Ball_Blue_AutoAim extends Auton_LongShot_6Ball_AutoAim {

    public void init() {
        setColor( Alliance.BLUE );
        super.init();
    }
}

