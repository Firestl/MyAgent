package io.dcloud.feature.contacts;

import android.content.Context;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.igexin.push.core.b;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.feature.oauth.BaseOAuthService;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContactAccessor {
    public final String LOG_TAG = "ContactsAccessor";
    public Context mApp;
    public IWebview mView;

    public class WhereOptions {
        public String selection;
        public String[] selectionArgs;

        public WhereOptions() {
        }

        public String getSelection() {
            return this.selection;
        }

        public String[] getSelectionArgs() {
            return this.selectionArgs;
        }

        public void setSelection(String str) {
            this.selection = str;
        }

        public void setSelectionArgs(String[] strArr) {
            this.selectionArgs = strArr;
        }
    }

    public HashMap<String, Boolean> buildPopulationSet(JSONArray jSONArray) {
        String str;
        boolean z;
        String str2;
        String str3;
        JSONArray jSONArray2 = jSONArray;
        HashMap<String, Boolean> map = new HashMap<>();
        try {
            str = "categories";
            z = true;
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        if (jSONArray.length() != 0) {
            str2 = "photos";
            if (jSONArray.length() != 1 || !jSONArray2.getString(0).equals(Operators.MUL)) {
                int i = 0;
                while (i < jSONArray.length()) {
                    String string = jSONArray2.getString(i);
                    if (string.startsWith(FileProvider.DISPLAYNAME_FIELD)) {
                        map.put(FileProvider.DISPLAYNAME_FIELD, Boolean.valueOf(z));
                    } else if (string.startsWith("name")) {
                        map.put("name", Boolean.valueOf(z));
                    } else if (string.startsWith(BaseOAuthService.KEY_NICKNAME)) {
                        map.put(BaseOAuthService.KEY_NICKNAME, Boolean.valueOf(z));
                    } else if (string.startsWith("phoneNumbers")) {
                        map.put("phoneNumbers", Boolean.valueOf(z));
                    } else if (string.startsWith("emails")) {
                        map.put("emails", Boolean.valueOf(z));
                    } else if (string.startsWith("addresses")) {
                        map.put("addresses", Boolean.valueOf(z));
                    } else if (string.startsWith("ims")) {
                        map.put("ims", Boolean.valueOf(z));
                    } else if (string.startsWith("organizations")) {
                        map.put("organizations", Boolean.valueOf(z));
                    } else if (string.startsWith("birthday")) {
                        map.put("birthday", Boolean.valueOf(z));
                    } else if (string.startsWith("note")) {
                        map.put("note", Boolean.valueOf(z));
                    } else if (string.startsWith("urls")) {
                        map.put("urls", Boolean.valueOf(z));
                    } else {
                        String str4 = str2;
                        if (string.startsWith(str4)) {
                            map.put(str4, true);
                            str2 = str4;
                        } else {
                            str3 = str;
                            str2 = str4;
                            if (string.startsWith(str3)) {
                                map.put(str3, true);
                            }
                            i++;
                            str = str3;
                            z = true;
                            jSONArray2 = jSONArray;
                        }
                    }
                    str3 = str;
                    i++;
                    str = str3;
                    z = true;
                    jSONArray2 = jSONArray;
                }
                return map;
            }
        } else {
            str2 = "photos";
        }
        map.put(FileProvider.DISPLAYNAME_FIELD, true);
        map.put("name", true);
        map.put(BaseOAuthService.KEY_NICKNAME, true);
        map.put("phoneNumbers", true);
        map.put("emails", true);
        map.put("addresses", true);
        map.put("ims", true);
        map.put("organizations", true);
        map.put("birthday", true);
        map.put("note", true);
        map.put("urls", true);
        map.put(str2, true);
        map.put(str, true);
        return map;
    }

    public String getJsonString(JSONObject jSONObject, String str) {
        String strOptString;
        if (jSONObject == null || (strOptString = jSONObject.optString(str)) == null || b.m.equals(strOptString)) {
            return null;
        }
        return strOptString;
    }

    public boolean isRequired(String str, HashMap<String, Boolean> map) {
        Boolean bool = map.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public abstract boolean remove(String str);

    public abstract boolean save(JSONObject jSONObject);

    public abstract JSONArray search(JSONArray jSONArray, JSONObject jSONObject);
}
