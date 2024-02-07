package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Hardware {
    public DcMotor leftFront;
    public DcMotor leftRear;
    public DcMotor rightRear;
    public DcMotor rightFront;
    public DcMotor leftSlide;
    public DcMotor rightSlide;
    public DcMotor verticalActuator;
    public DcMotor intake;
    public Servo door;
    public Servo boxRotation;
    public CRServo plane;
    public LynxModule lynx;
    public Rev2mDistanceSensor distance;
    public RevColorSensorV3 topSlot;

    public RevColorSensorV3 bottomSlot;

    public IMU imu;

    RevBlinkinLedDriver blinkinLedDriver;
    RevBlinkinLedDriver.BlinkinPattern black;

    public Hardware(HardwareMap hardwareMap) {
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        black = RevBlinkinLedDriver.BlinkinPattern.BLACK;
        blinkinLedDriver.setPattern(black);

        leftFront = hardwareMap.get(DcMotor.class, "2");
        leftRear = hardwareMap.get(DcMotor.class, "0");
        rightRear = hardwareMap.get(DcMotor.class, "1");
        rightFront = hardwareMap.get(DcMotor.class, "3");

        leftSlide = hardwareMap.get(DcMotor.class, "E0");
        rightSlide = hardwareMap.get(DcMotor.class, "E1");

        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        verticalActuator = hardwareMap.get(DcMotor.class, "E2");
        intake = hardwareMap.get(DcMotor.class, "E3");

        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);

        door = hardwareMap.get(Servo.class, "ES1");
        boxRotation = hardwareMap.get(Servo.class, "ES0");
        plane = hardwareMap.get(CRServo.class, "S0");

        // distance = hardwareMap.get(DistanceSensor.class, "distance");

        bottomSlot = hardwareMap.get(RevColorSensorV3.class, "I2C3");
        // distance = hardwareMap.get(Rev2mDistanceSensor.class, "I2C1");
        topSlot = hardwareMap.get(RevColorSensorV3.class, "I2C2");
    }

    public void setLEDs(RevBlinkinLedDriver.BlinkinPattern pattern){
        blinkinLedDriver.setPattern(pattern);
    }

    public Boolean pixelCheck() {
        if (topSlot.getDistance(DistanceUnit.CM) < 2 && bottomSlot.getDistance(DistanceUnit.CM) < 2) {
            return true;
        } else {
            return false;
        }
    }

    public void setRaw(double Lr, double Lf, double Rr, double Rf) {
        leftRear.setPower(Lr);
        leftFront.setPower(Lf);
        rightFront.setPower(Rf);
        rightRear.setPower(Rr);
    }

    // public double getDistance() {
    // return distance.getDistance(DistanceUnit.MM);
    // }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void strafeLeft(double power, double seconds) {
        rightFront.setPower(power);
        rightRear.setPower(power * -1);
        leftFront.setPower(power * -1);
        leftRear.setPower(power);
        sleep((long) (seconds * 100));
    }

    public void strafeRight(double power, double seconds) {
        rightFront.setPower(power * -1);
        rightRear.setPower(power);
        leftFront.setPower(power);
        leftRear.setPower(power * -1);
        sleep((long) (seconds * 100));
    }

    public void backward(double power, double seconds) {
        rightFront.setPower(power * -1);
        rightRear.setPower(power * -1);
        leftFront.setPower(power * -1);
        leftRear.setPower(power * -1);
        sleep((long) (seconds * 100));
    }

    public void forward(double power, double seconds) {
        rightFront.setPower(power);
        rightRear.setPower(power);
        leftFront.setPower(power);
        leftRear.setPower(power);
        sleep((long) (seconds * 100));
    }

    public void spinLeft(double power, double seconds) {
        rightFront.setPower(power * -1);
        rightRear.setPower(power * -1);
        leftFront.setPower(power);
        leftRear.setPower(power);
        sleep((long) (seconds * 100));
    }

    public void spinRight(double power, double seconds) {
        rightFront.setPower(power);
        rightRear.setPower(power);
        leftFront.setPower(power * -1);
        leftRear.setPower(power * -1);
        sleep((long) (seconds * 100));
    }

    public void forward() {
        forward(1, 0);
    }

    public void strafeLeft() {
        strafeLeft(1, 0);
    }

    public void strafeRight() {
        strafeRight(1, 0);
    }

    public void backward() {
        backward(1, 0);
    }

    public void spinLeft() {
        spinLeft(1, 0);
    }

    public void spinRight() {
        spinRight(1, 0);
    }
}
