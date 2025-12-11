package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.controller.AssistedShooterController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.Alliance;

public abstract class TeleOp_AutoVelocity extends RobotBaseOpMode
{
    protected AssistedShooterController assistedShooterController;

    @Override
    public void init() {
        super.init();
        assistedShooterController = new AssistedShooterController(this, alliance);
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
        limelightOdometerController.handleInput();
        feederController.handleInput();
        robotRelativeDriveController.handleInput();
        assistedShooterController.handleInput();
        intakeController.handleInput();
        kickerController.handleInput();

        // Display Telemetry
        telemetry.addData("Runtime:", runtime.toString());
    }
}
