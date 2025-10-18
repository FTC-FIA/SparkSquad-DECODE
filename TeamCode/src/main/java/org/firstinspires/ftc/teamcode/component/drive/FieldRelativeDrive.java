package org.firstinspires.ftc.teamcode.component.drive;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class FieldRelativeDrive {

    private MecanumDrive mecanumDrive = null;
    private GoBildaPinpointDriver odometer = null;

    public FieldRelativeDrive(MecanumDrive mecanumDrive, GoBildaPinpointDriver odometer) {
        this.mecanumDrive = mecanumDrive;
        this.odometer = odometer;
    }

    public void drive(double forward, double strafe, double rotate) {
        drive(forward, strafe, rotate, 1.0);
    }

    public void drive(double forward, double strafe, double rotate, double speed) {

        Pose2D currentPose = odometer.getPosition();
        double heading = currentPose.getHeading(AngleUnit.RADIANS);

        // from https://www.ctrlaltftc.com/practical-examples/drivetrain-control
        double forwardAdjusted = forward * Math.cos(heading) - strafe * Math.sin(heading);
        double strafeAdjusted = forward * Math.sin(heading) + strafe * Math.cos(heading);

        mecanumDrive.drive(forwardAdjusted, strafeAdjusted, rotate, speed);
    }
}
