package org.firstinspires.ftc.teamcode.opmode.teleop.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
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
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.SparkLogger;
import org.firstinspires.ftc.teamcode.util.Units;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class TeleOp_LimelightTest extends RobotBaseOpMode {

    public static final int ALL_TAG_PIPELINE = 0;
    public static final int BLUE_TAG_PIPELINE = 1;
    public static final int RED_TAG_PIPELINE = 2;
    public static final int BASEMENT_BLUE_PIPELINE = 3;

    private static final Logger log = LoggerFactory.getLogger(TeleOp_LimelightTest.class);

    private Limelight3A limelight;
    private Odometer odometer;

    private FtcDashboard ftcDashboard;
    private Telemetry dashTelemetry;
    private SparkLogger logger;

    @Override
    public void init() {
        super.init();
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);

        odometer = getOdometer();

        ftcDashboard = FtcDashboard.getInstance();
        dashTelemetry = ftcDashboard.getTelemetry();
        logger = SparkLogger.getLogger();

        double initHeading = alliance == Alliance.BLUE ? 45.0 : -45.0;
        odometer.setPosition(new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, initHeading));
    }

    public void start() {
        int pipeline = alliance == Alliance.BLUE ? BLUE_TAG_PIPELINE : RED_TAG_PIPELINE;
        limelight.pipelineSwitch(pipeline);
        limelight.start();
    }

    private String formatDouble(double d) {
        return String.format(Locale.US, "%.2f", d);
    }

    @Override
    public void loop() {
        List<String> logMessageParts;

        robotRelativeDriveController.handleInput();
        odometer.update();
        limelight.updateRobotOrientation(odometer.getHeading(AngleUnit.DEGREES) - 90.0);

        // get info from limelight
        LLResult result = limelight.getLatestResult();
        Pose3D botPose = result.getBotpose(); // TODO - maybe try MT2 again

        dashTelemetry.addData("result is valid?", result.isValid());

        double llXInches = Units.meters2Inches(botPose.getPosition().x);
        double llYInches = Units.meters2Inches(botPose.getPosition().y);
        double odoXInches = odometer.getX(DistanceUnit.INCH);
        double odoYInches = odometer.getY(DistanceUnit.INCH);
        double llHeading = botPose.getOrientation().getYaw(AngleUnit.DEGREES);
        double odoHeading = odometer.getHeading(AngleUnit.DEGREES);

        double llCorrectedX = -llYInches + 5.0;
        double llCorrectedY = -llXInches - 6.0;
        double llCorrectedH = -llHeading - 90.0;

        if (alliance == Alliance.RED) {
            llCorrectedH = 90.0 - llHeading;
            llCorrectedX = llYInches;
            llCorrectedY = llXInches;
        }

        dashTelemetry.addData("X LL X", llXInches);
        dashTelemetry.addData("Y LL Y", llYInches);

        dashTelemetry.addData("X XCorr", llCorrectedX);
        dashTelemetry.addData("Y YCorr", llCorrectedY);
        dashTelemetry.addData("H HCorr", llCorrectedH);

        dashTelemetry.addData("X Odo X", odoXInches);
        dashTelemetry.addData("Y Odo Y", odoYInches);

        dashTelemetry.addData("H BotPose Yaw", llHeading);
        dashTelemetry.addData("H Odo H",odoHeading );


//        dashTelemetry.addData("X diff", llXInches - odoXInches);
//        dashTelemetry.addData("Y diff", llYInches - odoYInches);
//        dashTelemetry.addData("H diff", llHeading - odoHeading);
//
//        dashTelemetry.addData("-------", "----------");
//        dashTelemetry.addData("Tx", result.getTx());
//        dashTelemetry.addData("Ty", result.getTy());
//        dashTelemetry.addData("Distance", result.getBotposeAvgDist());



       logMessageParts = Arrays.asList(
                "#",
                "OdoX", formatDouble(odoXInches),
                "OdoY", formatDouble(odoYInches),
                "OdoH", formatDouble(odoHeading));
        logger.log(String.join(",", logMessageParts));

        logMessageParts = Arrays.asList(
                "#",
                "Tx", formatDouble(result.getTx()),
                "Ty", formatDouble(result.getTy()),
                "Ta", formatDouble(result.getTa())
        );
        logger.log(String.join(",", logMessageParts));

        logMessageParts = Arrays.asList(
                "#",
                "BotPoseMTX", formatDouble(llXInches),
                "BotPoseMTY", formatDouble(llYInches),
                "BotPoseMTH", formatDouble(llHeading));
        logger.log(String.join(",", logMessageParts));


        dashTelemetry.update();
    }
}
