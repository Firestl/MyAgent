package com.baidu.idl.face.platform;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public interface IDetectStrategyCallback {
    public static final String IMAGE_KEY_BEST_IMAGE = "bestImage";

    void onDetectCompletion(FaceStatusEnum faceStatusEnum, String str, HashMap<String, String> map);
}
