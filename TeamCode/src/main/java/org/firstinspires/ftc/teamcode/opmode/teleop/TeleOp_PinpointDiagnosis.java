package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

import java.util.Locale;

@TeleOp(name="TeleOp_PinpointDiagnosis", group="Iterative OpMode")
public class TeleOp_PinpointDiagnosis extends OpMode
{
    final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";
    final String SHOOTER_MOTOR_NAME = "shooter";
    final String INTAKE_MOTOR_NAME = "intake";

    private final ElapsedTime runtime = new ElapsedTime();
    private MecanumDrive mecanumDrive = null;
    private GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer

    private DcMotor shooter = null;
    private DcMotor intake = null;

    private double targetX = 0.0;
    private double targetY = 0.0;
    private double targetH = 0.0;

    double errorToleranceX = 20.0;
    double errorToleranceY = 20.0;
    double errorToleranceH = 20.0;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        // INIT DRIVETRAIN
        DcMotor frontLeft  = hardwareMap.get(DcMotor.class, FRONT_LEFT_DRIVE_MOTOR_NAME);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, FRONT_RIGHT_DRIVE_MOTOR_NAME);
        DcMotor rearLeft  = hardwareMap.get(DcMotor.class, REAR_LEFT_DRIVE_MOTOR_NAME);
        DcMotor rearRight = hardwareMap.get(DcMotor.class, REAR_RIGHT_DRIVE_MOTOR_NAME);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        mecanumDrive = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);

        // INIT PINPOINT
        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-84.1, -117.5, DistanceUnit.MM);

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.REVERSED,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );
        odo.resetPosAndIMU();

        // INIT OTHER MECHANISMS - SHOOTER, INTAKE, LIFT, LIMELIGHT, ETC.
        shooter = hardwareMap.get(DcMotor.class, SHOOTER_MOTOR_NAME);
        shooter.setDirection(DcMotor.Direction.FORWARD);

        intake = hardwareMap.get(DcMotor.class, INTAKE_MOTOR_NAME);
        intake.setDirection(DcMotor.Direction.REVERSE);

        // PRINT TELEMETRY
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        // Read sensors and inputs
        odo.update();
        Pose2D pos = odo.getPosition();
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
                odo.resetPosAndIMU();
            }
            if (gamepad1.b) {
                odo.recalibrateIMU();
            }

            shooter.setPower(gamepad1.right_trigger);
            intake.setPower(gamepad1.left_trigger);

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
                odo.getVelX(DistanceUnit.MM),
                odo.getVelY(DistanceUnit.MM),
                odo.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES)
        );
        telemetry.addData("Velocity", velocity);
        telemetry.addData("Pinppoint Device Status", odo.getDeviceStatus());
        telemetry.addData("Elapsed time (ms)", runtime.milliseconds() );
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
