package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-62, 12, Math.toRadians(0)))
//                                .strafeRight(50) // Top Right

//                                .strafeLeft(50) // Top Left

//                                .forward(27) // Bottom Right
//                                .turn(Math.toRadians(-90))
//                                .forward(27*2.85)
//                                .turn(Math.toRadians(-90))
//                                .forward(27)

//                                .forward(27) // Bottom Left
//                                .turn(Math.toRadians(90))
//                                .forward(27*2.85)
//                                .turn(Math.toRadians(90))
//                                .forward(27)


//                                .splineTo((new Vector2d(-45,-35)), 0)
//                                .splineTo((new Vector2d(-35,0)),(Math.toRadians(90)))
//                                .splineTo((new Vector2d(-35, 30)), Math.toRadians(90))
//                                .splineTo((new Vector2d(-56,56)), Math.toRadians(90))

                                .splineTo((new Vector2d(-35,30)), Math.toRadians(90))
                                .splineTo((new Vector2d(-56,56)), Math.toRadians(90))
                                .build()
                );

        Image img = null;
        try { img = ImageIO.read(new File("C:/Users/space/code/robotics/2023-2024-Centerstage/701-Gonk-Squad-2023-2024-Centerstage/MeepMeepTesting/src/main/java/com/example/meepmeeptesting/CENTERSTAGE-FIELD.png")); }
        catch (IOException e) {}

        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}