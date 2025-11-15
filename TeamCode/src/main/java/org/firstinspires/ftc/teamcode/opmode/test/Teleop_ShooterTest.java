package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.ShooterTestController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="TeleOp_ShooterTest", group="Main")
public class Teleop_ShooterTest extends RobotBaseOpMode {
    private ShooterTestController shooterTestController;
    public void init(){
        super.init();
        shooterTestController = new ShooterTestController(this);
    }
    public void loop(){
        shooterTestController.handleInput();
    }
}

