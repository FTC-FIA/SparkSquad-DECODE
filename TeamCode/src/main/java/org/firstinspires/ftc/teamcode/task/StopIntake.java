package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StopIntake implements Task {

    private final Intake intake;

    public StopIntake(RobotBaseOpMode robot) {
        this.intake = robot.getIntake();
    }

    public boolean execute() {
        intake.stop();
        return false; // that's it!
    }
}
