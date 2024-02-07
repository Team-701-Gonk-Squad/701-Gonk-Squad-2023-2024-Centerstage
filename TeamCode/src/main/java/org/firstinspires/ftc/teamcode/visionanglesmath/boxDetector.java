package org.firstinspires.ftc.teamcode.visionanglesmath;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.Hardware;

@Autonomous
public class boxDetector extends LinearOpMode {
    Hardware hardware;
    public double numPixels;
    @Override
    public void runOpMode() {
        hardware = new Hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
//            telemetry.addData("upSlot dist", hardware.upSlot.getDistance(DistanceUnit.METER)*100);
//            telemetry.addData("downSlot dist", hardware.downSlot.getDistance(DistanceUnit.METER)*100);
//            if ((hardware.upSlot.getDistance(DistanceUnit.METER)*100) < 3) {
//                telemetry.addData("UpSlot", "White");
//            } else {
//                telemetry.addData("UpSlot", "None");
//            }
//            if ((hardware.downSlot.getDistance(DistanceUnit.METER)*100) < 3) {
//                telemetry.addData("DownSlot", "White");
//            } else {
//                telemetry.addData("DownSlot", "None");
//            }
//            telemetry.addData("Red", (Math.round(hardware.upSlot.red()/hardware.upSlot.getDistance(DistanceUnit.METER)*100)/1000));
//            telemetry.addData("G", (Math.round(hardware.upSlot.green()/hardware.upSlot.getDistance(DistanceUnit.METER)*100)/1000));
//            telemetry.addData("B", (Math.round(hardware.upSlot.blue()/hardware.upSlot.getDistance(DistanceUnit.METER)*100)/1000));
//

//            if ((hardware.upSlot.getDistance(DistanceUnit.METER)*100) < 3) {
//
//                if (((hardware.upSlot.blue()) > (hardware.upSlot.green())) && ((hardware.upSlot.blue()) > (hardware.upSlot.red()))) {
//                    telemetry.addData("Color", "White/Purple");
//                } else if (((hardware.upSlot.red()) < (hardware.upSlot.blue())) && ((hardware.upSlot.red()) < (hardware.upSlot.green()))) {
//                    telemetry.addData("Color", "Green");
//                } else if (((hardware.upSlot.red()) > (hardware.upSlot.blue())) && ((hardware.upSlot.red()) < (hardware.upSlot.green()))) {
//                    telemetry.addData("Color", "Yellow");
//                } else {
//                    telemetry.addData("Color", "Unknown");
//                }
//
//            } else {
//                telemetry.addData("Color", "None");
//            }

            numPixels = 0;

            telemetry.addData("TopSlot: ", hardware.topSlot.getDistance(DistanceUnit.CM));
            telemetry.addData("BottomSlot: ", hardware.bottomSlot.getDistance(DistanceUnit.CM));

            if (hardware.topSlot.getDistance(DistanceUnit.CM) <= 3.5) {
                numPixels += 1;
            }

            if (hardware.bottomSlot.getDistance(DistanceUnit.CM) <= 3.5) {
                numPixels += 1;
            }


            if (numPixels == 1) {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
            } else if (numPixels == 2) {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.RED);
            } else {
                hardware.setLEDs(RevBlinkinLedDriver.BlinkinPattern.BLACK);
            }


            sleep(100);
            telemetry.update();
        }
    }
}
