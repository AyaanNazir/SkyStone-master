package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Timer;

@Autonomous
public class AutoMain extends LinearOpMode {
    private DcMotorEx leftTopMotor, rightTopMotor, leftBottomMotor, rightBottomMotor, arm, transfer, intake, shooter;
    private Servo claw, flicker, holder;
    private DcMotorEx[] motors;
    private final double TPI = 570;
    private ElapsedTime shooterTime;
    private int distance;

    @Override
    public void runOpMode() throws InterruptedException  { //test for now
        initialize();
        driveForward(.25, 8);
        yeetRing();
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
        double current = leftTopMotor.getCurrentPosition();
        for (DcMotorEx motor : motors) {
            motor.setTargetPosition(travel);
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setPower(power);
        }
        //This is what checks if the motors are supposed to be still running.
        while (driving(travel, current)) {
            heartbeat();
        }
        for (DcMotorEx motor : motors) {
            motor.setPower(0);
        }
    }
    public void turn()
    {
        double current = leftTopMotor.getCurrentPosition();
        //public double currentAngle() {
        //returns heading from gyro using unit circle values (-180 to 180 degrees, -pi to pi radians. We're using degrees)
       // return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }


    public void yeetRing() throws InterruptedException {
        shooter.setPower(.75);
        shooterTime = new ElapsedTime();
        while (shooterTime.milliseconds() <= 5000) {
            heartbeat();
        }
        shooter.setPower(0);
    }

    //Checks positions has been moved
    public boolean driving(int ticks, double startingPosition) {
        return Math.abs(leftTopMotor.getCurrentPosition() - startingPosition) < ticks || Math.abs(rightTopMotor.getCurrentPosition() - startingPosition) < ticks || Math.abs(leftTopMotor.getCurrentPosition() - startingPosition) < ticks || Math.abs(rightTopMotor.getCurrentPosition() - startingPosition) < ticks;
    }

    public void heartbeat() throws InterruptedException {
        //if opMode is stopped, will throw and catch an InterruptedException rather than resulting in red text and program crash on phone
        if (!opModeIsActive()) {
            throw new InterruptedException();
        }
    }
}