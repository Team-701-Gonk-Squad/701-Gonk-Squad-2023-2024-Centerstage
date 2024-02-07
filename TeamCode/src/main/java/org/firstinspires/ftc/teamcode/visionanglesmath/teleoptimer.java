//package org.firstinspires.ftc.teamcode.visionanglesmath;
//
//import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.subsystems.Hardware;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.Timer;
//import java.util.TimerTask;
//
//@TeleOp
//public class teleoptimer extends LinearOpMode {
//
//    public double TIME_TO_ORANGE;
//    public double TIME_TO_RED;
//    Hardware hardware;
//
//    @Override
//    public void runOpMode() {
//
//        TIME_TO_ORANGE = 5;
//        TIME_TO_RED = 10;
//        hardware = new Hardware(hardwareMap);
//        Timer timer = new Timer();;
//
//        timer.schedule(new TimerTask() { @Override
//            public void run() {
//                hardware.signal.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
//            }
//        }, TIME_TO_ORANGE * 1000);
//
//        timer.schedule(new TimerTask() { @Override
//            public void run() {
//                hardware.signal.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
//            }
//        }, TIME_TO_RED * 1000);
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            while (opModeIsActive()) {
//                if (timer.seconds() >= TIME_TO_ORANGE) {
//
//                }
//
//                if (timer.seconds() >= TIME_TO_RED) {
//
//                }
//
//                telemetry.update();
//            }
//            telemetry.update();
//        }
//    }
//}