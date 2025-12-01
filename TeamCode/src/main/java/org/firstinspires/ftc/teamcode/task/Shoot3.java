
package org.firstinspires.ftc.teamcode.task;

import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class Shoot3 extends TaskList {

    public Shoot3(RobotBaseOpMode robot) {
        super(robot, new Task[] {
                // Shoot all but last
                new StartFeeder(robot, 0.5),                 // start the feeder
                new Wait(robot, 3.0),

                // Shoot the last one
                new StartKicker(robot, 0.1),
                new Wait (robot, 5.0),

                // Stop
                new StopKicker(robot),
                new StopFeeder(robot)
        });
    }
}
