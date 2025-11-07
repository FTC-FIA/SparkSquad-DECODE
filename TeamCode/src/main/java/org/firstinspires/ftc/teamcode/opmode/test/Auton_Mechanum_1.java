package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskRunner;
import org.firstinspires.ftc.teamcode.task.Drive;
import org.firstinspires.ftc.teamcode.task.StartFeeder;
import org.firstinspires.ftc.teamcode.task.StartIntake;
import org.firstinspires.ftc.teamcode.task.StartKicker;
import org.firstinspires.ftc.teamcode.task.StartShooterWithVelocity;
import org.firstinspires.ftc.teamcode.task.StopFeeder;
import org.firstinspires.ftc.teamcode.task.StopIntake;
import org.firstinspires.ftc.teamcode.task.StopKicker;
import org.firstinspires.ftc.teamcode.task.StopShooter;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.Wait;

@Autonomous(name="Auton_Mechanum_1", group="Test")
public class Auton_Mechanum_1 extends RobotBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    private AutonTaskRunner autonTaskRunner;

    public void init() {
        super.init();
        this.autonTaskRunner = new AutonTaskRunner(
            new Task[] {
                new Drive( this, -1.0, 0.0, 0.0, 0.5 ),       // drive backwards
                new Wait( this, 1.5 ),                        // for 1.5 second
                new Drive( this, 0.0, 0.0, 0.0, 0.0 ),        // then stop
                new StartShooterWithVelocity(this, 650),      // start the shooter
                new StartFeeder(this),                        // start the feeder
                new Wait( this, 2.0 ),                        // wait for 2 seconds

                // jiggle
                new Drive( this, 1.0, 0.0, 0.0, 0.5 ),
                new Wait( this, 0.25 ),
                new Drive( this, -1.0, 0.0, 0.0, 0.5 ),
                new Wait( this, 0.25 ),
                new Drive( this, 0.0, 0.0, 0.0, 0.0 ),

                // start intake and rotate kicker for a while to get rest of balls
                new StartIntake( this ),
                new StartKicker( this, 0.4 ),
                new Wait( this, 5.0 ),

                new StopKicker( this ),
                new StopShooter( this ),
                new StopFeeder( this ),
                new StopIntake( this ),

                // now try to pick up more balls

                // back up
                new Drive( this, -1.0, 0.0, 0.0, 0.5 ),
                new Wait( this, 0.875 ),
                new Drive( this, 0.0, 0.0, 0.0, 0.0 ),

                // turn the robot so feeder faces balls
                new Drive( this, 0.0, 0.0, -1.0, 0.5 ),
                new Wait( this, 1.1 ),
                new Drive( this, 0.0, 0, 0, 0.0 ),  // stop rotation

                // collect more balls
                new StartIntake( this ),
                new Drive( this, -1.0, 0.0, 0.0, 0.5 ),      // drive slowly
                new Wait( this, 2.0 ),

                // drive back to shooting position
                new Drive( this, 1.0, 0.0, 0.0, 0.5 ),
                new Wait( this, 2.0 ),

                // turn the robot so shooter faces basket
                new Drive( this, 0.0, 0.0, 1.0, 0.5 ),
                new Wait( this, 1.1 ),
                new Drive( this, 0.0, 0, 0, 0.0 ),  // stop rotation

                // shoot etc - same as before
                new StartShooterWithVelocity(this, 650),      // start the shooter
                new Wait( this, 1.0 ),                        // wait for 1 second
                new StartFeeder(this),                        // start the feeder
                new Wait( this, 2.0 ),                        // wait for 2 seconds

                // inch forward just a bit for the last ball(s)
                new Drive( this, 1.0, 0.0, 0.0, 0.125 ),
                new Wait( this, 1.0 ),
                new Drive( this, 0.0, 0.0, 0.0, 0.0 ),

                // start intake and rotate kicker for a while to get rest of balls
                new StartIntake( this ),
                new Wait( this, 1.0 ),
                new StartKicker( this, 0.4 ),
                new Wait( this, 3.0 ),

                new StopKicker( this ),
                new StopShooter( this ),
                new StopFeeder( this ),
                new StopIntake( this ),

                // try to park

                // turn 45 degrees
                new Drive( this, 0.0, 0.0, 1.0, 0.25 ),
                new Wait( this, 1.5 ),
                new Drive( this, 0.0, 0, 0, 0.0 ),  // stop rotation

                // back up
                new Drive( this, -1.0, 0.0, 0.0, 0.25 ),
                new Wait( this, 3.0 ),
            },
            this.telemetry
        );
    }

    public void loop() {
        telemetry.addData("Elapsed time (ms)", elapsedTime.milliseconds());
        autonTaskRunner.execute();
        telemetry.update();
    }
}
