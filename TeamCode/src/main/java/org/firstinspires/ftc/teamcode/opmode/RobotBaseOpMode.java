package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.util.SparkLogger;

public abstract class RobotBaseOpMode extends OpMode
{
    final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";
    final String SHOOTER_MOTOR_NAME = "shooter";
    final String INTAKE_MOTOR_NAME = "intake";
    final String TRIGGER_SERVO_NAME = "trigger";
    final String CONVEYOR_SERVO_NAME = "conveyor";

    final double ODOMETER_X_OFFSET = -82.5;
    final double ODOMETER_Y_OFFSET = 125.0;

    protected final ElapsedTime runtime = new ElapsedTime();

    // raw devices
    protected DcMotor frontLeftMotor = null;
    protected DcMotor frontRightMotor = null;
    protected DcMotor rearLeftMotor = null;
    protected DcMotor rearRightMotor = null;

    protected DcMotor shooterMotor = null;
    protected DcMotor intakeMotor = null;

    protected Servo triggerServo = null;
    protected Servo conveyorServo = null;

    protected GoBildaPinpointDriver odometer = null;

    // components
    protected MecanumDrive mecanumDrive = null;
    protected FieldRelativeDrive fieldRelativeDrive = null;
    protected Shooter shooter = null;

    // util
    protected SparkLogger logger = SparkLogger.getLogger();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        // Initialize devices
        frontLeftMotor  = hardwareMap.get(DcMotor.class, FRONT_LEFT_DRIVE_MOTOR_NAME);
        frontRightMotor = hardwareMap.get(DcMotor.class, FRONT_RIGHT_DRIVE_MOTOR_NAME);
        rearLeftMotor  = hardwareMap.get(DcMotor.class, REAR_LEFT_DRIVE_MOTOR_NAME);
        rearRightMotor = hardwareMap.get(DcMotor.class, REAR_RIGHT_DRIVE_MOTOR_NAME);
        shooterMotor = hardwareMap.get(DcMotor.class, SHOOTER_MOTOR_NAME);
        triggerServo = hardwareMap.get(Servo.class, TRIGGER_SERVO_NAME);
        //conveyorServo = hardwareMap.get(Servo.class, CONVEYOR_SERVO_NAME);
        intakeMotor = hardwareMap.get(DcMotor.class, INTAKE_MOTOR_NAME);
        odometer = hardwareMap.get(com.qualcomm.hardware.gobilda.GoBildaPinpointDriver.class,"odo");

        // Configure devices
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        // TODO: servo config?
        odometer.setOffsets(ODOMETER_X_OFFSET, ODOMETER_Y_OFFSET, DistanceUnit.MM); // TODO: check if signs are correct +/-
        odometer.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometer.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );
        odometer.resetPosAndIMU();
        odometer.recalibrateIMU();

        // Initialize components
        mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor);
        fieldRelativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);
        shooter = new Shooter(shooterMotor, triggerServo);

        // Log status
        telemetry.addData("Status", "Robot Base Initialized");
        telemetry.update();
        logger.log("Robot Base Initialized");
    }
}
