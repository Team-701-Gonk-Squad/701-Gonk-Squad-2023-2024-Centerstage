package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Trajectories;

@Autonomous(name = "ParkTesting", group = "Ronan's OpModes")
public class ParkTesting extends LinearOpMode {
    public ElapsedTime runtime = new ElapsedTime();
    public SampleMecanumDrive drive;
    public Trajectories trajectories;

    @Override
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        trajectories = new Trajectories(hardwareMap);

        waitForStart();

        runtime.reset();

        if (isStopRequested()) return;

        //start pose estimate
        drive.setPoseEstimate(trajectories.redBackstageStartPose);
        drive.followTrajectory(trajectories.RedBackstageParking);

        sleep(20000);

        drive.setPoseEstimate(trajectories.blueBackstageStartPose);
        drive.followTrajectory(trajectories.BlueBackstageParking);

        sleep(20000);

        drive.setPoseEstimate(trajectories.redOnstageStartPose);
        drive.followTrajectory(trajectories.RedOnstageParking);

        sleep(20000);

        drive.setPoseEstimate(trajectories.blueOnstageStartPose);
        drive.followTrajectory(trajectories.BlueOnstageParking);

            // Do other actions here (e.g., shoot, collect, etc.)

            // Stop the robot
            //drive.setMotorPowers(0, 0, 0, 0);
    }
}
