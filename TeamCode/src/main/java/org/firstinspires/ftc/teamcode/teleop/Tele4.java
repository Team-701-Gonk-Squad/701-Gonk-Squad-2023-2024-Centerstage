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

    public void runOpMode() {

        hardware = new Hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.a) {
                hardware.door.setPosition(0);
                hardware.boxRotation.setPosition(0);
            }

            if (gamepad1.b) {
                hardware.door.setPosition(0.25);
                hardware.boxRotation.setPosition(1);
            }
        }
    }
}