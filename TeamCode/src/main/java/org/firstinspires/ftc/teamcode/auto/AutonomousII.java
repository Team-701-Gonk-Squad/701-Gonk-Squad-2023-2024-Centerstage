package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Hardware;

public class AutonomousII extends LinearOpMode {
    public Hardware hardware;

    public void runOpMode() {
        hardware = new Hardware(hardwareMap);
        waitForStart();

//        Trajectory myTrajectory = hardware.trajectoryBuilder(new Pose2d())

    }
}
