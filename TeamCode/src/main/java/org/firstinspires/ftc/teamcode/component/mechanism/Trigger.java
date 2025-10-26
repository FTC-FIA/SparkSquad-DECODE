package org.firstinspires.ftc.teamcode.component.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import javax.crypto.spec.DESedeKeySpec;

public class Trigger {

    private static final double DEFAULT_START_POSITION = 1.0;
    private static final double DEFAULT_FIRING_POSITION = 0.4;

    private final double startPosition;
    private final double firingPosition;

    private final Servo trigger;

    public Trigger(Servo trigger, double startPos, double firingPos) {
        this.trigger = trigger;
        this.startPosition = startPos;
        this.firingPosition = firingPos;
        this.trigger.setPosition(startPosition);
    }

    public Trigger(Servo trigger) {
        this(trigger, DEFAULT_START_POSITION, DEFAULT_FIRING_POSITION);
    }

    public void fire() {
        trigger.setPosition(firingPosition);
        trigger.setPosition(firingPosition);
    }
}
