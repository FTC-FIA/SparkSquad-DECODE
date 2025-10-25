package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="TeleOp_No_Cap", group="Main")
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
    public double getVelocity() {
        return shooter.getShooterVelocity();
    }
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double forward = -gamepad1.left_stick_y; // TODO: why is this negative?
        double strafe = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;

        mecanumDrive.drive(forward, strafe, rotate);





        double shooterPower = 0.0;

        if ( gamepad2.a || gamepad2.b ) {
            shooterPower = 1.0;
            shooter.spinUp(shooterPower);

            int targetVelocity = 0;
            if ( gamepad2.a ) {
                targetVelocity = 600;
            } else if ( gamepad2.b ) {
                targetVelocity = 750;
            }

            if (getVelocity() > targetVelocity)
            {
                shooter.triggerActivate();
            }
        } else {
            shooter.triggerDeactivate();
            shooter.spinUp(0);
        }





        // Display Telemetry

        telemetry.addData("Trigger Pos", String.valueOf(shooter.getTriggerPosition()) );
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Drive params", "forward (%.2f), strafe (.2f), rotate (%.2f)", forward, strafe, rotate);
//        telemetry.addData("Shooter params", "shooterSpeed (%.2f), trigger (%b)", shooterPower, trigger);
        telemetry.addData("Velocity",String.valueOf(getVelocity()) );
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
