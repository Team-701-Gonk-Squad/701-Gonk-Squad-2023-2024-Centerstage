package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp
@Disabled
public class chassis extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("weeboo : ", "weeboo");
        telemetry.update();
    }
}
