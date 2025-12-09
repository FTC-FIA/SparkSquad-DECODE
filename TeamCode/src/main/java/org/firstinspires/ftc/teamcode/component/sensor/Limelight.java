package org.firstinspires.ftc.teamcode.component.sensor;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.LastKnown;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.Units;

public class Limelight {
    protected Limelight3A limelight;
    protected LLResult latestResult = null;
    protected Alliance alliance = Alliance.UNDEFINED;
    protected ElapsedTime timer = new ElapsedTime();
    protected double lastResultTimeMS = 0;
    private static final int STALENESS_THRESHOLD = 100; // ms

    public Limelight(Limelight3A limelight, Alliance alliance) {
        this.limelight = limelight;
        this.alliance = alliance;
    }

    private void updateResult() {
        if (timer.milliseconds() - lastResultTimeMS >  STALENESS_THRESHOLD) {
            latestResult = limelight.getLatestResult();
            lastResultTimeMS = timer.milliseconds();
        }
    }

    private boolean prepareAndCheck() {
        if (this.alliance == Alliance.UNDEFINED || this.limelight == null) {
            return false;
        }

        updateResult();

        return (latestResult != null && latestResult.isValid());
    }

    /**
     * Gets the current robot pose X value
     * @return robot pose X in inches, or Double.NaN if not valid result
     */
    public double getRobotX() {
        if (!prepareAndCheck()) {
            return Double.NaN;
        }

        // we need to swap x and y, and apply an inverse for blue alliance
        double botPoseY = latestResult.getBotpose().getPosition().y;
        double botPoseBlueX = Units.meters2Inches(-botPoseY);
        double botPoseRedX = Units.meters2Inches(botPoseY);

        return alliance == Alliance.BLUE ? botPoseBlueX : botPoseRedX;
    }

    /**
     * Gets the current robot pose Y value in the odometry coordinate system
     * @return robot pose Y in inches, or Double.NaN if not valid result
     */
    public double getRobotY() {
        if (!prepareAndCheck()) {
            return Double.NaN;
        }

        // we need to swap x and y, and apply an inverse for blue alliance
        double botPoseX = latestResult.getBotpose().getPosition().x;
        double botPoseBlueY = Units.meters2Inches(-botPoseX);
        double botPoseRedY = Units.meters2Inches(botPoseX);

        return alliance == Alliance.BLUE ? botPoseBlueY : botPoseRedY;
    }

    /**
     * Gets the current robot pose heading (yaw) value in the odometry coordinate system
     * @return robot pose H in degrees, or Double.NaN if not valid result
     */
    public double getRobotH() {
        if (!prepareAndCheck()) {
            return Double.NaN;
        }

        // we need to swap x and y, and apply an inverse for blue alliance
        Pose3D botPose = latestResult.getBotpose();
        double botPoseHeading = botPose.getOrientation().getYaw(AngleUnit.DEGREES);
        double botPoseBlueH = -botPoseHeading - 90.0;
        double botPoseRedH = 90.0 - botPoseHeading;

        return alliance == Alliance.BLUE ? botPoseBlueH : botPoseRedH;
    }

    public double getTx() {
        if (!prepareAndCheck()) {
            return Double.NaN;
        }
        return latestResult.getTx();
    }

    public double getTy() {
        if (!prepareAndCheck()) {
            return Double.NaN;
        }
        return latestResult.getTy();
    }

    public double getTa() {
        if (!prepareAndCheck()) {
            return Double.NaN;
        }
        return latestResult.getTa();
    }

}
