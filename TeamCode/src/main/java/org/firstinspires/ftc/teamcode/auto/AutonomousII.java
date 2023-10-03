package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@Autonomous
public class AutonomousII extends LinearOpMode {
    public Hardware hardware;

    @Override
    public void runOpMode() {
        hardware = new Hardware(hardwareMap);
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
//                .splineTo((new Vector2d(-35,30)), Math.toRadians(90))
//                .splineTo((new Vector2d(-56,56)), Math.toRadians(90))
//                .build();
//
//        waitForStart();
//
//        drive.followTrajectory(myTrajectory);
    }

    public void strafeLeft(double power, int seconds) {
        hardware.rightFront.setPower(power);
        hardware.rightRear.setPower(power*-1);
        hardware.leftFront.setPower(power*-1);
        hardware.leftRear.setPower(power);
        sleep(seconds*100);
    }

    public void strafeRight(double power, int seconds) {
        hardware.rightFront.setPower(power*-1);
        hardware.rightRear.setPower(power);
        hardware.leftFront.setPower(power);
        hardware.leftRear.setPower(power*-1);
        sleep(seconds*100);
    }

    public void backward(double power, int seconds) {
        hardware.rightFront.setPower(power*-1);
        hardware.rightRear.setPower(power*-1);
        hardware.leftFront.setPower(power*-1);
        hardware.leftRear.setPower(power*-1);
        sleep(seconds*100);
    }

    public void forward(double power, int seconds) {
        hardware.rightFront.setPower(power);
        hardware.rightRear.setPower(power);
        hardware.leftFront.setPower(power);
        hardware.leftRear.setPower(power);
        sleep(seconds*100);
    }

    public void spinLeft(double power, int seconds) {
        hardware.rightFront.setPower(power*-1);
        hardware.rightRear.setPower(power*-1);
        hardware.leftFront.setPower(power);
        hardware.leftRear.setPower(power);
        sleep(seconds*100);
    }

    public void spinRight(double power, int seconds) {
        hardware.rightFront.setPower(power);
        hardware.rightRear.setPower(power);
        hardware.leftFront.setPower(power*-1);
        hardware.leftRear.setPower(power*-1);
        sleep(seconds*100);
    }

    public void forward() {
        forward(1,0);
    }

    public void strafeLeft() {
        strafeLeft(1,0);
    }

    public void strafeRight() {
        strafeRight(1,0);
    }

    public void backward() {
        backward(1,0);
    }

    public void spinLeft() {
        spinLeft(1,0);
    }

    public void spinRight() {
        spinRight(1,0);
    }
}
