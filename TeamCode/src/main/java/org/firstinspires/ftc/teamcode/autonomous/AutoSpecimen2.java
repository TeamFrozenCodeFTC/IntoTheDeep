package org.firstinspires.ftc.teamcode.autonomous;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class AutoSpecimen2 extends AutoSpecimen {
    @Override
    public void runOpMode() {
        initRobot();

        viperSlide.clawGrab();

        waitForStart();

        hookFirst();

        double power = 0.7;

        goForward(4.5,power);
        turnLeft(90, power);
        goBackward(24+(24-18)-1,power);
        slideLeft((double) (24/2)+2, power);
        goBackward(6, power);
        slideRight((double) 24*2, power);

        slideLeft((double) 24*2, power);
        goBackward(8, power);

        turnRight(90, power);
        goBackward(24*2, power);
    }
}
