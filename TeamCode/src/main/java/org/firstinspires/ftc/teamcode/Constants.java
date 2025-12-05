package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AlliancePose2D;

public class Constants {
    public static double LED_GREEN = 0.5;
    public static double LED_RED = 0.31;

    public static AlliancePose2D TARGET = new AlliancePose2D( 72.0, 72.0, 0.0, Alliance.BLUE);

    public static final double DEFAULT_FEEDER_POWER = 0.4;
    public static final double DEFAULT_INTAKE_POWER = 1.0;
    public static final double DEFAULT_KICKER_FORWARD_POWER = 0.3;
    public static final double DEFAULT_KICKER_REVERSE_POWER = -0.25;

    public static final double SHOOTER_P = 400.0;
    public static final double SHOOTER_I = 10.0;
    public static final double SHOOTER_D = 50.0;
    public static final double SHOOTER_F = 0.0;

    public static final double DEFAULT_AUTON_FORWARD_POWER = 0.5;
    public static final double DEFAULT_AUTON_STRAFE_POWER = 0.7;
    public static final double DEFAULT_AUTON_ROTATE_POWER = 0.3;
    public static final double DEFAULT_AUTON_X_TOLERANCE = 1.0;
    public static final double DEFAULT_AUTON_Y_TOLERANCE = 1.0;
    public static final double DEFAULT_AUTON_H_TOLERANCE = 0.5;

    public static final double DEFAULT_SHOOTER_FORWARD_VELOCITY = 600;
    public static final double DEFAULT_SHOOTER_REVERSE_VELOCITY = -100;
    public static final double MAX_SHOOTER_VELOCITY = 1600;
    public static final double SHOOTER_VELOCITY_INCREMENT = 20.0;

    public static final double CLOSEUP_SHOOTER_VELOCITY = 580.0;
    public static final double LONGSHOT_SHOOTER_VELOCITY = 680.0;

    public static final double AIM_TOLERANCE = 1.0;
    public static final double AIMER_ROTATE_SPEED = 0.5;

    public static final double ODOMETER_X_OFFSET = -0.375; // in inches
    public static final double ODOMETER_Y_OFFSET = 6.375; // in inches

    // Initial positions
    public static AlliancePose2D I1 = new AlliancePose2D(16.125, -63.5, 90.0, Alliance.BLUE) ; // long
    public static AlliancePose2D I3 = new AlliancePose2D(53.5, 51.5, 37.6, Alliance.BLUE); // close, at angle
    //public static AlliancePose2D I3 = new AlliancePose2D(39.0, 63.25, 0.0, Alliance.BLUE); // close, square

    // Shooting positions
    public static AlliancePose2D S1 = new AlliancePose2D(16.125, -60.0, 66.0, Alliance.BLUE); // long
    public static AlliancePose2D S3 = new AlliancePose2D(20.0, 20.0, 40.0, Alliance.BLUE); // close

    // Intake start positions
    public static AlliancePose2D IS1 = new AlliancePose2D(24.0, -38.0, 180.0, Alliance.BLUE); // GGP (farthest from goal)
    public static AlliancePose2D IS2 = new AlliancePose2D(24.0, -8.0, 180.0, Alliance.BLUE);
    public static AlliancePose2D IS3 = new AlliancePose2D(20.0, 14.0, 180.0, Alliance.BLUE); // PPG (closest 2 goal)

    // Intake end positions
    public static AlliancePose2D IE1 = new AlliancePose2D(61.5, -38.0, 180.0, Alliance.BLUE); // GGP (farthest from goal)
    public static AlliancePose2D IE2 = new AlliancePose2D(61.5, -8.0, 180.0, Alliance.BLUE);
    public static AlliancePose2D IE3 = new AlliancePose2D(55.5, 14.0, 180.0, Alliance.BLUE); // PPG (closest 2 goal)

    // Ending positions
    public static AlliancePose2D E1 = new AlliancePose2D(20.0, -36.0,  0.0, Alliance.BLUE); // long
    public static AlliancePose2D E2 = new AlliancePose2D(12.0, -12.0, 0.0, Alliance.BLUE);
    public static AlliancePose2D E3 = new AlliancePose2D(54.0, 30.0, 0.0, Alliance.BLUE); // close
}
