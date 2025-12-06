package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

import java.util.Locale;

@TeleOp(name="TeleOp_Pinpoint_Diagnosis", group="Test")
@Disabled
public class TeleOp_Pinpoint_Diagnosis extends RobotBaseOpMode
{
    private double targetX = 0.0;
    private double targetY = 0.0;
    private double targetH = 0.0;

    double errorToleranceX = 20.0;
    double errorToleranceY = 20.0;
    double errorToleranceH = 20.0;

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        // Read sensors and inputs
        pinpointDriver.update();
        Pose2D pos = pinpointDriver.getPosition();
        double currentX = pos.getX(DistanceUnit.MM);
        double currentY = pos.getY(DistanceUnit.MM);
        double currentH = pos.getHeading(AngleUnit.DEGREES);

        if (gamepad1.left_bumper) {
            // save current pos
            targetX = currentX;
            targetY = currentY;
            targetH = currentH;

            String savedTargetPosition = String.format(
                    Locale.US,
                    "{X: %.3f, Y: %.3f, H: %.3f}",
                    targetX,
                    targetY,
                    targetH
            );
            telemetry.addData("Saved Target Position: ", savedTargetPosition );
        } else if (gamepad1.right_bumper) {
            double errorX = currentX - targetX;
            double errorY = currentY - targetY;
            double errorH = currentH - targetH;

            double forward = 0.0;
            double strafe = 0.0;
            double rotate = 0.0;
            double speed = 0.5;
            double rotateSpeed = 0.3;

            rotate = 0.0;
            if (Math.abs(errorH) > errorToleranceH) {
                if ( currentH < targetH ) {
                    rotate = -rotateSpeed;
                } else if ( currentH > targetH ) {
                    rotate = rotateSpeed;
                }
            } else {
                if (Math.abs(errorX) < errorToleranceX) {
                    forward = 0.0;
                } else if (errorX > 0) {
                    forward = -speed;
                } else {
                    forward = speed;
                }

                if (Math.abs(errorY) < errorToleranceY) {
                    strafe = 0.0;
                } else if (errorY > 0) {
                    strafe = -speed;
                } else {
                    strafe = speed;
                }
            }

            mecanumDrive.drive(forward, strafe, rotate);

            String headingToTarget = String.format(
                    Locale.US,
                    "{X: %.3f, Y: %.3f, H: %.3f}",
                    targetX,
                    targetY,
                    targetH
            );
            telemetry.addData("Heading to Target: ", headingToTarget );
        } else {
            if (gamepad1.a) {
                pinpointDriver.resetPosAndIMU();
            }
            if (gamepad1.b) {
                pinpointDriver.recalibrateIMU();
            }

            shooterMotor.setPower(gamepad1.right_trigger);
            intakeMotor.setPower(gamepad1.left_trigger);

            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;
            double speed = 0.5;

            // Actuate - execute robot functions
            mecanumDrive.drive(forward, strafe, rotate, speed);

            // Print telemetry
            String controls = String.format(
                    Locale.US,
                    "{forward: %.3f, strafe: %.3f, rotate: %.3f}",
                    forward,
                    strafe,
                    rotate
            );
            telemetry.addData("Controls", controls);
        }

        String position = String.format(
                Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}",
                pos.getX(DistanceUnit.MM),
                pos.getY(DistanceUnit.MM),
                pos.getHeading(AngleUnit.DEGREES)
        );
        telemetry.addData("Position", position);

        String velocity = String.format(
                Locale.US, "{XVel: %.3f, YVel: %.3f, HVel: %.3f}",
                pinpointDriver.getVelX(DistanceUnit.MM),
                pinpointDriver.getVelY(DistanceUnit.MM),
                pinpointDriver.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES)
        );
        telemetry.addData("Velocity", velocity);
        telemetry.addData("Pinppoint Device Status", pinpointDriver.getDeviceStatus());
        telemetry.addData("Elapsed time (ms)", runtime.milliseconds() );
        telemetry.update();
    }
}
