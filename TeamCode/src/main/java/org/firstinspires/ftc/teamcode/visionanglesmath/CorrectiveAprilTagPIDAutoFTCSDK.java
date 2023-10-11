//package org.firstinspires.ftc.teamcode.visionanglesmath;
//
//import android.util.Size;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
//import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
//
//import java.util.List;
//
//@Autonomous
//public class CorrectiveAprilTagPIDAutoFTCSDK extends OpMode {
//    AprilTagProcessor myAprilTagProcessor;
//    VisionPortal myVisionPortal;
//    AprilTagDetection myAprilTagDetection;
//    List<AprilTagDetection> myAprilTagDetections;
//    int myAprilTagIdCode;
//
//    @Override
//    public void init() {
//        myAprilTagProcessor = new AprilTagProcessor.Builder()
//                .setDrawTagID(true)
//                .setDrawTagOutline(true)
//                .setDrawAxes(true)
//                .setDrawCubeProjection(true)
//                .build();
//        myVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), myAprilTagProcessor);
//    }
//
//    @Override
//    public void loop() {
//        myAprilTagDetections = myAprilTagProcessor.getDetections();
//
//        for (myAprilTagDetection : myAprilTagDetections) {
//            if (myAprilTagDetection.metadata != null) {
//                myAprilTagIdCode = myAprilTagDetection.id;
//                telemetry.addLine(String.valueOf(myAprilTagIdCode));
//            }
//        }
//    }
//}
