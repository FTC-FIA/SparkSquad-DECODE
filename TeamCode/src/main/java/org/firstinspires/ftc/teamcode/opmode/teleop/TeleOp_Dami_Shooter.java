package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.component.drive.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.component.drive.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

@TeleOp(name="TeleOp_Dami_Shooter", group="Iterative OpMode")
public class TeleOp_Dami_Shooter extends OpMode
{
    final String FRONT_LEFT_DRIVE_MOTOR_NAME = "front_left";
    final String FRONT_RIGHT_DRIVE_MOTOR_NAME = "front_right";
    final String REAR_LEFT_DRIVE_MOTOR_NAME = "rear_left";
    final String REAR_RIGHT_DRIVE_MOTOR_NAME = "rear_right";
    final String SHOOTER_MOTOR_NAME = "shooter";
    final String INTAKE_MOTOR_NAME = "intake";

    private final ElapsedTime runtime = new ElapsedTime();
    private MecanumDrive mecanumDrive = null;
    private GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer

    private DcMotor shooter = null;
    private DcMotor intake = null;

    private double targetX = 0.0;
    private double targetY = 0.0;
    private double targetH = 0.0;

    double errorToleranceX = 20.0;
    double errorToleranceY = 20.0;
    double errorToleranceH = 20.0;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        // INIT DRIVETRAIN
        DcMotor frontLeft  = hardwareMap.get(DcMotor.class, FRONT_LEFT_DRIVE_MOTOR_NAME);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, FRONT_RIGHT_DRIVE_MOTOR_NAME);
        DcMotor rearLeft  = hardwareMap.get(DcMotor.class, REAR_LEFT_DRIVE_MOTOR_NAME);
        DcMotor rearRight = hardwareMap.get(DcMotor.class, REAR_RIGHT_DRIVE_MOTOR_NAME);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        mecanumDrive = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);

        // INIT PINPOINT
        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-84.1, -117.5, DistanceUnit.MM);

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.REVERSED,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );
        odo.resetPosAndIMU();

        // INIT OTHER MECHANISMS - SHOOTER, INTAKE, LIFT, LIMELIGHT, ETC.
        shooter = hardwareMap.get(DcMotor.class, SHOOTER_MOTOR_NAME);
        shooter.setDirection(DcMotor.Direction.FORWARD);

        intake = hardwareMap.get(DcMotor.class, INTAKE_MOTOR_NAME);
        intake.setDirection(DcMotor.Direction.REVERSE);

        // PRINT TELEMETRY
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        if (gamepad1.a){
            shooter.setPower(0.2);
        }
        if(gamepad1.b){
            shooter.setPower(0.4);
        }
        if(gamepad1.x){
            shooter.setPower(0.6);
        }
        if(gamepad1.y){
            shooter.setPower(0.8);
        }
        if(gamepad1.dpad_up){
            shooter.setPower(1);
        }
        if(gamepad1.dpad_down){
            shooter.setPower(0);
        }

        // Print telemetry
        String shooterPower = String.format(
                Locale.US,
                "{%.3f}",
                shooter.getPower()
        );
        telemetry.addData("Shooter power", shooterPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
