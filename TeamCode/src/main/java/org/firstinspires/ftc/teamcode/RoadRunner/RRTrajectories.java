<<<<<<< Updated upstream
package org.firstinspires.ftc.teamcode.RoadRunner;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.LynxModuleUtil;


@Autonomous


public class RRTrajectories {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor action1;
    private DcMotor action2;
    private CRServo action3;
    private CRServo action4;
    private static double DISTANCE;
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    Trajectory forwardToSpike = drive.trajectoryBuilder(new Pose2d())
            .forward(22)
            .build();
    Trajectory trajectoryRight = drive.trajectoryBuilder(new Pose2d())
            .strafeRight(DISTANCE)
            .build();

    public void leftSpike(){
        drive.followTrajectory(forwardToSpike);
        drive.turn(180);
    }
    public void rightSpike(){
        drive.followTrajectory(forwardToSpike);
        drive.turn(-180);
    }
    public void middleSpike(){
        drive.followTrajectory(forwardToSpike);
    }
//    public void runOpMode() {
//        telemetry.addData("Status", "Initialized");
//        fl = hardwareMap.get(DcMotor.class, "0");
//        fr = hardwareMap.get(DcMotor.class, "1");
//        bl = hardwareMap.get(DcMotor.class, "2");
//        br = hardwareMap.get(DcMotor.class, "3");
//        action1 = hardwareMap.get(DcMotor.class, "action1");
//        action2 = hardwareMap.get(DcMotor.class, "action2");
//        action3 = hardwareMap.get(CRServo.class, "action3");
//        action4 = hardwareMap.get(CRServo.class, "action4");
//        DISTANCE = 20;
//
//        Trajectory forwardToSpike = drive.trajectoryBuilder(new Pose2d())
//                .forward(22)
//                .build();
//
//        Trajectory spinToLeft = drive.trajectoryBuilder(forwardToSpike.end())
//                .back(DISTANCE)
//                .build();
//        Trajectory spinToRight = drive.trajectoryBuilder(forwardToSpike.end())
//                .strafeLeft(DISTANCE)
//                .build();
//        Trajectory trajectoryRight = drive.trajectoryBuilder(new Pose2d())
//                .strafeRight(DISTANCE)
//                .build();
//
//        waitForStart();
//
//        while (opModeIsActive() && !isStopRequested()) {
//            drive.followTrajectory(forwardToSpike);
//            drive.followTrajectory(spinToLeft);
//            drive.followTrajectory(spinToRight);
//            //drive.followTrajectory(trajectoryRight);
//        }
//
=======
//package org.firstinspires.ftc.teamcode.RoadRunner;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.trajectory.Trajectory;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
>>>>>>> Stashed changes
//
//
//@Autonomous
//
//
//public class RRTrajectories {
//
//    private DcMotor fl;
//    private DcMotor fr;
//    private DcMotor bl;
//    private DcMotor br;
//    private DcMotor action1;
//    private DcMotor action2;
//    private CRServo action3;
//    private CRServo action4;
//    private static double DISTANCE;
//    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//    Trajectory forwardToSpike = drive.trajectoryBuilder(new Pose2d())
//            .forward(22)
//            .build();
//    Trajectory trajectoryRight = drive.trajectoryBuilder(new Pose2d())
//            .strafeRight(DISTANCE)
//            .build();
//
//    public void leftSpike(){
////        drive.followTrajectory(forwardToSpike);
////        drive.turn(180);
//    }
//    public void rightSpike(){
//        drive.followTrajectory(forwardToSpike);
//        drive.turn(-180);
//    }
//    public void middleSpike(){
//        drive.followTrajectory(forwardToSpike);
//    }
////    public void runOpMode() {
////        telemetry.addData("Status", "Initialized");
//////        fl = hardwareMap.get(DcMotor.class, "0");
//////        fr = hardwareMap.get(DcMotor.class, "1");
//////        bl = hardwareMap.get(DcMotor.class, "2");
//////        br = hardwareMap.get(DcMotor.class, "3");
//////        action1 = hardwareMap.get(DcMotor.class, "action1");
//////        action2 = hardwareMap.get(DcMotor.class, "action2");
//////        action3 = hardwareMap.get(CRServo.class, "action3");
//////        action4 = hardwareMap.get(CRServo.class, "action4");
////        DISTANCE = 20;
////
////
////        Trajectory forwardToSpike = drive.trajectoryBuilder(new Pose2d())
////                .forward(22)
////                .build();
////
////        Trajectory spinToLeft = drive.trajectoryBuilder(forwardToSpike.end())
////                .back(DISTANCE)
////                .build();
////        Trajectory spinToRight = drive.trajectoryBuilder(forwardToSpike.end())
////                .strafeLeft(DISTANCE)
////                .build();
////        Trajectory trajectoryRight = drive.trajectoryBuilder(new Pose2d())
////                .strafeRight(DISTANCE)
////                .build();
////
////        waitForStart();
////
////        while (opModeIsActive() && !isStopRequested()) {
////            drive.followTrajectory(forwardToSpike);
////            drive.followTrajectory(spinToLeft);
////            drive.followTrajectory(spinToRight);
////            //drive.followTrajectory(trajectoryRight);
////        }
////
////
////
////
////
////
////
////    }
//}
