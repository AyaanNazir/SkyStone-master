package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Timer;

@Autonomous
public class AutoMain extends LinearOpMode {
    private DcMotor leftTopMotor , rightTopMotor, leftBottomMotor, rightBottomMotor, shooter;
    private DcMotor[] motors;
    private final double TPI = 570;
    private int distance;

    @Override
    public void runOpMode() { //test for now
        initialize();
        driveForward(1, 60);
        //driveForward(1, -100);
        //yeetRing();

    }

    public void initialize()
    {
        leftTopMotor = hardwareMap.dcMotor.get("leftTopMotor");
        rightTopMotor = hardwareMap.dcMotor.get("rightTopMotor");
        leftBottomMotor = hardwareMap.dcMotor.get("leftBottomMotor");
        rightBottomMotor = hardwareMap.dcMotor.get("rightBottomMotor");
        //shooter = hardwareMap.dcMotor.get("shooter");
        motors = new DcMotor[]{leftTopMotor, rightTopMotor, leftBottomMotor, rightBottomMotor};
        for(DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if(motor == leftTopMotor || motor == leftBottomMotor)
                motor.setDirection(DcMotor.Direction.REVERSE);
            else
                motor.setDirection(DcMotor.Direction.FORWARD);
        }
        waitForStart();
    }

    /**
     *
     * @param power
     * @param distance inchies so you will have to convert to tics
     */
    public void driveForward(double power, int distance)
    {
        int travel = (int)(distance*TPI);
        for(DcMotor motor : motors)
        {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(travel);
            motor.setPower(power);
        }
        while(driving(travel)){}
        for(DcMotor motor : motors)
        {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void turnLeft(int power, int distance)
    {
        for(DcMotor motor : motors)
        {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if(motor == leftTopMotor || motor == leftBottomMotor)
                motor.setDirection(DcMotorSimple.Direction.FORWARD);
            motor.setTargetPosition(distance);
            motor.setPower(power);
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
            if(motor == rightTopMotor || motor == rightBottomMotor)
                motor.setDirection(DcMotorSimple.Direction.REVERSE);
            motor.setTargetPosition(distance);
            motor.setPower(power);
        }
        while(leftTopMotor.isBusy() && rightTopMotor.isBusy()){}
        for(DcMotor motor : motors)
        {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void yeetRing()
    {
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setTargetPosition(99999999);
        shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shooter.setPower(.75);
        while(shooter.isBusy()){}
        shooter.setPower(0);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public boolean driving(int distance)
    {
        if(leftTopMotor.getCurrentPosition() < distance)
            return true;
        return false;
    }
}