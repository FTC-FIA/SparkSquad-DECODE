package org.firstinspires.ftc.teamcode.task;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Task_StopIntake implements Task {

    private DcMotor intake;
    private Telemetry telemetry;

    public Task_StopIntake(DcMotor intake, Telemetry telemetry) {
        this.intake = intake;
        this.telemetry = telemetry;
    }

    /**
     * runs task for 'duration' seconds
     * @return true if still running, false when time is reached
     */
    public boolean execute() {
        intake.setPower(0.0);
        return false;
    }
}
