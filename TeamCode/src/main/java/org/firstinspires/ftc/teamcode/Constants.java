package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.ColorPose2D;

public class Constants {

    public static final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    public static final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    public static final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    public static final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";
    public static final String SHOOTER_MOTOR_NAME = "shooter";
    public static final String INTAKE_MOTOR_NAME = "intake";
    public static final String KICKER_SERVO_NAME = "trigger";
    public static final String FEEDER_MOTOR_NAME = "feeder";
    public static final String SHOOTER_LED_NAME = "shooter_led";
    public static final String AIMER_LED_NAME = "aimer_led";
    public static final String ODOMETER_NAME = "odo";

    public static final double ODOMETER_X_OFFSET = -82.5;
    public static final double ODOMETER_Y_OFFSET = 125.0;

    // TODO: more fun colors!
    public static double LED_GREEN = 0.5;
    public static double LED_RED = 0.31;

    public static final double DEFAULT_FEEDER_POWER = 0.5;
    public static final double DEFAULT_INTAKE_POWER = 0.5;
    public static final double DEFAULT_KICKER_FORWARD_POWER = 0.3;
    public static final double DEFAULT_KICKER_REVERSE_POWER = -0.25;

    public static final double SHOOTER_P = 100.0;
    public static final double SHOOTER_I = 38.0;
    public static final double SHOOTER_D = 25.0;
    public static final double SHOOTER_F = 0.0;

    public static final double DEFAULT_SHOOTER_FORWARD_VELOCITY = 600;
    public static final double DEFAULT_SHOOTER_REVERSE_VELOCITY = -100;
    public static final double MAX_SHOOTER_VELOCITY = 1600;
    public static final double SHOOTER_VELOCITY_INCREMENT = 20.0;

    // Target positions
    public static ColorPose2D TARGET = new ColorPose2D( 72.0, 72.0, 0.0, AllianceColor.BLUE);

    // Initial positions
    public static ColorPose2D I1 = new ColorPose2D( 18.0, -64.0, 0.0, AllianceColor.BLUE) ;
    public static ColorPose2D I2 = new ColorPose2D( 60.0, 40.0, 0.0, AllianceColor.BLUE );
    public static ColorPose2D I3 = new ColorPose2D( 40.0, 60.0, 0.0, AllianceColor.BLUE );

    // Shooting positions
    public static ColorPose2D S1 = new ColorPose2D( 16.0, -60.0, 75.0, AllianceColor.BLUE);
    public static ColorPose2D S2 = new ColorPose2D( 0.0, 0.0, 45.0, AllianceColor.BLUE);
    public static ColorPose2D S3 = new ColorPose2D( 20.0, 20.0, 50.0, AllianceColor.BLUE);
    public static ColorPose2D S4 = new ColorPose2D( 18.0, -64.0, 0.0, AllianceColor.BLUE);

    // Ending positions
    public static ColorPose2D E1 = new ColorPose2D( 20.0, -36.0,  0.0, AllianceColor.BLUE);
    public static ColorPose2D E2 = new ColorPose2D( 12.0, -12.0, 0.0, AllianceColor.BLUE);
    public static ColorPose2D E3 = new ColorPose2D( 60.0, 30.0, 0.0, AllianceColor.BLUE);
}
