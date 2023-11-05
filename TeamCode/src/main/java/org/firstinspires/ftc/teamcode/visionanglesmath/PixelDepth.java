package org.firstinspires.ftc.teamcode.visionanglesmath;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@TeleOp
public class PixelDepth extends OpMode {
    Hardware hardware;

    @Override
    public void init() {
        hardware = new Hardware(hardwareMap);
    }

    @Override
    public void loop() {
//        telemetry.addLine("Distance: " + hardware.getDistance());
        telemetry.update();
    }
}
