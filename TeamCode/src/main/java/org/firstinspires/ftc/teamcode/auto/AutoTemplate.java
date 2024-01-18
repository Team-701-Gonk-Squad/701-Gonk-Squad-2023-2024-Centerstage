package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@Disabled
public class AutoTemplate extends LinearOpMode {
    Hardware hardware;
    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        hardware = new Hardware(hardwareMap);
        drive = new SampleMecanumDrive(hardwareMap);

    }
}
