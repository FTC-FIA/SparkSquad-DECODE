package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.AlliancePose2D;

public class Constants {

    public static final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    public static final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    public static final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    public static final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";
    public static final String SHOOTER_MOTOR_NAME = "shooter";
    public static final String INTAKE_MOTOR_NAME = "intake";
    public static final String KICKER_SERVO_NAME = "trigger"; // TODO: CHANGE TO KICKER
    public static final String FEEDER_MOTOR_NAME = "feeder";
    public static final String SHOOTER_LED_NAME = "shooter_led";
    public static final String AIMER_LED_NAME = "aimer_led";

    public static double LED_BLACK = 0.0;
    public static double LED_RED = 0.31;
    public static double LED_GREEN = 0.5;
    public static double LED_BLUE = 0.6; // no idea if this is right

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
    public static final double DEFAULT_AUTON_X_TOLERANCE = 1.5;
    public static final double DEFAULT_AUTON_Y_TOLERANCE = 1.5;
    public static final double DEFAULT_AUTON_H_TOLERANCE = 0.5;

    public static final double DEFAULT_SHOOTER_FORWARD_VELOCITY = 600;
    public static final double DEFAULT_SHOOTER_REVERSE_VELOCITY = -100;
    public static final double MAX_SHOOTER_VELOCITY = 1600;
    public static final double SHOOTER_VELOCITY_INCREMENT = 20.0;

    public static final double CLOSEUP_SHOOTER_VELOCITY = 570.0;
    public static final double LONGSHOT_SHOOTER_VELOCITY = 680.0;

    public static final double AIM_TOLERANCE = 1.0;
    public static final double AIMER_ROTATE_SPEED = 0.5;

    public static final double ODOMETER_X_OFFSET = -0.375; // in inches
    public static final double ODOMETER_Y_OFFSET = 6.375; // in inches

    // Initial positions
    public static AlliancePose2D LONG_START_BLUE = new AlliancePose2D(16.125, -63.5, 90.0, Alliance.BLUE);
    public static AlliancePose2D LONG_START_RED = new AlliancePose2D(16.125, 63.5, -90.0, Alliance.RED);

    public static AlliancePose2D CLOSE_START_BLUE = new AlliancePose2D(53.5, 51.5, 37.6, Alliance.BLUE);
    public static AlliancePose2D CLOSE_START_RED = new AlliancePose2D(53.5, -51.5, -37.6, Alliance.RED);

    // 6 ball shooting positions
    public static AlliancePose2D LONG_SHOT_BLUE = new AlliancePose2D(16.125, -60.0, 66.0, Alliance.BLUE);
    public static AlliancePose2D LONG_SHOT_RED = new AlliancePose2D(16.125, 60.0, -66.0, Alliance.RED);

    // Obsolete, replaced by CLOSE_SHOT_N
    public static AlliancePose2D CLOSE_SHOT_BLUE = new AlliancePose2D(20.0, 20.0, 42.0, Alliance.BLUE);
    public static AlliancePose2D CLOSE_SHOT_RED = new AlliancePose2D(20.0, -20.0, -42.0, Alliance.RED);

    // 9 ball shooting positions
    public static AlliancePose2D CLOSE_SHOT_1_BLUE = new AlliancePose2D(24.0, 24.0, 42.0, Alliance.BLUE);
    public static AlliancePose2D CLOSE_SHOT_1_RED = new AlliancePose2D(24.0, -24.0, -42.0, Alliance.RED);

    public static AlliancePose2D CLOSE_SHOT_2_BLUE = new AlliancePose2D(20.0, 20.0, 42.0, Alliance.BLUE);
    public static AlliancePose2D CLOSE_SHOT_2_RED = new AlliancePose2D(20.0, -20.0, -42.0, Alliance.RED);

    public static AlliancePose2D CLOSE_SHOT_3_BLUE = new AlliancePose2D(16.0, 36.0, 29.6, Alliance.BLUE);
    public static AlliancePose2D CLOSE_SHOT_3_RED = new AlliancePose2D(16.0, -36.0, -29.6, Alliance.RED);

    // Intake start positions
    public static AlliancePose2D INTAKE_CLOSE_START_BLUE = new AlliancePose2D(20.0, 14.0, 180.0, Alliance.BLUE); // PPG
    public static AlliancePose2D INTAKE_CLOSE_START_RED = new AlliancePose2D(20.0, -14.0, 180.0, Alliance.RED); // PPG

    public static AlliancePose2D INTAKE_MIDDLE_START_BLUE = new AlliancePose2D(24.0, -8.0, 180.0, Alliance.BLUE); // PGP
    public static AlliancePose2D INTAKE_MIDDLE_START_RED = new AlliancePose2D(24.0, 12.0, 180.0, Alliance.RED); // PGP

    public static AlliancePose2D INTAKE_FAR_START_BLUE = new AlliancePose2D(24.0, -38.0, 180.0, Alliance.BLUE); // GPP
    public static AlliancePose2D INTAKE_FAR_START_RED = new AlliancePose2D(24.0, 38.0, 180.0, Alliance.RED); // GPP

    // Intake end positions
    public static AlliancePose2D INTAKE_CLOSE_END_BLUE = new AlliancePose2D(54.0, 14.0, 180.0, Alliance.BLUE); // PPG
    public static AlliancePose2D INTAKE_CLOSE_END_RED = new AlliancePose2D(54.0, -14.0, 180.0, Alliance.RED); // PPG

    public static AlliancePose2D INTAKE_MIDDLE_END_BLUE = new AlliancePose2D(61.0, -8.0, 180.0, Alliance.BLUE); // PGP
    public static AlliancePose2D INTAKE_MIDDLE_END_RED = new AlliancePose2D(63.0, 12.0, 180.0, Alliance.RED); // PGP

    public static AlliancePose2D INTAKE_FAR_END_BLUE = new AlliancePose2D(61.5, -38.0, 180.0, Alliance.BLUE); // GPP
    public static AlliancePose2D INTAKE_FAR_END_RED = new AlliancePose2D(61.5, 38.0, 180.0, Alliance.RED); // GPP

    // Ending positions
    public static AlliancePose2D CLOSE_PARK_BLUE = new AlliancePose2D(54.0, 30.0, 0.0, Alliance.BLUE);
    public static AlliancePose2D CLOSE_PARK_RED = new AlliancePose2D(54.0, -30.0, 0.0, Alliance.RED);

    public static AlliancePose2D LONG_PARK_BLUE = new AlliancePose2D(20.0, -36.0,  0.0, Alliance.BLUE);
    public static AlliancePose2D LONG_PARK_RED = new AlliancePose2D(20.0, 36.0,  0.0, Alliance.RED);


}
