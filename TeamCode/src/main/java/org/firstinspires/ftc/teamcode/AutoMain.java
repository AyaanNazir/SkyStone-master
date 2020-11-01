package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Timer;

@Autonomous
public class AutoMain extends LinearOpMode {
    DcMotor leftTopMotor = null;
    DcMotor rightTopMotor = null;
    DcMotor leftBottomMotor = null;
    DcMotor rightBottomMotor = null;
    DcMotor shooter = null;
    DcMotor[] motors;

    @Override
    public void runOpMode() throws InterruptedException { //test for now
        initialize();
        driveForward(.3, 2000000000);
        driveForward(.5, -100000);
    }

    public void initialize() {
        leftTopMotor = hardwareMap.dcMotor.get("leftTopMotor");
        rightTopMotor = hardwareMap.dcMotor.get("rightTopMotor");
        leftBottomMotor = hardwareMap.dcMotor.get("leftBottomMotor");
        rightBottomMotor = hardwareMap.dcMotor.get("rightBottomMotor");
        shooter = (DcMotorEx) hardwareMap.dcMotor.get("shooter");
        motors = new DcMotor[]{leftTopMotor, rightTopMotor, leftBottomMotor, rightBottomMotor};
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if (motor == leftTopMotor || motor == leftBottomMotor)
                motor.setDirection(DcMotor.Direction.REVERSE);
            else
                motor.setDirection(DcMotor.Direction.FORWARD);
        }
        shooter.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
    }

    public void driveForward(double power, int distance) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(distance);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }
        while (leftTopMotor.isBusy() && rightTopMotor.isBusy()) {
        }
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnLeft(int power, int distance) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if (motor == rightTopMotor || motor == rightBottomMotor) {
                motor.setTargetPosition(distance);
                motor.setPower(power);
            }
        }
        while (leftTopMotor.isBusy() && rightTopMotor.isBusy()) {
        }
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnRight(int power, int distance) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            if (motor == leftTopMotor || motor == leftBottomMotor) {
                motor.setTargetPosition(distance);
                motor.setPower(power);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
        }
        while (leftTopMotor.isBusy() && rightTopMotor.isBusy()) {
        }
        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


    public void yeetRing()
    {
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setTargetPosition(2000000000);
        shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shooter.setPower(1);
        while(leftTopMotor.isBusy() && rightTopMotor.isBusy()){}
        shooter.setPower(0);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}