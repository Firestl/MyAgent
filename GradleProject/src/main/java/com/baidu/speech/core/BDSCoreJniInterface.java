package com.baidu.speech.core;

import android.util.Log;
import com.baidu.speech.core.BDSSDKLoader;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class BDSCoreJniInterface implements BDSSDKLoader.BDSSDKInterface {
    public static HashMap<String, WeakReference<BDSCoreJniInterface>> s_sdkInstances = new HashMap<>();
    public WeakReference<BDSSDKLoader.BDSCoreEventListener> m_observer;
    public String m_sdkHandle;

    private native void EchoMessage(BDSMessage bDSMessage, String str);

    private native int Post(BDSMessage bDSMessage, String str);

    private native void ReleaseInstance(String str);

    public static void addInstance(String str, BDSCoreJniInterface bDSCoreJniInterface) {
        WeakReference<BDSCoreJniInterface> weakReference = new WeakReference<>(bDSCoreJniInterface);
        synchronized (s_sdkInstances) {
            s_sdkInstances.put(str, weakReference);
        }
    }

    public static BDSCoreJniInterface findInstance(String str) {
        synchronized (s_sdkInstances) {
            WeakReference<BDSCoreJniInterface> weakReference = s_sdkInstances.get(str);
            if (weakReference == null) {
                return null;
            }
            BDSCoreJniInterface bDSCoreJniInterface = weakReference.get();
            if (bDSCoreJniInterface == null) {
                removeInstance(str);
            }
            return bDSCoreJniInterface;
        }
    }

    public static BDSCoreJniInterface getNewSDK(String str) {
        String strInitCoreSDK = initCoreSDK(str);
        if (strInitCoreSDK == null || strInitCoreSDK.length() <= 0) {
            return null;
        }
        BDSCoreJniInterface bDSCoreJniInterface = new BDSCoreJniInterface();
        bDSCoreJniInterface.m_sdkHandle = strInitCoreSDK;
        addInstance(strInitCoreSDK, bDSCoreJniInterface);
        return bDSCoreJniInterface;
    }

    public static native String initCoreSDK(String str);

    public static void receiveCoreEvent(String str, BDSMessage bDSMessage) {
        StringBuilder sb;
        String str2;
        BDSCoreJniInterface bDSCoreJniInterfaceFindInstance = findInstance(str);
        if (bDSCoreJniInterfaceFindInstance != null) {
            BDSSDKLoader.BDSCoreEventListener bDSCoreEventListener = bDSCoreJniInterfaceFindInstance.m_observer.get();
            if (bDSCoreEventListener != null) {
                bDSCoreEventListener.receiveCoreEvent(bDSMessage, bDSCoreJniInterfaceFindInstance);
                return;
            } else {
                sb = new StringBuilder();
                str2 = "Listener is null for instance id ";
            }
        } else {
            sb = new StringBuilder();
            str2 = "Can't find instance for id ";
        }
        sb.append(str2);
        sb.append(str);
        Log.e("core event", sb.toString());
    }

    public static void removeInstance(String str) {
        synchronized (s_sdkInstances) {
            s_sdkInstances.remove(str);
        }
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSSDKInterface
    public void EchoMessage(BDSMessage bDSMessage) {
        EchoMessage(bDSMessage, this.m_sdkHandle);
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSSDKInterface
    public boolean instanceInitialized() {
        String str = this.m_sdkHandle;
        return str != null && str.length() > 0;
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSSDKInterface
    public int postMessage(BDSMessage bDSMessage) {
        return Post(bDSMessage, this.m_sdkHandle);
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSSDKInterface
    public void release() {
        if (instanceInitialized()) {
            ReleaseInstance(this.m_sdkHandle);
        }
        removeInstance(this.m_sdkHandle);
    }

    @Override // com.baidu.speech.core.BDSSDKLoader.BDSSDKInterface
    public void setListener(BDSSDKLoader.BDSCoreEventListener bDSCoreEventListener) {
        this.m_observer = new WeakReference<>(bDSCoreEventListener);
    }
}
