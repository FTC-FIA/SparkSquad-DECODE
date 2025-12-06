package org.firstinspires.ftc.teamcode.opmode.auton.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_6Ball;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Long 6 Ball GPP", group="Main")
public class Auton_LongShot_Intake3_Red extends Auton_LongShot_6Ball {

    public void init() {
        setColor( Alliance.RED );
        super.init();
    }
}

