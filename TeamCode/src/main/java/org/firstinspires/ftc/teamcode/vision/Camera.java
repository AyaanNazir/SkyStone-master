package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class Camera extends LinearOpMode
{
    OpenCvInternalCamera camera;
    UltiamteGoalDeterminationPipeline pipeline;

    @Override
    public void runOpMode() {
        //initialize phones
        int monitorID = hardwareMap.appContext.getResources().getIdentifier("monitorID", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, monitorID);
        pipeline = new UltiamteGoalDeterminationPipeline();
        waitForStart();
    }
}
