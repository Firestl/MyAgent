package com.igexin.sdk.main;

import android.content.Context;
import com.igexin.c.a.c.a;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class SdkInitSwitch {
    public String mSdkSwitchPath;

    public SdkInitSwitch(Context context) {
        if (context == null) {
            return;
        }
        this.mSdkSwitchPath = context.getFilesDir().getPath() + "/init.pid";
    }

    public void delete() {
        File file = new File(this.mSdkSwitchPath);
        if (file.exists()) {
            file.delete();
        }
    }

    public boolean isSwitchOn() {
        if (this.mSdkSwitchPath != null) {
            return new File(this.mSdkSwitchPath).exists();
        }
        return false;
    }

    public void switchOn() {
        if (isSwitchOn() || this.mSdkSwitchPath == null) {
            return;
        }
        try {
            new File(this.mSdkSwitchPath).createNewFile();
        } catch (IOException e2) {
            a.a(e2);
        }
    }
}
