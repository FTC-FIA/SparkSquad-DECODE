package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Trigger;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class PullTrigger implements Task {

    private Trigger trigger;

    public PullTrigger(RobotBaseOpMode robot) {
        this.trigger = robot.getTrigger();
    }

    public boolean execute() {
        trigger.fire();
        return false; // done
    }
}
