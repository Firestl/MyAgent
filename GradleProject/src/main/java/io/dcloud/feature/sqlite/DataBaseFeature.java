package io.dcloud.feature.sqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DataBaseFeature extends StandardFeature {
    public HashMap<String, SQLiteDatabase> map = new HashMap<>();
    public String resultMessage = "{'code':%d,'message':\"%s\"}";

    public void closeDatabase(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        String strOptString2 = jSONArray.optString(1);
        SQLiteDatabase sQLiteDatabase = this.map.get(strOptString2);
        if (PdrUtil.isEmpty(strOptString2)) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("parameter can't be null")), JSUtil.ERROR, true, false);
        } else {
            if (sQLiteDatabase == null) {
                Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1401, DOMException.toString("Not Open")), JSUtil.ERROR, true, false);
                return;
            }
            sQLiteDatabase.close();
            this.map.remove(strOptString2);
            Deprecated_JSUtil.execCallback(iWebview, strOptString, "{}", JSUtil.OK, true, false);
        }
    }

    public void executeSql(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        String strOptString2 = jSONArray.optString(1);
        SQLiteDatabase sQLiteDatabase = this.map.get(strOptString2);
        String strOptString3 = jSONArray.optString(2);
        if (PdrUtil.isEmpty(strOptString2) || PdrUtil.isEmpty(strOptString3)) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("parameter can't be null")), JSUtil.ERROR, true, false);
            return;
        }
        if (sQLiteDatabase == null) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1401, DOMException.toString("Not Open")), JSUtil.ERROR, true, false);
            return;
        }
        try {
            JSONArray jSONArray2 = new JSONArray(strOptString3);
            if (jSONArray2.length() <= 0) {
                Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("parameter can't be null")), JSUtil.ERROR, true, false);
                return;
            }
            for (int i = 0; i < jSONArray2.length(); i++) {
                String strOptString4 = jSONArray2.optString(i);
                if (!TextUtils.isEmpty(strOptString4.trim())) {
                    sQLiteDatabase.execSQL(strOptString4);
                }
            }
            Deprecated_JSUtil.execCallback(iWebview, strOptString, "{}", JSUtil.OK, true, false);
        } catch (Exception e2) {
            try {
                if (!(e2 instanceof SQLException)) {
                    sQLiteDatabase.execSQL(strOptString3);
                    Deprecated_JSUtil.execCallback(iWebview, strOptString, "{}", JSUtil.OK, true, false);
                } else {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("code", -1404);
                        jSONObject.put("message", DOMException.toString(e2.toString()));
                    } catch (JSONException unused) {
                    }
                    JSUtil.execCallback(iWebview, strOptString, jSONObject, JSUtil.ERROR, false);
                }
            } catch (Exception e3) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("code", -1404);
                    jSONObject2.put("message", DOMException.toString(e3.toString()));
                } catch (JSONException unused2) {
                }
                JSUtil.execCallback(iWebview, strOptString, jSONObject2, JSUtil.ERROR, false);
            }
        }
    }

    public String isOpenDatabase(IWebview iWebview, JSONArray jSONArray) {
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        if (PdrUtil.isEmpty(jSONObjectOptJSONObject)) {
            return Deprecated_JSUtil.wrapJsVar("undefined", false);
        }
        String strOptString = jSONObjectOptJSONObject.optString("name");
        String strOptString2 = jSONObjectOptJSONObject.optString("path");
        if (PdrUtil.isEmpty(strOptString) || PdrUtil.isEmpty(strOptString2)) {
            return Deprecated_JSUtil.wrapJsVar("undefined", false);
        }
        Iterator<String> it = this.map.keySet().iterator();
        while (it.hasNext()) {
            SQLiteDatabase sQLiteDatabase = this.map.get(it.next());
            String strConvert2AbsFullPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), strOptString2);
            if (!PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
                strConvert2AbsFullPath = DeviceInfo.sBaseFsRootPath + strConvert2AbsFullPath;
            }
            if (strConvert2AbsFullPath.equalsIgnoreCase(sQLiteDatabase.getPath())) {
                return JSUtil.wrapJsVar(true);
            }
        }
        return this.map.containsKey(strOptString) ? JSUtil.wrapJsVar(true) : JSUtil.wrapJsVar(false);
    }

    public void openDatabase(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        String strOptString2 = jSONArray.optString(1);
        String strOptString3 = jSONArray.optString(2);
        if (PdrUtil.isEmpty(strOptString2) || PdrUtil.isEmpty(strOptString3)) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("parameter can't be null")), JSUtil.ERROR, true, false);
            return;
        }
        int i = 268435456;
        if (strOptString3.startsWith(BaseInfo.REL_PRIVATE_WWW_DIR) || (!PdrUtil.isDeviceRootDir(strOptString3) && !strOptString3.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR) && !strOptString3.startsWith(BaseInfo.REL_PUBLIC_DOCUMENTS_DIR) && !strOptString3.startsWith(BaseInfo.REL_PUBLIC_DOWNLOADS_DIR))) {
            i = 1;
        }
        String strConvert2AbsFullPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), strOptString3);
        if (!PdrUtil.isDeviceRootDir(strConvert2AbsFullPath)) {
            String str = DeviceInfo.sBaseFsRootPath + strConvert2AbsFullPath;
            if (!new File(str).exists()) {
                DHFile.copyAssetsFile(strConvert2AbsFullPath, str);
            }
            strConvert2AbsFullPath = iWebview.obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), str);
            i = 1;
        }
        if (!new File(strConvert2AbsFullPath).exists() && i == 1) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1403, DOMException.toString("Cannot create file private directory,such as:\\'www\\'")), JSUtil.ERROR, true, false);
            return;
        }
        File file = new File(strConvert2AbsFullPath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException unused) {
            }
        }
        if (this.map.containsKey(strOptString2)) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1402, DOMException.toString("Same Name Already Open")), JSUtil.ERROR, true, false);
            return;
        }
        try {
            this.map.put(strOptString2, SQLiteDatabase.openDatabase(strConvert2AbsFullPath, null, i, null));
            Deprecated_JSUtil.execCallback(iWebview, strOptString, "{}", JSUtil.OK, true, false);
        } catch (Exception e2) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", -1404);
                jSONObject.put("message", DOMException.toString(e2.toString()));
            } catch (JSONException unused2) {
            }
            JSUtil.execCallback(iWebview, strOptString, jSONObject, JSUtil.ERROR, false);
        }
    }

    public void selectSql(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        String strOptString2 = jSONArray.optString(1);
        SQLiteDatabase sQLiteDatabase = this.map.get(strOptString2);
        int i = 2;
        if (sQLiteDatabase == null) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1401, DOMException.toString("Not Open")), JSUtil.ERROR, true, false);
            return;
        }
        String strOptString3 = jSONArray.optString(2);
        if (PdrUtil.isEmpty(strOptString2) || PdrUtil.isEmpty(strOptString3)) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("parameter can't be null")), JSUtil.ERROR, true, false);
            return;
        }
        try {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery(strOptString3, null);
            JSONArray jSONArray2 = new JSONArray();
            try {
                if (cursorRawQuery.moveToFirst()) {
                    String[] columnNames = cursorRawQuery.getColumnNames();
                    while (true) {
                        JSONObject jSONObject = new JSONObject();
                        int i2 = 0;
                        while (i2 < columnNames.length) {
                            int type = cursorRawQuery.getType(i2);
                            if (type == 0) {
                                jSONObject.put(columnNames[i2], JSONObject.NULL);
                            } else if (type == 1 || type == i) {
                                jSONObject.put(columnNames[i2], new BigDecimal(String.valueOf(cursorRawQuery.getDouble(i2))).doubleValue());
                            } else if (type == 3) {
                                jSONObject.put(columnNames[i2], cursorRawQuery.getString(i2));
                            } else if (type == 4) {
                                try {
                                    jSONObject.put(columnNames[i2], Arrays.toString(cursorRawQuery.getBlob(i2)));
                                } catch (JSONException unused) {
                                }
                            }
                            i2++;
                            i = 2;
                        }
                        jSONArray2.put(jSONObject);
                        if (!cursorRawQuery.moveToNext()) {
                            break;
                        } else {
                            i = 2;
                        }
                    }
                }
                cursorRawQuery.close();
                JSUtil.execCallback(iWebview, strOptString, jSONArray2, JSUtil.OK, false);
            } catch (Exception e2) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("code", -1404);
                    jSONObject2.put("message", DOMException.toString(e2.toString()));
                } catch (JSONException unused2) {
                }
                JSUtil.execCallback(iWebview, strOptString, jSONObject2, JSUtil.ERROR, false);
            }
        } catch (Exception e3) {
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("code", -1404);
                jSONObject3.put("message", DOMException.toString(e3.toString()));
            } catch (JSONException unused3) {
            }
            JSUtil.execCallback(iWebview, strOptString, jSONObject3, JSUtil.ERROR, false);
        }
    }

    public void transaction(IWebview iWebview, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        String strOptString2 = jSONArray.optString(1);
        SQLiteDatabase sQLiteDatabase = this.map.get(strOptString2);
        String strOptString3 = jSONArray.optString(2);
        if (PdrUtil.isEmpty(strOptString2) || PdrUtil.isEmpty(strOptString3)) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("parameter can't be null")), JSUtil.ERROR, true, false);
            return;
        }
        if (sQLiteDatabase == null) {
            Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1401, DOMException.toString("Not Open")), JSUtil.ERROR, true, false);
            return;
        }
        try {
            if (strOptString3.equals("begin")) {
                sQLiteDatabase.beginTransaction();
            } else if (strOptString3.equals("commit")) {
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } else if (strOptString3.equals("rollback")) {
                sQLiteDatabase.endTransaction();
            } else {
                Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format(this.resultMessage, -1404, DOMException.toString("Operation Error")), JSUtil.ERROR, true, false);
            }
            Deprecated_JSUtil.execCallback(iWebview, strOptString, "{}", JSUtil.OK, true, false);
        } catch (Exception e2) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", -1404);
                jSONObject.put("message", DOMException.toString(e2.toString()));
            } catch (JSONException unused) {
            }
            JSUtil.execCallback(iWebview, strOptString, jSONObject, JSUtil.ERROR, false);
        }
    }
}
