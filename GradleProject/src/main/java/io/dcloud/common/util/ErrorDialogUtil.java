package io.dcloud.common.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.view.KeyEvent;
import io.dcloud.PdrR;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IWebview;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorDialogUtil {
    public static ArrayList<String> list = new ArrayList<>();

    public static void checkAppKeyErrorTips(Activity activity) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(activity).setTitle(activity.getString(R.string.dcloud_offline_fail_tips)).setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.ErrorDialogUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Process.killProcess(Process.myPid());
            }
        }).create();
        alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.util.ErrorDialogUtil.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4;
            }
        });
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
    }

    public static Dialog getLossDialog(final IWebview iWebview, String str, final String str2, final String str3) {
        if (list.contains(str3)) {
            return null;
        }
        list.add(str3);
        AlertDialog alertDialogCreate = (Build.VERSION.SDK_INT < 11 ? new AlertDialog.Builder(iWebview.getActivity()) : new AlertDialog.Builder(iWebview.getActivity(), PdrR.FEATURE_LOSS_STYLE)).setTitle("HTML5+ Runtime").setIcon(android.R.drawable.ic_dialog_info).setMessage(str).setPositiveButton(iWebview.getContext().getString(R.string.dcloud_common_view_details), new DialogInterface.OnClickListener() { // from class: io.dcloud.common.util.ErrorDialogUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str2));
                iWebview.getActivity().startActivity(intent);
            }
        }).setNegativeButton(iWebview.getContext().getString(R.string.dcloud_common_ignore), (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: io.dcloud.common.util.ErrorDialogUtil.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ErrorDialogUtil.list.remove(str3);
            }
        });
        return alertDialogCreate;
    }

    public static void showErrorTipsAlert(Activity activity, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(activity).setMessage(str).setNegativeButton(android.R.string.ok, onClickListener).create();
        alertDialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: io.dcloud.common.util.ErrorDialogUtil.5
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4;
            }
        });
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.show();
    }
}
