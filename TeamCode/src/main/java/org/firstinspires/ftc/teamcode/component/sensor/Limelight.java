package org.firstinspires.ftc.teamcode.component.sensor;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class Limelight {
    protected Limelight3A limelight;

    public Limelight(Limelight3A limelight) {
        this.limelight = limelight;
    }

//    public Pose2D getRobotPose() {
//        LLResult result = limelight.getLatestResult();
//        Pose3D pose3D = result.getBotpose();
//        Position pos = pose3D.getPosition();
//        YawPitchRollAngles angles = pose3D.getOrientation();
//        double x = pos.x;
//        double y = pos.y;
//        double h = 0.0;
//
//
//    }
}
