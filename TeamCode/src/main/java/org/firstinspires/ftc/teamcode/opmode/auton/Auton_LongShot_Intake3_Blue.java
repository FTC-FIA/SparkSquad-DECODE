package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@Autonomous(name="Auton_LongShot_Intake3_Blue", group="Main")
public class Auton_LongShot_Intake3_Blue extends Auton_LongShot_Intake3 {

    public void init() {
        setColor( AllianceColor.BLUE );
        super.init();
    }
}

