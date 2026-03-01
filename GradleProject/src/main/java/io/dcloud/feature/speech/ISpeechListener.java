package io.dcloud.feature.speech;

import io.dcloud.common.DHInterface.IReflectAble;

/* JADX INFO: loaded from: classes3.dex */
public interface ISpeechListener extends IReflectAble {
    public static final byte FINALRESULT = 9;
    public static final byte ONAUDIOEND = 4;
    public static final byte ONAUDIOSTART = 3;
    public static final byte ONEND = 2;
    public static final byte ONERROR = 7;
    public static final byte ONRECOGNIZEEND = 6;
    public static final byte ONRECOGNIZESTART = 5;
    public static final byte ONSTART = 1;
    public static final byte ONSUCCESS = 8;
    public static final byte PARTICALRESULT = 10;
    public static final byte VOLUME = 11;

    void onStateChange(byte b, Object obj, boolean z);
}
