package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HelloBot {
    private DcMotor leftMotor;

    public void init(HardwareMap hwMap) {

        // DC Motor
        leftMotor = hwMap.get(DcMotor.class, "left_front_drive");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setLeftMotorSpeed(double speed) {
        leftMotor.setPower(speed); // -1 to 1
    }
}
