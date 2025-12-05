package org.firstinspires.ftc.teamcode.opmode.auton.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_Intake3;
import org.firstinspires.ftc.teamcode.opmode.auton.base.Auton_LongShot_Intake3_PID;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous(name="Blue Long 6 Ball GPP", group="Main")
public class Auton_LongShot_Intake3_Blue extends Auton_LongShot_Intake3_PID {

    public void init() {
        setColor( Alliance.BLUE );
        super.init();
    }
}

