package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;


@TeleOp(name="Test_TeleOp_MotorTester", group="Iterative OpMode")
@Disabled
public class TeleOp_MotorTester extends RobotBaseOpMode
{

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double frontLeftPower = gamepad1.dpad_up ? 0.5 : 0.0;
        double frontRightPower = gamepad1.dpad_down ? 0.5 : 0.0;
        double rearLeftPower = gamepad1.dpad_left ? 0.5 : 0.0;
        double rearRightPower = gamepad1.dpad_right ? 0.5 : 0.0;

        this.frontLeftMotor.setPower(frontLeftPower);
        this.frontRightMotor.setPower(frontRightPower);
        this.rearLeftMotor.setPower(rearLeftPower);
        this.rearRightMotor.setPower(rearRightPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }


}
