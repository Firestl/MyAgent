package io.dcloud.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadJSMgr {
    public static final int DELETE = 2;
    public static final int SAVE = 1;
    public static DownloadJSMgr mDownloadJSMgr;
    public HashMap<String, AppDownloadInfo> mAppsDownloadTasks;
    public AppDownloadInfo curAppSharePref = null;
    public Handler mHander = new Handler(Looper.getMainLooper()) { // from class: io.dcloud.net.DownloadJSMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                String[] strArr = (String[]) message.obj;
                String str = strArr[0];
                String str2 = strArr[1];
                String str3 = strArr[2];
                SharedPreferences.Editor editorEdit = DownloadJSMgr.this.getAppTaskList(str).sharePref.edit();
                editorEdit.putString(str2, str3);
                editorEdit.commit();
                return;
            }
            if (i == 2) {
                String[] strArr2 = (String[]) message.obj;
                String str4 = strArr2[0];
                String str5 = strArr2[1];
                SharedPreferences sharedPreferences = DownloadJSMgr.this.getAppTaskList(str4).sharePref;
                if (TextUtils.isEmpty(sharedPreferences.getString(str5, ""))) {
                    return;
                }
                sharedPreferences.edit().remove(str5).commit();
            }
        }
    };

    public class AppDownloadInfo {
        public String appid;
        public ArrayList<JsDownload> mList;
        public SharedPreferences sharePref;

        public AppDownloadInfo(Context context, IWebview iWebview, String str) {
            this.mList = null;
            this.appid = str;
            this.sharePref = context.getSharedPreferences(str + JsDownload.DOWNLOAD_NAME, 0);
            this.mList = new ArrayList<>();
            Map<String, ?> all = this.sharePref.getAll();
            if (all != null) {
                Iterator<String> it = all.keySet().iterator();
                ArrayList<JsDownload> arrayList = this.mList;
                while (it.hasNext()) {
                    try {
                        arrayList.add(new JsDownload(DownloadJSMgr.this, iWebview, new JSONObject((String) all.get(it.next()))));
                    } catch (JSONException unused) {
                    }
                }
                this.mList = arrayList;
            }
        }
    }

    public DownloadJSMgr() {
        this.mAppsDownloadTasks = null;
        if (this.mAppsDownloadTasks == null) {
            this.mAppsDownloadTasks = new HashMap<>();
        }
    }

    private JsDownload createDownloadTask(IWebview iWebview, JSONObject jSONObject) {
        return new JsDownload(this, iWebview, jSONObject);
    }

    private void enumerate(IWebview iWebview, String str, String str2, String str3) {
        String strEnumerateArr;
        AppDownloadInfo appTaskList = getAppTaskList(str3);
        try {
            int i = Integer.parseInt(str2);
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
                ArrayList<JsDownload> arrayList = new ArrayList<>();
                if (appTaskList != null && !appTaskList.mList.isEmpty()) {
                    int size = appTaskList.mList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        JsDownload jsDownload = appTaskList.mList.get(i2);
                        if (jsDownload.mWebview.obtainApp() == null) {
                            jsDownload.mWebview = iWebview;
                        }
                        if (i == jsDownload.mState) {
                            arrayList.add(jsDownload);
                        }
                    }
                }
                strEnumerateArr = enumerateArr(iWebview, arrayList);
            } else {
                strEnumerateArr = enumerateArr(iWebview, appTaskList.mList);
            }
        } catch (Exception unused) {
            ArrayList<JsDownload> arrayList2 = new ArrayList<>();
            if (appTaskList != null && !appTaskList.mList.isEmpty()) {
                int size2 = appTaskList.mList.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    JsDownload jsDownload2 = appTaskList.mList.get(i3);
                    jsDownload2.mWebview = iWebview;
                    if (jsDownload2.mState != 4) {
                        arrayList2.add(jsDownload2);
                    }
                }
            }
            strEnumerateArr = enumerateArr(iWebview, arrayList2);
        }
        Deprecated_JSUtil.execCallback(iWebview, str, strEnumerateArr, JSUtil.OK, true, false);
    }

    private String enumerateArr(IWebview iWebview, ArrayList<JsDownload> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Operators.ARRAY_START_STR);
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                JsDownload jsDownload = arrayList.get(i);
                stringBuffer.append(jsDownload.toSaveJSON());
                jsDownload.addRelWebview(iWebview);
                if (i != size - 1) {
                    stringBuffer.append(",");
                }
            }
        }
        stringBuffer.append(Operators.ARRAY_END_STR);
        return stringBuffer.toString();
    }

    private JsDownload findDownloadTask(IWebview iWebview, String str, String str2) {
        AppDownloadInfo appTaskList = getAppTaskList(str);
        if (appTaskList != null) {
            int size = appTaskList.mList.size();
            for (int i = 0; i < size; i++) {
                JsDownload jsDownload = appTaskList.mList.get(i);
                if (str2.equals(jsDownload.mUUID)) {
                    if (jsDownload.mWebview.obtainApp() != null) {
                        return jsDownload;
                    }
                    jsDownload.mWebview = iWebview;
                    return jsDownload;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppDownloadInfo getAppTaskList(String str) {
        return this.mAppsDownloadTasks.get(str);
    }

    public static DownloadJSMgr getInstance() {
        if (mDownloadJSMgr == null) {
            mDownloadJSMgr = new DownloadJSMgr();
        }
        return mDownloadJSMgr;
    }

    private void initAppDownloadList(IWebview iWebview, String str) {
        if (this.mAppsDownloadTasks.containsKey(str)) {
            return;
        }
        this.mAppsDownloadTasks.put(str, new AppDownloadInfo(iWebview.getContext(), iWebview, str));
    }

    private void pushDownloadTask(IWebview iWebview, String str, JsDownload jsDownload) {
        AppDownloadInfo appDownloadInfo = this.mAppsDownloadTasks.get(str);
        if (appDownloadInfo == null) {
            appDownloadInfo = new AppDownloadInfo(iWebview.getContext(), iWebview, str);
            this.mAppsDownloadTasks.put(str, appDownloadInfo);
        }
        appDownloadInfo.mList.add(jsDownload);
    }

    public void deleteDownloadTaskInfo(String str, String str2) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = new String[]{str, str2};
        this.mHander.sendMessage(messageObtain);
    }

    public void dispose() {
        Iterator<String> it = this.mAppsDownloadTasks.keySet().iterator();
        while (it.hasNext()) {
            Iterator<JsDownload> it2 = getAppTaskList(it.next()).mList.iterator();
            while (it2.hasNext()) {
                it2.next().saveInDatabase();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r7, java.lang.String r8, java.lang.String[] r9) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.net.DownloadJSMgr.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public void saveDownloadTaskInfo(String str, String str2, String str3) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = new String[]{str, str2, str3};
        this.mHander.sendMessage(messageObtain);
    }
}
