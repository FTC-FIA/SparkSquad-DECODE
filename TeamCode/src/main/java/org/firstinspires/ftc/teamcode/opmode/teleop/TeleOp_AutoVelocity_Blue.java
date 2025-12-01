package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp(name="TeleOp Blue", group="Main")
public class TeleOp_AutoVelocity_Blue extends TeleOp_AutoVelocity {
    public void init() {
        setColor(Alliance.BLUE);
        super.init();
    }
}

