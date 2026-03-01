package io.dcloud.feature.barcode2.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.feature.barcode2.view.DetectorViewConfig;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class CameraConfigurationManager {
    public static final Pattern COMMA_PATTERN = Pattern.compile(",");
    public static final int DESIRED_SHARPNESS = 30;
    public static final String TAG = "CameraConfigurationManager";
    public static final int TEN_DESIRED_ZOOM = 27;
    public Point cameraResolution;
    public final Context context;
    public Camera.Parameters parameters;
    public int previewFormat;
    public String previewFormatString;

    public CameraConfigurationManager(Context context) {
        this.context = context;
    }

    public static int findBestMotZoomValue(CharSequence charSequence, int i) {
        int i2 = 0;
        for (String str : COMMA_PATTERN.split(charSequence)) {
            try {
                double d = Double.parseDouble(str.trim());
                int i3 = (int) (10.0d * d);
                if (Math.abs(((double) i) - d) < Math.abs(i - i2)) {
                    i2 = i3;
                }
            } catch (NumberFormatException unused) {
                return i;
            }
        }
        return i2;
    }

    public static Point findBestPreviewSizeValue(CharSequence charSequence, Point point) {
        String[] strArrSplit = COMMA_PATTERN.split(charSequence);
        int length = strArrSplit.length;
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String strTrim = strArrSplit[i2].trim();
            int iIndexOf = strTrim.indexOf(120);
            if (iIndexOf < 0) {
                Log.w(TAG, "Bad preview-size: " + strTrim);
            } else {
                try {
                    int i5 = Integer.parseInt(strTrim.substring(0, iIndexOf));
                    int i6 = Integer.parseInt(strTrim.substring(iIndexOf + 1));
                    if (i5 >= i6) {
                        int iAbs = Math.abs(i5 - point.x) + Math.abs(i6 - point.y);
                        if (iAbs == 0) {
                            i4 = i6;
                            i3 = i5;
                            break;
                        }
                        if (iAbs < i) {
                            i4 = i6;
                            i = iAbs;
                            i3 = i5;
                        }
                    } else {
                        continue;
                    }
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Bad preview-size: " + strTrim);
                }
            }
            i2++;
        }
        if (i3 <= 0 || i4 <= 0) {
            return null;
        }
        return new Point(i3, i4);
    }

    public static Point findBestPreviewSizeValue0(CharSequence charSequence, Point point) {
        String[] strArrSplit = COMMA_PATTERN.split(charSequence);
        int i = 0;
        int i2 = 0;
        byte b = 0;
        int i3 = 0;
        while (true) {
            if (i >= strArrSplit.length) {
                break;
            }
            String strTrim = ((b == 0 || b == 1) ? strArrSplit[i] : strArrSplit[(strArrSplit.length - 1) - i]).trim();
            int iIndexOf = strTrim.indexOf(120);
            if (iIndexOf < 0) {
                Log.w(TAG, "Bad preview-size: " + strTrim);
            } else {
                try {
                    int i4 = Integer.parseInt(strTrim.substring(0, iIndexOf));
                    i3 = Integer.parseInt(strTrim.substring(iIndexOf + 1));
                    if (b != 0) {
                        if (i2 > 0 && i4 <= point.y && i3 <= point.x) {
                            i2 = i4;
                            break;
                        }
                    } else if (i2 != 0) {
                        i = -1;
                        if (i4 > i2) {
                            i2 = 0;
                            b = -1;
                        } else {
                            i2 = 0;
                            b = 1;
                        }
                        i3 = 0;
                    }
                    i2 = i4;
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Bad preview-size: " + strTrim);
                }
            }
            i++;
        }
        if (i2 <= 0 || i3 <= 0) {
            return null;
        }
        return new Point(i2, i3);
    }

    private void setFlash(Camera.Parameters parameters) {
        if (Build.MODEL.contains("Behold II") && CameraManager.SDK_INT == 3) {
            parameters.set("flash-value", 1);
        } else {
            parameters.set("flash-value", 2);
        }
        parameters.set("flash-mode", "off");
    }

    private void setSharpness(Camera.Parameters parameters) {
        String str = parameters.get("sharpness-max");
        int i = 30;
        if (str != null) {
            try {
                int i2 = Integer.parseInt(str);
                if (30 > i2) {
                    i = i2;
                }
            } catch (NumberFormatException unused) {
                Log.w(TAG, "Bad sharpness-max: " + str);
            }
        }
        parameters.set("sharpness", i);
    }

    private void setZoom(Camera.Parameters parameters) {
        String str = parameters.get("zoom-supported");
        if (str == null || Boolean.parseBoolean(str)) {
            int iFindBestMotZoomValue = 27;
            String str2 = parameters.get("max-zoom");
            if (str2 != null) {
                try {
                    int i = (int) (Double.parseDouble(str2) * 10.0d);
                    if (27 > i) {
                        iFindBestMotZoomValue = i;
                    }
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Bad max-zoom: " + str2);
                }
            }
            String str3 = parameters.get("taking-picture-zoom-max");
            if (str3 != null) {
                try {
                    int i2 = Integer.parseInt(str3);
                    if (iFindBestMotZoomValue > i2) {
                        iFindBestMotZoomValue = i2;
                    }
                } catch (NumberFormatException unused2) {
                    Log.w(TAG, "Bad taking-picture-zoom-max: " + str3);
                }
            }
            String str4 = parameters.get("mot-zoom-values");
            if (str4 != null) {
                iFindBestMotZoomValue = findBestMotZoomValue(str4, iFindBestMotZoomValue);
            }
            String str5 = parameters.get("mot-zoom-step");
            if (str5 != null) {
                try {
                    int i3 = (int) (Double.parseDouble(str5.trim()) * 10.0d);
                    if (i3 > 1) {
                        iFindBestMotZoomValue -= iFindBestMotZoomValue % i3;
                    }
                } catch (NumberFormatException unused3) {
                }
            }
            if (str2 != null || str4 != null) {
                parameters.set("zoom", String.valueOf(((double) iFindBestMotZoomValue) / 10.0d));
            }
            if (str3 != null) {
                parameters.set("taking-picture-zoom", iFindBestMotZoomValue);
            }
        }
    }

    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    public int getPreviewFormat() {
        return this.previewFormat;
    }

    public String getPreviewFormatString() {
        return this.previewFormatString;
    }

    public void initFromCameraParameters(Camera camera) {
        if (this.parameters == null) {
            this.parameters = camera.getParameters();
        }
        this.previewFormat = this.parameters.getPreviewFormat();
        this.previewFormatString = this.parameters.get("preview-format");
        Log.d(TAG, "Default preview format: " + this.previewFormat + '/' + this.previewFormatString);
        Rect rect = DetectorViewConfig.getInstance().gatherRect;
        int iWidth = rect.width();
        int iHeight = rect.height();
        if (this.context.getResources().getConfiguration().orientation == 1) {
            this.cameraResolution = getCameraResolution(this.parameters, new Point(iHeight, iWidth));
        } else {
            this.cameraResolution = getCameraResolution(this.parameters, new Point(iWidth, iHeight));
        }
    }

    public void setDesiredCameraParameters(Camera camera) {
        if (this.parameters == null) {
            this.parameters = camera.getParameters();
        }
        Log.d(TAG, "Setting preview size: " + this.cameraResolution);
        Camera.Parameters parameters = this.parameters;
        Point point = this.cameraResolution;
        parameters.setPreviewSize(point.x, point.y);
        setFlash(this.parameters);
        setZoom(this.parameters);
        try {
            camera.setParameters(this.parameters);
        } catch (Exception unused) {
        }
    }

    public static Point getCameraResolution(Camera.Parameters parameters, Point point) {
        String str = parameters.get("preview-size-values");
        if (str == null) {
            str = parameters.get("preview-size-value");
        }
        Point pointFindBestPreviewSizeValue = null;
        if (str != null) {
            Logger.i(TAG, "preview-size-values parameter: " + str);
            pointFindBestPreviewSizeValue = findBestPreviewSizeValue(str, point);
        }
        return pointFindBestPreviewSizeValue == null ? new Point((point.x >> 3) << 3, (point.y >> 3) << 3) : pointFindBestPreviewSizeValue;
    }
}
