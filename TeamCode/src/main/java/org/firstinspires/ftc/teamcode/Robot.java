package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

// if extending doesn't work then remove LinearOpMode and abstract
abstract class Robot extends LinearOpMode {
    DcMotor frontLeftWheel;
    DcMotor backLeftWheel;
    DcMotor frontRightWheel;
    DcMotor backRightWheel;

    DcMotor linearSlide;
    
    DcMotor intakeExtender;
    CRServo sweeper;
    Servo sweeperRotator;

    Gyro2 gyro;

    void initHardware() {
        frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftWheel.setDirection(DcMotor.Direction.REVERSE);

        backLeftWheel = hardwareMap.get(DcMotor.class, "backLeft");
        backLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRightWheel = hardwareMap.get(DcMotor.class, "frontRight");
        frontRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backRightWheel = hardwareMap.get(DcMotor.class, "backRight");
        backRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        backRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeExtender = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeExtender.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        intakeExtender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        intakeExtender.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sweeper = hardwareMap.get(CRServo.class, "sweeper");
        sweeper.setDirection(DcMotorSimple.Direction.REVERSE);

        sweeperRotator = hardwareMap.get(Servo.class, "sweeperRotator");

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro2(imu, this);
    }
}

