package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.component.drive.TankDrive;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AimAt;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.TaskList;
import org.firstinspires.ftc.teamcode.task.Wait;
import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.ShooterUtils;

public class AssistedShooterController {


    protected RobotBaseOpMode robot;
    protected Gamepad operatorGamepad;
    protected Shooter shooter;
    protected Odometer odometer;
    protected Feeder feeder;
    protected Kicker kicker;
    protected Servo shooterLed;
    protected Servo aimerLed;
    protected MecanumDrive mecanumDrive;
    protected Telemetry telemetry;
    protected Pose2D targetPose;

    protected AllianceColor color;

    private TaskList taskList;

    private boolean taskRunning = false;

    public AssistedShooterController(RobotBaseOpMode robot, AllianceColor color) {
        this.robot = robot;
        this.shooter = robot.getShooter();
        this.odometer = robot.getOdometer();
        this.feeder = robot.getFeeder();
        this.kicker = robot.getKicker();
        this.telemetry = robot.getTelemetry();
        this.shooterLed = robot.getShooterLed();
        this.aimerLed = robot.getAimerLed();
        this.mecanumDrive = robot.getMecanumDrive();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.color = color;
        this.targetPose = Constants.TARGET.forColor(color);
        this.initTaskList();

    }

    private void initTaskList() {
        taskList = new AutonTaskList(
                robot,
                new Task[]{
                    new AimAt(robot, targetPose)
                }
            );
    }

    public void handleInput() {
        // always set shooter velocity based on distance
        odometer.update();
        double currentX = odometer.getX(DistanceUnit.INCH);
        double currentY = odometer.getY(DistanceUnit.INCH);

        Pose2D target = Constants.TARGET.forColor(color);
        double targetX = target.getX(DistanceUnit.INCH);
        double targetY = target.getY(DistanceUnit.INCH);

        double distance = ShooterUtils.calculateDistance(currentX, currentY, targetX, targetY);
        double recommendedVelocity = ShooterUtils.distance2Velocity(distance);
        double targetHeading = ShooterUtils.headingTowards(currentX, currentY, targetX, targetY);

        // display velocity accuracy
        double actualVelocity = shooter.getShooterVelocity();
        if ( Math.abs(actualVelocity - recommendedVelocity) <= Constants.SHOOTER_VELOCITY_INCREMENT ){
            shooterLed.setPosition(Constants.LED_GREEN);
        } else {
            shooterLed.setPosition(Constants.LED_RED);
        }

        // display heading accuracy
        double robotHeading = odometer.getHeading(AngleUnit.DEGREES);
        if (Math.abs(targetHeading - robotHeading) <= Constants.AIM_TOLERANCE) {
            aimerLed.setPosition(Constants.LED_GREEN);
        } else {
            aimerLed.setPosition(Constants.LED_RED);
        }

        // update velocity based on distance
        shooter.setVelocity(recommendedVelocity);

        // run the task if button is held down
        taskRunning = operatorGamepad.b;

        // run the task, but stop when it's done
        if (taskRunning) {
            boolean result = execute();
            taskRunning = result;
        }

        // Reset task list when button is released
        if (operatorGamepad.bWasReleased()) {
            initTaskList();
        }

        telemetry.addData("X", currentX);
        telemetry.addData("Y", currentY);

        telemetry.addData("Robot Heading", robotHeading);
        telemetry.addData("Target heading", targetHeading);
        telemetry.addData("** HEADING ERROR", targetHeading - robotHeading);
        telemetry.addData("Led Position", aimerLed.getPosition());
        telemetry.addData("Distance to Target", distance);

        double currVelocity = shooter.getShooterVelocity();
        telemetry.addData("Target velocity", recommendedVelocity);
        telemetry.addData("Current Velocity", currVelocity);
        telemetry.addData("** VELOCITY ERROR", recommendedVelocity - currVelocity);
    }

    public boolean execute() {
        return taskList.execute();
    }
}
