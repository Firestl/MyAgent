package io.dcloud;

import android.content.Context;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.util.BaseInfo;

/* JADX INFO: loaded from: classes2.dex */
public class PdrR implements IReflectAble {
    public static int DRAWABLE_ICON = AndroidResources.mApplicationInfo.applicationInfo.icon;
    public static int LAYOUT_SNOW_WHITE_PROGRESS = R.layout.dcloud_snow_white_progress;
    public static int LAYOUT_SNOW_BLACK_PROGRESS = R.layout.dcloud_snow_black_progress;
    public static int ID_PROGRESSBAR = R.id.progressBar;
    public static int FEATURE_LOSS_STYLE = R.style.featureLossDialog;
    public static int LAYOUT_CUSTION_NOTIFICATION_DCLOUD = R.layout.dcloud_custom_notification;
    public static int ID_IMAGE_NOTIFICATION_DCLOUD = R.id.image;
    public static int ID_TITLE_NOTIFICATION_DCLOUD = R.id.title;
    public static int ID_TEXT_NOTIFICATION = R.id.text;
    public static int ID_TIME_NOTIFICATION_DCLOUD = R.id.time;
    public static int DRAWABLE_DCLOUD_DIALOG_SHAPE = R.drawable.dcloud_dialog_shape;
    public static int LAYOUT_DIALOG_LAYOUT_DCLOUD_DIALOG = R.layout.dcloud_dialog;
    public static int ID_DCLOUD_DIALOG_ROOTVIEW = R.id.dcloud_dialog_rootview;
    public static int ID_DCLOUD_DIALOG_TITLE = R.id.dcloud_dialog_title;
    public static int ID_DCLOUD_DIALOG_ICON = R.id.dcloud_dialog_icon;
    public static int ID_DCLOUD_DIALOG_MSG = R.id.dcloud_dialog_msg;
    public static int ID_DCLOUD_DIALOG_BTN1 = R.id.dcloud_dialog_btn1;
    public static int ID_DCLOUD_DIALOG_BTN2 = R.id.dcloud_dialog_btn2;
    public static int STYLE_DIALOG_DCLOUD_DEFALUT_DIALOG = R.style.dcloud_defalut_dialog;
    public static int STYLE_DIALOG_STYLE_DCLOUD_ANIM_DIALOG_WINDOW_IN_OUT = R.style.dcloud_anim_dialog_window_in_out;
    public static int ANIM_DIALOG_ANIM_DCLOUD_SLIDE_IN_FROM_TOP = R.anim.dcloud_slide_in_from_top;
    public static int ANIM_DIALOG_ANIM_DCLOUD_SLIDE_OUT_TO_TOP = R.anim.dcloud_slide_out_to_top;
    public static int STREAMAPP_DELETE_THEME = R.style.streamDelete19Dialog;
    public static int STREAMAPP_DRAWABLE_APPDEFULTICON = R.drawable.dcloud_streamapp_icon_appdefault;
    public static int DRAWBLE_PROGRESSBAR_BLACK_SNOW = R.drawable.dcloud_snow_black_progress;
    public static int DRAWBLE_PROGRESSBAR_WHITE_SNOW = R.drawable.dcloud_snow_white_progress;
    public static int DRAWBLE_PROGRESSBAR_BLACK_CIRCLE = R.drawable.dcloud_circle_black_progress;
    public static int DRAWBLE_PROGRESSBAR_WHITE_CIRCLE = R.drawable.dcloud_circle_white_progress;
    public static int DRAWEBL_SHADOW_LEFT = R.drawable.dcloud_shadow_left;
    public static int STREAMAPP_CUSTOM_ALERT_DIALOG_LAYOUT = R.layout.dcloud_custom_alert_dialog_layout;
    public static int UNI_CUSTOM_PRIVACY_DIALOG_LAYOUT = R.layout.dcloud_custom_privacy_dialog_layout;
    public static int STREAMAPP_CUSTOM_ALERT_DIALOG_TITLE = R.id.title;
    public static int STREAMAPP_CUSTOM_ALERT_DIALOG_CHECKBOX = R.id.checkbox;
    public static int STREAMAPP_CUSTOM_ALERT_DIALOG_SURE = R.id.sure;
    public static int STREAMAPP_CUSTOM_ALERT_DIALOG_CANCEL = R.id.cancel;
    public static int STREAMAPP_CUSTOM_ALERT_DIALOG_CUSTOM_LAYOUT = R.id.customLayout;
    public static int WEBVIEW_ACTIVITY_LAYOUT = R.layout.webview_layout;
    public static int WEBVIEW_ACTIVITY_LAYOUT_BACK = R.id.back;
    public static int WEBVIEW_ACTIVITY_LAYOUT_CLOSE = R.id.close;
    public static int WEBVIEW_ACTIVITY_LAYOUT_TITLE = R.id.title;
    public static int WEBVIEW_ACTIVITY_LAYOUT_MENU = R.id.menu;
    public static int WEBVIEW_ACTIVITY_LAYOUT_REFRESH = R.id.refresh;
    public static int WEBVIEW_ACTIVITY_LAYOUT_CONTENT = R.id.content;
    public static int WEBVIEW_ACTIVITY_LAYOUT_WEBVIEW = R.id.webview;
    public static int ANIM_DCLOUD_SLIDE_IN_FROM_RIGHT = R.anim.dcloud_slide_in_from_right;
    public static int ANIM_DCLOUD_SLIDE_OUT_TO_RIGHT = R.anim.dcloud_slide_out_to_right;
    public static int DRAWABLE_DCLOUD_WEBVIEW_DOWNLOAD_PIN = R.drawable.offline_pin;
    public static int DRAWABLE_DCLOUD_WEBVIEW_DOWNLOAD_PIN_AROUND = R.drawable.offline_pin_round;
    public static int WEBVIEW_ACTIVITY_LAYOUT_ACTS_STYLE_ActionSheetStyleIOS7 = R.style.ActionSheetStyleIOS7;
    public static int DCLOUD_SHORTCUT_PERMISSION_GUIDE_LAYOUT = R.layout.dcloud_shortcut_permission_guide_layout;
    public static int DCLOUD_GUIDE_CLOSE = R.id.dcloud_guide_close;
    public static int DCLOUD_GUIDE_PLAY_LAYOUT = R.id.dcloud_guide_play_layout;
    public static int DCLOUD_GUIDE_GIFVIEW = R.id.dcloud_guide_gifview;
    public static int DCLOUD_GUIDE_PLAY = R.id.dcloud_guide_play;
    public static int DCLOUD_GUIDE_TIP = R.id.dcloud_guide_tip;
    public static int[] STYLE_GIFVIEW = R.styleable.GIFVIEW;
    public static int STYLE_GIFVIEW_gifSrc = R.styleable.GIFVIEW_gifSrc;
    public static int STYLE_GIFVIEW_authPlay = R.styleable.GIFVIEW_authPlay;
    public static int STYLE_GIFVIEW_playCount = R.styleable.GIFVIEW_playCount;
    public static int DCLOUD_GUIDE_GIF_MEIZU = R.drawable.dcloud_shortcut_guide_meizu;
    public static int DCLOUD_GUIDE_GIF_XIAOMI = R.drawable.dcloud_shortcut_guide_xiaomi;
    public static int DCLOUD_GUIDE_GIF_HUAWEI = R.drawable.dcloud_shortcut_guide_huawei;
    public static int DCLOUD_SYNC_DEBUD_MESSAGE = R.string.dcloud_sync_debug_message;
    public static int DCLOUD_PACKAGE_NAME_BASE = R.string.dcloud_package_name_base_application;

    public static int getInt(Context context, String str, String str2) {
        try {
            return context.getResources().getIdentifier(str2, str, context.getPackageName());
        } catch (Exception e2) {
            if (!BaseInfo.SyncDebug) {
                return 0;
            }
            e2.printStackTrace();
            return 0;
        }
    }

    public static String getStringValve(Context context, String str) {
        try {
            return context.getString(context.getResources().getIdentifier(str, "string", context.getPackageName()));
        } catch (Exception e2) {
            if (!BaseInfo.SyncDebug) {
                return "";
            }
            e2.printStackTrace();
            return "";
        }
    }
}
