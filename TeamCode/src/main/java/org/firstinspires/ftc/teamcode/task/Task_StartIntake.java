package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_StartIntake implements Task {

    private final double INTAKE_SPEED = 1.0;
    private double intakeSpeed = INTAKE_SPEED;
    private DcMotor intake;
    private Telemetry telemetry;

    public Task_StartIntake(DcMotor intake, Telemetry telemetry) {
        this.intake = intake;
        this.telemetry = telemetry;
    }

    /**
     * runs task for 'duration' seconds
     * @return true if still running, false when time is reached
     */
    public boolean execute() {
        intake.setPower(intakeSpeed);
        return false;
    }
}
