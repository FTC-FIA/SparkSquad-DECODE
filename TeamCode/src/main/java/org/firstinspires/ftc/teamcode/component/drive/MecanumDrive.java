package org.firstinspires.ftc.teamcode.component.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrive {

    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor rearLeft;
    protected DcMotor rearRight;

    public MecanumDrive(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
    }

    public void drive(double forward, double strafe, double rotate) {
        drive( forward, strafe, rotate, 1.0 );
    }

    public void drive(double forward, double strafe, double rotate, double speedFactor) {
        // clip speedFactor to between 0.0 and 1.0
        double speedFactorClipped = Math.min( 1.0, Math.max( 0.0, speedFactor ) );
        double denominator = ( Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotate), 1) ) / speedFactorClipped;
        frontLeft.setPower((forward + strafe + rotate) / denominator);
        rearLeft.setPower((forward - strafe + rotate) / denominator);
        frontRight.setPower((forward - strafe - rotate) / denominator);
        rearRight.setPower((forward + strafe - rotate) / denominator);
    }










}
