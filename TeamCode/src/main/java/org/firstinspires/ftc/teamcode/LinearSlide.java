package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LinearSlide {
    Robot operationMode;

    public LinearSlide(OperationMode operationMode) {
        this.operationMode = operationMode;
    }

    static final int MAX_TICKS = 4746;
    static final int TICKS_MARGIN = 26;

    static final int TICKS_TO_EQUATION = MAX_TICKS - 426;

    static final int MIN = TICKS_MARGIN;
    static final int MAX = MAX_TICKS - TICKS_MARGIN;

    boolean isExtended = false;

    void extend() {
//        new Thread(() -> {
//            isExtended = true;
//            while (isExtended) {
//                int ticks = operationMode.linearSlide.getCurrentPosition();
//
//                if (ticks < 4320) {
//                    operationMode.linearSlide.setPower(1);
//                }
//                else {
//                    double power = ((-1/400.0) * (ticks) + 11.8) / 4;
//                    operationMode.linearSlide.setPower(power);
//                }
//            }
//        }).start();
        new Thread(() -> {
            isExtended = true;
            while (isExtended) {
                int ticks = operationMode.linearSlide.getCurrentPosition();

                if (ticks < TICKS_TO_EQUATION) {
                    operationMode.linearSlide.setPower(1);
                }
                else {
                    // (MIN, 1) (MAX, 0)
                    // y-y_2/x-x_2
                    int x_1 = MIN;
                    int y_1 = 1;
                    int x_2 = MAX;
                    int y_2 = 0;

                    double slope = (double) (y_1 - y_2) / (x_1 - x_2);
                    // y = mx + b
                    // 1 = slope*MIN + b
                    // b = 1 - slope * MIN
                    double y_int = 1 - slope * MIN;

                    double power = (slope * ticks + y_int) / 4;
                    operationMode.linearSlide.setPower(power);
                }
            }
        }).start();
    }

    void retract() {
        new Thread(() -> {
            isExtended = false;

            while (!isExtended && operationMode.linearSlide.getCurrentPosition() > 20) {
                operationMode.linearSlide.setPower(-0.2);
            }
        }).start();
    }

}

class LinearSlideControls {
    OperationMode operationMode;
    LinearSlide linearSlide;

    public LinearSlideControls(OperationMode operationMode) {
        this.operationMode = operationMode;
        this.linearSlide = new LinearSlide(operationMode);
    }

//    void linearSlide() {
//        if (operationMode.gamepad2.right_bumper) {
//            pressed = true;
//        }
//        else if (operationMode.gamepad2.left_bumper) {
//            pressed = false;
//        }
//
//        int ticks = operationMode.linearSlide.getCurrentPosition();
//
//        if (pressed && ticks > 4320) {
//            // (4320, 0) (4720, 1)
//            double power = ((-1/400.0) * (ticks) + 11.8) / 4;
//            operationMode.linearSlide.setPower(power);
//        }
//        else if (pressed && ticks < 4720) {
//            operationMode.linearSlide.setPower(1);
//        }
//        else if (!pressed && ticks > 20) {
//            operationMode.linearSlide.setPower(-0.15);
//        }
//        else {
//            operationMode.linearSlide.setPower(0);
//        }
//    }

    void control() {
        if (operationMode.gamepad2.right_bumper) {
            linearSlide.extend();
        }
        else if (operationMode.gamepad2.left_bumper) {
            linearSlide.retract();
        }
    }
}
