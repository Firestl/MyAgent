package io.dcloud.feature.barcode2.camera;

import android.hardware.Camera;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.MobilePhoneModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class FlashlightManager {
    public static final String TAG = "FlashlightManager";
    public static final Object iHardwareService;
    public static final Method setFlashEnabledMethod;

    static {
        String simpleName = FlashlightManager.class.getSimpleName();
        Object hardwareService = getHardwareService();
        iHardwareService = hardwareService;
        setFlashEnabledMethod = getSetFlashEnabledMethod(hardwareService);
        if (hardwareService == null) {
            Log.v(simpleName, "This device does supports control of a flashlight");
        } else {
            Log.v(simpleName, "This device does not support control of a flashlight");
        }
    }

    public static void disableFlashlight() {
        try {
            setFlashlight(false);
        } catch (Exception unused) {
        }
    }

    public static void enableFlashlight() {
        try {
            setFlashlight(true);
        } catch (Exception unused) {
        }
    }

    public static Object getHardwareService() {
        Method methodMaybeGetMethod;
        Object objInvoke;
        Class<?> clsMaybeForName;
        Method methodMaybeGetMethod2;
        Class<?> clsMaybeForName2 = maybeForName("android.os.ServiceManager");
        if (clsMaybeForName2 == null || (methodMaybeGetMethod = maybeGetMethod(clsMaybeForName2, "getService", String.class)) == null || (objInvoke = invoke(methodMaybeGetMethod, null, "hardware")) == null || (clsMaybeForName = maybeForName("android.os.IHardwareService$Stub")) == null || (methodMaybeGetMethod2 = maybeGetMethod(clsMaybeForName, "asInterface", IBinder.class)) == null) {
            return null;
        }
        return invoke(methodMaybeGetMethod2, null, objInvoke);
    }

    public static Method getSetFlashEnabledMethod(Object obj) {
        if (obj == null) {
            return null;
        }
        return maybeGetMethod(obj.getClass(), "setFlashlightEnabled", Boolean.TYPE);
    }

    public static Object invoke(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            Log.w(TAG, "Unexpected error while invoking " + method, e2);
            return null;
        } catch (RuntimeException e3) {
            Log.w(TAG, "Unexpected error while invoking " + method, e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.w(TAG, "Unexpected error while invoking " + method, e4.getCause());
            return null;
        }
    }

    public static Class<?> maybeForName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected error while finding class " + str, e2);
            return null;
        }
    }

    public static Method maybeGetMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (RuntimeException e2) {
            Log.w(TAG, "Unexpected error while finding method " + str, e2);
            return null;
        }
    }

    public static void setFlashlight(boolean z) {
        Camera cameraHandler = CameraManager.get().getCameraHandler();
        Camera.Parameters parameters = cameraHandler.getParameters();
        if (z) {
            parameters.setFlashMode("torch");
        } else {
            parameters.setFlashMode("off");
        }
        cameraHandler.setParameters(parameters);
        if (DeviceInfo.sBrand.equalsIgnoreCase(MobilePhoneModel.XIAOMI) && Build.VERSION.SDK_INT == 19) {
            cameraHandler.stopPreview();
            cameraHandler.startPreview();
            cameraHandler.autoFocus(CameraManager.get().getAutoFocusCallback());
        }
    }
}
