package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="Old TeleOp w/Manual Shooting", group="Test")
@Disabled
public class TeleOp_NoAutoVelocity extends RobotBaseOpMode
{
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void init_loop() {
        mecanumDrive.drive(0.0, 0.0, 0.0);
    }

    @Override
    public void loop() {

        // let controllers do their thing
        kickerController.handleInput();
        feederController.handleInput();
        robotRelativeDriveController.handleInput();
        intakeController.handleInput();
        shooterController.handleInput();

        // Display Telemetry
        telemetry.addData("Runtime:", runtime.toString());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
