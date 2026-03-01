package io.dcloud.common.util;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import io.dcloud.PdrR;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.adapter.util.AndroidResources;

/* JADX INFO: loaded from: classes2.dex */
public class DialogUtil {
    public static AlertDialog.Builder initDialogTheme(Activity activity, boolean z) {
        if (z) {
            return new AlertDialog.Builder(activity);
        }
        int i = Build.VERSION.SDK_INT;
        return i < 11 ? new AlertDialog.Builder(activity) : i < 20 ? new AlertDialog.Builder(activity, PdrR.STREAMAPP_DELETE_THEME) : new AlertDialog.Builder(activity);
    }

    public static void showAlertDialog(Activity activity, String str, String str2, String str3, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnDismissListener onDismissListener, boolean z, int i, int i2, int i3) {
        if (activity == null) {
            return;
        }
        final AlertDialog alertDialogCreate = initDialogTheme(activity).create();
        View viewInflate = LayoutInflater.from(activity).inflate(PdrR.STREAMAPP_CUSTOM_ALERT_DIALOG_LAYOUT, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(PdrR.STREAMAPP_CUSTOM_ALERT_DIALOG_TITLE);
        Button button = (Button) viewInflate.findViewById(PdrR.STREAMAPP_CUSTOM_ALERT_DIALOG_SURE);
        Button button2 = (Button) viewInflate.findViewById(PdrR.STREAMAPP_CUSTOM_ALERT_DIALOG_CANCEL);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
            textView.setGravity(i);
        }
        if (!z) {
            viewInflate.findViewById(PdrR.STREAMAPP_CUSTOM_ALERT_DIALOG_CUSTOM_LAYOUT).setVisibility(8);
        }
        if (!TextUtils.isEmpty(str2)) {
            button.setText(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            button2.setText(str3);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.common.util.DialogUtil.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
                View.OnClickListener onClickListener3 = onClickListener;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.common.util.DialogUtil.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
            }
        });
        if (onCancelListener != null) {
            alertDialogCreate.setOnCancelListener(onCancelListener);
        }
        if (onDismissListener != null) {
            alertDialogCreate.setOnDismissListener(onDismissListener);
        }
        alertDialogCreate.show();
        alertDialogCreate.setContentView(viewInflate);
        Window window = alertDialogCreate.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setGravity(i2);
        window.setLayout(i3, attributes.height);
    }

    public static void showConfirm(Activity activity, String str, String str2, String[] strArr, final ICallBack iCallBack) {
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(activity).create();
        alertDialogCreate.setTitle(str);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setMessage(str2);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.DialogUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -2) {
                    alertDialogCreate.cancel();
                    alertDialogCreate.dismiss();
                } else if (i != -3 && i == -1) {
                    alertDialogCreate.dismiss();
                }
                iCallBack.onCallBack(i, null);
            }
        };
        alertDialogCreate.setButton(-1, strArr[0], onClickListener);
        alertDialogCreate.setButton(-2, strArr[1], onClickListener);
        alertDialogCreate.show();
    }

    public static void showDialog(Activity activity, String str, String str2, String[] strArr) {
        final AlertDialog alertDialogCreate = initDialogTheme(activity).create();
        if (strArr != null && PdrUtil.isEmpty(strArr[0])) {
            strArr[0] = AndroidResources.getString(R.string.ok);
        }
        if (!PdrUtil.isEmpty(str)) {
            alertDialogCreate.setTitle(str);
        }
        alertDialogCreate.setCanceledOnTouchOutside(true);
        alertDialogCreate.setMessage(str2);
        alertDialogCreate.setButton(strArr[0], new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.DialogUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.util.DialogUtil.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1 || i != 4) {
                    return false;
                }
                alertDialogCreate.dismiss();
                return true;
            }
        });
        alertDialogCreate.show();
    }

    public static void showLoadAPPDialog(final Context context, String str, final String str2) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setMessage(str).setNegativeButton(context.getString(io.dcloud.base.R.string.dcloud_common_ok), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.DialogUtil.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                NotificationUtil.cancelNotification(context, str2.hashCode());
                context.startActivity(LoadAppUtils.getDataAndTypeIntent(context, str2, "application/vnd.android.package-archive"));
            }
        }).setNeutralButton(context.getString(io.dcloud.base.R.string.dcloud_common_cancel), (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
    }

    public static AlertDialog.Builder initDialogTheme(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        if (i < 11) {
            return new AlertDialog.Builder(activity);
        }
        if (i < 20) {
            return new AlertDialog.Builder(activity, PdrR.STREAMAPP_DELETE_THEME);
        }
        return new AlertDialog.Builder(activity, R.style.Theme.DeviceDefault.Light.Dialog);
    }
}
