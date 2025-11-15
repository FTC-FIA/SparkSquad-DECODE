package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.AllianceColor;

@TeleOp(name="AutoVel Blue", group="Dev")
public class TeleOp_AutoVelocity_Blue extends TeleOp_AutoVelocity {
    public void init() {
        setColor(AllianceColor.BLUE);
        super.init();
    }
}

