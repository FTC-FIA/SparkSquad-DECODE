package org.firstinspires.ftc.teamcode.opmode.teleop.test;

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
import org.firstinspires.ftc.teamcode.util.Units;

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
        limelight.pipelineSwitch(BLUE_TAG_PIPELINE);
        limelight.start();
    }

    @Override
    public void loop() {
        robotRelativeDriveController.handleInput();
        odometer.update();
        limelight.updateRobotOrientation(odometer.getHeading(AngleUnit.DEGREES));

        // get info from limelight
        LLResult result = limelight.getLatestResult();
        Pose3D botPose = result.getBotpose();
        Pose3D botPoseMT = result.getBotpose_MT2();
        //Pose3D botPoseMT = botPose;
        dashTelemetry.addData("result is valid?", result.isValid());

        double llXInches = Units.meters2Inches(botPoseMT.getPosition().x);
        double llYInches = Units.meters2Inches(botPoseMT.getPosition().y);
        double odoXInches = odometer.getX(DistanceUnit.INCH);
        double odoYInches = odometer.getY(DistanceUnit.INCH);
        double llHeading = botPoseMT.getOrientation().getYaw(AngleUnit.DEGREES);
        double odoHeading = odometer.getHeading(AngleUnit.DEGREES);

        dashTelemetry.addData("BotPose X", llXInches);
        dashTelemetry.addData("BotPose Y", llYInches);

        dashTelemetry.addData("-------", "----------");
        dashTelemetry.addData("Odo X", odoXInches);
        dashTelemetry.addData("Odo Y", odoYInches);

        dashTelemetry.addData("-------", "----------");
        dashTelemetry.addData("BotPose Yaw", llHeading);
        dashTelemetry.addData("Odo H",odoHeading );

        dashTelemetry.addData("-------", "----------");

        dashTelemetry.addData("X diff", llXInches - odoXInches);
        dashTelemetry.addData("Y diff", llYInches - odoYInches);
        dashTelemetry.addData("H diff", llHeading - odoHeading);


        dashTelemetry.addData("-------", "----------");
        dashTelemetry.addData("Tx", result.getTx());
        dashTelemetry.addData("Ty", result.getTy());
        dashTelemetry.addData("Distance", result.getBotposeAvgDist());

        dashTelemetry.update();
    }
}
