package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Autonomous extends Robot {
   static final double TICKS_TO_INCHES = ((13303-9360)/92.9);

   void stopWheels() {
       backLeftWheel.setPower(0);
       backRightWheel.setPower(0);
       frontLeftWheel.setPower(0);
       frontRightWheel.setPower(0);
   }

   void resetEncoder() {
       backRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       backRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
   }

    void goForward(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(power);
        backRightWheel.setPower(power);
        frontLeftWheel.setPower(power);
        frontRightWheel.setPower(power);

        double targetTics = inchesToTics(inches);

        while (Math.abs(backRightWheel.getCurrentPosition()) < targetTics) {

        }

        stopWheels();
    }
    double inchesToTics(double inches){
        return inches*TICKS_TO_INCHES;
    }

    void goBackward(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(-power);
        backRightWheel.setPower(-power);
        frontLeftWheel.setPower(-power);
        frontRightWheel.setPower(-power);
        double targetTics = inchesToTics(inches);
        while (Math.abs(backRightWheel.getCurrentPosition()) < targetTics) {

        }
        stopWheels();
    }

    void slideLeft(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(power);
        backRightWheel.setPower(power);
        frontLeftWheel.setPower(-power);
        frontRightWheel.setPower(-power);
        double targetTics = inchesToTics(inches);
        while (backRightWheel.getCurrentPosition() < targetTics) {

        }
        stopWheels();
    }

    void slideRight(double inches, double power) {
        resetEncoder();

        backLeftWheel.setPower(-power);
        backRightWheel.setPower(-power);
        frontLeftWheel.setPower(power);
        frontRightWheel.setPower(power);

        double targetTics = inchesToTics(inches);
        while (backRightWheel.getCurrentPosition() < targetTics) {

        }
        stopWheels();
    }

    void turnLeft(double degrees, double power) {
        gyro.reset();

        backLeftWheel.setPower(-power);
        backRightWheel.setPower(power);
        frontLeftWheel.setPower(-power);
        frontRightWheel.setPower(power);

        telemetry.addData("angle", gyro.getAngle());
        telemetry.update();

        while (Math.abs(gyro.getAngle()) < degrees) {

        }

        stopWheels();
    }

    void turnRight(double degrees, double power) {
        gyro.reset();

        backLeftWheel.setPower(power);
        backRightWheel.setPower(-power);
        frontLeftWheel.setPower(power);
        frontRightWheel.setPower(-power);

        while (Math.abs(gyro.getAngle()) < degrees) {

        }

        stopWheels();
    }

    void scoreSpecimen() {
        raiseSpecimen();
        dumpSpecimen();
        linearSlide.lower();
    }
}
