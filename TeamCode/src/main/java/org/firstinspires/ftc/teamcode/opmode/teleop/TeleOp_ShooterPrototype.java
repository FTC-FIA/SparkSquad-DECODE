package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="TeleOp_ShooterPrototype", group="Test")
public class TeleOp_ShooterPrototype extends OpMode {
    final String SHOOTER_MOTOR_NAME = "motor_0";
    final String SERVO_1_NAME = "servo_1";
    private final ElapsedTime runtime = new ElapsedTime();
    private int mode = 0;
    private DcMotor shooter;
    private Servo servo_1;
    private boolean modeButtonWasPressedLastLoop = false;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        shooter  = hardwareMap.get(DcMotor.class,SHOOTER_MOTOR_NAME);
        servo_1  = hardwareMap.get(Servo.class,SERVO_1_NAME);
        shooter.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        System.out.println("TeleOp_Starter: Initializing Logging"); // where does this go?
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
        // Setup a variable for each drive wheel to save power level for telemetry



        if (gamepad2.dpad_down) {
            servo_1.setPosition(0);
        }
        if (gamepad2.dpad_right){
            servo_1.setPosition(0.25);
        }
        if (gamepad2.dpad_up) {
            servo_1.setPosition(.5);
        }
        if (gamepad2.dpad_left) {
            servo_1.setPosition(.75);
        }

        if (gamepad2.start && !modeButtonWasPressedLastLoop) {
            mode = mode + 1;
            if (mode > 2) {
                mode = 0;
            }
        }
        modeButtonWasPressedLastLoop = gamepad2.start;

        if (mode == 0) {
            mode0();
        } else if (mode == 1) {
            mode1();
        } else if (mode == 2) {
            mode2();
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime);
        telemetry.addData("Shooter Power", shooter.getPower());
        telemetry.addData("Mode", mode);
        telemetry.update();
    }

    private void mode0() {
        boolean Stop =  gamepad2.right_bumper;
        boolean shooter25 =  gamepad2.a;
        boolean shooter50 =  gamepad2.b;
        boolean shooter75 =  gamepad2.y;
        boolean shooter100 =  gamepad2.x;


        if (Stop) {
            shooter.setPower(0);
        }
        if (shooter25) {
            shooter.setPower(.25);
        }
        if (shooter50) {
            shooter.setPower(.5);
        }

        if (shooter75) {
            shooter.setPower(.75);

        }

        if (shooter100) {
            shooter.setPower(1);

        }
    }

    private void mode1() {
        boolean Stop =  gamepad2.right_bumper;
        boolean shooter25 =  gamepad2.a;
        boolean shooter50 =  gamepad2.b;
        boolean shooter75 =  gamepad2.y;
        boolean shooter100 =  gamepad2.x;


        if (Stop) {
            shooter.setPower(0);
        }
        if (shooter25) {
            shooter.setPower(-.25);
        }
        if (shooter50) {
            shooter.setPower(-.5);
        }

        if (shooter75) {
            shooter.setPower(-.75);

        }

        if (shooter100) {
            shooter.setPower(-1);

        }
    }

    private void mode2() {
        shooter.setPower(gamepad2.right_stick_y);
    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}


