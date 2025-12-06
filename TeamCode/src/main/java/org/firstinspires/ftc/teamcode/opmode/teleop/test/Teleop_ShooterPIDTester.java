package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.ShooterTestController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="Shooter PID Tester", group="Test")
public class Teleop_ShooterPIDTester extends RobotBaseOpMode {
    private ShooterTestController shooterTestController;
    public void init(){
        super.init();
        shooterTestController = new ShooterTestController(this);
    }
    public void loop(){
        shooterTestController.handleInput();
    }
}

