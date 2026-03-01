package com.baidu.idl.face.platform;

import com.baidu.idl.face.platform.model.FaceModel;

/* JADX INFO: loaded from: classes.dex */
public interface IDetect {
    FaceModel detect(byte[] bArr, int i, int i2);

    int[] getBestFaceImage();

    void reset();
}
