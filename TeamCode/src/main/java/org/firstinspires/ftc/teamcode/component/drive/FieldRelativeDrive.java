package org.firstinspires.ftc.teamcode.component.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class FieldRelativeDrive {

    private MecanumDrive mecanumDrive = null;
    private GoBildaPinpointDriver odometer = null;
    private Telemetry telemetry = null;
    private SparkLogger logger = SparkLogger.getLogger();

    public FieldRelativeDrive(
            MecanumDrive mecanumDrive,
            GoBildaPinpointDriver odometer,
            Telemetry telemetry
    ) {
        this.mecanumDrive = mecanumDrive;
        this.odometer = odometer;
        this.telemetry = telemetry;
    }

    public void drive(double forward, double strafe, double rotate) {
        drive(forward, strafe, rotate, 1.0);
    }

    public void drive(double forward, double strafe, double rotate, double speed) {
        odometer.update();
        Pose2D currentPose = odometer.getPosition();
        double heading = currentPose.getHeading(AngleUnit.RADIANS);
        double h = currentPose.getHeading(AngleUnit.DEGREES); // for display
        double x = currentPose.getX(DistanceUnit.INCH);
        double y = currentPose.getY(DistanceUnit.INCH);

        // from https://www.ctrlaltftc.com/practical-examples/drivetrain-control
        double forwardAdjusted = forward * Math.cos(heading) - strafe * Math.sin(heading);
        double strafeAdjusted = forward * Math.sin(heading) + strafe * Math.cos(heading);

        mecanumDrive.drive(forwardAdjusted, strafeAdjusted, rotate, speed);
        String pose = "x " + x + " y " + y + " heading " + heading;
        String commands = "forward " + forward + " strafe " + strafe + " rotate " + rotate;
        String adjCommands = "forwardAdjusted " + forwardAdjusted + " strafeAdjusted " + strafeAdjusted;

        logger.log(pose);
        logger.log(commands);
        logger.log(adjCommands);

        telemetry.addData("X", String.format(Locale.US, "%.1f", x));
        telemetry.addData("Y", String.format(Locale.US, "%.1f", y));
        telemetry.addData("H", String.format(Locale.US, "%.1f", h));
    }
}
