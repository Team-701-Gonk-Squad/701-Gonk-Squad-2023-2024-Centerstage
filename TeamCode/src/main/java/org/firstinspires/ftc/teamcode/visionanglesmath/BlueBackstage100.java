package org.firstinspires.ftc.teamcode.visionanglesmath;

import static android.os.SystemClock.sleep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
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
public class BlueBackstage100 extends OpMode {

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
        TrajectorySequence start_centerstriprelease = drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(270)))
                .lineToSplineHeading(new Pose2d(16, 33.5, Math.toRadians(270)))
                .build();
        TrajectorySequence centerstriprelease_centerboard = drive.trajectorySequenceBuilder(start_centerstriprelease.end())
                .lineToSplineHeading(new Pose2d(43, 33, Math.toRadians(180)))
                .build();
        TrajectorySequence postcenterboard_backup = drive.trajectorySequenceBuilder(centerstriprelease_centerboard.end())
                .lineToSplineHeading(new Pose2d(30, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postcenterboard_park = drive.trajectorySequenceBuilder(postcenterboard_backup.end())
                .lineToSplineHeading(new Pose2d(40, 59.5, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(57.5, 59.5, Math.toRadians(180)))
                .build();

        TrajectorySequence start_leftstriprelease = drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(270)))
                .lineToSplineHeading(new Pose2d(34.5, 31, Math.toRadians(180)))
                .build();
        TrajectorySequence leftstriprelease_leftboard = drive.trajectorySequenceBuilder(start_leftstriprelease.end())
                .lineToSplineHeading(new Pose2d(43, 40, Math.toRadians(180)))
                .build();
        TrajectorySequence postleftboard_backup = drive.trajectorySequenceBuilder(leftstriprelease_leftboard.end())
                .lineToSplineHeading(new Pose2d(41, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postleftboard_park = drive.trajectorySequenceBuilder(postleftboard_backup.end())
                .lineToSplineHeading(new Pose2d(40, 59.5, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(57.5, 59.5, Math.toRadians(180)))
                .build();

        TrajectorySequence start_rightstriprelease = drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(270)))
                .lineToSplineHeading(new Pose2d(11, 32, Math.toRadians(180)))
                .build();
        TrajectorySequence rightstriprelease_rightboard = drive.trajectorySequenceBuilder(start_rightstriprelease.end())
                .lineToSplineHeading(new Pose2d(43, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postrightboard_backup = drive.trajectorySequenceBuilder(rightstriprelease_rightboard.end())
                .lineToSplineHeading(new Pose2d(30, 27, Math.toRadians(180)))
                .build();
        TrajectorySequence postrightboard_park = drive.trajectorySequenceBuilder(postrightboard_backup.end())
                .lineToSplineHeading(new Pose2d(40, 59.5, Math.toRadians(180)))
                .lineToSplineHeading(new Pose2d(57.5, 59.5, Math.toRadians(180)))
                .build();

        if (position == "center") {
            drive.setPoseEstimate(new Pose2d(12, 60, Math.toRadians(270)));
            drive.followTrajectorySequence(start_centerstriprelease);
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            drive.followTrajectorySequence(centerstriprelease_centerboard);
            hardware.leftSlide.setPower(0.75);
            hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(2000);
            drive.followTrajectorySequence(postcenterboard_backup);
            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            hardware.rightSlide.setPower(-0.25);
            drive.followTrajectorySequence(postcenterboard_park);
        } else if (position == "left") {
            drive.setPoseEstimate(new Pose2d(12, 60, Math.toRadians(270)));
            drive.followTrajectorySequence(start_leftstriprelease);
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            drive.followTrajectorySequence(leftstriprelease_leftboard);
            hardware.leftSlide.setPower(0.75);
            hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(2000);
            drive.followTrajectorySequence(postleftboard_backup);
            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            hardware.rightSlide.setPower(-0.25);
            drive.followTrajectorySequence(postleftboard_park);
        } else if (position == "right") {
            drive.setPoseEstimate(new Pose2d(12, 60, Math.toRadians(270)));
            drive.followTrajectorySequence(start_rightstriprelease);
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            drive.followTrajectorySequence(rightstriprelease_rightboard);
            hardware.leftSlide.setPower(0.75);
            hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(2000);
            drive.followTrajectorySequence(postrightboard_backup);
            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            hardware.rightSlide.setPower(-0.25);
            drive.followTrajectorySequence(postrightboard_park);
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
