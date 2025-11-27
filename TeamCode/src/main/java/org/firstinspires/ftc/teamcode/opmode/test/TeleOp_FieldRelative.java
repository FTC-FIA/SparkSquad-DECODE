package org.firstinspires.ftc.teamcode.opmode.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.AssistedShooterController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.AllianceColor;

@TeleOp(name="Test - Field Relative", group="Dev")
public class TeleOp_FieldRelative extends RobotBaseOpMode
{
    protected AllianceColor color;

    protected void setColor(AllianceColor color) {
        this.color = color;
    }

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

        fieldRelativeDriveController.handleInput();


        // Display Telemetry
        telemetry.addData("Runtime:", runtime.toString());
        telemetry.update();
    }
}
