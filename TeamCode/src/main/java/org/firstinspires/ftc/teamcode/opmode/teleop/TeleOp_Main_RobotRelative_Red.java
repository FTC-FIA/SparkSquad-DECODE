package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@TeleOp(name="TeleOp_Main_RobotRelative_Red", group="Main")
public class TeleOp_Main_RobotRelative_Red extends TeleOp__Main_RobotRelative {
    public void init() {
        setColor(AllianceColor.RED);
        super.init();
    }
}

