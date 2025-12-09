package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Alliance;

@TeleOp(name="Limelight Test Blue", group="Test")
public class TeleOp_LimelightTest_Blue extends TeleOp_LimelightTest {

    @Override
    public void init() {
        setAlliance(Alliance.BLUE);
        super.init();
    }
}
