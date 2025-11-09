package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="TeleOp_Main_RobotRelative", group="Main")
public class TeleOp__Main_RobotRelative extends RobotBaseOpMode
{
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        runtime.reset();
    }

    public double getVelocity() {
        return shooter.getShooterVelocity();
    }

    @Override
    public void init_loop() {
        mecanumDrive.drive(0.0, 0.0, 0.0);
    }

    @Override
    public void loop() {

        // let controllers do their thing
        shooterController.handleInput();
        kickerController.handleInput();
        feederController.handleInput();
        robotRelativeDriveController.handleInput();
        intakeController.handleInput();

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
