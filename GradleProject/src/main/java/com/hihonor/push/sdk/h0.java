package com.hihonor.push.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.hihonor.push.framework.aidl.DataBuffer;
import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.IPushCallback;
import com.hihonor.push.framework.aidl.MessageCodec;
import com.hihonor.push.framework.aidl.entity.ResponseHeader;
import com.hihonor.push.sdk.common.data.ApiException;
import supwisdom.bu0;
import supwisdom.du0;
import supwisdom.pu0;

/* JADX INFO: loaded from: classes.dex */
public class h0 extends IPushCallback.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2585a;
    public final du0 b;

    public h0(Object obj, du0 du0Var) {
        this.f2585a = obj;
        this.b = du0Var;
    }

    @Override // com.hihonor.push.framework.aidl.IPushCallback
    public void onResult(DataBuffer dataBuffer) {
        Log.i("IPCCallback", "onResult parse start.");
        Bundle header = dataBuffer.getHeader();
        Bundle body = dataBuffer.getBody();
        ResponseHeader responseHeader = new ResponseHeader();
        MessageCodec.parseMessageEntity(header, responseHeader);
        Object obj = this.f2585a;
        if (obj instanceof IMessageEntity) {
            MessageCodec.parseMessageEntity(body, (IMessageEntity) obj);
        }
        du0 du0Var = this.b;
        ApiException apiException = new ApiException(responseHeader.getStatusCode(), responseHeader.getStatusMessage());
        Object obj2 = this.f2585a;
        pu0.b bVar = (pu0.b) du0Var;
        bVar.getClass();
        pu0 pu0Var = pu0.c;
        bu0<?> bu0Var = bVar.f8833a;
        pu0Var.getClass();
        Log.i("HonorApiManager", "sendResolveResult start");
        Handler handler = pu0Var.f8830a;
        handler.sendMessage(handler.obtainMessage(2, bu0Var));
        bVar.f8833a.b(apiException, obj2);
        Log.i("IPCCallback", "onResult parse end.");
    }
}
