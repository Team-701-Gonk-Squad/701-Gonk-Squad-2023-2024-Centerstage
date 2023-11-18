package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import org.firstinspires.ftc.teamcode.RoadRunner.RRTrajectories;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous


public class TrajectoryBrokeMaybe extends LinearOpMode {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor action1;
    private DcMotor action2;
    private CRServo action3;
    private CRServo action4;
    private static double DISTANCE;
    Pose2d startPose = new Pose2d(-35, -
            60, Math.toRadians(90));


    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        telemetry.addData("Status", "Initialized");
//        fl = hardwareMap.get(DcMotor.class, "0");
//        fr = hardwareMap.get(DcMotor.class, "1");
//        bl = hardwareMap.get(DcMotor.class, "2");
//        br = hardwareMap.get(DcMotor.class, "3");
//        action1 = hardwareMap.get(DcMotor.class, "action1");
//        action2 = hardwareMap.get(DcMotor.class, "action2");
//        action3 = hardwareMap.get(CRServo.class, "action3");
//        action4 = hardwareMap.get(CRServo.class, "action4");
//        DISTANCE = 20;

        TrajectorySequence forward = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .forward(140)//20
                .turn(Math.toRadians(-90))
                .build();
        TrajectorySequence forward1 = drive.trajectorySequenceBuilder(forward.end())
                .forward(20)
                .turn(Math.toRadians(-90))
                .build();
        TrajectorySequence forward2 = drive.trajectorySequenceBuilder(forward1.end())
                .forward(20)
                .turn(Math.toRadians(-90))
                .build();
        TrajectorySequence forward3 = drive.trajectorySequenceBuilder(forward2.end())
                .forward(20)
                .turn(Math.toRadians(-90))
                .build();
        TrajectorySequence splineywiney = drive.trajectorySequenceBuilder(startPose)
                .splineTo(new Vector2d(-35, -46), Math.toRadians(90))
                .splineTo(new Vector2d(0, -35), Math.toRadians(0))
                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
                .splineTo(new Vector2d(45, -56), Math.toRadians(270))
                .splineTo(new Vector2d(60, -60), Math.toRadians(0))
                .build();
        drive.setPoseEstimate(startPose);
        TrajectorySequence UnderTruss = drive.trajectorySequenceBuilder(new Pose2d(-35, -60, Math.toRadians(90)))
                .splineTo(new Vector2d(-35, -46), Math.toRadians(90))
                .splineTo(new Vector2d(0, -35), Math.toRadians(0))
                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
                .splineTo(new Vector2d(45, -54), Math.toRadians(270))
                .splineTo(new Vector2d(60, -55), Math.toRadians(0))
                .build();

//                .splineTo(new Vector2d(10,24),90)
//                .splineTo(new Vector2d(0,0),0)
        waitForStart();
        drive.followTrajectorySequence(UnderTruss);
//        drive.followTrajectorySequence(forward);
//        drive.followTrajectorySequence(forward1);
//        drive.followTrajectorySequence(forward2);
//        drive.followTrajectorySequence(forward3);
    }
}