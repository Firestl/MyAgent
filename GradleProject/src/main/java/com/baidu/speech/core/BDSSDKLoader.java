package com.baidu.speech.core;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BDSSDKLoader {

    public interface BDSCoreEventListener {
        void receiveCoreEvent(BDSMessage bDSMessage, BDSSDKInterface bDSSDKInterface);
    }

    public interface BDSSDKInterface {
        void EchoMessage(BDSMessage bDSMessage);

        boolean instanceInitialized();

        int postMessage(BDSMessage bDSMessage);

        void release();

        void setListener(BDSCoreEventListener bDSCoreEventListener);
    }

    public static native void SetLogLevel(int i);

    public static native int getEngineVersion();

    public static BDSSDKInterface getSDKObjectForSDKType(String str, Context context) {
        String str2 = context.getApplicationInfo().nativeLibraryDir;
        if (!str2.endsWith("/") && str2.length() > 0) {
            str2 = str2 + "/";
        }
        setLibrarySearchPath(str2);
        setJavaContext(context);
        String str3 = context.getCacheDir().getAbsolutePath() + "/";
        String str4 = context.getFilesDir().getAbsolutePath() + "/";
        makeDir(str4);
        setWriteableTempPath(str3);
        setWriteableLibraryDataPath(str4);
        setWriteableUserDataPath(str4);
        return BDSCoreJniInterface.getNewSDK(str);
    }

    public static void loadLibraries() throws Exception {
        try {
            System.loadLibrary("BaiduSpeechSDK");
        } catch (Throwable th) {
            th.printStackTrace();
            throw new IOException("Can not load BaiduSpeechSDK library");
        }
    }

    public static boolean makeDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() ? file.isDirectory() && file.canWrite() : file.mkdirs();
    }

    public static native void setJavaContext(Context context);

    public static native void setLibrarySearchPath(String str);

    public static native void setWriteableLibraryDataPath(String str);

    public static native void setWriteableTempPath(String str);

    public static native void setWriteableUserDataPath(String str);
}
