package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.mechanism.Feeder;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class FeederController {

    private double forwardSpeed = Constants.DEFAULT_FEEDER_POWER;
    private double reverseSpeed = -forwardSpeed;

    private final Feeder feeder;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;

    public FeederController(RobotBaseOpMode robot) {
        this.feeder = robot.getFeeder();
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
        double fPower = 0.0;
        if (operatorGamepad.dpad_up) {
            fPower = forwardSpeed;
        } else if (operatorGamepad.dpad_down){
            fPower = reverseSpeed;
        }
        feeder.setPower(fPower);
    }
}
