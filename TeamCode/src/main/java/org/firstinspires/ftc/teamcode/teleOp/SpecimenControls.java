package org.firstinspires.ftc.teamcode.teleOp;

import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.Robot;

// Uses gamepad2
public class SpecimenControls {
    Robot operationMode;

    public SpecimenControls(Robot operationMode) {
        this.operationMode = operationMode;
    }

    void control() {
        linearSlide();
        dumper();

        intakeExtender();
        intakeArm();
        intakeSweeper();
    }

    void run() {
        if (operationMode.gamepad2.dpad_down) {
            operationMode.moveSpecimenToBucket();
            operationMode.telemetry.addData("raising", "specimen");
            operationMode.telemetry.update();
            operationMode.raiseSpecimen();
            operationMode.telemetry.addData("raised", "specimen");
            operationMode.telemetry.update();
        }
        else {
            control();
        }
    }

    void linearSlide() {
        if (operationMode.gamepad2.right_bumper) {
            operationMode.linearSlide.raise(2);
        }
        else if (operationMode.gamepad2.left_bumper) {
            operationMode.linearSlide.lower();
        }
    }

    void dumper() {
        if (operationMode.gamepad2.dpad_up) {
            operationMode.linearSlide.dump();
        }
        else {
            operationMode.linearSlide.undump();
        }
    }

    void intakeExtender() {
        double power = -operationMode.gamepad2.left_stick_y;
        double position = operationMode.intakeExtender.getCurrentPosition();

        if (position > Intake.MAX_TICKS && power > 0) {
            power = 0;
        }
        if (position < Intake.MIN_TICKS && power < 0) {
            power = 0;
        }
        operationMode.intakeExtender.setPower(power);
    }

    void intakeArm() {
        if (operationMode.gamepad2.right_stick_y < 0) {
            operationMode.intake.armOut();
        }
        else if (operationMode.gamepad2.right_stick_y > 0) {
            operationMode.intake.armIn();
        }
    }

    void intakeSweeper() {
        if (operationMode.gamepad2.square) {
            operationMode.intake.spinSweeperOut();
        }
        else if (operationMode.gamepad2.circle) {
            operationMode.intake.spinSweeperIn();
        }
        else {
            operationMode.sweeper.setPower(0);
        }
    }
}