package org.firstinspires.ftc.teamcode.opmode.auton.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_I1S1E1;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Long 3 Ball", group="Main")
public class Auton_LongShot_I1S1E1_Red extends Auton_LongShot_I1S1E1 {

    public void init() {
        setColor( Alliance.RED );
        super.init();
    }
}

