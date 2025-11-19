package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.component.mechanism.Intake;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class StartIntake implements Task {

    private static final double DEFAULT_INTAKE_SPEED = 1.0;

    private final double intakeSpeed;
    private final Intake intake;

    public StartIntake(RobotBaseOpMode robot, double intakeSpeed) {
        this.intake = robot.getIntake();
        this.intakeSpeed = intakeSpeed;
    }

    public StartIntake(RobotBaseOpMode robot) {
        this(robot, DEFAULT_INTAKE_SPEED);
    }

    public boolean execute() {
        intake.setIntakePower(intakeSpeed);
        intake.start();
        return false;
    }
}
