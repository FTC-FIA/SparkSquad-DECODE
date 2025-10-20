package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import java.util.Locale;
import java.lang.Math;
@Autonomous(name="Auton_MecanumPinpoint", group="Auton Main")
public class Auton_Mecanum_Pinpoint extends OpMode
{
    final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";

    private final ElapsedTime runtime = new ElapsedTime();
    private MecanumDrive mecanumDrive = null;
    private GoBildaPinpointDriver odo;

    double targetX = 200.0;
    double targetY = 200.0;
    double targetA = 0.0;

    double errorToleranceX = 20.0;
    double errorToleranceY = 20.0;
    double errorToleranceA = 20.0;

    double speed = 0.25;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();

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
        odo.setOffsets(-82.5, 125, DistanceUnit.MM); // TODO: check if signs are correct +/-
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
        odo.recalibrateIMU();
        // INIT OTHER MECHANISMS - SHOOTER, INTAKE, LIFT, LIMELIGHT, ETC.

        // PRINT TELEMETRY
        Pose2D pos = odo.getPosition();
        String position = String.format(
                Locale.US, "{CurrX: %.3f, CurrY: %.3f, CurrH: %.3f}",
                pos.getX(DistanceUnit.MM),
                pos.getY(DistanceUnit.MM),
                pos.getHeading(AngleUnit.DEGREES)
        );
        telemetry.addData("Position", position);
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
        odo.resetPosAndIMU();
        odo.recalibrateIMU();
        runtime.reset(); // TODO: do we care about runtime?
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {

        // Read sensors and inputs
        odo.update();

        double forward;
        double strafe;
        double rotate = 0.0;

        Pose2D pos = odo.getPosition();
        double currentX = pos.getX(DistanceUnit.MM);
        double currentY = pos.getY(DistanceUnit.MM);
        double currentA = pos.getHeading(AngleUnit.DEGREES);

        double errorX = currentX - targetX;
        double errorY = currentY - targetY;
        double errorA = currentA - targetA;

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

        if (Math.abs(errorA) < errorToleranceA) {
            rotate = 0.0;
        } else if (errorA > 0) {
            rotate = -speed;
        } else {
            rotate = speed;
        }
        if (Math.abs(errorA) > 180.0) {
            rotate = -rotate;
        }

        // determine angle error and how to rotate

        // Actuate - execute robot functions
        mecanumDrive.drive(forward, strafe, rotate);

        // Print telemetry
        String position = String.format(
            Locale.US, "{CurrX: %.3f, CurrY: %.3f, CurrH: %.3f}",
            pos.getX(DistanceUnit.MM),
            pos.getY(DistanceUnit.MM),
            pos.getHeading(AngleUnit.DEGREES)
        );
        telemetry.addData("Position", position);

        String error = String.format(
            Locale.US, "{XErr: %.3f, YErr: %.3f, HErr: %.3f}",
            errorX, errorY, errorA
        );
        telemetry.addData("Error", error);
        telemetry.addData("Pinppoint Device Status", odo.getDeviceStatus());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
