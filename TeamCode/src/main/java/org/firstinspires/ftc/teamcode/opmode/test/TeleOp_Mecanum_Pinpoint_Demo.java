package org.firstinspires.ftc.teamcode.opmode.test;


import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

import java.util.Locale;

@TeleOp(name="TeleOp_MecanumStarter", group="Iterative OpMode")
@Disabled
public class TeleOp_Mecanum_Pinpoint_Demo extends RobotBaseOpMode {

    @Override
    public void loop() {

        // Read sensors and inputs
        odometer.update();

        if (gamepad1.a) {
            odometer.resetPosAndIMU();
        }
        if (gamepad1.b) {
            odometer.recalibrateIMU();
        }

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = -gamepad1.right_stick_x;

        // Actuate - execute robot functions
        mecanumDrive.drive(forward, strafe, rotate);

        // Print telemetry
        Pose2D pos = odometer.getPosition();
        String position = String.format(
                Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}",
                pos.getX(DistanceUnit.MM),
                pos.getY(DistanceUnit.MM),
                pos.getHeading(AngleUnit.DEGREES)
        );
        telemetry.addData("Position", position);

        String velocity = String.format(
                Locale.US, "{XVel: %.3f, YVel: %.3f, HVel: %.3f}",
                odometer.getVelX(DistanceUnit.MM),
                odometer.getVelY(DistanceUnit.MM),
                odometer.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES)
        );
        telemetry.addData("Velocity", velocity);
        telemetry.addData("Pinppoint Device Status", odometer.getDeviceStatus());
    }
}
