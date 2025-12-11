package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.component.mechanism.LED;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.sensor.Limelight;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.controller.AssistedShooterController;
import org.firstinspires.ftc.teamcode.controller.FieldRelativeDriveController;
import org.firstinspires.ftc.teamcode.controller.FeederController;
import org.firstinspires.ftc.teamcode.controller.IntakeController;
import org.firstinspires.ftc.teamcode.controller.KickerController;
import org.firstinspires.ftc.teamcode.controller.LimelightOdometerController;
import org.firstinspires.ftc.teamcode.controller.RobotRelativeDriveController;
import org.firstinspires.ftc.teamcode.controller.ShooterController;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

public abstract class RobotBaseOpMode extends OpMode
{
    protected Alliance alliance = Alliance.BLUE; // default

    protected final ElapsedTime runtime = new ElapsedTime();

    // raw devices
    protected DcMotor frontLeftMotor = null;
    protected DcMotor frontRightMotor = null;
    protected DcMotor rearLeftMotor = null;
    protected DcMotor rearRightMotor = null;

    protected DcMotorEx shooterMotor = null;
    protected DcMotor intakeMotor = null;

    protected CRServo kickerCRServo = null;
    protected DcMotorEx feederMotor = null;

    protected GoBildaPinpointDriver pinpointDriver = null;
    protected Limelight3A limelight3A = null;

    protected Servo shooterLedServo = null;
    protected Servo aimerLedServo = null;

    // components
    protected MecanumDrive mecanumDrive = null;
    protected FieldRelativeDrive fieldRelativeDrive = null;
    protected Shooter shooter = null;
    protected Intake intake = null;
    protected Kicker kicker = null;
    protected Feeder feeder = null;
    protected Odometer odometer = null;
    protected Limelight limelight = null;
    protected LED shooterLed = null;
    protected LED aimerLed = null;

    // controllers
    protected ShooterController shooterController = null;
    protected AssistedShooterController assistedShooterController = null;
    protected KickerController kickerController = null;
    protected FeederController feederController = null;
    protected IntakeController intakeController = null;
    protected FieldRelativeDriveController fieldRelativeDriveController = null;
    protected RobotRelativeDriveController robotRelativeDriveController = null;
    protected LimelightOdometerController limelightOdometerController = null;

    // util
    protected SparkLogger logger = SparkLogger.getLogger();
    protected FtcDashboard dashboard = FtcDashboard.getInstance();

    @Override
    public void init() {

        // Initialize devices
        frontLeftMotor  = hardwareMap.get(DcMotor.class, Constants.FRONT_LEFT_DRIVE_MOTOR_NAME);
        frontRightMotor = hardwareMap.get(DcMotor.class, Constants.FRONT_RIGHT_DRIVE_MOTOR_NAME);
        rearLeftMotor  = hardwareMap.get(DcMotor.class, Constants.REAR_LEFT_DRIVE_MOTOR_NAME);
        rearRightMotor = hardwareMap.get(DcMotor.class, Constants.REAR_RIGHT_DRIVE_MOTOR_NAME);
        shooterMotor = hardwareMap.get(DcMotorEx.class, Constants.SHOOTER_MOTOR_NAME);
        kickerCRServo = hardwareMap.get(CRServo.class, Constants.KICKER_SERVO_NAME);
        feederMotor = hardwareMap.get(DcMotorEx.class, Constants.FEEDER_MOTOR_NAME);
        intakeMotor = hardwareMap.get(DcMotor.class, Constants.INTAKE_MOTOR_NAME);
        pinpointDriver = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        shooterLedServo = hardwareMap.get(Servo.class, Constants.SHOOTER_LED_NAME);
        aimerLedServo = hardwareMap.get(Servo.class, Constants.AIMER_LED_NAME);

        // Configure devices
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        kickerCRServo.setDirection(DcMotorSimple.Direction.FORWARD);
        feederMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        pinpointDriver.setOffsets(
                Constants.ODOMETER_X_OFFSET,
                Constants.ODOMETER_Y_OFFSET,
                DistanceUnit.INCH
        );
        pinpointDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpointDriver.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.REVERSED, // X
                GoBildaPinpointDriver.EncoderDirection.REVERSED  // Y
        );

        // Initialize components
        odometer = new Odometer(pinpointDriver);
        limelight = new Limelight(limelight3A, alliance, telemetry); // TODO: remove or reconsider pattern
        mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, rearLeftMotor, rearRightMotor);
        fieldRelativeDrive = new FieldRelativeDrive(
                frontLeftMotor,
                frontRightMotor,
                rearLeftMotor,
                rearRightMotor,
                odometer,
                telemetry);
        shooter = new Shooter(shooterMotor);
        intake = new Intake(intakeMotor);
        kicker = new Kicker(kickerCRServo);
        feeder = new Feeder(feederMotor);
        shooterLed = new LED(shooterLedServo);
        aimerLed = new LED(aimerLedServo);

        // Initialize controllers
        shooterController = new ShooterController(this);
        kickerController = new KickerController(this);
        feederController = new FeederController(this);
        intakeController = new IntakeController(this);
        fieldRelativeDriveController = new FieldRelativeDriveController(this);
        robotRelativeDriveController = new RobotRelativeDriveController(this);
        limelightOdometerController = new LimelightOdometerController(this);


        // Log status
        telemetry.addData("Status", "Robot Base Initialized");
        telemetry.update();
        logger.log("Robot Base Initialized");
    }

    public void setAlliance(Alliance a) {
        this.alliance = a;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setColor(Alliance a) {
        this.alliance = a;
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

    public DcMotorEx getShooterMotor() {
        return shooterMotor;
    }

    public DcMotor getIntakeMotor() {
        return intakeMotor;
    }

    public CRServo getKickerServo() {
        return kickerCRServo;
    }

    public DcMotorEx getFeederMotor() {
        return feederMotor;
    }

    public GoBildaPinpointDriver getPinpointDriver() {
        return pinpointDriver;
    }

    public Limelight3A getLimelight3A() { return limelight3A; }

    public Limelight getLimelight() { return limelight; }

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

    public Servo getShooterLedServo(){ return shooterLedServo; }

    public Servo getAimerLedServo(){ return aimerLedServo; }

    public LED getShooterLed() { return shooterLed; }

    public LED getAimerLed() { return aimerLed; }

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

    public FieldRelativeDriveController getFieldRelativeDriveController() {
        return fieldRelativeDriveController;
    }

    public RobotRelativeDriveController getRobotRelativeDriveController() {
        return robotRelativeDriveController;
    }

    public ShooterController getShooterController() { return shooterController; }

    public LimelightOdometerController getLimelightOdometerController() {
        return limelightOdometerController;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public FtcDashboard getDashboard() {
        return dashboard;
    }
}
