package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="TeleOp_FieldRelativeTest", group="Test")
public class TeleOp_FieldRelativeTest extends RobotBaseOpMode
{
    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;

        fieldRelativeDrive.drive(forward, strafe, rotate);
    }


}
