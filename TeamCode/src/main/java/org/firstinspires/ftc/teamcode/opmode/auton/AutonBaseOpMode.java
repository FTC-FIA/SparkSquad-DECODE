package org.firstinspires.ftc.teamcode.opmode.auton;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;
import org.firstinspires.ftc.teamcode.task.AutonTaskList;
import org.firstinspires.ftc.teamcode.util.AllianceColor;
import org.firstinspires.ftc.teamcode.util.ColorPose2D;

public abstract class AutonBaseOpMode extends RobotBaseOpMode {

    private final ElapsedTime elapsedTime = new ElapsedTime();
    protected AutonTaskList autonTaskList;

    // Initial positions
    protected ColorPose2D I1 = new ColorPose2D( 18.0, -64.0, 0.0, AllianceColor.BLUE) ;
    protected ColorPose2D I2 = new ColorPose2D( 60.0, 40.0, 0.0, AllianceColor.BLUE );
    protected ColorPose2D I3 = new ColorPose2D( 40.0, 60.0, 0.0, AllianceColor.BLUE );

    // Shooting positions
    protected ColorPose2D S1 = new ColorPose2D( 16.0, -60.0, 75.0, AllianceColor.BLUE);
    protected ColorPose2D S2 = new ColorPose2D( 0.0, 0.0, 45.0, AllianceColor.BLUE);
    protected ColorPose2D S3 = new ColorPose2D( 20.0, 20.0, 50.0, AllianceColor.BLUE);
    protected ColorPose2D S4 = new ColorPose2D( 18.0, -64.0, 0.0, AllianceColor.BLUE);

    // Ending positions
    protected ColorPose2D E1 = new ColorPose2D( 20.0, -36.0,  0.0, AllianceColor.BLUE);
    protected ColorPose2D E2 = new ColorPose2D( 12.0, -12.0, 0.0, AllianceColor.BLUE);
    protected ColorPose2D E3 = new ColorPose2D( 60.0, 30.0, 0.0, AllianceColor.BLUE);

    public void loop() {
        telemetry.addData("Elapsed time (ms)", elapsedTime.seconds());

        autonTaskList.execute();

        telemetry.addData("X", odometer.getX(DistanceUnit.INCH));
        telemetry.addData("Y", odometer.getY(DistanceUnit.INCH));
        telemetry.addData("H", odometer.getHeading(AngleUnit.DEGREES));
        telemetry.addData("Vel", shooter.getShooterVelocity());
        telemetry.update();
    }
}

