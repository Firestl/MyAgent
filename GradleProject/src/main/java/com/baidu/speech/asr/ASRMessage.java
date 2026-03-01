package com.baidu.speech.asr;

/* JADX INFO: loaded from: classes.dex */
public class ASRMessage {
    public static final String TAG = "ASRMessage";
    public String mCommand;
    public byte[] mData;
    public boolean mIsVip;
    public int mLength;
    public int mOffset;
    public String mParam;

    public ASRMessage(String str, String str2, byte[] bArr, int i, int i2) {
        this.mCommand = "";
        this.mParam = "";
        this.mData = null;
        this.mOffset = 0;
        this.mLength = 0;
        this.mIsVip = false;
        this.mCommand = str;
        this.mParam = str2;
        this.mData = bArr;
        this.mOffset = i;
        this.mLength = i2;
        this.mIsVip = false;
    }

    public ASRMessage(String str, String str2, byte[] bArr, int i, int i2, boolean z) {
        this.mCommand = "";
        this.mParam = "";
        this.mData = null;
        this.mOffset = 0;
        this.mLength = 0;
        this.mIsVip = false;
        this.mCommand = str;
        this.mParam = str2;
        this.mData = bArr;
        this.mOffset = i;
        this.mLength = i2;
        this.mIsVip = z;
    }
}
