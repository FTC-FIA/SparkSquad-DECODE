package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="TeleOp_No_Cap", group="TeleOp Main")
public class TeleOp_No_Cap extends RobotBaseOpMode
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
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double forward = -gamepad1.left_stick_y; // TODO: why is this negative?
        double strafe = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;

        mecanumDrive.drive(forward, strafe, rotate);

        double shooterSpeed = 0.0;
        if (gamepad2.a) {
            shooterSpeed = 0.75;
        }
        shooter.spinUp(shooterSpeed);

        boolean trigger = gamepad2.rightBumperWasPressed();
        if (trigger) {
            shooter.shoot();
        }

        // Display Telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Drive params", "forward (%.2f), strafe (%.2f), rotate (%.2f)", forward, strafe, rotate);
        telemetry.addData("Shooter params", "shooterSpeed (%.2f), trigger (%b)", shooterSpeed, trigger);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
