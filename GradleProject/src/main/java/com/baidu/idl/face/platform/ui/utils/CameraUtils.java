package com.baidu.idl.face.platform.ui.utils;

import android.hardware.Camera;

/* JADX INFO: loaded from: classes.dex */
public class CameraUtils {
    public static final String TAG = "CameraUtils";

    public static void releaseCamera(Camera camera) {
        try {
            try {
                camera.release();
            } catch (RuntimeException e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
