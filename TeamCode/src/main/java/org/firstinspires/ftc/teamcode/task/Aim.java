package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.ShooterUtils;

public class Aim implements Task {
    private MecanumDrive mecanumDrive;
    private Odometer odometer;
    private Shooter shooter;
    private Telemetry telemetry;

    private Alliance alliance;

    private double aimCorrectionBlue = 0.0;
    private double velocityCorrection = 0.0;

    private static final DistanceUnit DU = DistanceUnit.INCH;
    private static final AngleUnit AU = AngleUnit.DEGREES;

    public Aim(RobotBaseOpMode robot, double aimCorrectionBlue, double velocityCorrection) {
        mecanumDrive = robot.getMecanumDrive();
        odometer = robot.getOdometer();
        shooter = robot.getShooter();
        telemetry = robot.getTelemetry();
        alliance = robot.getAlliance();
        this.aimCorrectionBlue = aimCorrectionBlue;
        this.velocityCorrection = velocityCorrection;
    }


    public Aim(RobotBaseOpMode robot) {
        this(robot, 0.0, 0.0);
    }

    public boolean execute() {

        odometer.update();

        Pose2D targetPose = Constants.TARGET.forAlliance(alliance);
        double targetX = targetPose.getX(DU);
        double targetY = targetPose.getY(DU);

        double currentX = odometer.getX(DU);
        double currentY = odometer.getY(DU);
        double currentH = odometer.getHeading(AU);

        double distance = ShooterUtils.calculateDistance(currentX, currentY, targetX, targetY);
        double recVelocity = ShooterUtils.distance2Velocity(distance);
        recVelocity += velocityCorrection;

        double recHeading = ShooterUtils.headingTowards(currentX, currentY, targetX, targetY);
        recHeading += alliance == Alliance.BLUE ? aimCorrectionBlue : -aimCorrectionBlue;

        shooter.setVelocity(recVelocity);

        double velError = recVelocity - shooter.getShooterVelocity();
        double hError = recHeading - currentH;

        double rotateSpeed = 0.0;
        if (hError > Constants.AIM_TOLERANCE) {
            rotateSpeed = Constants.AIMER_ROTATE_SPEED;
        } else if (hError < -Constants.AIM_TOLERANCE) {
            rotateSpeed = -Constants.AIMER_ROTATE_SPEED; //TODO: why negative?
        } else {
            rotateSpeed = 0.0;
        }
        mecanumDrive.drive(0.0, 0.0, rotateSpeed);

        telemetry.addData("Task", "Aim");
        telemetry.addData("recHeading", recHeading);
        telemetry.addData("currHeading", currentH);

        // keep going if either
        boolean keepGoing =
                Math.abs(hError) > Constants.AIM_TOLERANCE ||
                Math.abs(velError) > Constants.SHOOTER_VELOCITY_INCREMENT;
        if (!keepGoing) {
            mecanumDrive.drive(0.0, 0.0, 0.0);
        }
        return keepGoing;
    }
}
