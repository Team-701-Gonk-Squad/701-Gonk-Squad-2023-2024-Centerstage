package org.firstinspires.ftc.teamcode.visionanglesmath;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class blinkin extends LinearOpMode {
    Hardware hardware;

    public void runOpMode() {

        hardware = new Hardware(hardwareMap);

        waitForStart();

        sleep(1000);
        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_LAVA_PALETTE);
        sleep(1000);
        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_FOREST_PALETTE);
        sleep(1000);
        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_OCEAN_PALETTE);
        sleep(1000);
        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE);
        sleep(1000);
        hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_RAINBOW_PALETTE);
    }
}
