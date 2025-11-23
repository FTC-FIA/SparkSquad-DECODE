package org.firstinspires.ftc.teamcode.component.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.util.SparkLogger;

import java.util.Locale;

public class FieldRelativeDrive extends MecanumDrive {

    private MecanumDrive mecanumDrive = null;
    private Odometer odometer = null;
    private Telemetry telemetry = null;
    private SparkLogger logger = SparkLogger.getLogger();

    public FieldRelativeDrive(
            DcMotor frontLeft,
            DcMotor frontRight,
            DcMotor rearLeft,
            DcMotor rearRight,
            Odometer odometer,
            Telemetry telemetry
    ) {
        super( frontLeft, frontRight, rearLeft, rearRight );
        this.odometer = odometer;
        this.telemetry = telemetry;
    }

    public void drive(double forward, double strafe, double rotate) {
        drive(forward, strafe, rotate, 1.0);
    }

    public void drive(double forward, double strafe, double rotate, double speed) {
        odometer.update();
        double hInRadians = odometer.getHeading(AngleUnit.RADIANS);
        double hInDegrees = odometer.getHeading(AngleUnit.DEGREES);
        double x = odometer.getX(DistanceUnit.INCH);
        double y = odometer.getY(DistanceUnit.INCH);

        // from https://www.ctrlaltftc.com/practical-examples/drivetrain-control
        double forwardAdjusted = forward * Math.cos(hInRadians) - strafe * Math.sin(hInRadians);
        double strafeAdjusted = forward * Math.sin(hInRadians) + strafe * Math.cos(hInRadians);

        super.drive(forwardAdjusted, strafeAdjusted, rotate, speed);

        String pose = "x " + x + " y " + y + " heading " + hInDegrees;
        String commands = "forward " + forward + " strafe " + strafe + " rotate " + rotate;
        String adjCommands = "forwardAdjusted " + forwardAdjusted + " strafeAdjusted " + strafeAdjusted;

        logger.log(pose);
        logger.log(commands);
        logger.log(adjCommands);

        telemetry.addData("X", String.format(Locale.US, "%.1f", x));
        telemetry.addData("Y", String.format(Locale.US, "%.1f", y));
        telemetry.addData("H", String.format(Locale.US, "%.1f", hInDegrees));
    }
}
