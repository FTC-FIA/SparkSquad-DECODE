package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_LongShot_Intake3_Red", group="Main")
public class Auton_LongShot_Intake3_Red extends Auton_LongShot_Intake3 {

    public void init() {
        setColor( AllianceColor.RED );
        super.init();
    }
}

