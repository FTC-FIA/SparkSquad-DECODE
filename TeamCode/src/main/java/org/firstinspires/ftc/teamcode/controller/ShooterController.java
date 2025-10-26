package org.firstinspires.ftc.teamcode.controller;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.component.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.component.mechanism.Trigger;
import org.firstinspires.ftc.teamcode.opmode.RobotBaseOpMode;

public class ShooterController {

    private final double DEFAULT_SHOOTER_POWER = 1.0;
    private final double HIGH_VELOCITY = 750;
    private final double MEDIUM_VELOCITY = 600;

    private final Shooter shooter;
    private final Trigger trigger;
    private final Gamepad operatorGamepad;
    private final Telemetry telemetry;

    private double shooterPower = DEFAULT_SHOOTER_POWER;

    public ShooterController(RobotBaseOpMode robot) {
        this.shooter = robot.getShooter();
        this.trigger = robot.getTrigger();
        this.operatorGamepad = robot.getOperatorGamepad();
        this.telemetry = robot.getTelemetry();
    }

    public void handleInput() {
        if (operatorGamepad.a) {
            spinUpAndShoot(MEDIUM_VELOCITY);
        } else if (operatorGamepad.b){
            spinUpAndShoot(HIGH_VELOCITY);
        } else {
            idle();
        }
        telemetry.addData("Trigger Pos", String.valueOf(trigger.getPosition()) );
        telemetry.addData("Shooter Velocity",String.valueOf(shooter.getShooterVelocity()) );
    }

    /**
     * This should be called iteratively, from TeleOp loop() or an Auton Task
     * @param targetVelocity
     */
    public boolean spinUpAndShoot(double targetVelocity) {
        shooter.spinUp(shooterPower);

        if (shooter.getShooterVelocity() > targetVelocity) {
            trigger.fire();
            return false; // action completed
        } else {
            trigger.reset();
            return true;
        }
    }

    public void idle() {
        shooter.stop();
        trigger.reset();
    }


}
