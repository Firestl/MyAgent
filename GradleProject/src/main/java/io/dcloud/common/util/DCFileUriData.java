package io.dcloud.common.util;

import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DCFileUriData {
    public String filePath;
    public String fileReplacePath;
    public Uri fileUri;
    public boolean isReplace = false;

    public void clear() {
        if (this.fileUri != null) {
            this.fileUri = null;
        }
        if (!TextUtils.isEmpty(this.filePath)) {
            this.filePath = null;
        }
        if (TextUtils.isEmpty(this.fileReplacePath)) {
            return;
        }
        this.fileReplacePath = null;
    }
}
