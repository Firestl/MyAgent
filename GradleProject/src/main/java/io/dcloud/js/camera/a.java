package io.dcloud.js.camera;

import android.hardware.Camera;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.HttpUrl;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f6730e = 501;
    public static int f = 502;
    public static int g = 5011;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<Camera.Size> f6731a = null;
    public List<Integer> b = null;
    public List<Camera.Size> c = null;
    public int d;

    /* JADX INFO: renamed from: io.dcloud.js.camera.a$a, reason: collision with other inner class name */
    public static class C0195a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6732a;
        public boolean b = true;
        public int c = 0;
        public JSONObject d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f6733e = false;

        public String a() {
            return this.f6732a;
        }

        public int b() {
            return this.c;
        }
    }

    public a(int i) {
        this.d = i;
    }

    private String c() {
        List<Camera.Size> list = this.c;
        return list != null ? b(list) : HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
    }

    private String[] d() {
        List<Integer> list = this.b;
        String[] strArrA = list != null ? a(list) : null;
        return strArrA == null ? new String[]{"['jpg']", "['mp4']"} : strArrA;
    }

    private String e() {
        List<Camera.Size> list = this.f6731a;
        return (list == null || DeviceInfo.sDeviceSdkVer < 11) ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : b(list);
    }

    public String a() {
        String[] strArrD = d();
        return StringUtil.format("(function(){return{supportedImageResolutions : %s,supportedVideoResolutions : %s,supportedImageFormats : %s,supportedVideoFormats : %s};})();", c(), e(), strArrD[0], strArrD[1]);
    }

    public void b() {
        Camera cameraOpen;
        try {
            if (this.d != 2 || DeviceInfo.sDeviceSdkVer < 9) {
                cameraOpen = null;
            } else {
                for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == 1) {
                        cameraOpen = Camera.open(i);
                        break;
                    }
                }
                cameraOpen = null;
            }
            if (cameraOpen == null) {
                cameraOpen = Camera.open();
            }
            if (DeviceInfo.sDeviceSdkVer >= 11) {
                this.f6731a = cameraOpen.getParameters().getSupportedVideoSizes();
            }
            this.c = cameraOpen.getParameters().getSupportedPictureSizes();
            if (DeviceInfo.sDeviceSdkVer >= 8) {
                this.b = cameraOpen.getParameters().getSupportedPictureFormats();
            }
            cameraOpen.release();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String[] a(List<Integer> list) {
        return new String[]{"['jpg']", "['mp4']"};
    }

    public static C0195a a(String str, boolean z) {
        C0195a c0195a = new C0195a();
        if (str != null) {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
            JSONUtil.getString(jSONObject, "resolution");
            String string = JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_FILENAME);
            JSONUtil.getString(jSONObject, "format");
            c0195a.f6732a = PdrUtil.getDefaultPrivateDocPath(string, z ? BitmapUtils.IMAGE_KEY_SUFFIX : "mp4");
            JSONUtil.getInt(jSONObject, "index");
            if (jSONObject != null && jSONObject.has("optimize")) {
                c0195a.b = JSONUtil.getBoolean(jSONObject, "optimize");
            }
            if (jSONObject != null && jSONObject.has("videoMaximumDuration")) {
                c0195a.c = JSONUtil.getInt(jSONObject, "videoMaximumDuration");
            }
            if (jSONObject != null && jSONObject.has("crop")) {
                c0195a.d = jSONObject.optJSONObject("crop");
            }
            if (!z) {
                if (jSONObject != null && jSONObject.has("videoCompress")) {
                    c0195a.f6733e = jSONObject.optBoolean("videoCompress", false);
                }
            } else if (jSONObject != null && jSONObject.has("sizeType")) {
                String strOptString = jSONObject.optString("sizeType");
                if (strOptString.contains(Constants.Value.ORIGINAL) && strOptString.contains("compressed")) {
                    c0195a.f6733e = true;
                } else {
                    c0195a.f6733e = !strOptString.contains(Constants.Value.ORIGINAL);
                }
            }
        }
        return c0195a;
    }

    private String b(List<Camera.Size> list) {
        int size = list.size();
        if (list == null || size <= 1) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Operators.ARRAY_START_STR);
        for (int i = 0; i < size; i++) {
            stringBuffer.append("'" + list.get(i).width + Operators.MUL + list.get(i).height + "'");
            if (i != size - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }
}
