package org.firstinspires.ftc.teamcode.teleop;

import static java.lang.Thread.sleep;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;
import org.firstinspires.ftc.teamcode.visionanglesmath.Ploop;

import java.util.Timer;

@TeleOp

public class Tele4 extends LinearOpMode {
    Hardware hardware;
    double position;

    public void runOpMode() {

        hardware = new Hardware(hardwareMap);

        position = 0;

        waitForStart();

        while (opModeIsActive()) {
//            if (gamepad1.a) {
//                hardware.door.setPosition(0);
//                hardware.boxRotation.setPosition(1);
//            } else if (gamepad1.b) {
//                hardware.door.setPosition(0.25);
//                hardware.boxRotation.setPosition(0);
//            }

//            if (gamepad1.left_trigger > 0) {
                position = gamepad1.left_trigger;
//            }

//            if (gamepad1.a) {
//                position = 0.25;
//                telemetry.addData("A Pressed", 1);
//            } else if (gamepad1.b) {
//                position = 0;
//                telemetry.addData("B Pressed", 1);
//            } else {
//                telemetry.addData("A Pressed", 0);
//                telemetry.addData("B Pressed", 0);
//            }

            hardware.boxRotation.setPosition(position);
//            hardware.door.setPosition(position);

            telemetry.addData("Position", position);
            telemetry.update();
        }
    }
}