package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

@TeleOp(name="TeleOp_LimelightTest", group="Test")
public class TeleOp_LimelightTest extends RobotBaseOpMode {

    private Limelight3A limelight;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
    }

    @Override
    public void loop() {
        robotRelativeDriveController.handleInput();

        // get info from limelight
        LLResult result = limelight.getLatestResult();
        Pose3D botPose = result.getBotpose();
        telemetry.addData("LL X", botPose.getPosition().x);
        telemetry.addData("LL Y", botPose.getPosition().y);
        telemetry.addData("LL Z", botPose.getPosition().z);
        telemetry.addData("LL X", botPose.getOrientation().getYaw(AngleUnit.DEGREES));
        telemetry.update();
    }
}
