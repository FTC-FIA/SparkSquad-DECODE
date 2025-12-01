package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AlliancePose2D;

public class Constants {
    public static double LED_GREEN = 0.5;
    public static double LED_RED = 0.31;

    public static AlliancePose2D TARGET = new AlliancePose2D( 72.0, 72.0, 0.0, Alliance.BLUE);

    public static final double DEFAULT_FEEDER_POWER = 0.4;
    public static final double DEFAULT_INTAKE_POWER = 0.6;
    public static final double DEFAULT_KICKER_FORWARD_POWER = 0.3;
    public static final double DEFAULT_KICKER_REVERSE_POWER = -0.25;

    public static final double SHOOTER_P = 400.0;
    public static final double SHOOTER_I = 10.0;
    public static final double SHOOTER_D = 50.0;
    public static final double SHOOTER_F = 0.0;

    public static final double DEFAULT_AUTON_FORWARD_POWER = 0.5;
    public static final double DEFAULT_AUTON_STRAFE_POWER = 0.8;
    public static final double DEFAULT_AUTON_ROTATE_POWER = 0.3;
    public static final double DEFAULT_AUTON_X_TOLERANCE = 1.0;
    public static final double DEFAULT_AUTON_Y_TOLERANCE = 1.0;
    public static final double DEFAULT_AUTON_H_TOLERANCE = 1.0;

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
    public static AlliancePose2D I1 = new AlliancePose2D(20.0, -64.0, 0.0, Alliance.BLUE) ; // long
    //public static AlliancePose2D I1 = new AlliancePose2D(18.0, -64.0, 0.0, Alliance.BLUE) ; // long
    public static AlliancePose2D I2 = new AlliancePose2D(60.0, 40.0, 0.0, Alliance.BLUE);
    public static AlliancePose2D I3 = new AlliancePose2D(54.0, 54.0, 45.0, Alliance.BLUE); // close, at angle
    //public static AlliancePose2D I3 = new AlliancePose2D(40.0, 60.0, 0.0, Alliance.BLUE); // close

    // Shooting positions
    public static AlliancePose2D S1 = new AlliancePose2D(16.0, -60.0, 65.0, Alliance.BLUE); // long
    public static AlliancePose2D S2 = new AlliancePose2D(0.0, 0.0, 45.0, Alliance.BLUE);
    public static AlliancePose2D S3 = new AlliancePose2D(20.0, 20.0, 40.0, Alliance.BLUE); // close
    public static AlliancePose2D S4 = new AlliancePose2D(18.0, -64.0, 0.0, Alliance.BLUE);

    // Intake start positions
    public static AlliancePose2D IS1 = new AlliancePose2D(24.0, -36.0, 180.0, Alliance.BLUE);
    public static AlliancePose2D IS2 = new AlliancePose2D(24.0, -12.0, 180.0, Alliance.BLUE);
    public static AlliancePose2D IS3 = new AlliancePose2D(20.0, 8.0, 180.0, Alliance.BLUE); // PPG (closest 2 goal)

    // Intake end positions
    public static AlliancePose2D IE1 = new AlliancePose2D(48.0, -36.0, 180.0, Alliance.BLUE);
    public static AlliancePose2D IE2 = new AlliancePose2D(48.0, -12.0, 180.0, Alliance.BLUE);
    public static AlliancePose2D IE3 = new AlliancePose2D(48.0, 12.0, 180.0, Alliance.BLUE); // PPG (closest 2 goal)

    // Ending positions
    public static AlliancePose2D E1 = new AlliancePose2D(20.0, -36.0,  0.0, Alliance.BLUE); // long
    public static AlliancePose2D E2 = new AlliancePose2D(12.0, -12.0, 0.0, Alliance.BLUE);
    public static AlliancePose2D E3 = new AlliancePose2D(60.0, 30.0, 0.0, Alliance.BLUE); // close
}
