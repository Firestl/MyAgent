package com.baidu.idl.face.platform.decode;

import android.graphics.Bitmap;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.IDetect;
import com.baidu.idl.face.platform.ILiveness;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.baidu.idl.face.platform.model.FaceExtInfo;
import com.baidu.idl.face.platform.model.FaceModel;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.baidu.idl.facesdk.FaceInfo;
import com.baidu.idl.facesdk.FaceSDK;
import com.baidu.idl.facesdk.FaceTracker;
import com.baidu.idl.facesdk.FaceVerifyData;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class FaceModule implements IDetect, ILiveness {
    public static final String TAG = "FaceModule";
    public FaceExtInfo mFaceExtInfo;
    public FaceExtInfo[] mFaceExtInfos;
    public FaceModel mFaceModel;
    public FaceTracker mFaceTracker;
    public int mImageWidth = 0;
    public int mImageHeight = 0;
    public int[] mArgbData = null;
    public int[] mSaveFaceArgbData = null;
    public int mErrCode = 0;
    public boolean mLivenessFlag = false;
    public int mDegree = 90;

    public FaceModule(FaceTracker faceTracker) {
        this.mFaceTracker = faceTracker;
        if (faceTracker != null) {
            faceTracker.clearTrackedFaces();
        }
    }

    private FaceInfo[] faceTrackerDecode(byte[] bArr, int i, int i2) {
        if (this.mArgbData == null || i * i2 != this.mImageWidth * this.mImageHeight) {
            this.mArgbData = new int[i * i2];
            this.mImageWidth = i;
            this.mImageHeight = i2;
        }
        System.nanoTime();
        if (FaceSDK.getAuthorityStatus() != 0) {
            return null;
        }
        FaceSDK.getARGBFromYUVimg(bArr, this.mArgbData, i, i2, 360 - this.mDegree, 1);
        this.mErrCode = this.mFaceTracker.faceVerification(this.mArgbData, i, i2, FaceSDK.ImgType.ARGB, FaceTracker.ActionType.RECOGNIZE).ordinal();
        FaceInfo[] faceInfoArr = this.mFaceTracker.get_TrackedFaceInfo();
        System.nanoTime();
        if (faceInfoArr == null || faceInfoArr.length <= 0 || this.mErrCode != FaceTracker.ErrCode.OK.ordinal()) {
            return faceInfoArr;
        }
        this.mSaveFaceArgbData = this.mArgbData;
        return faceInfoArr;
    }

    private FaceExtInfo[] getExtInfo(FaceInfo[] faceInfoArr) {
        if (this.mFaceExtInfos == null) {
            this.mFaceExtInfos = new FaceExtInfo[1];
            this.mFaceExtInfo = new FaceExtInfo();
        }
        if (faceInfoArr == null || faceInfoArr.length <= 0) {
            this.mFaceExtInfos[0] = null;
        } else {
            if (this.mFaceExtInfo == null) {
                this.mFaceExtInfo = new FaceExtInfo();
            }
            this.mFaceExtInfo.addFaceInfo(faceInfoArr[0]);
            this.mFaceExtInfos[0] = this.mFaceExtInfo;
        }
        return this.mFaceExtInfos;
    }

    private FaceStatusEnum getModuleState(int i) {
        return i == FaceTracker.ErrCode.OK.ordinal() ? FaceStatusEnum.OK : i == FaceTracker.ErrCode.PITCH_OUT_OF_DOWN_MAX_RANGE.ordinal() ? FaceStatusEnum.Detect_PitchOutOfDownMaxRange : i == FaceTracker.ErrCode.PITCH_OUT_OF_UP_MAX_RANGE.ordinal() ? FaceStatusEnum.Detect_PitchOutOfUpMaxRange : i == FaceTracker.ErrCode.YAW_OUT_OF_LEFT_MAX_RANGE.ordinal() ? FaceStatusEnum.Detect_PitchOutOfLeftMaxRange : i == FaceTracker.ErrCode.YAW_OUT_OF_RIGHT_MAX_RANGE.ordinal() ? FaceStatusEnum.Detect_PitchOutOfRightMaxRange : i == FaceTracker.ErrCode.POOR_ILLUMINATION.ordinal() ? FaceStatusEnum.Detect_PoorIllumintion : i == FaceTracker.ErrCode.NO_FACE_DETECTED.ordinal() ? FaceStatusEnum.Detect_NoFace : i == FaceTracker.ErrCode.DATA_NOT_READY.ordinal() ? FaceStatusEnum.Detect_DataNotReady : i == FaceTracker.ErrCode.DATA_HIT_ONE.ordinal() ? FaceStatusEnum.Detect_DataHitOne : i == FaceTracker.ErrCode.DATA_HIT_LAST.ordinal() ? FaceStatusEnum.Detect_DataHitLast : i == FaceTracker.ErrCode.IMG_BLURED.ordinal() ? FaceStatusEnum.Detect_ImageBlured : i == FaceTracker.ErrCode.OCCLUSION_LEFT_EYE.ordinal() ? FaceStatusEnum.Detect_OccLeftEye : i == FaceTracker.ErrCode.OCCLUSION_RIGHT_EYE.ordinal() ? FaceStatusEnum.Detect_OccRightEye : i == FaceTracker.ErrCode.OCCLUSION_NOSE.ordinal() ? FaceStatusEnum.Detect_OccNose : i == FaceTracker.ErrCode.OCCLUSION_MOUTH.ordinal() ? FaceStatusEnum.Detect_OccMouth : i == FaceTracker.ErrCode.OCCLUSION_LEFT_CONTOUR.ordinal() ? FaceStatusEnum.Detect_OccLeftContour : i == FaceTracker.ErrCode.OCCLUSION_RIGHT_CONTOUR.ordinal() ? FaceStatusEnum.Detect_OccRightContour : i == FaceTracker.ErrCode.OCCLUSION_CHIN_CONTOUR.ordinal() ? FaceStatusEnum.Detect_OccChin : i == FaceTracker.ErrCode.FACE_NOT_COMPLETE.ordinal() ? FaceStatusEnum.Detect_FaceNotComplete : i == FaceTracker.ErrCode.UNKNOW_TYPE.ordinal() ? FaceStatusEnum.Detect_NoFace : FaceStatusEnum.Detect_NoFace;
    }

    @Override // com.baidu.idl.face.platform.IDetect
    public FaceModel detect(byte[] bArr, int i, int i2) {
        if (bArr == null || i <= 0 || i2 <= 0) {
            return null;
        }
        FaceModel faceModel = new FaceModel();
        FaceInfo[] faceInfoArrFaceTrackerDecode = faceTrackerDecode(bArr, i, i2);
        faceModel.setArgbImage(this.mArgbData);
        faceModel.setFaceInfos(getExtInfo(faceInfoArrFaceTrackerDecode));
        faceModel.setFaceModuleState(getModuleState(this.mErrCode));
        faceModel.setFrameTime(System.currentTimeMillis());
        return faceModel;
    }

    @Override // com.baidu.idl.face.platform.IDetect
    public int[] getBestFaceImage() {
        return this.mSaveFaceArgbData;
    }

    public String getDetectBestImage(int i) throws Throwable {
        FaceVerifyData[] faceVerifyDataArr = this.mFaceTracker.get_FaceVerifyData(0);
        if (faceVerifyDataArr == null || faceVerifyDataArr.length <= 0) {
            return "";
        }
        int length = faceVerifyDataArr.length - 1;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(faceVerifyDataArr[length].cols, faceVerifyDataArr[length].rows, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(faceVerifyDataArr[length].mRegImg, 0, faceVerifyDataArr[length].cols, 0, 0, faceVerifyDataArr[length].cols, faceVerifyDataArr[length].rows);
        String strBitmapToJpegBase64 = BitmapUtils.bitmapToJpegBase64(bitmapCreateBitmap, 100);
        return (strBitmapToJpegBase64 == null || strBitmapToJpegBase64.length() <= 0) ? strBitmapToJpegBase64 : strBitmapToJpegBase64.replace("\\/", "/");
    }

    public ArrayList<String> getDetectBestImageList() throws Throwable {
        ArrayList<String> arrayList = new ArrayList<>();
        FaceVerifyData[] faceVerifyDataArr = this.mFaceTracker.get_FaceVerifyData(0);
        if (faceVerifyDataArr != null && faceVerifyDataArr.length > 0) {
            for (int i = 0; i < faceVerifyDataArr.length; i++) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(faceVerifyDataArr[i].cols, faceVerifyDataArr[i].rows, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.setPixels(faceVerifyDataArr[i].mRegImg, 0, faceVerifyDataArr[i].cols, 0, 0, faceVerifyDataArr[i].cols, faceVerifyDataArr[i].rows);
                String strBitmapToJpegBase64 = BitmapUtils.bitmapToJpegBase64(bitmapCreateBitmap, 100);
                if (strBitmapToJpegBase64 != null && strBitmapToJpegBase64.length() > 0) {
                    strBitmapToJpegBase64 = strBitmapToJpegBase64.replace("\\/", "/");
                }
                arrayList.add(strBitmapToJpegBase64);
            }
        }
        return arrayList;
    }

    @Override // com.baidu.idl.face.platform.ILiveness
    public FaceModel liveness(LivenessTypeEnum livenessTypeEnum, byte[] bArr, int i, int i2) {
        if (bArr == null || livenessTypeEnum == null || i <= 0 || i2 <= 0) {
            return null;
        }
        FaceModel faceModel = new FaceModel();
        FaceInfo[] faceInfoArrFaceTrackerDecode = faceTrackerDecode(bArr, i, i2);
        faceModel.setArgbImage(this.mArgbData);
        faceModel.setFaceInfos(getExtInfo(faceInfoArrFaceTrackerDecode));
        faceModel.setFaceModuleState(getModuleState(this.mErrCode));
        faceModel.setFrameTime(System.currentTimeMillis());
        return faceModel;
    }

    @Override // com.baidu.idl.face.platform.IDetect, com.baidu.idl.face.platform.ILiveness
    public void reset() {
        FaceTracker faceTracker = this.mFaceTracker;
        if (faceTracker != null) {
            faceTracker.re_collect_reg_imgs();
            this.mFaceTracker.clearTrackedFaces();
        }
    }

    public void setPreviewDegree(int i) {
        if (i < 0 || i > 360) {
            return;
        }
        this.mDegree = i;
    }

    public String getDetectBestImage() throws Throwable {
        FaceVerifyData[] faceVerifyDataArr = this.mFaceTracker.get_FaceVerifyData(0);
        if (faceVerifyDataArr == null || faceVerifyDataArr.length <= 0) {
            return "";
        }
        int length = faceVerifyDataArr.length - 1;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(faceVerifyDataArr[length].cols, faceVerifyDataArr[length].rows, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(faceVerifyDataArr[length].mRegImg, 0, faceVerifyDataArr[length].cols, 0, 0, faceVerifyDataArr[length].cols, faceVerifyDataArr[length].rows);
        String strBitmapToJpegBase64 = BitmapUtils.bitmapToJpegBase64(bitmapCreateBitmap, 100);
        return (strBitmapToJpegBase64 == null || strBitmapToJpegBase64.length() <= 0) ? strBitmapToJpegBase64 : strBitmapToJpegBase64.replace("\\/", "/");
    }
}
