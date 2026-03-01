package io.dcloud.common.DHInterface;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import io.dcloud.PdrR;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class ViewHelper {
    public static Dialog createDefaultDialog(Context context, int i, int i2) {
        Dialog dialog = new Dialog(context, PdrR.STYLE_DIALOG_DCLOUD_DEFALUT_DIALOG);
        dialog.requestWindowFeature(1);
        dialog.setContentView(PdrR.LAYOUT_DIALOG_LAYOUT_DCLOUD_DIALOG);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(PdrR.STYLE_DIALOG_STYLE_DCLOUD_ANIM_DIALOG_WINDOW_IN_OUT);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = i;
        attributes.y = i2;
        attributes.width = context.getResources().getDisplayMetrics().widthPixels;
        attributes.gravity = 48;
        window.setAttributes(attributes);
        return dialog;
    }

    public static void showDefaultDialog(final Dialog dialog, View.OnClickListener onClickListener, final Bitmap bitmap, String str, String str2, String str3, String str4, int i) {
        ((TextView) dialog.findViewById(PdrR.ID_DCLOUD_DIALOG_TITLE)).setText(str);
        ((TextView) dialog.findViewById(PdrR.ID_DCLOUD_DIALOG_MSG)).setText(str2);
        ((ImageView) dialog.findViewById(PdrR.ID_DCLOUD_DIALOG_ICON)).setImageBitmap(bitmap);
        TextView textView = (TextView) dialog.findViewById(PdrR.ID_DCLOUD_DIALOG_BTN1);
        textView.setOnClickListener(onClickListener);
        textView.setText(str3);
        TextView textView2 = (TextView) dialog.findViewById(PdrR.ID_DCLOUD_DIALOG_BTN2);
        textView2.setText(str4);
        textView2.setOnClickListener(onClickListener);
        dialog.show();
        if (i > 0) {
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() { // from class: io.dcloud.common.DHInterface.ViewHelper.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }, i);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: io.dcloud.common.DHInterface.ViewHelper.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    timer.cancel();
                    Bitmap bitmap2 = bitmap;
                    if (bitmap2 == null || bitmap2.isRecycled()) {
                        return;
                    }
                    bitmap.recycle();
                }
            });
        }
    }
}
