package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Timer;

@Autonomous
public class AutoMain extends LinearOpMode {
    DcMotor leftTopMotor = null;
    DcMotor rightTopMotor = null;
    DcMotor leftBottomMotor = null;
    DcMotor rightBottomMotor = null;
    DcMotor[] motors;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        driveForward(.1, 200);
        driveForward(.5, -100);
        driveForward(.1, 2000);
        driveForward(.5, -100);
        driveForward(1, 200);
        driveForward(.5, -100);
        driveForward(1, 2000);
        driveForward(.5, -100);
    }

    public void initialize()
    {
        leftTopMotor = hardwareMap.dcMotor.get("leftTopMotor");
        rightTopMotor = hardwareMap.dcMotor.get("rightTopMotor");
        leftBottomMotor = hardwareMap.dcMotor.get("leftBottomMotor");
        rightBottomMotor = hardwareMap.dcMotor.get("rightBottomMotor");
        motors = new DcMotor[]{leftTopMotor, rightTopMotor, leftBottomMotor, rightBottomMotor};
        for(DcMotor motor : motors)
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
    }

    public void driveForward(double power, int distance)
    {
        for(DcMotor motor : motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(distance);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }
        while(leftTopMotor.isBusy() && rightTopMotor.isBusy()){} //needed so next stuff doesn't happen until robot is done moving
        for(DcMotor motor : motors)
        {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnLeft(int power, int distance)
    {
        for(DcMotor motor : motors)
        {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if(motor == leftTopMotor || motor == leftBottomMotor) {
                motor.setTargetPosition(-distance);
                motor.setPower(-power);
            }
            else {
                motor.setTargetPosition(distance);
                motor.setPower(power);
            }
        }
        while(leftTopMotor.isBusy() && rightTopMotor.isBusy()){}
        for(DcMotor motor : motors)
        {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnRight(int power, int distance)
    {
        for(DcMotor motor : motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            if(motor == leftTopMotor || motor == leftBottomMotor) {
                motor.setTargetPosition(distance);
                motor.setPower(power);
            }
            else {
                motor.setTargetPosition(-distance);
                motor.setPower(-power);
            }
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if(motor == leftTopMotor || motor == leftBottomMotor)
                motor.setPower(power);
            else
                motor.setTargetPosition(-power);
        }
        while(leftTopMotor.isBusy() && rightTopMotor.isBusy()){}
        for(DcMotor motor : motors)
        {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
