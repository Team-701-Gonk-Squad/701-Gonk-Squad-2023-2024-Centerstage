package org.firstinspires.ftc.teamcode.teleop;

import static java.lang.Thread.sleep;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;
import org.firstinspires.ftc.teamcode.visionanglesmath.Ploop;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@TeleOp

public class Tele3 extends LinearOpMode {
    Hardware hardware;
    double speed = 1;
    Ploop looper;
    double cyclecount = 0;
    double loop = 0;
    public double numPixels;

    private static ElapsedTime myStopwatch = new ElapsedTime();

    Boolean keepPixels = true;

    public void runOpMode() {

        /* init */ {
            telemetry.addData("Status", "Initialized");

            hardware = new Hardware(hardwareMap);

            hardware.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            hardware.rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            hardware.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            hardware.leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }

        waitForStart();

        myStopwatch.reset();


        while (opModeIsActive()) {

            /* Drive Control */
            {
                double x = gamepad1.left_stick_x * speed;
                double y = gamepad1.left_stick_y * speed;
                double turn = gamepad1.right_stick_x * speed;

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
            }

            /* Speed Control */
            {
                speed = (gamepad1.right_trigger * 0.7) + 0.3;
            }

            // Arm up with encoders intake sequence
            if (gamepad2.a) {
                hardware.door.setPosition(0);
                hardware.leftSlide.setPower(-0.75);
                hardware.rightSlide.setPower(-0.75);
                hardware.boxRotation.setPosition(1);
                hardware.intake.setPower(1);
            }

            if (gamepad2.b) {
                hardware.door.setPosition(0.25);
                hardware.intake.setPower(0);
                hardware.leftSlide.setPower(1);
                hardware.rightSlide.setPower(1);
                sleep(400);
                hardware.boxRotation.setPosition(0);
            }

            if (gamepad2.x) {
                hardware.door.setPosition(0);
            }

            if (gamepad1.b) {
                hardware.intake.setPower(-1);
            }
            //set powers to pull up hooks
            hardware.verticalActuator.setPower(-(gamepad2.left_stick_y) * 5);

            telemetry.addData("speed", speed);

            if (gamepad2.right_trigger != 0) {
                hardware.leftSlide.setPower(0.75);
                hardware.rightSlide.setPower(0.75);
            } else if (gamepad2.left_trigger != 0) {
                hardware.leftSlide.setPower(-0.75);
                hardware.rightSlide.setPower(-0.75);
            } else {
                hardware.leftSlide.setPower(0);
                hardware.rightSlide.setPower(0);
            }

            telemetry.addData("leftslidepower", hardware.leftSlide.getPower());
            telemetry.addData("rightslidepower", hardware.rightSlide.getPower());

            // plane
            if (gamepad1.y && gamepad2.y) {
                hardware.plane.setPower(-1);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    telemetry.addData("error", e);
                }
                hardware.plane.setPower(1);
            }

            if (gamepad2.left_bumper) {
                hardware.verticalActuator.setPower(1);
            } else if (gamepad2.right_bumper) {
                hardware.verticalActuator.setPower(-1);
                hardware.intake.setPower(0);
            } else {
                hardware.verticalActuator.setPower(0);
            }

            numPixels = 0;

            telemetry.addData("TopSlot: ", hardware.topSlot.getDistance(DistanceUnit.CM));
            telemetry.addData("BottomSlot: ", hardware.bottomSlot.getDistance(DistanceUnit.CM));

//            if (hardware.topSlot.getDistance(DistanceUnit.CM) <= 3.5) {
//                numPixels += 1;
//            }
//
//            if (hardware.bottomSlot.getDistance(DistanceUnit.CM) <= 3.5) {
//                numPixels += 1;
//            }

            if (myStopwatch.time(TimeUnit.SECONDS) >= 105) {
                if (myStopwatch.time(TimeUnit.SECONDS) <= 110) {
                    hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN);
                } else if (myStopwatch.time(TimeUnit.SECONDS) <= 115) {
                    if ((myStopwatch.time(TimeUnit.SECONDS) % 2) == 0) {
                        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BLUE_GREEN);
                    } else {
                        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BLACK);
                    }
                } else {
                    hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD);
                }
            } else if (hardware.bottomSlot.getDistance(DistanceUnit.CM) <= 3.5) {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RED);
            } else {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BLACK);
            }

            telemetry.addData("Stopwatch timer: ", myStopwatch.time(TimeUnit.SECONDS));

            telemetry.update();
        }
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