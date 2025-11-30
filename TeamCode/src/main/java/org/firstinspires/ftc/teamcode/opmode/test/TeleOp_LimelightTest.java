package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.MagneticFlux;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

@TeleOp(name="Limelight Test", group="Test")
public class TeleOp_LimelightTest extends RobotBaseOpMode {

    public static final int ALL_TAG_PIPELINE = 0;
    public static final int BLUE_TAG_PIPELINE = 1;
    public static final int RED_TAG_PIPELINE = 2;

    private Limelight3A limelight;
    private Odometer odometer;

    private FtcDashboard ftcDashboard;
    private Telemetry dashTelemetry;
    private SparkLogger logger;

    @Override
    public void init() {
        super.init();
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        odometer = getOdometer();

        ftcDashboard = FtcDashboard.getInstance();
        dashTelemetry = ftcDashboard.getTelemetry();
        logger = SparkLogger.getLogger();

        odometer.setPosition(new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, 0.0));
    }

    public void start() {
        limelight.start();
        limelight.pipelineSwitch(BLUE_TAG_PIPELINE);
    }

    @Override
    public void loop() {
        robotRelativeDriveController.handleInput();

        limelight.updateRobotOrientation(odometer.getHeading(AngleUnit.RADIANS));

        // get info from limelight
        LLResult result = limelight.getLatestResult();
        //Pose3D botPose = result.getBotpose();
        Pose3D botPoseMT = result.getBotpose_MT2();
        dashTelemetry.addData("result is valid?", result.isValid());
        dashTelemetry.addData("BotPose X", botPoseMT.getPosition().x);
        dashTelemetry.addData("BotPose Y", botPoseMT.getPosition().y);
        dashTelemetry.addData("BotPose Z", botPoseMT.getPosition().z);
        dashTelemetry.addData("BotPose Yaw", botPoseMT.getOrientation().getYaw(AngleUnit.DEGREES));
        dashTelemetry.addData("-------", "----------");
        dashTelemetry.addData("Odo X", odometer.getX(DistanceUnit.INCH));
        dashTelemetry.addData("Odo Y", odometer.getY(DistanceUnit.INCH));
        dashTelemetry.addData("Odo H", odometer.getHeading(AngleUnit.DEGREES));
        dashTelemetry.addData("-------", "----------");
        //telemetry.addData("IMU YAW", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        dashTelemetry.addData("-------", "----------");
        dashTelemetry.addData("Tx", result.getTx());
        dashTelemetry.addData("Ty", result.getTy());
        dashTelemetry.addData("Distance", result.getBotposeAvgDist());
        dashTelemetry.addData("Full result", result.toString());

        dashTelemetry.update();
    }
}
