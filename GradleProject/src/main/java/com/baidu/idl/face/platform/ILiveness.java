package com.baidu.idl.face.platform;

import com.baidu.idl.face.platform.model.FaceModel;

/* JADX INFO: loaded from: classes.dex */
public interface ILiveness {
    FaceModel liveness(LivenessTypeEnum livenessTypeEnum, byte[] bArr, int i, int i2);

    void reset();
}
