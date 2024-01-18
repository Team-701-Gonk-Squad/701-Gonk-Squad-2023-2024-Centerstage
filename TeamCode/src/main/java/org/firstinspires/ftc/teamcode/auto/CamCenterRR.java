/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.visionanglesmath.Ploop;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@TeleOp
public class CamCenterRR extends LinearOpMode
{
    OpenCvCamera camera;
    AprilTagDetectionPipeling aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    int numFramesWithoutDetection = 0;

    final float DECIMATION_HIGH = 3;
    final float DECIMATION_LOW = 2;
    final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

    public Hardware hardware;
    public Ploop ploop;
    public Boolean centered;
    @Override
    public void runOpMode()
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

//        TrajectorySequence tag = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
//                .splineTo(new Vector2d(-5, 5), Math.toRadians(180))
//                .splineTo(new Vector2d(-20, 5), Math.toRadians(180))
//                .build();
        TrajectorySequence tag = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .splineTo(new Vector2d(-5, 5), Math.toRadians(180))
                .splineTo(new Vector2d(-45, 5), Math.toRadians(180))
                .build();

        TrajectorySequence youreit = drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                .splineTo(new Vector2d(-5, 5), Math.toRadians(0))
                .splineTo(new Vector2d(-20, 5), Math.toRadians(0))
                .build();

        hardware = new Hardware(hardwareMap);
        Ploop looper = new Ploop(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeling(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPSIDE_DOWN);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        waitForStart();

        telemetry.setMsTransmissionInterval(50);

        while (opModeIsActive())
        {
            // Calling getDetectionsUpdate() will only return an object if there was a new frame
            // processed since the last time we called it. Otherwise, it will return null. This
            // enables us to only run logic when there has been a new frame, as opposed to the
            // getLatestDetections() method which will always return an object.
            ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getDetectionsUpdate();

            // If there's been a new frame...
            if(detections != null)
            {
                telemetry.addData("FPS", camera.getFps());
                telemetry.addData("Overhead ms", camera.getOverheadTimeMs());
                telemetry.addData("Pipeline ms", camera.getPipelineTimeMs());

                // If we don't see any tags
                if(detections.size() == 0)
                {
                    hardware.forward(0,0.001);
                    numFramesWithoutDetection++;

                    // If we haven't seen a tag for a few frames, lower the decimation
                    // so we can hopefully pick one up if we're e.g. far back
                    if(numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION)
                    {
                        aprilTagDetectionPipeline.setDecimation(DECIMATION_LOW);
                    }
                }
                // We do see tags!
                else
                {
                    numFramesWithoutDetection = 0;

                    // If the target is within 1 meter, turn on high decimation to
                    // increase the frame rate
                    if(detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS)
                    {
                        aprilTagDetectionPipeline.setDecimation(DECIMATION_LOW);
                    }

                    for(AprilTagDetection detection : detections)
                    {
                        Orientation rot = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);

                        Float yaw = rot.firstAngle; // Degrees
                        Float pitch = rot.secondAngle; // Degrees
                        Float roll = rot.thirdAngle; // Degrees

                        double x = detection.pose.x*FEET_PER_METER; // Ft
                        double y = detection.pose.y*FEET_PER_METER; //Ft
                        double z = detection.pose.z*FEET_PER_METER; // Ft

                        centered = looper.pLoop(yaw, x, z, telemetry);

                        if (centered) {
                            drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));
                            drive.followTrajectorySequence(tag);
                            telemetry.addLine("centered");
                        } else {
                            telemetry.addLine("not centered");
                        }

                        // Correct x position and angle based on yaw

//                        if (yaw < -3) {
//                            hardware.spinRight(-yaw*0.015+0.02, 0.1);
//                            telemetry.addLine("spinright");
//                        }
//                        else if (yaw > 3) {
//                            hardware.spinLeft(yaw*0.015+0.02, 0.1);
//                            telemetry.addLine("spinright");
//                        }
//                        else {
//                            telemetry.addLine("spin complete");
//                        }
//                            if (x < -0.1) {
//                                hardware.strafeLeft((-x*0.5)+0.03, 0.1);
//                                telemetry.addLine("strafeleft");
//                            }
//                            else if (x > 0.1) {
//                                hardware.strafeRight((x*0.5)+0.03, 0.1);
//                                telemetry.addLine("straferight");
//                            }
//                            else {
//                                telemetry.addLine("strafe complete");
//                                if (z < 1.75) {
//                                    hardware.backward(0.5*(Math.abs(z-2)), 0.1);
//                                    telemetry.addLine("backward");
//                                }
//                                else if (z > 2.25) {
//                                    hardware.forward(0.5*(Math.abs(z-2)), 0.1);
//                                    telemetry.addLine("forward");
//                                }
//                                else {
//                                    telemetry.addLine("distanced");
//                                    telemetry.addLine("stop");
//                                }
//                            }
//                        }


                        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
                        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
                        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
                        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
                        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rot.firstAngle));
                        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rot.secondAngle));
                        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rot.thirdAngle));
                    }
                }

                telemetry.update();
            }

//            if(detections != null) {
//                telemetry.addData("Tags seen: ", detections.size());
//                gamepad1.rumble(500);
//                for(AprilTagDetection detection : detections) {
//                    if (detection.id == 8) {
//                        telemetry.addData("Tag ID :", "8");
//                        telemetry.addData("Tag X: ", detection.pose.x);
//                        Orientation rot = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
//                        telemetry.addData("Tag yaw: ", rot.firstAngle);
//                    }
//                }
//            }

            telemetry.update();

            sleep(30);
        }
    }
}