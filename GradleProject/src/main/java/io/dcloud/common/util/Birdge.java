package io.dcloud.common.util;

import android.webkit.JavascriptInterface;
import io.dcloud.common.DHInterface.IJsInterface;
import io.dcloud.common.adapter.util.MessageHandler;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class Birdge implements IJsInterface, MessageHandler.IMessages {
    public IJsInterface mJsInterface;

    public Birdge(IJsInterface iJsInterface) {
        this.mJsInterface = iJsInterface;
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    @Deprecated
    public String exec(String str, String str2, String str3) {
        return this.mJsInterface.exec(str, str2, str3);
    }

    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
    public void execute(Object obj) {
        this.mJsInterface.forceStop((String) obj);
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    @JavascriptInterface
    public void forceStop(String str) {
        MessageHandler.sendMessage(this, str);
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    @JavascriptInterface
    public String prompt(String str, String str2) {
        return this.mJsInterface.prompt(str, str2);
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    public String exec(String str, String str2, JSONArray jSONArray) {
        return this.mJsInterface.exec(str, str2, jSONArray);
    }
}
