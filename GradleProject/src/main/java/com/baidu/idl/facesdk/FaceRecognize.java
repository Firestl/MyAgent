package com.baidu.idl.facesdk;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.baidu.idl.facesdk.FaceSDK;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FaceRecognize {
    public static final String TAG = "FaceRecognize";
    public Context context;
    public boolean isInitModel = false;
    public Map<FaceSDK.RecognizeType, Boolean> abilities = new HashMap();

    public FaceRecognize(Context context) {
        this.context = context;
    }

    private native int extractFeature(int[] iArr, int i, int i2, int i3, byte[] bArr, int[] iArr2, int i4, int i5);

    private native float getConsineDistance(byte[] bArr, byte[] bArr2);

    private native float getFaceSimilarity(byte[] bArr, byte[] bArr2, int i, int i2);

    private native int recognizeModelInit(AssetManager assetManager, String str, int i);

    private native int scoreMapInit(AssetManager assetManager, String str, int i);

    public int extractFeature(int[] iArr, int i, int i2, FaceSDK.ImgType imgType, byte[] bArr, int[] iArr2, FaceSDK.RecognizeType recognizeType) {
        if (!this.abilities.containsKey(recognizeType)) {
            Log.e(TAG, "not success init " + recognizeType);
            return -1;
        }
        if (FaceSDK.checkParameter(iArr, i, i2) && iArr2 != null && bArr != null && bArr.length == 2048) {
            return extractFeature(iArr, i, i2, imgType.ordinal(), bArr, iArr2, 1, recognizeType.ordinal());
        }
        return -1;
    }

    public float getFaceFeatureDistance(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return -1.0f;
        }
        float consineDistance = getConsineDistance(bArr, bArr2);
        if (Float.isNaN(consineDistance)) {
            return -1.0f;
        }
        return consineDistance;
    }

    public float getFaceSimilarity(byte[] bArr, byte[] bArr2, FaceSDK.RecognizeType recognizeType) {
        if (recognizeType == FaceSDK.RecognizeType.RECOGNIZE_ID_PHOTO || recognizeType == FaceSDK.RecognizeType.RECOGNIZE_NIR) {
            if (!BDFaceUtils.hasModel(this.context, FaceConfig.recognize_finance_compare_model_path)) {
                return 0.0f;
            }
            scoreMapInit(this.context.getAssets(), FaceConfig.recognize_finance_compare_model_path, recognizeType.ordinal());
            return getFaceSimilarity(bArr, bArr2, 0, recognizeType.ordinal());
        }
        if (recognizeType != FaceSDK.RecognizeType.RECOGNIZE_LIVE || !BDFaceUtils.hasModel(this.context, FaceConfig.recognize_compare_model_path)) {
            return 0.0f;
        }
        scoreMapInit(this.context.getAssets(), FaceConfig.recognize_compare_model_path, recognizeType.ordinal());
        return getFaceSimilarity(bArr, bArr2, 0, recognizeType.ordinal());
    }

    public void initModel(FaceSDK.RecognizeType recognizeType) {
        Context context = this.context;
        if (context != null) {
            if (recognizeType == FaceSDK.RecognizeType.RECOGNIZE_ID_PHOTO && BDFaceUtils.hasModel(context, FaceConfig.recognize_finance_model_path)) {
                recognizeModelInit(this.context.getAssets(), FaceConfig.recognize_finance_model_path, FaceSDK.RecognizeType.RECOGNIZE_ID_PHOTO.ordinal());
                this.abilities.put(FaceSDK.RecognizeType.RECOGNIZE_ID_PHOTO, true);
            }
            if (recognizeType == FaceSDK.RecognizeType.RECOGNIZE_LIVE && BDFaceUtils.hasModel(this.context, FaceConfig.recognize_model_path)) {
                recognizeModelInit(this.context.getAssets(), FaceConfig.recognize_model_path, FaceSDK.RecognizeType.RECOGNIZE_LIVE.ordinal());
                this.abilities.put(FaceSDK.RecognizeType.RECOGNIZE_LIVE, true);
            }
            if (recognizeType == FaceSDK.RecognizeType.RECOGNIZE_NIR && BDFaceUtils.hasModel(this.context, FaceConfig.recognize_nir_model_path)) {
                recognizeModelInit(this.context.getAssets(), FaceConfig.recognize_nir_model_path, FaceSDK.RecognizeType.RECOGNIZE_NIR.ordinal());
                this.abilities.put(FaceSDK.RecognizeType.RECOGNIZE_NIR, true);
            }
        }
    }

    public int extractFeature(int[] iArr, int i, int i2, FaceSDK.ImgType imgType, int i3, byte[] bArr, FaceSDK.RecognizeType recognizeType) {
        int iExtractFeature;
        FaceInfo[] faceInfoArrRun_detect;
        if (!this.abilities.containsKey(recognizeType)) {
            Log.e(TAG, "not success init " + recognizeType);
            return -1;
        }
        if (!FaceSDK.checkParameter(iArr, i, i2) || bArr == null || bArr.length != 2048) {
            return -1;
        }
        if (!this.isInitModel) {
            Context context = this.context;
            if (context == null) {
                return -1;
            }
            FaceSDK.initModel(context);
            this.isInitModel = true;
        }
        FaceInfo[] faceInfoArr = null;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        int i4 = i3 == 0 ? 100 : i3;
        try {
            Log.e(TAG, "detect start");
            if (recognizeType != FaceSDK.RecognizeType.RECOGNIZE_LIVE && recognizeType != FaceSDK.RecognizeType.RECOGNIZE_ID_PHOTO) {
                faceInfoArrRun_detect = FaceSDK.run_detect(iArr, i, i2, imgType, FaceSDK.DetectMethodType.NIR, i4);
            } else {
                faceInfoArrRun_detect = FaceSDK.run_detect(iArr, i, i2, imgType, FaceSDK.DetectMethodType.CNN, i4);
            }
            faceInfoArr = faceInfoArrRun_detect;
            Log.e(TAG, "detect end " + (System.currentTimeMillis() - jCurrentTimeMillis2));
        } catch (Exception e2) {
            Log.i(TAG, "FaceSDK: You need to apply for the authorization to use the facesdk");
            e2.printStackTrace();
        }
        if (faceInfoArr == null || faceInfoArr.length <= 0) {
            return -1;
        }
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < faceInfoArr.length; i7++) {
            if (faceInfoArr[i7].mWidth > i6) {
                i6 = faceInfoArr[i7].mWidth;
                i5 = i7;
            }
        }
        int[] iArr2 = {faceInfoArr[i5].mCenter_x, faceInfoArr[i5].mCenter_y, faceInfoArr[i5].mWidth, faceInfoArr[i5].mAngle};
        int[] iArr3 = new int[144];
        int[] iArr4 = {0};
        float[] fArr = {0.0f};
        try {
            Log.e(TAG, "align start");
            FaceSDK.run_align(iArr, i, i2, imgType, FaceSDK.AlignMethodType.CDNN, iArr2, iArr3, iArr4, fArr);
            Log.e(TAG, "align end " + (System.currentTimeMillis() - jCurrentTimeMillis3));
        } catch (Exception e3) {
            Log.i(TAG, "You need to apply for the authorization to use the facesdk");
            e3.printStackTrace();
        }
        long jCurrentTimeMillis4 = System.currentTimeMillis();
        try {
            Log.e(TAG, "feature start");
            iExtractFeature = extractFeature(iArr, i, i2, imgType.ordinal(), bArr, iArr3, 1, recognizeType.ordinal());
            try {
                Log.e(TAG, "feature end " + (System.currentTimeMillis() - jCurrentTimeMillis4));
            } catch (Exception e4) {
                e = e4;
                Log.i(TAG, "You need to apply for the authorization to use the facesdk");
                e.printStackTrace();
            }
        } catch (Exception e5) {
            e = e5;
            iExtractFeature = -1;
        }
        Log.e(TAG, "all time " + (System.currentTimeMillis() - jCurrentTimeMillis));
        return iExtractFeature;
    }
}
