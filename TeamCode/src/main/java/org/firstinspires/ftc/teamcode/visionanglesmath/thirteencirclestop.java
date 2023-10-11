package org.firstinspires.ftc.teamcode.visionanglesmath;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@Autonomous(name="yawTracker")
public class thirteencirclestop extends LinearOpMode {
    Hardware hardware;

    Double yaw;
    @Override
    public void runOpMode() {
        hardware = new Hardware(hardwareMap);
        hardware.imu.resetYaw();
        yaw = hardware.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        waitForStart();
        while (opModeIsActive()) {
            yaw = hardware.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
            telemetry.addLine(yaw.toString());
            telemetry.update();
            sleep(10);
        }
    }
}
