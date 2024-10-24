package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="Main", group="Linear OpMode")
public class OperationMode extends Robot {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        initHardware();

        IntakeControls intakeControls = new IntakeControls(this);
        LinearSlideControls linearSlideControls = new LinearSlideControls(this);
        MovementControls movement = new MovementControls(this);

        waitForStart();

        intakeControls.intake.sweeperArmIn();
        intakeControls.intake.moveExtenderBack();
        linearSlideControls.linearSlide.retract();

        while (opModeIsActive()) {
            movement.movementControls();
            intakeControls.control();
            linearSlideControls.control();

            // Get ticks to inches ratio
            telemetry.addData("frontLeft", backRightWheel.getCurrentPosition());
            telemetry.addData("ticks", intakeExtender.getCurrentPosition());
            telemetry.addData("ticks", intakeExtender.getCurrentPosition());
            // get multiple wheels for accuracy?
            telemetry.update();
        }
    }
}
