package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.auton.Auton_CloseUp_I3S3E3;
import org.firstinspires.ftc.teamcode.util.AllianceColor;

@TeleOp(name="TeleOp_Main_RobotRelative_Blue", group="Main")
public class TeleOp_Main_RobotRelative_Blue extends TeleOp__Main_RobotRelative {
    public void init() {
        setColor(AllianceColor.BLUE);
        super.init();
    }
}

