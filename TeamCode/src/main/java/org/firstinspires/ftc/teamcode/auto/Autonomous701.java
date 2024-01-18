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


public class Autonomous701 extends LinearOpMode{

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor action1;
    private DcMotor action2;
    private CRServo action3;
    private CRServo action4;
    private static double DISTANCE;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing");

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//        action1 = hardwareMap.get(DcMotor.class, "action1");
//        action2 = hardwareMap.get(DcMotor.class, "action2");
//        action3 = hardwareMap.get(CRServo.class, "action3");
//        action4 = hardwareMap.get(CRServo.class, "action4");
//        DISTANCE = 20;

//        Trajectory trajectoryForward = drive.trajectoryBuilder(new Pose2d())
//                .forward(DISTANCE)
//                .build();
//
//        Trajectory trajectoryBackward = drive.trajectoryBuilder(trajectoryForward.end())
//                .back(DISTANCE)
//                .build();
//        Trajectory trajectoryLeft = drive.trajectoryBuilder(trajectoryBackward.end())
//                .strafeLeft(DISTANCE)
//                .build();
//        Trajectory trajectoryRight = drive.trajectoryBuilder(trajectoryLeft.end())
//                .strafeRight(DISTANCE)
//                .build();
//        TrajectorySequence bottomRight = drive.trajectorySequenceBuilder(new Pose2d(-62, -35, Math.toRadians(0)))
//                .forward(27)
//                .turn(Math.toRadians(-90))
//                .forward(27*2.85)
//                .turn(Math.toRadians(-90))
//                .forward(27)
//                .build();
//        TrajectorySequence TESTYWESTY = drive.trajectorySequenceBuilder(new Pose2d(-62,62, Math.toRadians(0)))
//                .forward(120)
//                .turn(Math.toRadians(180))
//                .strafeLeft(120)
//                .turn(Math.toRadians(180))
//                .back(120)
//                .turn(Math.toRadians(90))
//                .forward(120)
//                .build();

        TrajectorySequence preboardBlueBackstage = drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(270)))
                .splineTo(new Vector2d(12, 46), Math.toRadians(270))
                .splineTo(new Vector2d(18, 35), Math.toRadians(0))
                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
                .build();
        TrajectorySequence postboardBlueBackstage = drive.trajectorySequenceBuilder(new Pose2d(18, 35, Math.toRadians(0)))
                .splineToConstantHeading(new Vector2d(45, 55), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, 55), Math.toRadians(0))
                .build();

        TrajectorySequence preboardBlueNonBackstage = drive.trajectorySequenceBuilder(new Pose2d(-35, 60, Math.toRadians(270)))
                .splineTo(new Vector2d(-35, 46), Math.toRadians(270))
                .splineTo(new Vector2d(0, 35), Math.toRadians(0))
                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
                .build();
        TrajectorySequence postboardBlueNonBackstage = drive.trajectorySequenceBuilder(new Pose2d(40, 35, Math.toRadians(0)))
                .splineToConstantHeading(new Vector2d(45, 59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                .build();

        TrajectorySequence preboardRedBackstage = drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(90)))
                .splineTo(new Vector2d(12, -46), Math.toRadians(90))
                .splineTo(new Vector2d(18, -35), Math.toRadians(0))
                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
                .build();
        TrajectorySequence postboardRedBackstage = drive.trajectorySequenceBuilder(new Pose2d(40, -35, Math.toRadians(0)))
                .splineToConstantHeading(new Vector2d(45, -59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                .build();

        TrajectorySequence preboardRedNonBackstage = drive.trajectorySequenceBuilder(new Pose2d(-35, -60, Math.toRadians(90)))
                .splineTo(new Vector2d(-35, 46), Math.toRadians(270))
                .splineTo(new Vector2d(0, 35), Math.toRadians(0))
                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
                .build();
        TrajectorySequence postboardRedNonBackstage = drive.trajectorySequenceBuilder(new Pose2d(40, 35, Math.toRadians(0)))
                .splineToConstantHeading(new Vector2d(45, 59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                .build();

        telemetry.addData("Status", "Initialized");

        waitForStart();

        telemetry.addData("Status", "Starting");

        //blue backstage
        drive.setPoseEstimate(new Pose2d(12, 60, Math.toRadians(270)));
        //blue non-backstage
        drive.setPoseEstimate(new Pose2d(-35, 60, Math.toRadians(270)));
        // red backstage
        drive.setPoseEstimate(new Pose2d(12, -60, Math.toRadians(90)));
        //red non-backstage
        drive.setPoseEstimate(new Pose2d(-35, -60, Math.toRadians(90)));


        drive.followTrajectorySequence(preboardBlueNonBackstage);
        sleep(1000);
        drive.followTrajectorySequence(postboardBlueNonBackstage);

        telemetry.addData("Status", "Complete");
    }
}
