package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class ColorPose2D {

    protected final Pose2D pose;
    protected final AllianceColor poseColor;

    private static final DistanceUnit DU = DistanceUnit.INCH;
    private static final AngleUnit AU = AngleUnit.DEGREES;

    public ColorPose2D( Pose2D pose, AllianceColor poseColor ) {
        this.pose = pose;
        this.poseColor = poseColor;
    }

    public ColorPose2D( double x, double y, double h, AllianceColor poseColor ) {
        this(new Pose2D(DU, x, y, AU, h), poseColor);
    }

    public Pose2D blue() {
        if (poseColor == AllianceColor.BLUE) {
            return pose;
        } else {
            return new Pose2D(DU, pose.getX(DU), -pose.getY(DU), AU, -pose.getHeading(AU));
        }
    }

    public Pose2D red() {
        if (poseColor == AllianceColor.BLUE) {
            return new Pose2D(DU, pose.getX(DU), -pose.getY(DU), AU, -pose.getHeading(AU));
        } else {
            return pose;
        }
    }

    public Pose2D forColor( AllianceColor color ) {
        return color == AllianceColor.BLUE ? blue() : red();
    }
}
