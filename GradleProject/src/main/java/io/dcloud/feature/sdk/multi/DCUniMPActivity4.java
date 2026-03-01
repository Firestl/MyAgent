package io.dcloud.feature.sdk.multi;

import android.content.Intent;
import android.os.Bundle;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.feature.sdk.DCUniMPActivity;
import io.dcloud.feature.sdk.IDCUniMPServer;
import io.dcloud.feature.unimp.DCUniMPService;

/* JADX INFO: loaded from: classes3.dex */
public class DCUniMPActivity4 extends DCUniMPActivity {
    @Override // io.dcloud.feature.sdk.DCUniMPActivity
    public void bindMiniAppService() {
        bindService(new Intent(getContext(), (Class<?>) DCUniMPService4.class), this.mServiceConnection, 1);
    }

    @Override // io.dcloud.feature.sdk.DCUniMPActivity
    public void uniMPServerCallBack(String str, Bundle bundle) {
        IDCUniMPServer iDCUniMPServer = this.mServer;
        if (iDCUniMPServer != null) {
            try {
                iDCUniMPServer.callBack(str, bundle);
                if (RuningAcitvityUtil.isRunningProcess(getContext(), DCUniMPService.sProcessName)) {
                    return;
                }
                DCUniMPService.startHostService(getContext(), DCUniMPService.sHostServiceName);
                this.mServer.callBack("bindHostService", null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
