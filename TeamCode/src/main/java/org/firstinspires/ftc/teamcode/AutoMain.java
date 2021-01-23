//As of 1/22/2021, this class is open to everyone. Please comment up here if you edit code. -Ayaan Nazir
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.vision.Camera;

import java.util.Timer;

@Autonomous
public class AutoMain extends LinearOpMode {
    private DcMotorEx leftTopMotor, rightTopMotor, leftBottomMotor, rightBottomMotor, arm, transfer, intake, shooter;
    private Servo claw, flicker, holder;
    private DcMotorEx[] motors;
    private final double TPI = 33.5625;
    private ElapsedTime shooterTime;
    private int distance;

    @Override
    public void runOpMode() throws InterruptedException  { //Load Zone B
        initialize();
        driveForward(.75, 84); //moves bot to Zone B
        //set wobbler down using arm and claw //use servo stuff from teleop and elapsedtime to time it
        driveForward(.75, -24); //parks bot on starting half line
        //turn the robot backwards //90, 180, 270, or 360 degrees; in this case, 180
        driveForward(.75, 12); //sets the bot up to start shooting
        strafeLeft(.75, 6); //move bot to line up with shooter
        //yeetRing(rings: 3); //need to revamp so parameters shoot for the amount of rings
        strafeRight(.75, 6); //move bot to line up with rings
    }

    public void initialize() {
        //Even if I'm not using it, I have to map it because it is mapped on the bot.
        leftTopMotor = (DcMotorEx) hardwareMap.dcMotor.get("leftFrontDrive");
        leftBottomMotor = (DcMotorEx) hardwareMap.dcMotor.get("leftRearDrive");
        rightTopMotor = (DcMotorEx) hardwareMap.dcMotor.get("rightFrontDrive");
        rightBottomMotor = (DcMotorEx) hardwareMap.dcMotor.get("rightRearDrive");
        arm = (DcMotorEx) hardwareMap.dcMotor.get("arm");
        shooter = (DcMotorEx) hardwareMap.dcMotor.get("shooter");
        transfer = (DcMotorEx) hardwareMap.dcMotor.get("transfer");
        intake = (DcMotorEx) hardwareMap.dcMotor.get("intake");
        claw = hardwareMap.servo.get("claw");
        flicker = hardwareMap.servo.get("flicker");
        holder = hardwareMap.servo.get("holder");
        motors = new DcMotorEx[]{leftTopMotor, rightTopMotor, leftBottomMotor, rightBottomMotor};
        for (DcMotorEx motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            if (motor == leftTopMotor || motor == leftBottomMotor)
                motor.setDirection(DcMotor.Direction.REVERSE);
            else
                motor.setDirection(DcMotor.Direction.FORWARD);
        }
        waitForStart();
    }

    /**
     * @param power
     * @param distance inchies so you will have to convert to tics
     */
    public void driveForward(double power, int distance) throws InterruptedException  {
        int travel = (int) (distance * TPI);
        for (DcMotorEx motor : motors) {
            motor.setTargetPosition(travel);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setPower(power);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION); //THIS WORKS
        }
        //This is what checks if the motors are supposed to be still running.
        while (leftTopMotor.isBusy() && rightTopMotor.isBusy() && leftBottomMotor.isBusy() && rightBottomMotor.isBusy()) {
            heartbeat();
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
        }
    }

    public void strafeLeft(double power, int distance) throws InterruptedException {
        rightBottomMotor.setDirection(DcMotor.Direction.REVERSE);
        leftTopMotor.setDirection(DcMotor.Direction.FORWARD);
        int travel = (int) (distance * TPI);
        for (DcMotorEx motor : motors) {
            motor.setTargetPosition(travel);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setPower(power);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        //This is what checks if the motors are supposed to be still running.
        while (leftTopMotor.isBusy() && rightTopMotor.isBusy() && leftBottomMotor.isBusy() && rightBottomMotor.isBusy()) {
            heartbeat();
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
            if (motor == leftTopMotor || motor == leftBottomMotor)
                motor.setDirection(DcMotor.Direction.REVERSE);
            else
                motor.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void strafeRight(double power, int distance) throws InterruptedException {
        rightTopMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBottomMotor.setDirection(DcMotor.Direction.FORWARD);
        int travel = (int) (distance * TPI);
        for (DcMotorEx motor : motors) {
            motor.setTargetPosition(travel);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setPower(power);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        while (leftTopMotor.isBusy() && rightTopMotor.isBusy() && leftBottomMotor.isBusy() && rightBottomMotor.isBusy()) {
            heartbeat();
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
            if (motor == leftTopMotor || motor == leftBottomMotor)
                motor.setDirection(DcMotor.Direction.REVERSE);
            else
                motor.setDirection(DcMotor.Direction.FORWARD);
        }
    }

    public void yeetRing() throws InterruptedException { //NEEDS TO BE REVAMPED TO INCLUDE FLICKER AND PARAMETER FOR RINGS
        shooter.setPower(.75);
        shooterTime = new ElapsedTime();
        while (shooterTime.milliseconds() <= 5000) {
            heartbeat();
        }
        shooter.setPower(0);
    }

    public void heartbeat() throws InterruptedException {
        //if opMode is stopped, will throw and catch an InterruptedException rather than resulting in red text and program crash on phone
        if (!opModeIsActive()) {
            throw new InterruptedException();
        }
    }

}