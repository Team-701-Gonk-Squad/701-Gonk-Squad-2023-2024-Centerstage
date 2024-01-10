package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Trajectories;

import com.qualcomm.robotcore.hardware.RobotCoreLynxController;

@Autonomous(name = "ParkTesting", group = "Ronan's OpModes")
public class ParkTesting extends LinearOpMode {
//    public ElapsedTime runtime = new ElapsedTime();
    public SampleMecanumDrive drive;
    public Trajectories trajectories;

    @Override
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        trajectories = new Trajectories(hardwareMap);

        waitForStart();

//        runtime.reset();
//
//        if (isStopRequested()) return;

        Pose2d redBackstageStartPose = new Pose2d(12, -60, 90);
        Pose2d blueBackstageStartPose = new Pose2d(12, 60, 90);
        Pose2d redOnstageStartPose = new Pose2d(-35, -60, 270);
        Pose2d blueOnstageStartPose = new Pose2d(-35, 60, 270);

        // Define trajectories
        Trajectory RedBackstageParking = drive.trajectoryBuilder(redBackstageStartPose)
                .splineTo(new Vector2d(12, -46), Math.toRadians(90))
                .splineTo(new Vector2d(18, -35), Math.toRadians(0))
                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(45, -59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                .build();

        Trajectory BlueBackstageParking = drive.trajectoryBuilder(blueBackstageStartPose)
                .splineTo(new Vector2d(12, 46), Math.toRadians(270))
                .splineTo(new Vector2d(18, 35), Math.toRadians(0))
                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(45, 55), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, 55), Math.toRadians(0))
                .build();

        Trajectory RedOnstageParking = drive.trajectoryBuilder(redOnstageStartPose)
                .splineTo(new Vector2d(-35, -46), Math.toRadians(90))
                .splineTo(new Vector2d(0, -35), Math.toRadians(0))
                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(45, -59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                .build();

        Trajectory BlueOnstageParking = drive.trajectoryBuilder(blueOnstageStartPose)
                .splineTo(new Vector2d(-35, 46), Math.toRadians(270))
                .splineTo(new Vector2d(0, 35), Math.toRadians(0))
                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(45, 59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
                .build();



        //start pose estimate

        drive.setPoseEstimate(redBackstageStartPose);
        drive.followTrajectory(RedBackstageParking);

        sleep(20000);

        drive.setPoseEstimate(blueBackstageStartPose);
        drive.followTrajectory(BlueBackstageParking);

        sleep(20000);

        drive.setPoseEstimate(redOnstageStartPose);
        drive.followTrajectory(RedOnstageParking);

        sleep(20000);

        drive.setPoseEstimate(blueOnstageStartPose);
        drive.followTrajectory(BlueOnstageParking);

            // Do other actions here (e.g., shoot, collect, etc.)

            // Stop the robot
            //drive.setMotorPowers(0, 0, 0, 0);
    }
}
