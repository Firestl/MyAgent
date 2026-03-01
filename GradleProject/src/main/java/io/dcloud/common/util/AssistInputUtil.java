package io.dcloud.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import io.dcloud.common.adapter.ui.RecordView;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes2.dex */
public class AssistInputUtil {
    public static final String SP_KEY_CURRENT_ADDRESS = "assisiSettingCurrentAddress";
    public static final String SP_KEY_DUTY_PARAGRAPH = "assisiSettingDutyParagraph";
    public static final String SP_KEY_EMAIL_A = "assisiSettingEmailA";
    public static final String SP_KEY_EMAIL_B = "assisiSettingEmailB";
    public static final String SP_KEY_HOME_ADDRESS = "assisiSettingHomeAddress";
    public static final String SP_KEY_ID = "assisiSettingId";
    public static final String SP_KEY_NAME = "assisiSettingName";
    public static final String SP_KEY_NAME_B = "assisiSettingNameB";
    public static final String SP_KEY_PHONE_A = "assisiSettingPhoneA";
    public static final String SP_KEY_PHONE_B = "assisiSettingPhoneB";
    public static final String SP_KEY_WORK_ADDRESS = "assisiSettingWorkAddress";
    public static final String SP_KEY_WORK_NAME = "assisiSettingWorkName";
    public static final String SP_NAME = "assisiSetting";
    public static final int XORNUMBER = 6;

    public static synchronized void changeSaveDataToEncrypt(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
            if (sharedPreferences.getAll().isEmpty()) {
                sharedPreferences.edit().putInt("isEncrypt", 1).commit();
                return;
            }
            if (sharedPreferences.contains("isEncrypt")) {
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            for (String str : sharedPreferences.getAll().keySet()) {
                String string = sharedPreferences.getString(str, "");
                if (!TextUtils.isEmpty(string)) {
                    editorEdit.putString(str, encrypt(string));
                }
            }
            editorEdit.putInt("isEncrypt", 1);
            editorEdit.commit();
        }
    }

    public static void clearData(Context context) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
            editorEdit.clear();
            editorEdit.commit();
        }
    }

    public static String decrypt(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return "";
        }
        String strDecodeString = Base64.decodeString(str, true, 6);
        if (PdrUtil.isEmpty(strDecodeString)) {
            return "";
        }
        try {
            return URLDecoder.decode(strDecodeString, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return strDecodeString;
        }
    }

    public static String encrypt(String str) {
        if (str == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return Base64.encodeString(str, true, 6);
    }

    public static String[] getCoreRecordViewSaveData(String str) {
        String[] strArrSplit = null;
        String recordDatas = RecordView.getRecordDatas(null, str);
        if (TextUtils.isEmpty(recordDatas)) {
            return null;
        }
        if (!recordDatas.contains("&")) {
            try {
                strArrSplit = new String[]{URLDecoder.decode(recordDatas, "utf-8")};
                return strArrSplit;
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return strArrSplit;
            }
        }
        try {
            strArrSplit = recordDatas.split("&");
            if (strArrSplit.length <= 0) {
                return strArrSplit;
            }
            if (1 <= strArrSplit.length) {
                strArrSplit[0] = URLDecoder.decode(strArrSplit[0], "utf-8");
            }
            if (2 > strArrSplit.length) {
                return strArrSplit;
            }
            strArrSplit[1] = URLDecoder.decode(strArrSplit[1], "utf-8");
            return strArrSplit;
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
            return strArrSplit;
        }
    }

    public static String getCurrentAddress(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_CURRENT_ADDRESS, "")) : "";
    }

    public static String getDutyParagraph(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_DUTY_PARAGRAPH, "")) : "";
    }

    public static String getEmailA(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_EMAIL_A, "")) : "";
    }

    public static String getEmailB(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_EMAIL_B, "")) : "";
    }

    public static String getHomeAddress(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_HOME_ADDRESS, "")) : "";
    }

    public static String getId(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_ID, "")) : "";
    }

    public static String getName(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_NAME, "")) : "";
    }

    public static String getNameB(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_NAME_B, "")) : "";
    }

    public static String getPhoneA(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_PHONE_A, "")) : "";
    }

    public static String getPhoneB(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_PHONE_B, "")) : "";
    }

    public static String[] getRecordViewCompany() {
        return getCoreRecordViewSaveData("company");
    }

    public static String[] getRecordViewEmails() {
        return getCoreRecordViewSaveData("email");
    }

    public static String[] getRecordViewId() {
        return getCoreRecordViewSaveData("id");
    }

    public static String[] getRecordViewNames() {
        return getCoreRecordViewSaveData("nick");
    }

    public static String[] getRecordViewPhones() {
        return getCoreRecordViewSaveData(Constants.Value.TEL);
    }

    public static String[] getRecordViewTax() {
        return getCoreRecordViewSaveData("tax");
    }

    public static String getWorkAddress(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_WORK_ADDRESS, "")) : "";
    }

    public static String getWorkName(Context context) {
        return context != null ? decrypt(context.getSharedPreferences(SP_NAME, 0).getString(SP_KEY_WORK_NAME, "")) : "";
    }

    public static void saveAll(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
            if (str != null) {
                editorEdit.putString(SP_KEY_CURRENT_ADDRESS, encrypt(str));
            }
            if (str2 != null) {
                editorEdit.putString(SP_KEY_HOME_ADDRESS, encrypt(str2));
            }
            if (str3 != null) {
                editorEdit.putString(SP_KEY_WORK_ADDRESS, encrypt(str3));
            }
            if (str4 != null) {
                editorEdit.putString(SP_KEY_WORK_NAME, encrypt(str4));
            }
            if (str5 != null) {
                editorEdit.putString(SP_KEY_DUTY_PARAGRAPH, encrypt(str5));
            }
            if (str6 != null) {
                editorEdit.putString(SP_KEY_ID, encrypt(str6));
            }
            editorEdit.putInt("isEncrypt", 1);
            editorEdit.commit();
        }
    }

    public static void saveCompany(Context context, boolean z, String str) {
        String strEncode;
        if (context == null || !z) {
            return;
        }
        if (str != null) {
            try {
                strEncode = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            strEncode = null;
        }
        if (strEncode != null) {
            RecordView.setRcordDatas(context, (String) null, "company", strEncode);
        }
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str)) {
                RecordView.setAssisBundleData(context, "useAssistSettingCompany", "");
            }
        } else {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
            editorEdit.putString(SP_KEY_WORK_NAME, encrypt(str));
            editorEdit.commit();
            RecordView.setAssisBundleData(context, "useAssistSettingCompany", "1");
        }
    }

    public static void saveCurrentAddress(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        context.getSharedPreferences(SP_NAME, 0).edit().putString(SP_KEY_CURRENT_ADDRESS, encrypt(str)).commit();
    }

    public static void saveEmail(Context context, boolean z, String str, String str2) {
        String strEncode;
        if (context == null || !z) {
            return;
        }
        if (str != null) {
            try {
                strEncode = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            strEncode = null;
        }
        if (str2 != null) {
            String strEncode2 = URLEncoder.encode(str2, "utf-8");
            if (TextUtils.isEmpty(strEncode)) {
                strEncode = strEncode2;
            } else {
                strEncode = strEncode + "&" + strEncode2;
            }
        }
        if (strEncode != null) {
            RecordView.setRcordDatas(context, (String) null, "email", strEncode);
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                RecordView.setAssisBundleData(context, "useAssistSettingEmail", "");
                return;
            }
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
        editorEdit.putString(SP_KEY_EMAIL_A, encrypt(str));
        editorEdit.putString(SP_KEY_EMAIL_B, encrypt(str2));
        editorEdit.commit();
        RecordView.setAssisBundleData(context, "useAssistSettingEmail", "1");
    }

    public static void saveId(Context context, boolean z, String str) {
        String strEncode;
        if (context == null || !z) {
            return;
        }
        if (str != null) {
            try {
                strEncode = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            strEncode = null;
        }
        if (strEncode != null) {
            RecordView.setRcordDatas(context, (String) null, "id", strEncode);
        }
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str)) {
                RecordView.setAssisBundleData(context, "useAssistSettingId", "");
            }
        } else {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
            editorEdit.putString(SP_KEY_ID, encrypt(str));
            editorEdit.commit();
            RecordView.setAssisBundleData(context, "useAssistSettingId", "1");
        }
    }

    public static void saveName(Context context, boolean z, String str, String str2) {
        String strEncode;
        if (context == null || !z) {
            return;
        }
        if (str != null) {
            try {
                strEncode = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            strEncode = null;
        }
        if (str2 != null) {
            String strEncode2 = URLEncoder.encode(str2, "utf-8");
            if (TextUtils.isEmpty(strEncode)) {
                strEncode = strEncode2;
            } else {
                strEncode = strEncode + "&" + strEncode2;
            }
        }
        if (strEncode != null) {
            RecordView.setRcordDatas(context, (String) null, "nick", strEncode);
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                RecordView.setAssisBundleData(context, "useAssistSettingName", "");
                return;
            }
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
        editorEdit.putString(SP_KEY_NAME, encrypt(str));
        editorEdit.putString(SP_KEY_NAME_B, encrypt(str2));
        editorEdit.commit();
        RecordView.setAssisBundleData(context, "useAssistSettingName", "1");
    }

    public static void savePhone(Context context, boolean z, String str, String str2) {
        String strEncode;
        if (context == null || !z) {
            return;
        }
        if (str != null) {
            try {
                strEncode = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            strEncode = null;
        }
        if (str2 != null) {
            String strEncode2 = URLEncoder.encode(str2, "utf-8");
            if (TextUtils.isEmpty(strEncode)) {
                strEncode = strEncode2;
            } else {
                strEncode = strEncode + "&" + strEncode2;
            }
        }
        if (strEncode != null) {
            RecordView.setRcordDatas(context, (String) null, Constants.Value.TEL, strEncode);
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                RecordView.setAssisBundleData(context, "useAssistSettingPhone", "");
                return;
            }
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
        editorEdit.putString(SP_KEY_PHONE_A, encrypt(str));
        editorEdit.putString(SP_KEY_PHONE_B, encrypt(str2));
        editorEdit.commit();
        RecordView.setAssisBundleData(context, "useAssistSettingPhone", "1");
    }

    public static void saveTax(Context context, boolean z, String str) {
        String strEncode;
        if (context == null || !z) {
            return;
        }
        if (str != null) {
            try {
                strEncode = URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        } else {
            strEncode = null;
        }
        if (strEncode != null) {
            RecordView.setRcordDatas(context, (String) null, "tax", strEncode);
        }
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str)) {
                RecordView.setAssisBundleData(context, "useAssistSettingTax", "");
            }
        } else {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
            editorEdit.putString(SP_KEY_DUTY_PARAGRAPH, encrypt(str));
            editorEdit.commit();
            RecordView.setAssisBundleData(context, "useAssistSettingTax", "1");
        }
    }

    public static boolean useAssistSettingCompany() {
        return !TextUtils.isEmpty(RecordView.getAssisBundleData("useAssistSettingCompany"));
    }

    public static boolean useAssistSettingEmail() {
        return !TextUtils.isEmpty(RecordView.getAssisBundleData("useAssistSettingEmail"));
    }

    public static boolean useAssistSettingId() {
        return !TextUtils.isEmpty(RecordView.getAssisBundleData("useAssistSettingId"));
    }

    public static boolean useAssistSettingName() {
        return !TextUtils.isEmpty(RecordView.getAssisBundleData("useAssistSettingName"));
    }

    public static boolean useAssistSettingPhone() {
        return !TextUtils.isEmpty(RecordView.getAssisBundleData("useAssistSettingPhone"));
    }

    public static boolean useAssistSettingTax() {
        return !TextUtils.isEmpty(RecordView.getAssisBundleData("useAssistSettingTax"));
    }

    public static void saveAll(Context context, String str, String str2, String str3) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(SP_NAME, 0).edit();
            if (str != null) {
                editorEdit.putString(SP_KEY_CURRENT_ADDRESS, encrypt(str));
            }
            if (str2 != null) {
                editorEdit.putString(SP_KEY_HOME_ADDRESS, encrypt(str2));
            }
            if (str3 != null) {
                editorEdit.putString(SP_KEY_WORK_ADDRESS, encrypt(str3));
            }
            editorEdit.putInt("isEncrypt", 1);
            editorEdit.commit();
        }
    }
}
