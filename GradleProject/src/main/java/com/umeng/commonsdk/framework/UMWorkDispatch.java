package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.taobao.weex.el.parse.Operators;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class UMWorkDispatch {
    public static final String GENERAL_CONTENT = "content";
    public static final String GENERAL_HEADER = "header";
    public static final String KEY_EXCEPTION = "exception";
    public static final int MSG_AUTO_PROCESS = 769;
    public static final int MSG_CHECKER_TIMER = 771;
    public static final int MSG_DELAY_PROCESS = 770;
    public static final int MSG_QUIT = 784;
    public static final int MSG_SEND_EVENT = 768;
    public static HandlerThread mNetTask;
    public static a mSender;
    public static Object mSenderInitLock = new Object();
    public static Handler mTaskHandler;

    public static void Quit() {
        Handler handler = mTaskHandler;
        if (handler != null) {
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.what = MSG_QUIT;
            mTaskHandler.sendMessage(messageObtainMessage);
        }
    }

    public static void delayProcess() {
        JSONObject jSONObjectBuildEnvelopeWithExtHeader;
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> delayProcess Enter...");
        Context appContext = UMModuleRegister.getAppContext();
        if (appContext == null || !UMFrUtils.isOnline(appContext)) {
            return;
        }
        long jMaxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
        JSONObject jSONObject = null;
        if (callbackFromModuleName != null) {
            try {
                jSONObject = callbackFromModuleName.setupReportData(jMaxDataSpace);
                if (jSONObject == null) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> analyticsCB.setupReportData() return null");
                    return;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(appContext, th);
                return;
            }
        }
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
        JSONObject jSONObject3 = (JSONObject) jSONObject.opt("content");
        if (appContext == null || jSONObject2 == null || jSONObject3 == null || (jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jSONObject2, jSONObject3)) == null) {
            return;
        }
        try {
            if (jSONObjectBuildEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: Build envelope error code: " + jSONObjectBuildEnvelopeWithExtHeader.getInt("exception"));
            }
        } catch (Throwable unused) {
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: removeCacheData ... ");
        callbackFromModuleName.removeCacheData(jSONObjectBuildEnvelopeWithExtHeader);
    }

    public static synchronized boolean eventHasExist(int i) {
        if (mTaskHandler == null) {
            return false;
        }
        return mTaskHandler.hasMessages(i);
    }

    public static void handleEvent(Message message) {
        int i = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(i));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i) + Operators.ARRAY_END_STR);
            callbackFromModuleName.workEvent(obj, i);
        }
    }

    public static void handleQuit() {
        if (mSender == null || mNetTask == null) {
            return;
        }
        a.c();
        ULog.d("--->>> handleQuit: Quit dispatch thread.");
        mNetTask.quit();
        teardown();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0033 A[Catch: all -> 0x003a, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:13:0x0033, B:12:0x002c, B:5:0x0008, B:7:0x000c, B:9:0x001d), top: B:19:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void init() {
        /*
            java.lang.Class<com.umeng.commonsdk.framework.UMWorkDispatch> r0 = com.umeng.commonsdk.framework.UMWorkDispatch.class
            monitor-enter(r0)
            java.lang.String r1 = "--->>> Dispatch: init Enter..."
            com.umeng.commonsdk.statistics.common.ULog.d(r1)     // Catch: java.lang.Throwable -> L3a
            android.os.HandlerThread r1 = com.umeng.commonsdk.framework.UMWorkDispatch.mNetTask     // Catch: java.lang.Throwable -> L2b
            if (r1 != 0) goto L33
            android.os.HandlerThread r1 = new android.os.HandlerThread     // Catch: java.lang.Throwable -> L2b
            java.lang.String r2 = "work_thread"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L2b
            com.umeng.commonsdk.framework.UMWorkDispatch.mNetTask = r1     // Catch: java.lang.Throwable -> L2b
            r1.start()     // Catch: java.lang.Throwable -> L2b
            android.os.Handler r1 = com.umeng.commonsdk.framework.UMWorkDispatch.mTaskHandler     // Catch: java.lang.Throwable -> L2b
            if (r1 != 0) goto L33
            com.umeng.commonsdk.framework.UMWorkDispatch$1 r1 = new com.umeng.commonsdk.framework.UMWorkDispatch$1     // Catch: java.lang.Throwable -> L2b
            android.os.HandlerThread r2 = com.umeng.commonsdk.framework.UMWorkDispatch.mNetTask     // Catch: java.lang.Throwable -> L2b
            android.os.Looper r2 = r2.getLooper()     // Catch: java.lang.Throwable -> L2b
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L2b
            com.umeng.commonsdk.framework.UMWorkDispatch.mTaskHandler = r1     // Catch: java.lang.Throwable -> L2b
            goto L33
        L2b:
            r1 = move-exception
            android.content.Context r2 = com.umeng.commonsdk.framework.UMModuleRegister.getAppContext()     // Catch: java.lang.Throwable -> L3a
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r2, r1)     // Catch: java.lang.Throwable -> L3a
        L33:
            java.lang.String r1 = "--->>> Dispatch: init Exit..."
            com.umeng.commonsdk.statistics.common.ULog.d(r1)     // Catch: java.lang.Throwable -> L3a
            monitor-exit(r0)
            return
        L3a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.UMWorkDispatch.init():void");
    }

    public static void registerConnStateObserver(UMSenderStateNotify uMSenderStateNotify) {
        if (mSender != null) {
            a.a(uMSenderStateNotify);
        }
    }

    public static synchronized void removeEvent() {
        if (mTaskHandler == null) {
            return;
        }
        mTaskHandler.removeMessages(MSG_CHECKER_TIMER);
    }

    public static void sendDelayProcessMsg(long j) {
        Handler handler = mTaskHandler;
        if (handler != null) {
            if (handler.hasMessages(MSG_DELAY_PROCESS)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
                return;
            }
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS not exist. send it.");
            Message messageObtainMessage = mTaskHandler.obtainMessage();
            messageObtainMessage.what = MSG_DELAY_PROCESS;
            mTaskHandler.sendMessageDelayed(messageObtainMessage, j);
        }
    }

    public static void sendEvent(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        sendEventInternal(context, 768, i, uMLogDataProtocol, obj, 0L);
    }

    public static void sendEventEx(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj, long j) {
        sendEventInternal(context, MSG_CHECKER_TIMER, i, uMLogDataProtocol, obj, j);
    }

    public static void sendEventInternal(Context context, int i, int i2, UMLogDataProtocol uMLogDataProtocol, Object obj, long j) {
        if (context == null || uMLogDataProtocol == null) {
            ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        UMModuleRegister.registerAppContext(context.getApplicationContext());
        if (UMModuleRegister.registerCallback(i2, uMLogDataProtocol)) {
            if (mNetTask == null || mTaskHandler == null) {
                init();
            }
            try {
                if (mTaskHandler != null) {
                    if (UMUtils.isMainProgress(context)) {
                        synchronized (mSenderInitLock) {
                            if (mSender == null) {
                                UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                                mSender = new a(context, mTaskHandler);
                            }
                        }
                    }
                    Message messageObtainMessage = mTaskHandler.obtainMessage();
                    messageObtainMessage.what = i;
                    messageObtainMessage.arg1 = i2;
                    messageObtainMessage.obj = obj;
                    mTaskHandler.sendMessageDelayed(messageObtainMessage, j);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
        }
    }

    public static void teardown() {
        if (mNetTask != null) {
            mNetTask = null;
        }
        if (mTaskHandler != null) {
            mTaskHandler = null;
        }
        if (mSender != null) {
            mSender = null;
        }
    }

    public static void sendEvent(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj, long j) {
        sendEventInternal(context, 768, i, uMLogDataProtocol, obj, j);
    }

    public static synchronized boolean eventHasExist() {
        if (mTaskHandler == null) {
            return false;
        }
        return mTaskHandler.hasMessages(MSG_CHECKER_TIMER);
    }

    public static synchronized void removeEvent(int i) {
        if (mTaskHandler == null) {
            return;
        }
        mTaskHandler.removeMessages(i);
    }
}
