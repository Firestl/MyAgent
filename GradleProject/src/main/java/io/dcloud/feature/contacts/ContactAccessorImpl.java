package io.dcloud.feature.contacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.huawei.secure.android.common.util.LogsUtil;
import com.igexin.push.core.b;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.WXUtils;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.bq;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.feature.contacts.ContactAccessor;
import io.dcloud.feature.oauth.BaseOAuthService;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ContactAccessorImpl extends ContactAccessor {
    public static final String EMAIL_REGEXP = ".+@.+\\.+.+";
    public static final long MAX_PHOTO_SIZE = 1048576;
    public static final String Tag = "contacts";
    public static final Map<String, String> dbMap;

    static {
        HashMap map = new HashMap();
        dbMap = map;
        map.put("id", "raw_contact_id");
        dbMap.put(FileProvider.DISPLAYNAME_FIELD, bm.s);
        dbMap.put("name", "data1");
        dbMap.put("name.formatted", "data1");
        dbMap.put("name.familyName", "data3");
        dbMap.put("name.givenName", "data2");
        dbMap.put("name.middleName", "data5");
        dbMap.put("name.honorificPrefix", "data4");
        dbMap.put("name.honorificSuffix", "data6");
        dbMap.put(BaseOAuthService.KEY_NICKNAME, "data1");
        dbMap.put("phoneNumbers", "data1");
        dbMap.put("phoneNumbers.value", "data1");
        dbMap.put("emails", "data1");
        dbMap.put("emails.value", "data1");
        dbMap.put("addresses", "data1");
        dbMap.put("addresses.formatted", "data1");
        dbMap.put("addresses.streetAddress", "data4");
        dbMap.put("addresses.locality", "data7");
        dbMap.put("addresses.region", "data8");
        dbMap.put("addresses.postalCode", "data9");
        dbMap.put("addresses.country", "data10");
        dbMap.put("ims", "data1");
        dbMap.put("ims.value", "data1");
        dbMap.put("organizations", "data1");
        dbMap.put("organizations.name", "data1");
        dbMap.put("organizations.department", "data5");
        dbMap.put("organizations.title", "data4");
        dbMap.put("birthday", "vnd.android.cursor.item/contact_event");
        dbMap.put("note", "data1");
        dbMap.put("photos.value", "vnd.android.cursor.item/photo");
        dbMap.put("urls", "data1");
        dbMap.put("urls.value", "data1");
    }

    public ContactAccessorImpl(Context context) {
        this.mApp = context;
    }

    private JSONObject addressQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("type", getAddressType(cursor.getInt(cursor.getColumnIndex("data2"))));
            jSONObject.put("formatted", cursor.getString(cursor.getColumnIndex("data1")));
            jSONObject.put("streetAddress", cursor.getString(cursor.getColumnIndex("data4")));
            jSONObject.put("locality", cursor.getString(cursor.getColumnIndex("data7")));
            jSONObject.put("region", cursor.getString(cursor.getColumnIndex("data8")));
            jSONObject.put("postalCode", cursor.getString(cursor.getColumnIndex("data9")));
            jSONObject.put(bm.O, cursor.getString(cursor.getColumnIndex("data10")));
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        return jSONObject;
    }

    private ContactAccessor.WhereOptions buildIdClause(ArrayList<String> arrayList) {
        ContactAccessor.WhereOptions whereOptions = new ContactAccessor.WhereOptions();
        Iterator<String> it = arrayList.iterator();
        StringBuffer stringBuffer = new StringBuffer("(");
        while (it.hasNext()) {
            stringBuffer.append("'" + it.next() + "'");
            if (it.hasNext()) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(")");
        whereOptions.setSelection("raw_contact_id IN " + stringBuffer.toString());
        whereOptions.setSelectionArgs(null);
        return whereOptions;
    }

    private ContactAccessor.WhereOptions buildWhereClause(JSONArray jSONArray) {
        int length;
        ContactAccessor.WhereOptions whereOptions = new ContactAccessor.WhereOptions();
        if (jSONArray != null && (length = jSONArray.length()) > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = JSONUtil.getJSONObject(jSONArray, i);
                String string = JSONUtil.getString(jSONObject, "field");
                String logic = formatLogic(JSONUtil.getString(jSONObject, "logic"));
                String string2 = JSONUtil.getString(jSONObject, "value");
                if (string != null && dbMap.containsKey(string) && string2 != null && !string2.equals("")) {
                    String strReplace = string2.replace(Operators.CONDITION_IF, '_').replace(LogsUtil.b, WXUtils.PERCENT);
                    stringBuffer.append("(" + dbMap.get(string) + " LIKE ? )");
                    if (i != length - 1) {
                        stringBuffer.append(Operators.SPACE_STR + logic + Operators.SPACE_STR);
                    }
                    arrayList.add(strReplace);
                    z = true;
                }
            }
            if (z) {
                whereOptions.setSelection(stringBuffer.toString());
                String[] strArr = new String[arrayList.size()];
                arrayList.toArray(strArr);
                whereOptions.setSelectionArgs(strArr);
            }
        }
        return whereOptions;
    }

    private boolean createNewContact(JSONObject jSONObject, Account account) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        if (account != null) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", account.type).withValue("account_name", account.name).build());
        } else {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("aggregation_mode", 3).build());
        }
        arrayList2.add(jSONObject);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("name");
        String jsonString = getJsonString(jSONObject, FileProvider.DISPLAYNAME_FIELD);
        if (jsonString != null || jSONObjectOptJSONObject != null) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", jsonString).withValue("data3", getJsonString(jSONObjectOptJSONObject, "familyName")).withValue("data5", getJsonString(jSONObjectOptJSONObject, "middleName")).withValue("data2", getJsonString(jSONObjectOptJSONObject, "givenName")).withValue("data4", getJsonString(jSONObjectOptJSONObject, "honorificPrefix")).withValue("data6", getJsonString(jSONObjectOptJSONObject, "honorificSuffix")).build());
            arrayList2.add(jSONObjectOptJSONObject);
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("phoneNumbers");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    insertPhone(arrayList, (JSONObject) jSONArray.get(i));
                }
            }
        } catch (JSONException unused) {
            Log.d("ContactsAccessor", "Could not get phone numbers");
        }
        try {
            JSONArray jSONArray2 = jSONObject.getJSONArray("emails");
            if (jSONArray2 != null) {
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArray2.get(i2);
                    insertEmail(arrayList, jSONObject2);
                    arrayList2.add(jSONObject2);
                }
            }
        } catch (JSONException unused2) {
            Log.d("ContactsAccessor", "Could not get emails");
        }
        try {
            JSONArray jSONArray3 = jSONObject.getJSONArray("addresses");
            if (jSONArray3 != null) {
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray3.get(i3);
                    insertAddress(arrayList, jSONObject3);
                    arrayList2.add(jSONObject3);
                }
            }
        } catch (JSONException unused3) {
            Log.d("ContactsAccessor", "Could not get addresses");
        }
        try {
            JSONArray jSONArray4 = jSONObject.getJSONArray("organizations");
            if (jSONArray4 != null) {
                for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                    JSONObject jSONObject4 = (JSONObject) jSONArray4.get(i4);
                    insertOrganization(arrayList, jSONObject4);
                    arrayList2.add(jSONObject4);
                }
            }
        } catch (JSONException unused4) {
            Log.d("ContactsAccessor", "Could not get organizations");
        }
        try {
            JSONArray jSONArray5 = jSONObject.getJSONArray("ims");
            if (jSONArray5 != null) {
                for (int i5 = 0; i5 < jSONArray5.length(); i5++) {
                    JSONObject jSONObject5 = (JSONObject) jSONArray5.get(i5);
                    insertIm(arrayList, jSONObject5);
                    arrayList2.add(jSONObject5);
                }
            }
        } catch (JSONException unused5) {
            Log.d("ContactsAccessor", "Could not get emails");
        }
        try {
            JSONArray jSONArray6 = jSONObject.getJSONArray("websites");
            if (jSONArray6 != null) {
                for (int i6 = 0; i6 < jSONArray6.length(); i6++) {
                    JSONObject jSONObject6 = (JSONObject) jSONArray6.get(i6);
                    insertWebsite(arrayList, jSONObject6);
                    arrayList2.add(jSONObject6);
                }
            }
        } catch (JSONException unused6) {
            Log.d("ContactsAccessor", "Could not get websites");
        }
        try {
            JSONArray jSONArray7 = jSONObject.getJSONArray("photos");
            if (jSONArray7 != null) {
                for (int i7 = 0; i7 < jSONArray7.length(); i7++) {
                    JSONObject jSONObject7 = (JSONObject) jSONArray7.get(i7);
                    insertPhoto(arrayList, jSONObject7);
                    arrayList2.add(jSONObject7);
                }
            }
        } catch (JSONException unused7) {
            Log.d("ContactsAccessor", "Could not get photos");
        }
        String jsonString2 = getJsonString(jSONObject, "birthday");
        if (jsonString2 != null) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/contact_event").withValue("data2", 3).withValue("data1", jsonString2).build());
        }
        String jsonString3 = getJsonString(jSONObject, "note");
        if (jsonString3 != null) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", jsonString3).build());
        }
        String jsonString4 = getJsonString(jSONObject, BaseOAuthService.KEY_NICKNAME);
        if (jsonString4 != null) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/nickname").withValue("data1", jsonString4).build());
        }
        try {
            ContentProviderResult[] contentProviderResultArrApplyBatch = this.mApp.getContentResolver().applyBatch("com.android.contacts", arrayList);
            if (contentProviderResultArrApplyBatch.length >= 0) {
                for (int i8 = 0; i8 < arrayList2.size(); i8++) {
                    String lastPathSegment = contentProviderResultArrApplyBatch[i8].uri.getLastPathSegment();
                    JSONObject jSONObject8 = (JSONObject) arrayList2.get(i8);
                    if (jSONObject8 != null) {
                        jSONObject8.put("id", lastPathSegment);
                    }
                }
            }
            return true;
        } catch (OperationApplicationException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
            return false;
        } catch (RemoteException e3) {
            Log.e("ContactsAccessor", e3.getMessage(), e3);
            return false;
        } catch (JSONException e4) {
            Logger.e(e4.getMessage());
            return false;
        }
    }

    private JSONObject emailQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("value", cursor.getString(cursor.getColumnIndex("data1")));
            jSONObject.put("type", getContactType(cursor.getInt(cursor.getColumnIndex("data2"))));
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        return jSONObject;
    }

    private String formatLogic(String str) {
        String lowerCase = str != null ? str.toLowerCase() : "or";
        return (lowerCase.equals("not") || lowerCase.equals("and")) ? lowerCase : "or";
    }

    private int getAddressType(String str) {
        if (str != null) {
            if ("work".equals(str.toLowerCase())) {
                return 2;
            }
            if (!"other".equals(str.toLowerCase()) && "home".equals(str.toLowerCase())) {
                return 1;
            }
        }
        return 3;
    }

    private String getAddressType(int i) {
        return i != 1 ? i != 2 ? "other" : "work" : "home";
    }

    private int getContactType(String str) {
        if (str != null) {
            if ("home".equals(str.toLowerCase())) {
                return 1;
            }
            if ("work".equals(str.toLowerCase())) {
                return 2;
            }
            if ("other".equals(str.toLowerCase())) {
                return 3;
            }
            if ("mobile".equals(str.toLowerCase())) {
                return 4;
            }
            if ("custom".equals(str.toLowerCase())) {
                return 0;
            }
        }
        return 3;
    }

    private String getContactType(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 4 ? "other" : "mobile" : "work" : "home" : "custom";
    }

    private int getOrgType(String str) {
        if (str != null) {
            if ("work".equals(str.toLowerCase())) {
                return 1;
            }
            if (!"other".equals(str.toLowerCase()) && "custom".equals(str.toLowerCase())) {
                return 0;
            }
        }
        return 2;
    }

    private String getOrgType(int i) {
        return i != 0 ? i != 1 ? "other" : "work" : "custom";
    }

    private InputStream getPathFromUri(String str) throws IOException {
        if (str.startsWith("content:")) {
            return this.mApp.getContentResolver().openInputStream(Uri.parse(str));
        }
        return (str.startsWith("http:") || str.startsWith("file:")) ? new URL(str).openStream() : this.mView.obtainFrameView().obtainApp().obtainResInStream(this.mView.obtainFullUrl(), str);
    }

    private int getPhoneType(String str) {
        if ("home".equals(str.toLowerCase())) {
            return 1;
        }
        if ("mobile".equals(str.toLowerCase())) {
            return 2;
        }
        if ("work".equals(str.toLowerCase())) {
            return 3;
        }
        if ("work fax".equals(str.toLowerCase())) {
            return 4;
        }
        if ("home fax".equals(str.toLowerCase())) {
            return 5;
        }
        if ("fax".equals(str.toLowerCase())) {
            return 4;
        }
        if ("pager".equals(str.toLowerCase())) {
            return 6;
        }
        if ("other".equals(str.toLowerCase())) {
            return 7;
        }
        if ("car".equals(str.toLowerCase())) {
            return 9;
        }
        if ("company main".equals(str.toLowerCase())) {
            return 10;
        }
        if ("isdn".equals(str.toLowerCase())) {
            return 11;
        }
        if ("main".equals(str.toLowerCase())) {
            return 12;
        }
        if ("other fax".equals(str.toLowerCase())) {
            return 13;
        }
        if ("radio".equals(str.toLowerCase())) {
            return 14;
        }
        if ("telex".equals(str.toLowerCase())) {
            return 15;
        }
        if ("work mobile".equals(str.toLowerCase())) {
            return 17;
        }
        if ("work pager".equals(str.toLowerCase())) {
            return 18;
        }
        if ("assistant".equals(str.toLowerCase())) {
            return 19;
        }
        if ("mms".equals(str.toLowerCase())) {
            return 20;
        }
        if (WXBridgeManager.METHOD_CALLBACK.equals(str.toLowerCase())) {
            return 8;
        }
        if ("tty ttd".equals(str.toLowerCase())) {
            return 16;
        }
        return "custom".equals(str.toLowerCase()) ? 0 : 7;
    }

    private String getPhoneType(int i) {
        switch (i) {
            case 0:
                return "custom";
            case 1:
                return "home";
            case 2:
                return "mobile";
            case 3:
                return "work";
            case 4:
                return "work fax";
            case 5:
                return "home fax";
            case 6:
                return "pager";
            case 7:
            case 12:
            default:
                return "other";
            case 8:
                return WXBridgeManager.METHOD_CALLBACK;
            case 9:
                return "car";
            case 10:
                return "company main";
            case 11:
                return "isdn";
            case 13:
                return "other fax";
            case 14:
                return "radio";
            case 15:
                return "telex";
            case 16:
                return "tty tdd";
            case 17:
                return "work mobile";
            case 18:
                return "work pager";
            case 19:
                return "assistant";
            case 20:
                return "mms";
        }
    }

    private byte[] getPhotoBytes(String str) {
        int iIndexOf = str.indexOf(";base64,");
        if (iIndexOf > 0) {
            String strSubstring = str.substring(iIndexOf + 8);
            return DeviceInfo.sDeviceSdkVer >= 8 ? Base64.decode(strSubstring, 2) : io.dcloud.common.util.Base64.decode2bytes(strSubstring);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long j = 0;
        try {
            InputStream pathFromUri = getPathFromUri(str);
            if (pathFromUri != null) {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = pathFromUri.read(bArr, 0, 8192);
                    if (i == -1 || j > 1048576) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                    j += (long) i;
                }
                pathFromUri.close();
                byteArrayOutputStream.flush();
            }
        } catch (FileNotFoundException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        } catch (IOException e3) {
            Log.e("ContactsAccessor", e3.getMessage(), e3);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private JSONObject imQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("value", cursor.getString(cursor.getColumnIndex("data1")));
            jSONObject.put("type", getContactType(cursor.getInt(cursor.getColumnIndex("data2"))));
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        return jSONObject;
    }

    private void insertAddress(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data2", Integer.valueOf(getAddressType(getJsonString(jSONObject, "type")))).withValue("data1", getJsonString(jSONObject, "formatted")).withValue("data4", getJsonString(jSONObject, "streetAddress")).withValue("data7", getJsonString(jSONObject, "locality")).withValue("data8", getJsonString(jSONObject, "region")).withValue("data9", getJsonString(jSONObject, "postalCode")).withValue("data10", getJsonString(jSONObject, bm.O)).build());
    }

    private void insertEmail(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", getJsonString(jSONObject, "value")).withValue("data2", Integer.valueOf(getPhoneType(getJsonString(jSONObject, "type")))).build());
    }

    private void insertIm(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/im").withValue("data1", getJsonString(jSONObject, "value")).withValue("data2", Integer.valueOf(getContactType(getJsonString(jSONObject, "type")))).build());
    }

    private void insertOrganization(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data2", Integer.valueOf(getOrgType(getJsonString(jSONObject, "type")))).withValue("data5", getJsonString(jSONObject, "department")).withValue("data1", getJsonString(jSONObject, "name")).withValue("data4", getJsonString(jSONObject, "title")).build());
    }

    private void insertPhone(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", getJsonString(jSONObject, "value")).withValue("data2", Integer.valueOf(getPhoneType(getJsonString(jSONObject, "type")))).build());
    }

    private void insertPhoto(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("is_super_primary", 1).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", getPhotoBytes(getJsonString(jSONObject, "value"))).build());
    }

    private void insertWebsite(ArrayList<ContentProviderOperation> arrayList, JSONObject jSONObject) {
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", getJsonString(jSONObject, "value")).withValue("data2", Integer.valueOf(getContactType(getJsonString(jSONObject, "type")))).build());
    }

    private boolean isSearchAll(JSONArray jSONArray) {
        if (jSONArray.length() == 1) {
            try {
                if (Operators.MUL.equals(jSONArray.getString(0))) {
                    return true;
                }
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean modifyContact(org.json.JSONObject r30, android.accounts.Account r31, java.lang.String r32) {
        /*
            Method dump skipped, instruction units count: 961
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.contacts.ContactAccessorImpl.modifyContact(org.json.JSONObject, android.accounts.Account, java.lang.String):boolean");
    }

    private void modifyContactOption(ArrayList<ContentProviderOperation> arrayList, JSONArray jSONArray, String str, String str2, String str3, String str4) {
        arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("raw_contact_id=? and mimetype=?", new String[]{String.valueOf(str), str2}).build());
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("raw_contact_id", str);
                    contentValues.put("mimetype", str2);
                    contentValues.put(str3, getJsonString(jSONObjectOptJSONObject, "value"));
                    contentValues.put(str4, Integer.valueOf(getContactType(getJsonString(jSONObjectOptJSONObject, "type"))));
                    arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValues(contentValues).build());
                }
            }
        }
    }

    private JSONObject nameQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            String string = cursor.getString(cursor.getColumnIndex("data3"));
            String string2 = cursor.getString(cursor.getColumnIndex("data2"));
            String string3 = cursor.getString(cursor.getColumnIndex("data5"));
            String string4 = cursor.getString(cursor.getColumnIndex("data4"));
            String string5 = cursor.getString(cursor.getColumnIndex("data6"));
            StringBuffer stringBuffer = new StringBuffer("");
            if (string4 != null) {
                stringBuffer.append(string4 + Operators.SPACE_STR);
            }
            if (string2 != null) {
                stringBuffer.append(string2 + Operators.SPACE_STR);
            }
            if (string3 != null) {
                stringBuffer.append(string3 + Operators.SPACE_STR);
            }
            if (string != null) {
                stringBuffer.append(string + Operators.SPACE_STR);
            }
            if (string5 != null) {
                stringBuffer.append(string5 + Operators.SPACE_STR);
            }
            jSONObject.put("familyName", string);
            jSONObject.put("givenName", string2);
            jSONObject.put("middleName", string3);
            jSONObject.put("honorificPrefix", string4);
            jSONObject.put("honorificSuffix", string5);
            jSONObject.put("formatted", stringBuffer);
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        return jSONObject;
    }

    private JSONObject organizationQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("type", getOrgType(cursor.getInt(cursor.getColumnIndex("data2"))));
            jSONObject.put("department", cursor.getString(cursor.getColumnIndex("data5")));
            jSONObject.put("name", cursor.getString(cursor.getColumnIndex("data1")));
            jSONObject.put("title", cursor.getString(cursor.getColumnIndex("data4")));
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        return jSONObject;
    }

    private JSONObject phoneQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("value", cursor.getString(cursor.getColumnIndex("data1")));
            jSONObject.put("type", getPhoneType(cursor.getInt(cursor.getColumnIndex("data2"))));
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        } catch (Exception e3) {
            Log.e("ContactsAccessor", e3.getMessage(), e3);
        }
        return jSONObject;
    }

    private JSONObject photoQuery(Cursor cursor, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("type", "url");
            InputStream inputStreamOpenContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.mApp.getContentResolver(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, j));
            String str = "";
            try {
                byte[] bArr = new byte[inputStreamOpenContactPhotoInputStream.available()];
                inputStreamOpenContactPhotoInputStream.read(bArr);
                str = "data:image/png;base64," + Base64.encodeToString(bArr, 2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            jSONObject.put("value", str);
        } catch (JSONException e3) {
            Log.e("ContactsAccessor", e3.getMessage(), e3);
        }
        return jSONObject;
    }

    private JSONObject populateContact(HashMap<String, Boolean> map, JSONObject jSONObject, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4, JSONArray jSONArray5, JSONArray jSONArray6, JSONArray jSONArray7) {
        try {
            if (jSONArray3.length() > 0 || isRequired("phoneNumbers", map)) {
                jSONObject.put("phoneNumbers", jSONArray3);
            }
            if (jSONArray4.length() > 0 || isRequired("emails", map)) {
                jSONObject.put("emails", jSONArray4);
            }
            if (jSONArray2.length() > 0 || isRequired("addresses", map)) {
                jSONObject.put("addresses", jSONArray2);
            }
            if (jSONArray5.length() > 0 || isRequired("ims", map)) {
                jSONObject.put("ims", jSONArray5);
            }
            if (jSONArray.length() > 0 || isRequired("organizations", map)) {
                jSONObject.put("organizations", jSONArray);
            }
            if (jSONArray6.length() > 0 || isRequired("urls", map)) {
                jSONObject.put("urls", jSONArray6);
            }
            if (jSONArray7.length() > 0 || isRequired("photos", map)) {
                jSONObject.put("photos", jSONArray7);
            }
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", "e.getMessage()==" + e2.getMessage(), e2);
        }
        return jSONObject;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:224|9|220|10|(1:12)|13|(18:15|222|16|188|17|198|18|212|19|192|20|218|21|196|22|214|23|24)(1:43)|(3:45|210|46)|232|50|51|(4:53|54|216|55)(1:62)|(3:190|63|64)|(8:66|204|67|(1:69)|70|160|237|181)(1:74)|208|75|(2:81|(2:87|(2:93|(2:99|(7:105|(8:107|206|108|(2:110|111)|112|160|237|181)(1:115)|116|202|117|(4:119|200|120|(5:122|123|112|160|237)(8:124|127|(3:228|129|(5:131|132|112|160|237))|135|(3:137|138|(4:140|230|141|(1:143))(1:147))(6:148|194|149|(0)(4:153|154|226|155)|160|237)|159|160|237))(7:127|(0)|135|(0)(0)|159|160|237)|181)(1:103))(1:97))(1:91))(1:85))(1:79)|70|160|237|181|5) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:105|(8:107|206|108|(2:110|111)|112|160|237|181)(1:115)|116|202|117|(4:119|200|120|(5:122|123|112|160|237)(8:124|127|(3:228|129|(5:131|132|112|160|237))|135|(3:137|138|(4:140|230|141|(1:143))(1:147))(6:148|194|149|(0)(4:153|154|226|155)|160|237)|159|160|237))(7:127|(0)|135|(0)(0)|159|160|237)|181) */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x03bd, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x03be, code lost:
    
        r10 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x03c0, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x03c1, code lost:
    
        r10 = r1;
        r16 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:137:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.json.JSONArray populateContactArray(int r34, java.util.HashMap<java.lang.String, java.lang.Boolean> r35, android.database.Cursor r36) {
        /*
            Method dump skipped, instruction units count: 1113
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.contacts.ContactAccessorImpl.populateContactArray(int, java.util.HashMap, android.database.Cursor):org.json.JSONArray");
    }

    private JSONObject urlQuery(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", cursor.getString(cursor.getColumnIndex(bq.d)));
            jSONObject.put("pref", false);
            jSONObject.put("value", cursor.getString(cursor.getColumnIndex("data1")));
            jSONObject.put("type", getContactType(cursor.getInt(cursor.getColumnIndex("data2"))));
        } catch (JSONException e2) {
            Log.e("ContactsAccessor", e2.getMessage(), e2);
        }
        return jSONObject;
    }

    @Override // io.dcloud.feature.contacts.ContactAccessor
    public boolean remove(String str) {
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        arrayList.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, Long.parseLong(str))).build());
        try {
            return this.mApp.getContentResolver().applyBatch("com.android.contacts", arrayList).length > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // io.dcloud.feature.contacts.ContactAccessor
    public boolean save(JSONObject jSONObject) {
        Account[] accounts = AccountManager.get(this.mApp).getAccounts();
        Account account = null;
        if (accounts.length == 1) {
            account = accounts[0];
        } else if (accounts.length > 1) {
            int length = accounts.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Account account2 = accounts[i];
                if (account2.type.contains("eas") && account2.name.matches(EMAIL_REGEXP)) {
                    account = account2;
                    break;
                }
                i++;
            }
            if (account == null) {
                int length2 = accounts.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        break;
                    }
                    Account account3 = accounts[i2];
                    if (account3.type.contains("com.google") && account3.name.matches(EMAIL_REGEXP)) {
                        account = account3;
                        break;
                    }
                    i2++;
                }
            }
            if (account == null) {
                int length3 = accounts.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length3) {
                        break;
                    }
                    Account account4 = accounts[i3];
                    if (account4.name.matches(EMAIL_REGEXP)) {
                        account = account4;
                        break;
                    }
                    i3++;
                }
            }
            if (account == null) {
                account = accounts[0];
            }
        }
        String jsonString = getJsonString(jSONObject, "id");
        return (jsonString == null || b.m.equals(jsonString)) ? createNewContact(jSONObject, account) : modifyContact(jSONObject, account, jsonString);
    }

    @Override // io.dcloud.feature.contacts.ContactAccessor
    public JSONArray search(JSONArray jSONArray, JSONObject jSONObject) {
        JSONArray jSONArray2;
        String[] selectionArgs;
        String str;
        int i = Integer.MAX_VALUE;
        boolean z = true;
        if (jSONObject != null) {
            jSONArray2 = JSONUtil.getJSONArray(jSONObject, Constants.Name.FILTER);
            if (!jSONObject.optBoolean("multiple", true)) {
                i = 1;
            }
        } else {
            jSONArray2 = null;
        }
        if (jSONArray2 != null && jSONArray2.length() != 0 && (jSONArray2.length() != 1 || !jSONArray2.optJSONObject(0).isNull("field"))) {
            z = false;
        }
        if (z) {
            str = null;
            selectionArgs = null;
        } else {
            ContactAccessor.WhereOptions whereOptionsBuildWhereClause = buildWhereClause(jSONArray2);
            String selection = whereOptionsBuildWhereClause.getSelection();
            selectionArgs = whereOptionsBuildWhereClause.getSelectionArgs();
            str = selection;
        }
        Cursor cursorQuery = this.mApp.getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[]{"raw_contact_id"}, str, selectionArgs, "raw_contact_id ASC");
        ArrayList<String> arrayList = new ArrayList<>();
        while (cursorQuery.moveToNext()) {
            arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndex("raw_contact_id")));
        }
        cursorQuery.close();
        if (arrayList.size() == 0) {
            return new JSONArray();
        }
        ContactAccessor.WhereOptions whereOptionsBuildIdClause = buildIdClause(arrayList);
        return populateContactArray(i, buildPopulationSet(jSONArray), this.mApp.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereOptionsBuildIdClause.getSelection(), whereOptionsBuildIdClause.getSelectionArgs(), "raw_contact_id ASC"));
    }
}
