package org.firstinspires.ftc.teamcode.task;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.PIDController;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class MoveWithPIDTo implements Task {

    private static final double MIN_DRIVE_POWER = 0.2;
    private static final double MAX_DRIVE_POWER = 0.7;
    private static final double MIN_ROTATE_POWER = 0.2;
    private static final double MAX_ROTATE_POWER = 0.7;

    // error range [-72, 72]; practical range -36, 36
    // if error > 12: max speed
    private final double DRIVE_NORMALIZER = 12.0;
    private final double drive_kP = 1.0 / DRIVE_NORMALIZER;
    private final double drive_kI = 0.0 / DRIVE_NORMALIZER;
    private final double drive_kD = 0.005 / DRIVE_NORMALIZER;

    // error range [-180, 180]
    // if error > 30: max speed
    private final double ROTATE_NORMALIZER = 30.0;
    private final double rotate_kP = 1.0 / ROTATE_NORMALIZER;
    private final double rotate_kI = 0.0 / ROTATE_NORMALIZER;
    private final double rotate_kD = 0.005 / ROTATE_NORMALIZER; // divided by a small # of seconds

    private final double toleranceX = Constants.DEFAULT_AUTON_X_TOLERANCE;
    private final double toleranceY = Constants.DEFAULT_AUTON_Y_TOLERANCE;
    private final double toleranceH = Constants.DEFAULT_AUTON_H_TOLERANCE;

    private final PIDController forwardPID = new PIDController(drive_kP, drive_kI, drive_kD);
    private final PIDController strafePID = new PIDController(drive_kP, drive_kI, drive_kD);
    private final PIDController rotatePID = new PIDController(rotate_kP, rotate_kI, rotate_kD);

    private double speedScale = 1.0;
    private double timeoutInSeconds = 10.0;

    private final Telemetry telemetry;
    private final FtcDashboard dashboard;
    private final FieldRelativeDrive drive;
    private final Odometer odometer;

    private final SparkLogger logger = SparkLogger.getLogger();
    private final ElapsedTime timer = new ElapsedTime();
    private boolean timerWasStarted = false;

    private final Pose2D targetPose;

    public MoveWithPIDTo(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches,
            double targetHDegrees,
            double speedScale,
            double timeoutInSeconds
    ) {
        this.drive = robot.getFieldRelativeDrive();;
        this.odometer = robot.getOdometer();
        this.telemetry = robot.getTelemetry();
        this.dashboard = robot.getDashboard();
        this.speedScale = speedScale;
        this.timeoutInSeconds = timeoutInSeconds;

        this.targetPose = new Pose2D(
                DistanceUnit.INCH,
                targetXInches,
                targetYInches,
                AngleUnit.DEGREES,
                targetHDegrees
        );
    }

    public MoveWithPIDTo(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches,
            double targetHDegrees,
            double speedScale
    ) {
        this(robot, targetXInches, targetYInches, targetHDegrees, speedScale, 10.0);
    }
    public MoveWithPIDTo(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches,
            double targetHDegrees
    ) {
        this(robot, targetXInches, targetYInches, targetHDegrees, 1.0, 10.0);
    }

    /**
     * Keep motor power within min max range
     * @param power the requested power
     * @return the clamped power
     */
    private double clampPower(double power, double minPower, double maxPower) {
        double clampedPower = Math.min(maxPower, Math.abs(power));
        clampedPower = Math.max(minPower, clampedPower);
        clampedPower *= power > 0.0 ? 1.0 : -1.0;
        return clampedPower;
    }

    public boolean execute() {

        if (!timerWasStarted) {
            timer.reset();
            timerWasStarted = true;
        }
        odometer.update();

        double forward;
        double strafe;
        double rotate;

        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);
        double currentH = odometer.getHeading(AngleUnit.DEGREES);

        double errorX = targetPose.getX(DistanceUnit.INCH) - currentX;
        double errorY = targetPose.getY(DistanceUnit.INCH) - currentY;
        double errorH = targetPose.getHeading(AngleUnit.DEGREES) - currentH;

        if (errorH > 180.0) {
            errorH = 360.0 - errorH;
        }
        if (errorH < -180.0) {
            errorH = errorH + 360.0;
        }

        double pidForwardResult = forwardPID.calculate(errorX);
        double pidStrafeResult = strafePID.calculate(errorY);
        double pidRotateResult = rotatePID.calculate(errorH);

        if (Math.abs(errorX) < toleranceX) {
            forward = 0.0;
        } else {
            forward = clampPower(pidForwardResult, MIN_DRIVE_POWER, MAX_DRIVE_POWER);
        }

        if (Math.abs(errorY) < toleranceY) {
            strafe = 0.0;
        } else {
            strafe = clampPower(pidStrafeResult, MIN_DRIVE_POWER, MAX_DRIVE_POWER);
        }

        if (Math.abs(errorH) < toleranceH) {
            rotate = 0.0;
        } else {
            rotate = clampPower(pidRotateResult, MIN_ROTATE_POWER, MAX_ROTATE_POWER);
        }

        forward *= speedScale;
        strafe *= speedScale;
        rotate *= speedScale;

        // Actuate - execute robot functions
        drive.drive(forward, strafe, rotate);

        telemetry.addData("Task",
                String.format(Locale.US,
                        "MoveWithPIDTo %.2f, %.2f",
                        targetPose.getX(DistanceUnit.INCH),
                        targetPose.getY(DistanceUnit.INCH)));
        telemetry.addData("pidFwd", String.format(Locale.US, "%.2f", pidForwardResult));
        telemetry.addData("pidStrafe", String.format(Locale.US, "%.2f", pidStrafeResult));
        telemetry.addData("pidRotate", String.format(Locale.US, "%.2f", pidRotateResult));

        telemetry.addData("fwdPower", String.format(Locale.US, "%.2f", forward));
        telemetry.addData("stfPower", String.format(Locale.US, "%.2f", strafe));
        telemetry.addData("rotPower", String.format(Locale.US, "%.2f", rotate));

        telemetry.addData("errorX", String.format(Locale.US, "%.2f", errorX));
        telemetry.addData("errorY", String.format(Locale.US, "%.2f", errorY));
        telemetry.addData("errorH", String.format(Locale.US, "%.2f", errorH));

        Telemetry dashTelemetry = dashboard.getTelemetry();
        dashTelemetry.addData("errorX", errorX);
        dashTelemetry.addData("pidFwd", pidForwardResult);
        dashTelemetry.addData("fwdPower", forward);

        dashTelemetry.addData("errorY", errorY);
        dashTelemetry.addData("pidStrafe", pidStrafeResult);
        dashTelemetry.addData("stfPower", strafe);

        dashTelemetry.addData("errorH", errorH);
        dashTelemetry.addData("pidRotate", pidRotateResult);
        dashTelemetry.addData("rotPower", rotate);
        dashTelemetry.update();

        logger.log(String.format(Locale.US, "errorX %.2f, errorY %.2f, errorH %.2f", errorX, errorY, errorH));

        boolean timeExpired = timer.seconds() > timeoutInSeconds;
        boolean targetWasReached =
                Math.abs(errorX) < toleranceX
                && Math.abs(errorY) < toleranceY
                && Math.abs(errorH) < toleranceH;

        // keep going if target reached
        return ( !timeExpired && !targetWasReached );
    }
}
