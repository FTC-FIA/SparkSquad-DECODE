package org.firstinspires.ftc.teamcode.opmode.auton.attic.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.attic.Auton_LongShot_6Ball_AutoAim;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Long (AutoAim)", group="Main")
@Disabled
public class Auton_LongShot_6Ball_Blue_AutoAim extends Auton_LongShot_6Ball_AutoAim {

    public void init() {
        setColor( Alliance.BLUE );
        super.init();
    }
}

