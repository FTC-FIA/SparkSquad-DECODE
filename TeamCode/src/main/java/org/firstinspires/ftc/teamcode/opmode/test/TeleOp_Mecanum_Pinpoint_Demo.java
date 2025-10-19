package org.firstinspires.ftc.teamcode.opmode.test;


import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

import java.util.Locale;

@TeleOp(name="TeleOp_MecanumStarter", group="Iterative OpMode")
@Disabled
public class TeleOp_Mecanum_Pinpoint_Demo extends OpMode
{
    final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";

    private final ElapsedTime runtime = new ElapsedTime();
    private MecanumDrive mecanumDrive = null;
    private GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer

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
        odo.setOffsets(-82.5, 125, DistanceUnit.MM); // TODO: check if signs are correct +/-
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        // INIT OTHER MECHANISMS - SHOOTER, INTAKE, LIFT, LIMELIGHT, ETC.

        // PRINT TELEMETRY

        telemetry.addData("Status", "Initialized");
        System.out.println("TeleOp_Starter: Initializing Logging"); // TODO: where does this go?
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
        runtime.reset(); // TODO: do we care about runtime?
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {

        // Read sensors and inputs
        odo.update();

        if (gamepad1.a) {
            odo.resetPosAndIMU();
        }
        if (gamepad1.b) {
            odo.recalibrateIMU();
        }

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;

        // Actuate - execute robot functions
        mecanumDrive.drive(forward, strafe, rotate);

        // Print telemetry
        Pose2D pos = odo.getPosition();
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

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
