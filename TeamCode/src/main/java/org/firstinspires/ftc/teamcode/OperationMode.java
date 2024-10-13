package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends LinearOpMode {
    DcMotor frontLeftWheel;
    DcMotor backLeftWheel;
    DcMotor frontRightWheel;
    DcMotor backRightWheel;

    DcMotor intakeExtender;
    DcMotor linearSlide;

    CRServo sweeper;
    Servo sweeperRotator;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        initHardware();

        IntakeAndDeliverControls intakeAndDeliverControls = new IntakeAndDeliverControls(this);
        MovementControls movement = new MovementControls(this);

        waitForStart();

        while (opModeIsActive()) {
            movement.movementControls();
            intakeAndDeliverControls.intakeAndDeliverControls();

            telemetry.addData("frontLeft", backRightWheel.getCurrentPosition());
            telemetry.update();
        }

        intakeAndDeliverControls.intakeBack();
    }

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
        // backRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intakeExtender = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeExtender.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // intakeArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeExtender.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        // linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sweeper = hardwareMap.get(CRServo.class, "sweeper");
        sweeper.setDirection(DcMotorSimple.Direction.REVERSE);

        sweeperRotator = hardwareMap.get(Servo.class, "sweeperRotator");
    }
}
