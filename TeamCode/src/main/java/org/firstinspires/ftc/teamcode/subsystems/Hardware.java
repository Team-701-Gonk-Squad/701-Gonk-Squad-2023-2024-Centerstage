package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class Hardware {
    public DcMotor leftFront;
    public DcMotor leftRear;
    public DcMotor rightRear;
    public DcMotor rightFront;

    public IMU imu;

    public DistanceSensor distance;

    public Hardware(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "2");
        leftRear = hardwareMap.get(DcMotor.class, "0");
        rightRear = hardwareMap.get(DcMotor.class, "1");
        rightFront = hardwareMap.get(DcMotor.class, "3");

        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);

        distance = hardwareMap.get(DistanceSensor.class, "distance");
    }

    public void setRaw(double Lr, double Lf, double Rr, double Rf) {
        leftRear.setPower(Lr);
        leftFront.setPower(Lf);
        rightFront.setPower(Rf);
        rightRear.setPower(Rr);
    }

    public double getDistance() {
        return distance.getDistance(DistanceUnit.MM);
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void strafeLeft(double power, double seconds) {
        rightFront.setPower(power);
        rightRear.setPower(power*-1);
        leftFront.setPower(power*-1);
        leftRear.setPower(power);
        sleep((long)(seconds*100));
    }

    public void strafeRight(double power, double seconds) {
        rightFront.setPower(power*-1);
        rightRear.setPower(power);
        leftFront.setPower(power);
        leftRear.setPower(power*-1);
        sleep((long)(seconds*100));
    }

    public void backward(double power, double seconds) {
        rightFront.setPower(power*-1);
        rightRear.setPower(power*-1);
        leftFront.setPower(power*-1);
        leftRear.setPower(power*-1);
        sleep((long)(seconds*100));
    }

    public void forward(double power, double seconds) {
        rightFront.setPower(power);
        rightRear.setPower(power);
        leftFront.setPower(power);
        leftRear.setPower(power);
        sleep((long)(seconds*100));
    }

    public void spinLeft(double power, double seconds) {
        rightFront.setPower(power*-1);
        rightRear.setPower(power*-1);
        leftFront.setPower(power);
        leftRear.setPower(power);
        sleep((long)(seconds*100));
    }

    public void spinRight(double power, double seconds) {
        rightFront.setPower(power);
        rightRear.setPower(power);
        leftFront.setPower(power*-1);
        leftRear.setPower(power*-1);
        sleep((long)(seconds*100));
    }

    public void forward() {
        forward(1,0);
    }

    public void strafeLeft() {
        strafeLeft(1,0);
    }

    public void strafeRight() {
        strafeRight(1,0);
    }

    public void backward() {
        backward(1,0);
    }

    public void spinLeft() {
        spinLeft(1,0);
    }

    public void spinRight() {
        spinRight(1,0);
    }
}
