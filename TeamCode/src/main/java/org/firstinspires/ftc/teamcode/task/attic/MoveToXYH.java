package org.firstinspires.ftc.teamcode.task.attic;

import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.Task;
import org.firstinspires.ftc.teamcode.task.TaskList;

public class MoveToXYH extends TaskList {

    public MoveToXYH(
            RobotBaseOpMode robot,
            double targetXInches,
            double targetYInches,
            double targetHDegrees
    ) {

        super( robot, new Task[] {
                //new TurnTo( robot, 0.0 ),
                new MoveTo( robot, targetXInches, targetYInches),
                new TurnTo( robot, targetHDegrees)
        });

    }

}
