package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Kicker;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class KickerController {

    private final Kicker kicker;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;

    public KickerController(RobotBaseOpMode robot) {
        this.kicker = robot.getKicker();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        String message = "No input";
        if (operatorGamepad.right_bumper) {
            kicker.forward();
            message = "Forward";
        } else if (operatorGamepad.left_bumper){
            kicker.reverse();
            message = "Reverse";
        } else {
            kicker.stop();
        }

        telemetry.addData("Kicker power", kicker.getPower());
    }


}
