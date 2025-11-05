package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Wait;

@Autonomous(name="Auton_TaskRunner_Timer", group="Main")
@Disabled
public class Auton_TaskRunner_Timer extends RobotBaseOpMode {

    private AutonTaskRunner autonTaskRunner;

    public void init() {
        Wait timerTask1 = new Wait(this, 2.0f);
        Wait timerTask2 = new Wait(this, 2.5f);
        Task[] theTasks = {timerTask1, timerTask2};
        autonTaskRunner = new AutonTaskRunner(theTasks, this.telemetry);
    }

    public void loop() {
        autonTaskRunner.execute();
    }
}
