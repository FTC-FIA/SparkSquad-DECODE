package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.HelloBot;

@TeleOp
public class DcMotorPractice extends OpMode {

    HelloBot helloBot = new HelloBot();

    @Override
    public void init() {
        helloBot.init(hardwareMap);
    }

    @Override
    public void loop() {
        helloBot.setLeftMotorSpeed(0.5);
    }
}
