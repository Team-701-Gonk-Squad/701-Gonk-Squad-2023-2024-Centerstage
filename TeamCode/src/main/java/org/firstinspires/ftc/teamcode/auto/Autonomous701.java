package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous

public class Autonomous701 extends LinearOpMode{

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor action1;
    private DcMotor action2;
    private CRServo action3;
    private CRServo action4;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        fl = hardwareMap.get(DcMotor.class, "front_left");
        fr = hardwareMap.get(DcMotor.class, "front_right");
        bl = hardwareMap.get(DcMotor.class, "back_left");
        br = hardwareMap.get(DcMotor.class, "back_right");
        action1 = hardwareMap.get(DcMotor.class, "action1");
        action2 = hardwareMap.get(DcMotor.class, "action2");
        action3 = hardwareMap.get(CRServo.class, "action3");
        action4 = hardwareMap.get(CRServo.class, "action4");

    }
}
