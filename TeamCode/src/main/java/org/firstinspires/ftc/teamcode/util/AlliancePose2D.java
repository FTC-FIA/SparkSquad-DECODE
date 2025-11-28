package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class AlliancePose2D {

    protected final Pose2D pose;
    protected final Alliance alliance;

    private static final DistanceUnit DU = DistanceUnit.INCH;
    private static final AngleUnit AU = AngleUnit.DEGREES;

    public AlliancePose2D(Pose2D pose, Alliance alliance) {
        this.pose = pose;
        this.alliance = alliance;
    }

    public AlliancePose2D(double x, double y, double h, Alliance alliance) {
        this(new Pose2D(DU, x, y, AU, h), alliance);
    }

    public Pose2D blue() {
        if (alliance == Alliance.BLUE) {
            return pose;
        } else {
            return new Pose2D(DU, pose.getX(DU), -pose.getY(DU), AU, -pose.getHeading(AU));
        }
    }

    public Pose2D red() {
        if (alliance == Alliance.BLUE) {
            return new Pose2D(DU, pose.getX(DU), -pose.getY(DU), AU, -pose.getHeading(AU));
        } else {
            return pose;
        }
    }

    public Pose2D forAlliance(Alliance color ) {
        return color == Alliance.BLUE ? blue() : red();
    }
}
