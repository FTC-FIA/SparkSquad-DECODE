package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Task_ElapsedTime_Demo;

@Autonomous(name="Auton_TaskRunner_Timer", group="Test")
public class Auton_TaskRunner_Timer extends OpMode {

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        Task_ElapsedTime_Demo timerTask1 = new Task_ElapsedTime_Demo(1.0f);
        Task_ElapsedTime_Demo timerTask2 = new Task_ElapsedTime_Demo(2.5f);
        Task[] theTasks = {timerTask1, timerTask2};
        autonTaskRunner = new AutonTaskRunner(theTasks);
    }

    public void loop() {
        autonTaskRunner.execute();
    }
}
