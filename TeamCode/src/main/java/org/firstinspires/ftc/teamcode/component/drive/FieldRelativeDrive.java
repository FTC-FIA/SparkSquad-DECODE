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
        //double robotForward = forward * Math.cos(hInRadians) - strafe * Math.sin(hInRadians);
        //double robotStrafe = forward * Math.sin(hInRadians) + strafe * Math.cos(hInRadians);

        /*
        Updated version after realizing the problem (strange Pinpoint coordinate system)
        This is the same as above if you switch robotForward and robotStrafe
        
        vx_robot =  vx_field * cos(θ) + vy_field * sin(θ)
        vy_robot = -vx_field * sin(θ) + vy_field * cos(θ)
         */
        double robotForward = forward * Math.cos(hInRadians) + strafe * Math.sin(hInRadians);
        double robotStrafe = -forward * Math.sin(hInRadians) + strafe * Math.cos(hInRadians);

        super.drive(robotForward, robotStrafe, rotate, speed);

        String pose = "x " + x + " y " + y + " heading " + hInDegrees;
        String commands = "forward " + forward + " strafe " + strafe + " rotate " + rotate;
        String adjCommands = "robotForward " + robotForward + " robotStrafe " + robotStrafe;

        logger.log(pose);
        logger.log(commands);
        logger.log(adjCommands);

        telemetry.addData("Fwd", String.format(Locale.US, "%.1f", forward));
        telemetry.addData("Strafe", String.format(Locale.US, "%.1f", strafe));
        telemetry.addData("Rotate", String.format(Locale.US, "%.1f", rotate));

        telemetry.addData("X", String.format(Locale.US, "%.1f", x));
        telemetry.addData("Y", String.format(Locale.US, "%.1f", y));
        telemetry.addData("H", String.format(Locale.US, "%.1f", hInDegrees));
    }
}
