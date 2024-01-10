package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class Trajectories {
    public SampleMecanumDrive drive;
    public Pose2d redBackstageStartPose;
    public Pose2d blueBackstageStartPose;
    public Pose2d redOnstageStartPose;
    public Pose2d blueOnstageStartPose;

    // Define trajectories
    public Trajectory RedBackstageParking;
    public Trajectory BlueBackstageParking;
    public Trajectory RedOnstageParking;
    public Trajectory BlueOnstageParking;

    public Trajectories(HardwareMap hardwareMap) {
        drive = new SampleMecanumDrive(hardwareMap);

        // Define starting poses
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
    }
}
