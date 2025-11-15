package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.AssistedShooterController;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.AllianceColor;

@TeleOp(name="AutoVel Blue", group="Prod")
public class TeleOp_Main extends RobotBaseOpMode
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
