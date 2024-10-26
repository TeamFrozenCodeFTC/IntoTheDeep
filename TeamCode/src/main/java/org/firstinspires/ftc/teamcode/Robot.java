package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

// A parent class to all operation modes. Contains the Robot's Hardware but also LinearOpMode.
public abstract class Robot extends LinearOpMode {
    DcMotor frontLeftWheel;
    DcMotor backLeftWheel;
    DcMotor frontRightWheel;
    DcMotor backRightWheel;

    DcMotor linearSlide;
    
    DcMotor intakeExtender;
    CRServo sweeper;
    Servo sweeperRotator;

    Gyro2 gyro;

    Servo dumperServo;

    private void autoBrake(DcMotor motor) {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void reverse(DcMotor motor) {
        motor.setDirection(DcMotor.Direction.REVERSE);
    }

    private void resetTicks(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void initHardware() {
        frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLeft");
        autoBrake(frontLeftWheel); reverse(frontLeftWheel);

        backLeftWheel = hardwareMap.get(DcMotor.class, "backLeft");
        autoBrake(backLeftWheel);

        frontRightWheel = hardwareMap.get(DcMotor.class, "frontRight");
        autoBrake(frontRightWheel);

        backRightWheel = hardwareMap.get(DcMotor.class, "backRight");
        autoBrake(backRightWheel);

        intakeExtender = hardwareMap.get(DcMotor.class, "intakeMotor");
        autoBrake(intakeExtender); reverse(intakeExtender);

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        reverse(linearSlide);
        resetTicks(linearSlide);

        sweeper = hardwareMap.get(CRServo.class, "sweeper");
        sweeper.setDirection(DcMotorSimple.Direction.REVERSE);

        sweeperRotator = hardwareMap.get(Servo.class, "sweeperRotator");

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro2(imu, this);

        dumperServo = hardwareMap.get(Servo.class, "dumperServo");
    }
}

