package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.component.sensor.Odometer;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="Limelight Test", group="Test")
public class TeleOp_LimelightTest extends RobotBaseOpMode {

    private Limelight3A limelight;
    private IMU imu;
    private Odometer odometer;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        imu = hardwareMap.get(IMU.class, "imu");
        odometer = getOdometer();
        limelight.pipelineSwitch(0);

        imu.resetYaw();
        odometer.setPosition(new Pose2D(DistanceUnit.INCH, 0.0, 0.0, AngleUnit.DEGREES, 0.0));
    }

    @Override
    public void loop() {
        robotRelativeDriveController.handleInput();

        // get info from limelight
        LLResult result = limelight.getLatestResult();
        Pose3D botPose = result.getBotpose();
        telemetry.addData("BotPose X", botPose.getPosition().x);
        telemetry.addData("BotPose Y", botPose.getPosition().y);
        telemetry.addData("BotPose Z", botPose.getPosition().z);
        telemetry.addData("BotPose Yaw", botPose.getOrientation().getYaw(AngleUnit.DEGREES));
        telemetry.addData("-------", "----------");
        telemetry.addData("Odo X", odometer.getX(DistanceUnit.INCH));
        telemetry.addData("Odo Y", odometer.getY(DistanceUnit.INCH));
        telemetry.addData("Odo H", odometer.getHeading(AngleUnit.DEGREES));
        telemetry.addData("-------", "----------");
        telemetry.addData("IMU YAW", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("-------", "----------");
        telemetry.addData("Tx", result.getTx());
        telemetry.addData("Ty", result.getTy());
        telemetry.addData("Distance", result.getBotposeAvgDist());
        telemetry.addData("Full result", result.toString());

        telemetry.update();
    }
}
