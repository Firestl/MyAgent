package io.dcloud.net;

import android.content.Context;
import android.os.Bundle;
import io.dcloud.common.DHInterface.IBoot;
import io.dcloud.common.DHInterface.ISysEventListener;

/* JADX INFO: loaded from: classes3.dex */
public class DownloaderBootImpl implements IBoot {
    @Override // io.dcloud.common.DHInterface.ISysEventListener
    public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
        return false;
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onPause() {
        DownloadJSMgr.getInstance().dispose();
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onResume() {
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onStart(Context context, Bundle bundle, String[] strArr) {
    }

    @Override // io.dcloud.common.DHInterface.IBoot
    public void onStop() {
    }
}
