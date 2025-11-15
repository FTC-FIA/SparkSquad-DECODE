package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@TeleOp(name="AutoVel Red", group="Dev")
public class TeleOp_AutoVelocity_Red extends TeleOp_AutoVelocity {
    public void init() {
        setColor(AllianceColor.RED);
        super.init();
    }
}

