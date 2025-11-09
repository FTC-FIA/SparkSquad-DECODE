package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.controller.FieldRelativeDriveController;
import org.firstinspires.ftc.teamcode.controller.FeederController;
import org.firstinspires.ftc.teamcode.controller.IntakeController;
import org.firstinspires.ftc.teamcode.controller.KickerController;
import org.firstinspires.ftc.teamcode.controller.RobotRelativeDriveController;
import org.firstinspires.ftc.teamcode.controller.ShooterController;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

public abstract class RobotBaseOpMode extends OpMode
{
    final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";
    final String SHOOTER_MOTOR_NAME = "shooter";
    final String INTAKE_MOTOR_NAME = "intake";
    final String KICKER_SERVO_NAME = "trigger"; // TODO: CHANGE TO KICKER
    final String FEEDER_SERVO_NAME = "conveyor"; // TODO: CHANGE TO FEEDER

    final double ODOMETER_X_OFFSET = -82.5; // TODO: CHECK THAT THESE ARE CORRED
    final double ODOMETER_Y_OFFSET = 125.0;

    protected final ElapsedTime runtime = new ElapsedTime();

    // raw devices
    protected DcMotor frontLeftMotor = null;
    protected DcMotor frontRightMotor = null;
    protected DcMotor rearLeftMotor = null;
    protected DcMotor rearRightMotor = null;

    protected DcMotorEx shooterMotor = null;
    protected DcMotor intakeMotor = null;

    protected CRServo kickerCRServo = null;
    protected CRServo feederCRServo = null;

    protected GoBildaPinpointDriver pinpointDriver = null;

    // components
    protected MecanumDrive mecanumDrive = null;
    protected FieldRelativeDrive fieldRelativeDrive = null;
    protected Shooter shooter = null;
    protected Intake intake = null;
    protected Kicker kicker = null;
    protected Feeder feeder = null;
    protected Odometer odometer = null;

    // controllers
    protected ShooterController shooterController = null;
    protected KickerController kickerController = null;
    protected FeederController feederController = null;
    protected IntakeController intakeController = null;
    protected FieldRelativeDriveController fieldRelativeDriveController = null;
    protected RobotRelativeDriveController robotRelativeDriveController = null;

    // util
    protected SparkLogger logger = SparkLogger.getLogger();
    protected FtcDashboard dashboard = FtcDashboard.getInstance();

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
        shooterMotor = hardwareMap.get(DcMotorEx.class, SHOOTER_MOTOR_NAME);
        kickerCRServo = hardwareMap.get(CRServo.class, KICKER_SERVO_NAME);
        feederCRServo = hardwareMap.get(CRServo.class, FEEDER_SERVO_NAME);
        intakeMotor = hardwareMap.get(DcMotor.class, INTAKE_MOTOR_NAME);
        pinpointDriver = hardwareMap.get(com.qualcomm.hardware.gobilda.GoBildaPinpointDriver.class,"odo");

        // Configure devices
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        kickerCRServo.setDirection(DcMotorSimple.Direction.REVERSE);
        feederCRServo.setDirection(DcMotorSimple.Direction.REVERSE);

        pinpointDriver.setOffsets(ODOMETER_X_OFFSET, ODOMETER_Y_OFFSET, DistanceUnit.MM); // TODO: check if signs are correct +/-
        pinpointDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpointDriver.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.REVERSED, // X
                GoBildaPinpointDriver.EncoderDirection.REVERSED // Y
        );

        // Initialize components
        mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor);
        fieldRelativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);
        shooter = new Shooter(shooterMotor);
        intake = new Intake(intakeMotor);
        kicker = new Kicker(kickerCRServo);
        feeder = new Feeder(feederCRServo);
        odometer = new Odometer(pinpointDriver);

        odometer.reset();

        // Initialize controllers
        shooterController = new ShooterController(this);
        kickerController = new KickerController(this);
        feederController = new FeederController(this);
        intakeController = new IntakeController(this);
        fieldRelativeDriveController = new FieldRelativeDriveController(this);
        robotRelativeDriveController = new RobotRelativeDriveController(this);

        // Log status
        telemetry.addData("Status", "Robot Base Initialized");
        telemetry.update();
        logger.log("Robot Base Initialized");
    }

    public DcMotor getFrontLeftMotor() {
        return frontLeftMotor;
    }

    public DcMotor getFrontRightMotor() {
        return frontRightMotor;
    }

    public DcMotor getRearLeftMotor() {
        return rearLeftMotor;
    }

    public DcMotor getRearRightMotor() {
        return rearRightMotor;
    }

    public DcMotor getShooterMotor() {
        return shooterMotor;
    }

    public DcMotor getIntakeMotor() {
        return intakeMotor;
    }

    public CRServo getKickerServo() {
        return kickerCRServo;
    }

    public CRServo getFeederServo() {
        return feederCRServo;
    }

    public GoBildaPinpointDriver getPinpointDriver() {
        return pinpointDriver;
    }

    public Odometer getOdometer() {
        return odometer;
    }

    public MecanumDrive getMecanumDrive() {
        return mecanumDrive;
    }

    public FieldRelativeDrive getFieldRelativeDrive() {
        return fieldRelativeDrive;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Intake getIntake() {
        return intake;
    }

    public Kicker getKicker() {
        return kicker;
    }

    public Feeder getFeeder() {
        return feeder;
    }

    public Gamepad getDriverGamepad() { return gamepad1; }

    public Gamepad getOperatorGamepad() { return gamepad2; }

    public FieldRelativeDriveController getFieldRelativeDriveController() { return fieldRelativeDriveController; }

    public RobotRelativeDriveController getRobotRelativeDriveController() {
        return robotRelativeDriveController;
    }

    public ShooterController getShooterController() { return shooterController; }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public FtcDashboard getDashboard() {
        return dashboard;
    }
}
