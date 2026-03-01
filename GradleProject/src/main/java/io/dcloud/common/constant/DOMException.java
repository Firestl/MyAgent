package io.dcloud.common.constant;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.base.R;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.StringUtil;
import java.util.IllegalFormatException;

/* JADX INFO: loaded from: classes2.dex */
public class DOMException {
    public static final String CODE = "code";
    public static final int CODE_AUDIO_ERROR_MALFORMED = -1301;
    public static final int CODE_AUDIO_ERROR_TIMED_OUT = -1302;
    public static final int CODE_AUTHORIZE_FAILED = -10;
    public static final int CODE_BARCODE_ERROR = 8;
    public static final int CODE_BUSINESS_INTERNAL_ERROR = -100;
    public static final int CODE_BUSINESS_PARAMETER_HAS_NOT = -7;
    public static final int CODE_CAMERA_ERROR = 11;
    public static final int CODE_CLIENT_UNINSTALLED = -8;
    public static final int CODE_DECOMPRESS_ERROR = 2;
    public static final int CODE_FILE_NOT_EXIST = -4;
    public static final int CODE_GALLERY_ERROR = 12;
    public static final int CODE_GEOLOCATION_HASNT_BAIDU_APPKEY = 16;
    public static final int CODE_GEOLOCATION_PERMISSION_ERROR = 22;
    public static final int CODE_GEOLOCATION_PROVIDER_ERROR = 17;
    public static final int CODE_GET_TOKEN_ERROR = -1002;
    public static final int CODE_INSTALL_WGT_ILLEGALITY_APPID_ERROR = 10;
    public static final int CODE_IO_ERROR = -5;
    public static final int CODE_MESSAGING_ERROR = 13;
    public static final int CODE_NETWORK_ERROR = -6;
    public static final int CODE_NOT_FOUND_3TH = 1;
    public static final int CODE_NOT_FOUND_FILE = 0;
    public static final int CODE_NOT_SUPPORT = -3;
    public static final int CODE_OAUTH_FAIL = -1001;
    public static final int CODE_OAUTH_GET_SERVICES = 18;
    public static final int CODE_OAUTH_GET_USERINFO = 21;
    public static final int CODE_OAUTH_LOGIN = 19;
    public static final int CODE_OAUTH_LOGOUT = 20;
    public static final int CODE_OPERATE_DIR_ERROR = 9;
    public static final int CODE_PARAMETER_ERRORP = -1;
    public static final int CODE_PICK_DATE_ERROR = 5;
    public static final int CODE_PICK_TIME_ERROR = 6;
    public static final int CODE_PLAYER_ERROR = 4;
    public static final int CODE_RECOGNITION_ERROR = 7;
    public static final int CODE_RECORDER_ERROR = 3;
    public static final int CODE_RUNTIME_5PRUNTIME_LACK_MODULE = -1229;
    public static final int CODE_RUNTIME_COMPONENTS_MODE_NOT_SUPPORT = 1250;
    public static final int CODE_RUNTIME_WGTU_UPDATE_APPID_NOT_MATCH = -1223;
    public static final int CODE_RUNTIME_WGTU_UPDATE_ERROR_MALFORMED = -1222;
    public static final int CODE_RUNTIME_WGTU_UPDATE_NOT_EXIST = -1221;
    public static final int CODE_RUNTIME_WGTU_UPDATE_VERSION_NOT_MATCH = -1224;
    public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_APPID_NOT_MATCH = -1227;
    public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_ERROR_MALFORMED = -1226;
    public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST = -1225;
    public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_VERSION_NOT_MATCH = -1228;
    public static final int CODE_RUNTIME_WGT_MANIFEST_APPID_NOT_MATCH = -1204;
    public static final int CODE_RUNTIME_WGT_MANIFEST_ERROR_MALFORMED = -1203;
    public static final int CODE_RUNTIME_WGT_MANIFEST_NOT_EXIST = -1202;
    public static final int CODE_RUNTIME_WGT_MANIFEST_VERSION_NOT_MATCH = -1205;
    public static final int CODE_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED = -1201;
    public static final int CODE_SHARE_AUTHORIZE_ERROR = 14;
    public static final int CODE_SHARE_SEND_ERROR = 15;
    public static final int CODE_SHORT_CUT_ALREADY_EXSIT = -9;
    public static final int CODE_UNKNOWN_ERROR = -99;
    public static final int CODE_UNOAUTH_ERROR = -1003;
    public static final int CODE_USER_CANCEL = -2;
    public static final String ERROR_LINK = "http://ask.dcloud.net.cn/article/282";
    public static final String INNERCODE = "innerCode";
    public static final String JSON_ERROR_INFO = "{code:%d,message:'%s'}";
    public static final String JSON_ERROR_INFO2 = "{code:%s,message:'%s'}";
    public static final String JSON_ERROR_INNE_INFO = "{code:%d,message:'%s',innerCode:%d}";
    public static final String JSON_SHORTCUT_RESULT_INFO = "{result:%s}";
    public static final String JSON_SHORTCUT_SUCCESS_INFO = "{sure:%s}";
    public static final String MESSAGE = "message";
    public static final String MSG_BARCODE = "";
    public static final String MSG_DECOMPRESS_ERROR = "";
    public static final String MSG_GEOLOCATION_HASNT_AMAP_KEY = "has not amap  appkey";
    public static final String MSG_GEOLOCATION_HASNT_BAIDU_APKEY = "has not baidu appkey";
    public static final String MSG_IO_ERROR = "IO Error";
    public static final String MSG_NETWORK_ERROR = "network error";
    public static final String MSG_NOT_FOUND_3TH = "not found 3th activity";
    public static final String MSG_NOT_FOUND_FILE = "not found file";
    public static final String MSG_NO_PERMISSION = "No Permission";
    public static final String MSG_OPERATE_DIR_ERROR = "operate_dir_error";
    public static final String MSG_PICK_DATE = "";
    public static final String MSG_PICK_TIME = "";
    public static final String MSG_RECOGNITION = "";
    public static final String MSG_SHARE_AUTHORIZE_ERROR = "authorize";
    public static final String MSG_SHARE_SEND_ERROR = "send";
    public static final String STRING_ERROR_INFO = "[%s:%d]%s, %s";
    public static final String STRING_ERROR_INFO_THIRDSDK = "[%s%s:%d]%s";
    public static final String STRING_ERROR_INFO_THIRDSDK_NOCODE = "[%s%s]%s";
    public static final String STRING_ERROR_NOTLINK_INFO = "[%s:%d]%s";
    public static final String MSG_SHARE_SEND_PIC_ROUTE_ERROR = getString(R.string.dcloud_share_local_path);
    public static final String MSG_SHARE_SEND_CONTENT_EMPTY_ERROR = getString(R.string.dcloud_share_content_not_empty);
    public static final String MSG_GEOLOCATION_PROVIDER_ERROR = getString(R.string.dcloud_geo_provider_invalid);
    public static final String MSG_GEOLOCATION_PERMISSION_ERROR = getString(R.string.dcloud_geo_permission_failed);
    public static final String MSG_OAUTH_GET_SERVICES_ERROR = getString(R.string.dcloud_oauth_authentication_failed);
    public static final String MSG_OAUTH_LOGIN = getString(R.string.dcloud_oauth_empower_failed);
    public static final String MSG_OAUTH_LOGOUT = getString(R.string.dcloud_oauth_empower_failed);
    public static final String MSG_OAUTH_GET_USERINFO = getString(R.string.dcloud_oauth_empower_failed);
    public static final String MSG_PARAMETER_ERROR = getString(R.string.dcloud_common_parameter_error);
    public static final String MSG_USER_CANCEL = getString(R.string.dcloud_common_user_cancel);
    public static final String MSG_NOT_SUPPORT = getString(R.string.dcloud_common_not_supported);
    public static final String MSG_FILE_NOT_EXIST = getString(R.string.dcloud_common_file_not_exist);
    public static final String MSG_BUSINESS_PARAMETER_HAS_NOT = getString(R.string.dcloud_common_missing_parameter);
    public static final String MSG_CLIENT_UNINSTALLED = getString(R.string.dcloud_common_app_not_installed);
    public static final String MSG_SHORT_CUT_ALREADY_EXSIT = getString(R.string.dcloud_short_cut_exists);
    public static final String MSG_AUTHORIZE_FAILED = getString(R.string.dcloud_common_user_refuse_api);
    public static final String MSG_UNKNOWN_ERROR = getString(R.string.dcloud_common_unknown_error);
    public static final String MSG_BUSINESS_INTERNAL_ERROR = getString(R.string.dcloud_common_inside_error);
    public static final String MSG_PATH_NOT_PRIVATE_ERROR = getString(R.string.dcloud_common_app_target_tips);
    public static final String MSG_OAUTH_FAIL = getString(R.string.dcloud_oauth_logout_tips);
    public static final String MSG_GET_TOKEN_ERROR = getString(R.string.dcloud_oauth_token_failed);
    public static final String MSG_UNOAUTH_ERROR = getString(R.string.dcloud_oauth_oauth_not_empower);
    public static final String MSG_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED = getString(R.string.dcloud_wgt_format_error);
    public static final String MSG_RUNTIME_WGT_MANIFEST_NOT_EXIST = getString(R.string.dcloud_wgt_not_manifest);
    public static final String MSG_RUNTIME_WGT_MANIFEST_ERROR_MALFORMED = getString(R.string.dcloud_wgt_manifest_format_error);
    public static final String MSG_RUNTIME_WGT_MANIFEST_APPID_NOT_MATCH = getString(R.string.dcloud_wgt_appid_legal);
    public static final String MSG_RUNTIME_WGT_MANIFEST_VERSION_NOT_MATCH = getString(R.string.dcloud_wgt_version_error);
    public static final String MSG_RUNTIME_WGTU_UPDATE_NOT_EXIST = getString(R.string.dcloud_wgt_not_update_file);
    public static final String MSG_RUNTIME_WGTU_UPDATE_ERROR_MALFORMED = getString(R.string.dcloud_wgt_update_format_error);
    public static final String MSG_RUNTIME_WGTU_UPDATE_APPID_NOT_MATCH = getString(R.string.dcloud_wgt_update_appid_error);
    public static final String MSG_RUNTIME_WGTU_UPDATE_VERSION_NOT_MATCH = getString(R.string.dcloud_wgt_update_version_error);
    public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST = getString(R.string.dcloud_wgtu_not_manifest);
    public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_ERROR_MALFORMED = getString(R.string.dcloud_wgtu_manifest_format_error);
    public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_APPID_NOT_MATCH = getString(R.string.dcloud_wgtu_appid_legal);
    public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_VERSION_NOT_MATCH = getString(R.string.dcloud_wgtu_version_error);
    public static final String MSG_RUNTIME_5PRUNTIME_LACK_MODULE = getString(R.string.dcloud_runtime_not_manifest);
    public static final String MSG_AUDIO_ERROR_MALFORMED = getString(R.string.dcloud_audio_play_error);
    public static final String MSG_AUDIO_ERROR_TIMED_OUT = getString(R.string.dcloud_audio_timeout);
    public static final String MSG_RUNTIME_COMPONENTS_MODE_NOT_SUPPORT = getString(R.string.dcloud_runtime_not_update_tips);

    public static String getString(int i) {
        return DCLoudApplicationImpl.self().getContext().getString(i);
    }

    public static String toJSON(int i, String str) {
        try {
            return StringUtil.format(JSON_ERROR_INFO, Integer.valueOf(i), str);
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toString(int i, String str, String str2, String str3) {
        try {
            return !TextUtils.isEmpty(str3) ? StringUtil.format(STRING_ERROR_INFO, str, Integer.valueOf(i), str2, str3) : StringUtil.format(STRING_ERROR_NOTLINK_INFO, str, Integer.valueOf(i), str2);
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toStringForThirdSDK(String str, String str2, int i, String str3) {
        try {
            return StringUtil.format(STRING_ERROR_INFO_THIRDSDK, str, str2, Integer.valueOf(i), str3);
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toJSON(String str, String str2) {
        try {
            return StringUtil.format(JSON_ERROR_INFO2, str, str2);
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toStringForThirdSDK(String str, String str2, String str3) {
        try {
            return StringUtil.format(STRING_ERROR_INFO_THIRDSDK_NOCODE, str, str2, str3);
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toJSON(int i, String str, int i2) {
        try {
            return StringUtil.format(JSON_ERROR_INNE_INFO, Integer.valueOf(i), str, Integer.valueOf(i2));
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toString(String str, String str2, String str3, String str4) {
        String str5;
        try {
            if (!TextUtils.isEmpty(str4)) {
                str5 = Operators.ARRAY_START_STR + str2 + Constants.COLON_SEPARATOR + str + Operators.ARRAY_END_STR + Operators.SPACE_STR + str3 + ",  " + str4;
            } else {
                str5 = Operators.ARRAY_START_STR + str2 + Constants.COLON_SEPARATOR + str + Operators.ARRAY_END_STR + Operators.SPACE_STR + str3;
            }
            return str5;
        } catch (IllegalFormatException unused) {
            Logger.e("DOMException is format error!!!");
            return null;
        }
    }

    public static String toString(String str) {
        return str + "," + ERROR_LINK;
    }
}
