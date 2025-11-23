package org.firstinspires.ftc.teamcode.opmode.teleop;


import org.firstinspires.ftc.teamcode.controller.AssistedShooterController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.AllianceColor;

public abstract class TeleOp_AutoVelocity extends RobotBaseOpMode
{
    protected AssistedShooterController assistedShooterController;
    protected AllianceColor color;

    protected void setColor(AllianceColor color) {
        this.color = color;
    }

    @Override
    public void init() {
        super.init();
        assistedShooterController = new AssistedShooterController(this, color);
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
        kickerController.handleInput();
        feederController.handleInput();
        robotRelativeDriveController.handleInput();
        //shooterController.handleInput(); // replaced!
        assistedShooterController.handleInput();
        intakeController.handleInput();

        // Display Telemetry
        telemetry.addData("Runtime:", runtime.toString());
        telemetry.update();
    }
}
