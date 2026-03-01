package io.dcloud.net;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.net.UploadMgr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsUploadMgr {
    public HashMap<String, ArrayList<JsUpload>> mAppsUploadTasks;
    public UploadMgr mUploadMgr = UploadMgr.getUploadMgr();

    public JsUploadMgr() {
        this.mAppsUploadTasks = null;
        this.mAppsUploadTasks = new HashMap<>();
    }

    private JsUpload createUploadTask(IWebview iWebview, JSONObject jSONObject) {
        return new JsUpload(iWebview, jSONObject);
    }

    private JSONArray enumerate(String str, ArrayList<JsUpload> arrayList) {
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                try {
                    jSONArray.put(new JSONObject(arrayList.get(i).toJsonUpload()));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    private JsUpload findUploadTask(String str, String str2) {
        ArrayList<JsUpload> arrayList = this.mAppsUploadTasks.get(str);
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                JsUpload jsUpload = arrayList.get(i);
                if (str2.equals(jsUpload.mUUID)) {
                    return jsUpload;
                }
            }
        }
        return null;
    }

    private void pushUploadTask(String str, JsUpload jsUpload) {
        ArrayList<JsUpload> arrayList = this.mAppsUploadTasks.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mAppsUploadTasks.put(str, arrayList);
        }
        arrayList.add(jsUpload);
    }

    public String execute(IWebview iWebview, String str, String[] strArr) {
        String strObtainAppId = iWebview.obtainFrameView().obtainApp().obtainAppId();
        if ("start".equals(str) || AbsoluteConst.EVENTS_RESUME.equals(str)) {
            JsUpload jsUploadFindUploadTask = findUploadTask(strObtainAppId, strArr[0]);
            if (jsUploadFindUploadTask != null && !jsUploadFindUploadTask.isStart) {
                this.mUploadMgr.start(jsUploadFindUploadTask.mUploadNetWork);
                jsUploadFindUploadTask.isStart = true;
            }
            String str2 = strArr[1];
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str2);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jsUploadFindUploadTask.setRequestHeader(next, jSONObject.getString(next));
                }
                return null;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        if ("pause".equals(str)) {
            JsUpload jsUploadFindUploadTask2 = findUploadTask(strObtainAppId, strArr[0]);
            if (jsUploadFindUploadTask2 == null || !jsUploadFindUploadTask2.isStart) {
                return null;
            }
            this.mUploadMgr.abort(jsUploadFindUploadTask2.mUploadNetWork);
            jsUploadFindUploadTask2.isStart = false;
            return null;
        }
        if ("abort".equals(str)) {
            JsUpload jsUploadFindUploadTask3 = findUploadTask(strObtainAppId, strArr[0]);
            if (jsUploadFindUploadTask3 == null) {
                return null;
            }
            this.mUploadMgr.abort(jsUploadFindUploadTask3.mUploadNetWork);
            this.mAppsUploadTasks.get(strObtainAppId).remove(jsUploadFindUploadTask3);
            return null;
        }
        if ("createUpload".equals(str)) {
            try {
                pushUploadTask(strObtainAppId, createUploadTask(iWebview, new JSONObject(strArr[0])));
                return null;
            } catch (JSONException e3) {
                e3.printStackTrace();
                return null;
            }
        }
        if ("enumerate".equals(str)) {
            JSUtil.execCallback(iWebview, strArr[0], enumerate(strArr[0], this.mAppsUploadTasks.get(strObtainAppId)), JSUtil.OK, false);
            return null;
        }
        if ("clear".equals(str)) {
            ArrayList<JsUpload> arrayList = this.mAppsUploadTasks.get(strObtainAppId);
            int i = Integer.parseInt(strArr[0]);
            if (arrayList == null) {
                return null;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                JsUpload jsUpload = arrayList.get(size);
                if (jsUpload != null && i == jsUpload.mState) {
                    UploadMgr.getUploadMgr().abort(jsUpload.mUploadNetWork);
                    arrayList.remove(size);
                }
            }
            return null;
        }
        if ("startAll".equals(str)) {
            ArrayList<JsUpload> arrayList2 = this.mAppsUploadTasks.get(strObtainAppId);
            if (arrayList2 == null) {
                return null;
            }
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                this.mUploadMgr.start(arrayList2.get(i2).mUploadNetWork);
            }
            return null;
        }
        if (!"addFile".equals(str)) {
            if (!"addData".equals(str)) {
                return null;
            }
            findUploadTask(strObtainAppId, strArr[0]).addData(strArr[1], strArr[2]);
            return null;
        }
        try {
            findUploadTask(strObtainAppId, strArr[0]).addFile(iWebview, iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), strArr[1]), new JSONObject(strArr[2]));
            return null;
        } catch (JSONException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
