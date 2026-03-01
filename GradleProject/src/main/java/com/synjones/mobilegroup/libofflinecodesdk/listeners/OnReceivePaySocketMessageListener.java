package com.synjones.mobilegroup.libofflinecodesdk.listeners;

import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketReceiveResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketStateResult;

/* JADX INFO: loaded from: classes2.dex */
public interface OnReceivePaySocketMessageListener {
    void onSocketReceiveDataReslut(ExternalSocketReceiveResult externalSocketReceiveResult);

    void onSocketStateResult(ExternalSocketStateResult externalSocketStateResult);
}
