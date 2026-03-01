package com.baidu.idl.face.platform.common;

import android.text.TextUtils;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.network.LogRequest;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LogHelper {
    public static final String TAG = "LogHelper";
    public static HashMap<String, Object> logMap = new HashMap<>();
    public static ArrayList<Integer> logLivenessLiveness = new ArrayList<>();
    public static HashMap<String, Integer> logTipsMap = new HashMap<>();

    public static void addLivenessLog(int i) {
        ArrayList<Integer> arrayList = logLivenessLiveness;
        if (arrayList == null || arrayList.contains(Integer.valueOf(i))) {
            return;
        }
        logLivenessLiveness.add(Integer.valueOf(i));
    }

    public static void addLog(String str, Object obj) {
        HashMap<String, Object> map = logMap;
        if (map != null) {
            map.put(str, obj);
        }
    }

    public static void addLogWithKey(String str, Object obj) {
        HashMap<String, Object> map = logMap;
        if (map == null || map.containsKey(str)) {
            return;
        }
        logMap.put(str, obj);
    }

    public static void addTipsLogWithKey(String str) {
        HashMap<String, Integer> map = logTipsMap;
        if (map != null && !map.containsKey(str)) {
            logTipsMap.put(str, 1);
            return;
        }
        HashMap<String, Integer> map2 = logTipsMap;
        if (map2 == null || !map2.containsKey(str)) {
            return;
        }
        logTipsMap.put(str, Integer.valueOf(logTipsMap.get(str).intValue() + 1));
    }

    public static void clear() {
        logMap = new HashMap<>();
        logLivenessLiveness = new ArrayList<>();
        logTipsMap = new HashMap<>();
    }

    public static String getLog() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(Operators.BLOCK_START_STR);
            int i = 0;
            for (Map.Entry<String, Object> entry : logMap.entrySet()) {
                if (i != logMap.size() - 1) {
                    if (entry.getValue() instanceof String) {
                        sb.append(entry.getKey() + ":'" + entry.getValue() + "'");
                    } else {
                        sb.append(entry.getKey() + Constants.COLON_SEPARATOR + entry.getValue());
                    }
                    sb.append(",");
                } else if (entry.getValue() instanceof String) {
                    sb.append(entry.getKey() + ":'" + entry.getValue() + "'");
                } else {
                    sb.append(entry.getKey() + Constants.COLON_SEPARATOR + entry.getValue());
                }
                i++;
            }
            if (logLivenessLiveness != null && logLivenessLiveness.size() > 0) {
                sb.append(",lv:[");
                for (int i2 = 0; i2 < logLivenessLiveness.size(); i2++) {
                    if (i2 == logLivenessLiveness.size() - 1) {
                        sb.append(logLivenessLiveness.get(i2));
                    } else {
                        sb.append(logLivenessLiveness.get(i2) + ",");
                    }
                }
                sb.append(Operators.ARRAY_END_STR);
            }
            if (logTipsMap != null && logTipsMap.size() > 0) {
                sb.append(",msg:{");
                sb.append(getTipsMessage());
                sb.append(Operators.BLOCK_END_STR);
            }
            sb.append(Operators.BLOCK_END_STR);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        logMap = new HashMap<>();
        logLivenessLiveness = new ArrayList<>();
        logTipsMap = new HashMap<>();
        return sb.toString();
    }

    public static String getTipsKey(String str) {
        return TextUtils.equals(str, FaceStatusEnum.Detect_OccLeftEye.name()) ? ConstantHelper.LOG_TIPS_LEFTEYE_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_OccRightEye.name()) ? ConstantHelper.LOG_TIPS_RIGHTEYE_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_OccNose.name()) ? ConstantHelper.LOG_TIPS_NOSE_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_OccMouth.name()) ? ConstantHelper.LOG_TIPS_MOUTH_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_OccLeftContour.name()) ? ConstantHelper.LOG_TIPS_LEFTFACE_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_OccRightContour.name()) ? ConstantHelper.LOG_TIPS_RIGHTFACE_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_OccChin.name()) ? ConstantHelper.LOG_TIPS_CHIN_OCC : TextUtils.equals(str, FaceStatusEnum.Detect_PoorIllumintion.name()) ? ConstantHelper.LOG_TIPS_LIGHTUP : TextUtils.equals(str, FaceStatusEnum.Detect_ImageBlured.name()) ? ConstantHelper.LOG_TIPS_STAYSTILL : TextUtils.equals(str, FaceStatusEnum.Detect_FaceZoomIn.name()) ? ConstantHelper.LOG_TIPS_MOVECLOSE : TextUtils.equals(str, FaceStatusEnum.Detect_FaceZoomOut.name()) ? ConstantHelper.LOG_TIPS_MOVEFURTHER : TextUtils.equals(str, FaceStatusEnum.Detect_PitchOutOfDownMaxRange.name()) ? ConstantHelper.LOG_TIPS_HEADUP : TextUtils.equals(str, FaceStatusEnum.Detect_PitchOutOfUpMaxRange.name()) ? ConstantHelper.LOG_TIPS_HEADDOWN : TextUtils.equals(str, FaceStatusEnum.Detect_PitchOutOfRightMaxRange.name()) ? ConstantHelper.LOG_TIPS_TURNLEFT : TextUtils.equals(str, FaceStatusEnum.Detect_PitchOutOfLeftMaxRange.name()) ? ConstantHelper.LOG_TIPS_TURNRIGHT : (TextUtils.equals(str, FaceStatusEnum.Detect_NoFace.name()) || TextUtils.equals(str, FaceStatusEnum.Detect_FacePointOut.name())) ? ConstantHelper.LOG_TIPS_MOVEFACE : "";
    }

    public static String getTipsMessage() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : logTipsMap.entrySet()) {
            String tipsKey = getTipsKey(entry.getKey());
            if (!TextUtils.isEmpty(tipsKey)) {
                sb.append(tipsKey + Constants.COLON_SEPARATOR + entry.getValue());
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void sendLog() {
        LogRequest.sendLogMessage(getLog());
    }
}
