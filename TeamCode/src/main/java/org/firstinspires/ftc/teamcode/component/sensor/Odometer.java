package org.firstinspires.ftc.teamcode.component.sensor;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Odometer {

    private final GoBildaPinpointDriver pinpointDriver;
    private boolean invertX = false;
    private boolean invertY = true;
    private boolean invertH = true;

    public Odometer(GoBildaPinpointDriver pinpointDriver) {
        this.pinpointDriver = pinpointDriver;
    }

    public void reset() {
        pinpointDriver.resetPosAndIMU();
        pinpointDriver.recalibrateIMU();
    }

    public void update() {
        pinpointDriver.update();
    }

    public double getX(DistanceUnit units) {
        return pinpointDriver.getPosX(units) * (invertX ? -1.0 : 1.0) ;
    }

    public double getY(DistanceUnit units) {
        return pinpointDriver.getPosY(units) * (invertY ? -1.0 : 1.0) ;
    }

    public double getHeading(AngleUnit units) {
        return pinpointDriver.getHeading(units) * (invertH? -1.0 : 1.0) ;
    }

    public void setPosition(Pose2D newPosition) {
        DistanceUnit du = DistanceUnit.INCH;
        AngleUnit au = AngleUnit.DEGREES;
        Pose2D transformedPose = new Pose2D(
                du,
                newPosition.getX(du) * (invertX ? -1.0 : 1.0),
                newPosition.getY(du) * (invertY ? -1.0 : 1.0),
                au,
                newPosition.getHeading(au)  * (invertH? -1.0 : 1.0)
        );
        pinpointDriver.setPosition(transformedPose);
    }
}
