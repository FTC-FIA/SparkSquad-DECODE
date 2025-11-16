package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class KickerController {

    private final double DEFAULT_FORWARD_SPEED = 0.2;
    private final double DEFAULT_REVERSE_SPEED = -0.25;

    private double forwardSpeed = DEFAULT_FORWARD_SPEED;
    private double reverseSpeed = DEFAULT_REVERSE_SPEED;

    private final Kicker kicker;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;

    public KickerController(RobotBaseOpMode robot) {
        this.kicker = robot.getKicker();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.telemetry = robot.getTelemetry();
    }

    public void setForwardSpeed(double fSpeed) {
        forwardSpeed = Math.min(Math.abs(fSpeed), 1.0);
    }

    public double getForwardSpeed() {
        return forwardSpeed;
    }

    public void setReverseSpeed(double rSpeed) {
        reverseSpeed = Math.max(Math.abs(rSpeed) * -1.0, -1.0);
    }

    public double getReverseSpeed() {
        return reverseSpeed;
    }

    public void handleInput() {
        double kPower = 0.0;
        if (operatorGamepad.right_bumper) {
            kPower = forwardSpeed;
        } else if (operatorGamepad.left_bumper){
            kPower = reverseSpeed;
        }
        kicker.setPower(kPower);

        telemetry.addData("Kicker power", kPower);
    }


}
