package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartIntake implements Task {

    private final double intakeSpeed;
    private final Intake intake;

    public StartIntake(RobotBaseOpMode robot, double intakeSpeed) {
        this.intake = robot.getIntake();
        this.intakeSpeed = intakeSpeed;
    }

    public StartIntake(RobotBaseOpMode robot) {
        this(robot, Constants.DEFAULT_INTAKE_POWER);
    }

    public boolean execute() {
        intake.setIntakePower(intakeSpeed);
        intake.start();
        return false;
    }
}
