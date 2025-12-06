package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class LED {

    private Servo ledServo;

    public LED(Servo ledServo) {
        this.ledServo = ledServo;
    }

    public void setColor(double colorValue) {
        ledServo.setPosition(colorValue);
    }

    public void blink(double color, int numBlinks, int onMs, int offMs) {
        try {
            for (int i = 0; i < numBlinks; i++) {
                this.ledServo.setPosition(color);
                Thread.sleep(onMs);
                this.ledServo.setPosition(Constants.LED_BLACK);
                Thread.sleep(offMs);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception while blinking " + e.getMessage());
        }
    }
}
