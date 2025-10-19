package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.FieldRelativeDrive;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;


@TeleOp(name="TeleOp_FieldRelativeTest", group="Production")
public class TeleOp_FieldRelativeTest extends RobotBaseOpMode
{

    private FieldRelativeDrive fieldRelativeDrive = null;

    @Override
    public void init() {
        super.init();
        fieldRelativeDrive = new FieldRelativeDrive(mecanumDrive, odometer, telemetry);
    }

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;

        fieldRelativeDrive.drive(forward, strafe, rotate);
    }


}
