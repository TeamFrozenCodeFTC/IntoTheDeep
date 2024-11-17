package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

// A parent class to all operation modes. Contains the Robot's Hardware but also LinearOpMode.
public abstract class Robot extends LinearOpMode {
    public DcMotor frontLeftWheel;
    public DcMotor backLeftWheel;
    public DcMotor frontRightWheel;
    public DcMotor backRightWheel;

    public DcMotor viperSlideMotor;

    public DcMotor intakeExtender;
    public CRServo sweeper;
    public Servo sweeperRotator;

    public Gyro2 gyro;

    public Servo dumperServo;

    public ViperSlide viperSlide;
    public Intake intake;

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

    private void runToPositionMode(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void initHardware() {
        frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLeft");
        autoBrake(frontLeftWheel);
        reverse(frontLeftWheel);

        backLeftWheel = hardwareMap.get(DcMotor.class, "backLeft");
        autoBrake(backLeftWheel);

        frontRightWheel = hardwareMap.get(DcMotor.class, "frontRight");
        autoBrake(frontRightWheel);
        reverse(frontRightWheel);

        backRightWheel = hardwareMap.get(DcMotor.class, "backRight");
        autoBrake(backRightWheel);

        intakeExtender = hardwareMap.get(DcMotor.class, "intakeMotor");
        autoBrake(intakeExtender);
        reverse(intakeExtender);
        //runToPositionMode(intakeExtender);

        viperSlideMotor = hardwareMap.get(DcMotor.class, "linearSlide");
        reverse(viperSlideMotor);
        //runToPositionMode(viperSlideMotor);

        sweeper = hardwareMap.get(CRServo.class, "sweeper");
        sweeper.setDirection(DcMotorSimple.Direction.REVERSE);

        sweeperRotator = hardwareMap.get(Servo.class, "sweeperRotator");

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro2(imu, this);
        gyro.startGyro();

        dumperServo = hardwareMap.get(Servo.class, "dumperServo");
    }

    public void initRobot() {
        initHardware();

        viperSlide = new ViperSlide(this);
        intake = new Intake(this);

        intake.armIn();
        intake.moveExtenderBack();
        viperSlide.undump();
    }

    public void raiseSpecimen() {
        viperSlide.topBarRaise();
        sleep(500);
        viperSlide.waitForExtension();
    }

    public void dumpSpecimen() {
        viperSlide.dump();
        sleep(2000);
        viperSlide.undump();
    }

    public void moveSpecimenToBucket() {
        intake.armIn();
        intake.moveExtenderBack();
        intake.waitForExtension();
        intake.waitForArm();

        intake.spinSweeperOut();
        sleep(2000);
        intake.stopSweeper();
    }
}
