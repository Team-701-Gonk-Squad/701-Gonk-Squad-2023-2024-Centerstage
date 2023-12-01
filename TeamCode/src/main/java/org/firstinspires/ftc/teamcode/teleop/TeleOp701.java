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

@TeleOp

public class TeleOp701 extends OpMode {
    Hardware hardware;
    double speed = 1;
    Ploop looper;
    double cyclecount = 0;

    public void init() {
        telemetry.addData("Status", "Initialized");

        hardware = new Hardware(hardwareMap);

        hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hardware.leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {



        double x = gamepad1.left_stick_x * -speed;
        double y = gamepad1.left_stick_y * -speed;
        double turn = gamepad1.right_stick_x * -speed;

        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);
        double sin = Math.sin(theta - Math.PI / 4);
        double cos = Math.cos(theta - Math.PI / 4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        hardware.rightFront.setPower(power * cos / max + turn);
        hardware.leftFront.setPower(power * sin / max - turn);
        hardware.rightRear.setPower(power * sin / max + turn);
        hardware.leftRear.setPower(power * cos / max - turn);
        if ((power + Math.abs(turn)) > 1) {
            hardware.leftFront.setPower((hardware.leftFront.getPower()) / (power + turn));
            hardware.rightFront.setPower((hardware.rightFront.getPower()) / (power + turn));
            hardware.rightRear.setPower((hardware.rightRear.getPower()) / (power + turn));
            hardware.leftRear.setPower((hardware.leftRear.getPower()) / (power + turn));
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

        telemetry.addData("speed", speed);

//        hardware.leftSlide.setPower(gamepad2.right_trigger);
//        hardware.rightSlide.setPower(gamepad2.right_trigger);
//
//        hardware.leftSlide.setPower(-gamepad2.left_trigger);
//        hardware.rightSlide.setPower(-gamepad2.left_trigger);

        if (gamepad2.right_trigger != 0) {
            hardware.leftSlide.setPower(1);
            hardware.rightSlide.setPower(1);
        }
        else if (gamepad2.left_trigger != 0) {
            hardware.leftSlide.setPower(-1);
            hardware.rightSlide.setPower(-1);
        } else {
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
        }

        telemetry.addData("leftslidepower", hardware.leftSlide.getPower());
        telemetry.addData("rightslidepower", hardware.rightSlide.getPower());

        hardware.intake.setPower(gamepad1.right_trigger);
        hardware.intake.setPower(gamepad1.right_trigger);

        hardware.intake.setPower(-gamepad1.left_trigger);
        hardware.intake.setPower(-gamepad1.left_trigger);

        if (gamepad2.a) {
            hardware.door.setPosition(1);
        } else if (gamepad2.b) {
            hardware.door.setPosition(0);
        }

        // plane
        if (gamepad1.y && gamepad2.y) {
            hardware.plane.setPower(-1);
            try {
                Thread.sleep(50);
            } catch (Exception e) {telemetry.addData("error", e);}
            hardware.plane.setPower(0);
        }

//            cyclecount += 1;
//            telemetry.addData("Cycles", cyclecount);

            telemetry.update();
    }

    public void driveloop() {

    }

    public void speedloop() {
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

        telemetry.addData("speed", speed);
    }

    public void slideloop() {
        hardware.leftSlide.setPower(0);
        hardware.rightSlide.setPower(0);

        hardware.leftSlide.setPower(gamepad2.right_trigger);
        hardware.rightSlide.setPower(gamepad2.right_trigger);

        hardware.leftSlide.setPower(-gamepad2.left_trigger);
        hardware.rightSlide.setPower(-gamepad2.left_trigger);
    }

    public void intakeloop() {
        hardware.intake.setPower(0);
        hardware.intake.setPower(0);

        hardware.intake.setPower(gamepad1.right_trigger);
        hardware.intake.setPower(gamepad1.right_trigger);

        hardware.intake.setPower(-gamepad1.left_trigger);
        hardware.intake.setPower(-gamepad1.left_trigger);
    }

    public void doorloop() {
        if (gamepad2.a) {
            hardware.door.setPosition(1);
        } else if (gamepad2.b) {
            hardware.door.setPosition(0);
        }
    }
}