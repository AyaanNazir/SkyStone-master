package org.firstinspires.ftc.teamcode.Autonomous.OpenCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfPoint;
//import org.opencv.core.MatOfPoint2f;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;

import android.graphics.Color;

import com.google.gson.Gson;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class Camera extends LinearOpMode {
    //Change the following values to the max and the mins of Orange
    private static int valMid = 255;
    private static int valLeft = 255;
    private static int valRight = 255;

    //Maybe change the following to the heights/widths of image?
    private static float rectHeight = 0.4f/8f;
    private static float rectWidth = 0.5f/8f;
    private ClassFactory OpenCvCameraFactory;
    private Object OpenCvInternalCamera;

    @Override
    public void runOpMode() throws InterruptedException {

    }


    public void initCamera(int cameraMonitorViewId) {

    }
}