package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;

import javax.imageio.ImageIO;

import kotlin.time.MeasureTimeKt;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))  // start coordinates: (-35, +-60)   (12, +-60)  |  angles:90 or 270

                                //TODO: NEW TRAJECTORY SEQUENCE    --    Red OnstageRight   --   StartPose (-36, -60, 90 deg)
//                                .splineToSplineHeading(new Pose2d(-34, -32), Math.toRadians(90))
//                                .waitSeconds(3)
//                                .lineToConstantHeading(new Vector2d(-37, -10))
//                                .lineToSplineHeading(new Pose2d(30, -10, Math.toRadians(180)))
//                                .splineToConstantHeading(new Vector2d(30, -40), Math.toRadians(180))
//                                .lineToConstantHeading(new Vector2d(40, -40))
//                                .addDisplacementMarker(20,() -> {
//                                    //outtake purple pixel
//                                })
//                                .addDisplacementMarker(100, () -> {
//                                    //get arm ready
//                                })
//                                .addDisplacementMarker(165, () -> {
//                                    //outtake yellow pixel
//                                })

                                //TODO: NEW TRAJECTORY SEQUENCE    --    Red OnstageCenter   --   StartPose (-36, -60, 90 deg)
//                                .lineToConstantHeading(new Vector2d(-34, -36))
//                                .waitSeconds(3)
//                                .lineToSplineHeading(new Pose2d(-55,-40, Math.toRadians(90)))
//                                .splineToLinearHeading(new Pose2d(-37, -10), Math.toRadians(0))
//                                .lineToSplineHeading(new Pose2d(30, -10, Math.toRadians(180)))
//                                .splineToConstantHeading(new Vector2d(30, -40), Math.toRadians(180))
//                                .lineToConstantHeading(new Vector2d(40, -40))
//                                .addDisplacementMarker(200, () -> {
//                                    //outtake yellow
//                                })
//                                .addDisplacementMarker(150, () -> {
//                                    //move le arm
//                                })
//                                .addDisplacementMarker(20,() -> {
//                                    //outtake purple
//                                })

                                //TODO: NEW TRAJECTORY SEQUENCE    --    Red OnstageLeft   --   StartPose (-36, -60, 90 deg)
//                                .lineToLinearHeading(new Pose2d(-34, -32, Math.toRadians(180)))
//                                .waitSeconds(3)
//                                .lineToLinearHeading(new Pose2d(-37, -10, Math.toRadians(0)))
//                                .lineToSplineHeading(new Pose2d(30, -10, Math.toRadians(180)))
//                                .splineToConstantHeading(new Vector2d(30, -40), Math.toRadians(180))
//                                .lineToConstantHeading(new Vector2d(40, -40))
//                                .addDisplacementMarker(20,() -> {
//                                    //outtake purple pixel
//                                })
//                                .addDisplacementMarker(100, () -> {
//                                    //get arm ready
//                                })
//                                .addDisplacementMarker(165, () -> {
//                                    //outtake yellow pixel
//                                })



                                //TODO: Base method with just splineTo
//                                .splineTo(new Vector2d(-35, -46), Math.toRadians(90))
//                                .splineTo(new Vector2d(0, -35), Math.toRadians(0))
//                                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
//                                .splineTo(new Vector2d(45, -54), Math.toRadians(270))
//                                .splineTo(new Vector2d(60, -55), Math.toRadians(0))

                                //TODO: Red Side non-backstage side parking base
//                                .splineTo(new Vector2d(-35, -46), Math.toRadians(90))
//                                .splineTo(new Vector2d(0, -35), Math.toRadians(0))
//                                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(45, -59), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
//                                .build()

                                //TODO: Blue side non-backstage side parking base
//                                .splineTo(new Vector2d(-35, 46), Math.toRadians(270))
//                                .splineTo(new Vector2d(0, 35), Math.toRadians(0))
//                                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(45, 59), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(60, 60), Math.toRadians(0))
//                                .build()

                                //TODO: Blue side backstage side parking base
//                                .splineTo(new Vector2d(12, 46), Math.toRadians(270))
//                                .splineTo(new Vector2d(18, 35), Math.toRadians(0))
//                                .splineTo(new Vector2d(40, 35), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(45, 55), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(60, 55), Math.toRadians(0))
//                                .build()
                                //TODO: Red side backstage side parking base
//                                .splineTo(new Vector2d(12, -46), Math.toRadians(90))
//                                .splineTo(new Vector2d(18, -35), Math.toRadians(0))
//                                .splineTo(new Vector2d(40, -35), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(45, -59), Math.toRadians(0))
//                                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
//                                .build()


//                                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
//                                .lineToSplineHeading(new Pose2d(-31.5, -33.5, Math.toRadians(90)))
//                                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
//                                .lineToSplineHeading(new Pose2d(-40, -40, Math.toRadians(90)))
//                                .splineToConstantHeading(new Vector2d(-55, -35), Math.toRadians(90))
//                                .splineTo(new Vector2d(-45, -7), Math.toRadians(0))
//                                .setVelConstraint(new MecanumVelocityConstraint(27, 19.2))
//                                .resetVelConstraint()
//                                .lineToSplineHeading(new Pose2d(54, -6.4, Math.toRadians(178)))

//                                .setVelConstraint(new MecanumVelocityConstraint(25, 19.5))
//                                .lineToConstantHeading(new Vector2d(50, 12))
//                                .splineToConstantHeading(new Vector2d(44, 36), Math.toRadians(0))
//                                .resetVelConstraint()


//                                .setVelConstraint(new MecanumVelocityConstraint(25, 19.5))
//                                .lineToConstantHeading(new Vector2d(50, 12))
//                                .splineToConstantHeading(new Vector2d(45, 28), Math.toRadians(0))
//                                .resetVelConstraint()
//
//                                .lineToSplineHeading(new Pose2d(36, 27, Math.toRadians(180)))
//
//                                .setVelConstraint(new MecanumVelocityConstraint(30, 19.5))
//                                .lineToSplineHeading(new Pose2d(44, 27, Math.toRadians(180)))




//                                .lineToSplineHeading(new Pose2d(36, 9, Math.toRadians(0)))
//                                .splineTo(new Vector2d(44.5, 35), Math.toRadians(0))
//                                .lineToConstantHeading(new Vector2d(36, 40))
//                                .lineToConstantHeading(new Vector2d(60, 60))
                                .build()
                );

//        Image img = null;
//        try { img = ImageIO.read(new File("C:/Users/space/code/robotics/2023-2024-Centerstage/701-Gonk-Squad-2023-2024-Centerstage/MeepMeepTesting/src/main/java/com/example/meepmeeptesting/CENTERSTAGE-FIELD.png")); }
//        catch (IOException e) {}

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}