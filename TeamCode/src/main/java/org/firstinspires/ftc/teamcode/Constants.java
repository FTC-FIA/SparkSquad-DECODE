package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.ColorPose2D;

public class Constants {
    public static double LED_GREEN = 0.5;
    public static double LED_ORANGE = 0.3;

    public static ColorPose2D TARGET = new ColorPose2D( 72.0, 72.0, 0.0, AllianceColor.BLUE);

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

    public static final double ODOMETER_X_OFFSET = -0.1875;
    public static final double ODOMETER_Y_OFFSET = -6.625;

    // Initial positions
    public static ColorPose2D I1 = new ColorPose2D(18.0, -64.0, 0.0, AllianceColor.BLUE) ;
    public static ColorPose2D I2 = new ColorPose2D(60.0, 40.0, 0.0, AllianceColor.BLUE);
    public static ColorPose2D I3 = new ColorPose2D(40.0, 60.0, 0.0, AllianceColor.BLUE);

    // Shooting positions
    public static ColorPose2D S1 = new ColorPose2D(16.0, -60.0, 75.0, AllianceColor.BLUE);
    public static ColorPose2D S2 = new ColorPose2D(0.0, 0.0, 45.0, AllianceColor.BLUE);
    public static ColorPose2D S3 = new ColorPose2D(20.0, 20.0, 50.0, AllianceColor.BLUE);
    public static ColorPose2D S4 = new ColorPose2D(18.0, -64.0, 0.0, AllianceColor.BLUE);

    // Intake start positions
    public static ColorPose2D IS1 = new ColorPose2D(24.0, 12.0, 180.0, AllianceColor.BLUE);

    // Intake end positions
    public static ColorPose2D IE1 = new ColorPose2D(54.0, 12.0, 180.0, AllianceColor.BLUE);

    // Ending positions
    public static ColorPose2D E1 = new ColorPose2D(20.0, -36.0,  0.0, AllianceColor.BLUE);
    public static ColorPose2D E2 = new ColorPose2D(12.0, -12.0, 0.0, AllianceColor.BLUE);
    public static ColorPose2D E3 = new ColorPose2D(60.0, 30.0, 0.0, AllianceColor.BLUE);
}
