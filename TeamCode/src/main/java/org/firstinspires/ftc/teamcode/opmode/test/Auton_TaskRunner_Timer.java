package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_Wait;

@Autonomous(name="Auton_TaskRunner_Timer", group="Main")
@Disabled
public class Auton_TaskRunner_Timer extends OpMode {

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        Task_Wait timerTask1 = new Task_Wait(2.0f, telemetry);
        Task_Wait timerTask2 = new Task_Wait(2.5f, telemetry);
        Task[] theTasks = {timerTask1, timerTask2};
        autonTaskRunner = new AutonTaskRunner(theTasks);
    }

    public void loop() {
        autonTaskRunner.execute();
    }
}
