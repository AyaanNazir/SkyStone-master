package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
public class Camera {
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = true;
    private static final String VUFORIA_KEY = "AWyQgg3/////AAABmZs46bpkC0oRmLkfIxA32XcGqCKFDx7LtST7dk2NaI6x9bbZrXofseswpm9GuVMbIFvEY/IZFw18y5GimhGROoebmyusPk4XQyH6WaSM6MOvdigp5XAUez2IGtMbZl423om5TGgNg++zurKCY0DHO1IYb2bEUcOR4D2Zfy2Ob+/KVVa+k4yZOB0nazAfRt7Buaz1TKX9pP8/n4T/8BaLS0jU3dU8ph7KtJp8b1T+QQH7gRlmTCFyvlGMgETdzZNwCrRM5TKZF4SEJKdovFqi4wlSKVdEj04QcArmcQU6uTYivl0LCZ5Wbt4SbHizLZeTbM5ffrEIL3f92vL2JrqYXJp1iT0sqUa/1nii80SdcH7T";
    private VuforiaLocalizer vuforia = null;

    public void initVuforia(VuforiaLocalizer.Parameters parameters) {
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
}