package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware {
    public DcMotor leftFront;
    public DcMotor leftRear;
    public DcMotor rightRear;
    public DcMotor rightFront;

    public Hardware(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "2");
        leftRear = hardwareMap.get(DcMotor.class, "0");
        rightRear = hardwareMap.get(DcMotor.class, "1");
        rightFront = hardwareMap.get(DcMotor.class, "3");

        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
