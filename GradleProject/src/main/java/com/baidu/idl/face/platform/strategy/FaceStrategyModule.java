package com.baidu.idl.face.platform.strategy;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.idl.face.platform.common.LogHelper;
import com.baidu.idl.face.platform.decode.FaceModule;
import com.baidu.idl.facesdk.FaceTracker;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public abstract class FaceStrategyModule {
    public static final String TAG = "FaceStrategyModule";
    public static volatile int mProcessCount;
    public FaceModule mFaceModule;
    public byte[] mImageData;
    public long mLaunchTime = 0;
    public long mNoFaceTime = 0;
    public volatile boolean mIsProcessing = true;
    public volatile boolean mIsCompletion = false;
    public Handler mUIHandler = new Handler(Looper.getMainLooper());

    public class FaceProcessRunnable implements Runnable {
        public FaceProcessRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FaceStrategyModule faceStrategyModule = FaceStrategyModule.this;
            faceStrategyModule.processStrategy(faceStrategyModule.mImageData);
            FaceStrategyModule.access$106();
        }
    }

    public FaceStrategyModule(FaceTracker faceTracker) {
        this.mFaceModule = new FaceModule(faceTracker);
        LogHelper.clear();
        LogHelper.addLog("ca", "Baidu-IDL-FaceSDK3.1.0.0");
        LogHelper.addLog(ConstantHelper.LOG_OS, Integer.valueOf(Build.VERSION.SDK_INT));
        LogHelper.addLog("version", FaceEnvironment.SDK_VERSION);
        LogHelper.addLog(ConstantHelper.LOG_DE, Build.MODEL + Operators.SPACE_STR + Build.MANUFACTURER);
        LogHelper.addLog(ConstantHelper.LOG_STM, Long.valueOf(System.currentTimeMillis()));
    }

    public static /* synthetic */ int access$106() {
        int i = mProcessCount - 1;
        mProcessCount = i;
        return i;
    }

    public void process(byte[] bArr) {
        if (mProcessCount > 0) {
            return;
        }
        this.mImageData = bArr;
        new FaceProcessRunnable().run();
        mProcessCount++;
    }

    public abstract void processStrategy(byte[] bArr);

    public void processUIStrategy(Runnable runnable) {
        Handler handler = this.mUIHandler;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    public void processUIStrategyDelay(Runnable runnable, long j) {
        Handler handler = this.mUIHandler;
        if (handler != null) {
            handler.postDelayed(runnable, j);
        }
    }

    public void reset() {
        mProcessCount = 0;
        FaceModule faceModule = this.mFaceModule;
        if (faceModule != null) {
            faceModule.reset();
        }
    }
}
