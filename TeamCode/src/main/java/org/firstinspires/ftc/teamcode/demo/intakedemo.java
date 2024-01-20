package org.firstinspires.ftc.teamcode.demo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@Autonomous
public class intakedemo extends LinearOpMode {
    Hardware hardware;
    @Override
    public void runOpMode() {

        hardware = new Hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            hardware.intake.setPower(1);

            sleep(10);

            if (hardware.pixelCheck()) {
                hardware.intake.setPower(0);
                hardware.door.setPosition(0.25);
                sleep(5000);
                terminateOpModeNow();
            }

            telemetry.addData("top", hardware.topSlot.getDistance(DistanceUnit.CM));
            telemetry.addData("bottom", hardware.bottomSlot.getDistance(DistanceUnit.CM));
            telemetry.update();
        }

    }
}
