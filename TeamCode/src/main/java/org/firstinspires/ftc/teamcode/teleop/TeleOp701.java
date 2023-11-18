package org.firstinspires.ftc.teamcode.teleop;

import android.graphics.Color;

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

@TeleOp

public class TeleOp701 extends LinearOpMode {
    Hardware hardware;
    double speed = 1;
    Ploop looper;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        hardware = new Hardware(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            double turn = gamepad1.left_stick_x * speed;
            double y = gamepad1.left_stick_y * -speed;
            double x = gamepad1.right_stick_x * -speed;

            double theta = Math.atan2(y, x);
            double power = Math.hypot(x, y);
            double sin = Math.sin(theta - Math.PI / 4);
            double cos = Math.cos(theta - Math.PI / 4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));
            hardware.leftFront.setPower(power * cos / max + turn);
            hardware.rightFront.setPower(power * sin / max - turn);
            hardware.rightRear.setPower(power * sin / max + turn);
            hardware.leftRear.setPower(power * cos / max - turn);
            if ((power + Math.abs(turn)) > 1) {
                hardware.leftFront.setPower((hardware.leftFront.getPower()) / (power + turn));
                hardware.rightFront.setPower((hardware.leftFront.getPower()) / (power + turn));
                hardware.rightRear.setPower((hardware.rightRear.getPower()) / (power + turn));
                hardware.leftRear.setPower((hardware.leftRear.getPower()) / (power + turn));
            }

            if ((gamepad1.a) || (Math.abs(gamepad1.left_stick_x) + Math.abs(gamepad1.left_stick_y) + Math.abs(gamepad1.right_stick_x) <= 0.1)) {
                hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.leftFront.setPower(0);
                hardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.rightRear.setPower(0);
                hardware.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.rightFront.setPower(0);
                hardware.leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.leftRear.setPower(0);
            } else {
                hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                hardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                hardware.leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }
            if (gamepad1.dpad_down) {
                speed = 0.35;
            }
            if (gamepad1.dpad_left) {
                speed = 0.5;
            }
            if (gamepad1.dpad_up) {
                speed = 0.8;
            }
            if (gamepad1.dpad_right) {
                speed = 1;
            }

            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);

            hardware.leftSlide.setPower(gamepad1.right_trigger);
            hardware.rightSlide.setPower(gamepad1.right_trigger);

            hardware.leftSlide.setPower(-gamepad1.left_trigger);
            hardware.rightSlide.setPower(-gamepad1.left_trigger);

            telemetry.addData("speed", speed);
            telemetry.addData("LeftPower ", hardware.leftSlide.getPower());
            telemetry.addData("RightPower ", hardware.rightSlide.getPower());
            telemetry.update();
            }
        }
    }
