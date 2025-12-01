package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_I1S1E1;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Long 3 Ball", group="Main")
public class Auton_LongShot_I1S1E1_Blue extends Auton_LongShot_I1S1E1 {

    public void init() {
        setColor( Alliance.BLUE );
        super.init();
    }
}

