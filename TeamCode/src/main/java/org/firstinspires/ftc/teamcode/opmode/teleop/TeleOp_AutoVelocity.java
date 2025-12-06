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
    protected Limelight3A limelight;
    protected Alliance color;

//    protected void setColor(Alliance color) {
//        this.color = color;
//    }

    @Override
    public void init() {
        super.init();
        assistedShooterController = new AssistedShooterController(this, color);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
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
        feederController.handleInput();
        robotRelativeDriveController.handleInput();
        assistedShooterController.handleInput();
        intakeController.handleInput();
        kickerController.handleInput();

        LLResult result = limelight.getLatestResult();
        if (result.isValid()) {
            Pose3D botPose = result.getBotpose();
            telemetry.addData("LL X", botPose.getPosition().x);
            telemetry.addData("LL Y", botPose.getPosition().y);
            telemetry.addData("LL Z", botPose.getPosition().z);
            telemetry.addData("LL X", botPose.getOrientation().getYaw(AngleUnit.DEGREES));
        } else {
            telemetry.addData("LL Result", "Invalid");
        }

        // Display Telemetry
        telemetry.addData("Runtime:", runtime.toString());
        telemetry.update();
    }
}
