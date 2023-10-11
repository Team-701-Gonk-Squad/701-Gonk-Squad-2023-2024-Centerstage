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

@TeleOp

public class TeleOp701 extends LinearOpMode {

    Hardware hardware;

    double speed = 1;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        hardware = new Hardware(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
//            hardware.leftFront.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));
//            hardware.leftFront.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));
//            hardware.rightRear.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));
//            hardware.leftRear.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));

//            if (gamepad2.left_trigger > 0.1 && !gamepad2.left_bumper) {
//                action1.setPower(gamepad2.left_trigger*-1);
//            } else if (gamepad2.left_bumper){
//                action1.setPower(1);
//            }else{
//                action1.setPower(0);
//            }
//
//            if (gamepad2.left_stick_y > 0.1) {
//                action2.setPower(gamepad2.left_stick_y);
//            } else if (gamepad2.left_stick_y < -0.1){
//                action2.setPower(gamepad2.left_stick_y);
//            }else{
//                action2.setPower(0);
//            }
//
//            if (gamepad2.right_stick_y>0.1) {
//                action3.setPower(gamepad2.right_stick_y);
//            } else if (gamepad2.right_stick_y<-0.1) {
//                action3.setPower(gamepad2.right_stick_y);
//            } else{
//                action3.setPower(0);
//            }


            double x = gamepad1.left_stick_x * speed;
            double y = gamepad1.left_stick_y * -speed;
            double turn = gamepad1.right_stick_x * -speed;

            double theta = Math.atan2(y, x);
            double power = Math.hypot(x, y);
            double sin = Math.sin(theta - Math.PI / 4);
            double cos = Math.cos(theta - Math.PI / 4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));
            hardware.leftFront.setPower(power * cos / max + turn);
            hardware.leftFront.setPower(power * sin / max - turn);
            hardware.rightRear.setPower(power * sin / max + turn);
            hardware.leftRear.setPower(power * cos / max - turn);
            if ((power + Math.abs(turn)) > 1) {
                hardware.leftFront.setPower((hardware.leftFront.getPower()) / (power + turn));
                hardware.leftFront.setPower((hardware.leftFront.getPower()) / (power + turn));
                hardware.rightRear.setPower((hardware.rightRear.getPower()) / (power + turn));
                hardware.leftRear.setPower((hardware.leftRear.getPower()) / (power + turn));
            }

            if ((gamepad1.a) || (Math.abs(gamepad1.left_stick_x) + Math.abs(gamepad1.left_stick_y) + Math.abs(gamepad1.right_stick_x) <= 0.1)) {
                hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.leftFront.setPower(0);
                hardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.rightRear.setPower(0);
                hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                hardware.leftFront.setPower(0);
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



//            if (gamepad1.right_bumper) {
//                action4.setPower(1);
//            } else {
//                action4.setPower(0);
//            }
//            if (colorBlind.blue() <= 250) { //red > 50 && red < 70 && green > 50 && green < 70 && blue > 5 && blue < 23
//                telemetry.addData("object detected : ", "Block");
//            }
//            else if (colorBlind.blue() >= 330) { //red > 49 && red < 60 && green > 65 && green < 85 && blue > 34 && blue < 54
//                telemetry.addData("object detected : ", "Weefle");
//            }
//            else {
//                telemetry.addData("object detected : ", "null");
//            }
//            telemetry.addData("red", colorBlind.red());
//            telemetry.addData("green", colorBlind.green());
//            telemetry.addData("blue", colorBlind.blue());
                telemetry.addData("speed", speed);
                telemetry.update();
            }
        }
    }
