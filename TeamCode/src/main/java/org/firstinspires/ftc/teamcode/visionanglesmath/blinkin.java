package org.firstinspires.ftc.teamcode.visionanglesmath;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class blinkin extends LinearOpMode {
    public RevBlinkinLedDriver signal;
    public void runOpMode() {
        signal = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        signal.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_LAVA_PALETTE);
        sleep(10000);
    }
}
