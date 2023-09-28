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

@TeleOp

public class TeleOp701 extends LinearOpMode{

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor action1;
    private DcMotor action2;
    private CRServo action3;
    private CRServo action4;
    private ColorRangeSensor colorBlind;
    private float rgbValue;
    private float red;
    private float green;
    private float blue;
    public float[] hsvValues = {1};
    double speed = 1;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");

        fl = hardwareMap.get(DcMotor.class, "2");
        fr = hardwareMap.get(DcMotor.class, "3");
        bl = hardwareMap.get(DcMotor.class, "0");
        br = hardwareMap.get(DcMotor.class, "1");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

//        action1 = hardwareMap.get(DcMotor.class, "action1");
//        action2 = hardwareMap.get(DcMotor.class, "action2");
//        action3 = hardwareMap.get(CRServo.class, "action3");
//        action4 = hardwareMap.get(CRServo.class, "action4");
//
//        colorBlind = hardwareMap.get(ColorRangeSensor.class, "bruh");
//        red = colorBlind.red();
//        green = colorBlind.green();
//        blue = colorBlind.blue();



        waitForStart();
        while (opModeIsActive()) {
//            fl.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));
//            fr.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));
//            bl.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));
//            br.setPower(((gamepad1.left_stick_y) + (gamepad1.left_stick_x) + (gamepad1.right_stick_x) * speed));

            //action1.setPower(gamepad1.left_trigger);
            //action2.setPower(gamepad1.right_trigger);

            double x = gamepad1.left_stick_x*speed;
            double y = gamepad1.left_stick_y*-speed;
            double turn = gamepad1.right_stick_x*speed;

            double theta = Math.atan2(y, x);
            double power = Math.hypot(x, y);
            double sin = Math.sin(theta - Math.PI/4);
            double cos = Math.cos(theta - Math.PI/4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));
            fl.setPower(power * cos/max + turn);
            fr.setPower(power * sin/max - turn);
            bl.setPower(power * sin/max + turn);
            br.setPower(power * cos/max - turn);
            if ((power + Math.abs(turn)) > 1) {
                fl.setPower((fl.getPower()) / (power + turn));
                fr.setPower((fr.getPower()) / (power + turn));
                bl.setPower((bl.getPower()) / (power + turn));
                br.setPower((br.getPower()) / (power + turn));
            }

            if ((gamepad1.a) || (Math.abs(gamepad1.left_stick_x)+Math.abs(gamepad1.left_stick_y)+Math.abs(gamepad1.right_stick_x) <= 0.1)) {
                fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                fl.setPower(0);
                bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                bl.setPower(0);
                fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                fr.setPower(0);
                br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                br.setPower(0);
            } else {
                fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
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

//            if (gamepad1.left_bumper) {
//                action3.setPower(1);
//            } else {
//                action3.setPower(0);
//            }
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
