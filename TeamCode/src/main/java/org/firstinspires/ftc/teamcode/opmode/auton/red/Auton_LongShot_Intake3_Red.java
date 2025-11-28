package org.firstinspires.ftc.teamcode.opmode.auton.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_Intake3;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Red Long 6 Ball GPP", group="Main")
@Disabled
public class Auton_LongShot_Intake3_Red extends Auton_LongShot_Intake3 {

    public void init() {
        setColor( Alliance.RED );
        super.init();
    }
}

