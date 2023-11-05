package org.firstinspires.ftc.teamcode.visionanglesmath;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class touchpadTesting extends LinearOpMode {

    @Override
    public void runOpMode() {

        waitForStart();

        while (opModeIsActive()) {
            boolean finger = false;

            if (gamepad1.touchpad_finger_1) {
                finger = true;
                telemetry.addLine(String.format("Finger 1: x=%5.2f y=%5.2f\n", gamepad1.touchpad_finger_1_x, gamepad1.touchpad_finger_1_y));
            }

            if (gamepad1.touchpad_finger_2) {
                finger = true;
                telemetry.addLine(String.format("Finger 2: x=%5.2f y=%5.2f\n", gamepad1.touchpad_finger_2_x, gamepad1.touchpad_finger_2_y));
            }

            if (!finger) {
                telemetry.addLine("No Fingers");
            }
        }
    }
}
