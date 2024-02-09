package org.firstinspires.ftc.teamcode.visionanglesmath;

import static android.os.SystemClock.sleep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;



// BATTERY AT MAX VOLTAGE



@Autonomous
public class BlueOnstage extends OpMode {

    OpenCvWebcam webcam1 = null;
    String position = "none";
    String startposition = "none";

    @Override
    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                hardwareMap.appContext.getPackageName());
        webcam1 = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"),
                cameraMonitorViewId);

        webcam1.setPipeline(new examplePipeline());

        webcam1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            public void onOpened() {
                webcam1.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);
            }

            public void onError(int errorCode) {
            }
        });
    }

    @Override
    public void loop() {
        Hardware hardware = new Hardware(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence start_centerstriprelease = drive.trajectorySequenceBuilder(new Pose2d(-36, 60, Math.toRadians(270)))
                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
                .lineToSplineHeading(new Pose2d(-39.3, 33.5, Math.toRadians(270)))
                .build();
        TrajectorySequence centerstriprelease_pos2 = drive.trajectorySequenceBuilder(start_centerstriprelease.end())
//                 TODO: Add beginning of path to get around trusses
                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
                .lineToSplineHeading(new Pose2d(-40, 40, Math.toRadians(270)))
                .splineToConstantHeading(new Vector2d(-55, 35), Math.toRadians(270))
                .splineTo(new Vector2d(-45, 7), Math.toRadians(0))
                .setVelConstraint(new MecanumVelocityConstraint(27, 19.2))
                .resetVelConstraint()
                .lineToSplineHeading(new Pose2d(54, 7, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(65, 20))
                .build();
        TrajectorySequence pos2_boardlineup = drive.trajectorySequenceBuilder(new Pose2d(63, 14.5, Math.toRadians(180)))
                .setVelConstraint(new MecanumVelocityConstraint(25, 19.5))
                .lineToConstantHeading(new Vector2d(50, 12))
                .splineToConstantHeading(new Vector2d(45, 36), Math.toRadians(0))
                .resetVelConstraint()
                .build();
        TrajectorySequence postcenterboard_backup = drive.trajectorySequenceBuilder(pos2_boardlineup.end())
                .lineToSplineHeading(new Pose2d(30, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postcenterboard_repark = drive.trajectorySequenceBuilder(postcenterboard_backup.end())
                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
                .lineToSplineHeading(new Pose2d(44, 27, Math.toRadians(180)))
                .build();



        TrajectorySequence start_leftstriprelease = drive.trajectorySequenceBuilder(new Pose2d(-36, 60, Math.toRadians(270)))
                .lineToSplineHeading(new Pose2d(-37, 33, Math.toRadians(0)))
                .build();
        TrajectorySequence leftstriprelease_pos2 = drive.trajectorySequenceBuilder(start_leftstriprelease.end())
//                 TODO: Add beginning of path to get around trusses
                .setVelConstraint(new MecanumVelocityConstraint(28, 19.5))
                .lineToSplineHeading(new Pose2d(-44, 33.5, Math.toRadians(0)))
                .lineToSplineHeading(new Pose2d(-35, 7, Math.toRadians(0)))
                .resetVelConstraint()
                .lineToSplineHeading(new Pose2d(62, 15.5, Math.toRadians(180)))
//                .lineToConstantHeading(new Vector2d(55, 20))
//                .lineToConstantHeading(new Vector2d(70, 22))
                .build();
        TrajectorySequence pos2left_boardlineup = drive.trajectorySequenceBuilder(new Pose2d(63, 14.5, Math.toRadians(180)))
                .setVelConstraint(new MecanumVelocityConstraint(25, 19.5))
                .lineToConstantHeading(new Vector2d(50, 12))
                .splineToConstantHeading(new Vector2d(44, 43), Math.toRadians(0))
                .resetVelConstraint()
                .build();
        TrajectorySequence postleftboard_backup = drive.trajectorySequenceBuilder(pos2left_boardlineup.end())
                .lineToSplineHeading(new Pose2d(40, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postleftboard_repark = drive.trajectorySequenceBuilder(postleftboard_backup.end())
                .lineToSplineHeading(new Pose2d(44, 27, Math.toRadians(180)))
                .build();

//        TrajectorySequence start_leftstriprelease = drive.trajectorySequenceBuilder(new Pose2d(-36, 60, Math.toRadians(90)))
//                .lineToSplineHeading(new Pose2d(-36, 33.5, Math.toRadians(270)))
//                .lineToSplineHeading(new Pose2d(-58, 33, Math.toRadians(180)))
//                .lineToSplineHeading(new Pose2d(-58, 12, Math.toRadians(180)))
//                .lineToSplineHeading(new Pose2d(40, 12, Math.toRadians(180)))
//                .build();
//        TrajectorySequence leftstriprelease_leftboard = drive.trajectorySequenceBuilder()
                // TODO: Add beginning of path to get around trusses
//                .lineToSplineHeading(new Pose2d(43, 40, Math.toRadians(180)))
//                .build();
//        TrajectorySequence postleftboard_backup = drive.trajectorySequenceBuilder(leftstriprelease_leftboard.end())
//                .lineToSplineHeading(new Pose2d(41, 27, Math.toRadians(180)))
//                .build();


        TrajectorySequence start_rightstriprelease = drive.trajectorySequenceBuilder(new Pose2d(-36, 60, Math.toRadians(270)))
                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
                .lineToSplineHeading(new Pose2d(-35, 36, Math.toRadians(270)))
                .turn(Math.toRadians(-90))
                .build();
        TrajectorySequence rightstriprelease_pos2 = drive.trajectorySequenceBuilder(start_rightstriprelease.end())
//                 TODO: Add beginning of path to get around trusses
                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
                .lineToSplineHeading(new Pose2d(-36, 7, Math.toRadians(180)))
                .turn(Math.toRadians(180))
                .resetVelConstraint()
                .lineToSplineHeading(new Pose2d(54, 7, Math.toRadians(180)))
                .lineToConstantHeading(new Vector2d(65, 20))
                .build();
        TrajectorySequence pos2right_boardlineup = drive.trajectorySequenceBuilder(new Pose2d(63, 14.5, Math.toRadians(180)))
                .setVelConstraint(new MecanumVelocityConstraint(25, 19.5))
                .lineToConstantHeading(new Vector2d(50, 12))
                .splineToConstantHeading(new Vector2d(45, 30), Math.toRadians(0))
                .resetVelConstraint()
                .build();
        TrajectorySequence postrightboard_backup = drive.trajectorySequenceBuilder(pos2_boardlineup.end())
                .lineToSplineHeading(new Pose2d(36, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postrightboard_repark = drive.trajectorySequenceBuilder(postcenterboard_backup.end())
                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
                .lineToSplineHeading(new Pose2d(44, 27, Math.toRadians(180)))
                .build();

        if (position == "center") {
            drive.setPoseEstimate(new Pose2d(-36, 60, Math.toRadians(270))); // TODO: Set correct start
            drive.followTrajectorySequence(start_centerstriprelease); // TODO: Write this trajectory
            hardware.intake.setPower(0.6);
            sleep(3000);
            hardware.intake.setPower(0);
            drive.followTrajectorySequence(centerstriprelease_pos2); // TODO: Write this trajectory
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            drive.setPoseEstimate(new Pose2d(63, 14.5, Math.toRadians(180)));
            drive.followTrajectorySequence(pos2_boardlineup);
            hardware.leftSlide.setPower(0.57);
            hardware.rightSlide.setPower(0.57);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(1200);
            hardware.door.setPosition(0);
            hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
            sleep(1000);
            drive.followTrajectorySequence(postcenterboard_backup);
            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            hardware.rightSlide.setPower(-0.25);
            drive.followTrajectorySequence(postcenterboard_repark);
        } else if (position == "left") {
            drive.setPoseEstimate(new Pose2d(-36, 60, Math.toRadians(270))); // TODO: Set correct start
            drive.followTrajectorySequence(start_leftstriprelease); // TODO: Write this trajectory
            hardware.intake.setPower(0.8);
            sleep(3000);
            hardware.intake.setPower(0);
            drive.followTrajectorySequence(leftstriprelease_pos2); // TODO: Write this trajectory
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            drive.setPoseEstimate(new Pose2d(63, 14.5, Math.toRadians(180)));
            drive.followTrajectorySequence(pos2left_boardlineup);
            hardware.leftSlide.setPower(0.57);
            hardware.rightSlide.setPower(0.57);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(1200);
            hardware.door.setPosition(0);
            hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
            sleep(1000);
            drive.followTrajectorySequence(postleftboard_backup);
            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            hardware.rightSlide.setPower(-0.25);
            drive.followTrajectorySequence(postleftboard_repark);
        } else if (position == "right") {
            drive.setPoseEstimate(new Pose2d(-36, 60, Math.toRadians(270))); // TODO: Set correct start
            drive.followTrajectorySequence(start_rightstriprelease); // TODO: Write this trajectory
            hardware.intake.setPower(0.7);
            sleep(3000);
            hardware.intake.setPower(0);
            drive.followTrajectorySequence(rightstriprelease_pos2); // TODO: Write this trajectory
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(0.3);
            hardware.rightRear.setPower(0.3);
            sleep(350);
            hardware.rightFront.setPower(-0.3);
            hardware.leftRear.setPower(-0.3);
            hardware.leftFront.setPower(-0.3);
            hardware.rightRear.setPower(-0.3);
            sleep(350);
            drive.setPoseEstimate(new Pose2d(63, 14.5, Math.toRadians(180)));
            drive.followTrajectorySequence(pos2right_boardlineup);
            hardware.leftSlide.setPower(0.80);
            hardware.rightSlide.setPower(0.80);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(1200);
            hardware.door.setPosition(0);
            hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
            sleep(1000);
            drive.followTrajectorySequence(postrightboard_backup);
            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            hardware.rightSlide.setPower(-0.25);
            drive.followTrajectorySequence(postrightboard_repark);
        }
        requestOpModeStop();
    }

    class examplePipeline extends OpenCvPipeline {
        Mat YCbCr = new Mat();
        Mat leftCrop;
        Mat centerCrop;
        Mat rightCrop;
        double leftavgfin;
        double centeravgfin;
        double rightavgfin;
        Mat outPut = new Mat();
        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
            telemetry.addLine("pipeline running :)");

            Rect centerRect = new Rect(240, 90, 100, 90);
            Rect leftRect = new Rect(1, 80, 80, 100);
            Rect rightRect = new Rect(540, 90, 99, 100);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            Imgproc.rectangle(outPut, centerRect, rectColor, 2);
            Imgproc.rectangle(outPut, rightRect, rectColor, 2);

            leftCrop = YCbCr.submat(leftRect);
            centerCrop = YCbCr.submat(centerRect);
            rightCrop = YCbCr.submat(rightRect);

            Core.extractChannel(leftCrop, leftCrop, 2);
            Core.extractChannel(centerCrop, centerCrop, 2);
            Core.extractChannel(rightCrop, rightCrop, 2);

            Scalar leftavg = Core.mean(leftCrop);
            Scalar centeravg = Core.mean(centerCrop);
            Scalar rightavg = Core.mean(rightCrop);

            leftavgfin = leftavg.val[0];
            centeravgfin = centeravg.val[0];
            rightavgfin = rightavg.val[0];

            if (leftavgfin > rightavgfin && leftavgfin > centeravgfin) {
                telemetry.addLine("Left");
                position = "left";
            }
            if (rightavgfin > centeravgfin && rightavgfin > leftavgfin) {
                telemetry.addLine("Right");
                position = "right";
            }
            if (centeravgfin > rightavgfin && centeravgfin > leftavgfin) {
                telemetry.addLine("Center :)");
                position = "center";
            }

            return (outPut);
        }
    }
}
