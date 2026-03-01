package io.dcloud.common.adapter.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.dcloud.common.adapter.util.PermissionUtil;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FileChooseDialog extends Dialog {
    public Activity activity;
    public int onedp;
    public int onesp;
    public List<File> uris;

    public class GridAdapter extends BaseAdapter {
        public Context context;
        public List<Item> items;

        public class ViewHolder {
            public ImageView iv;

            /* JADX INFO: renamed from: tv, reason: collision with root package name */
            public TextView f6355tv;

            public ViewHolder() {
            }
        }

        public GridAdapter(Context context, List<Item> list) {
            this.context = context;
            this.items = list;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.items.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.items.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = FileChooseDialog.this.createView(this.context, this.items.get(i));
                viewHolder = new ViewHolder();
                ViewGroup viewGroup2 = (ViewGroup) view;
                viewHolder.iv = (ImageView) viewGroup2.getChildAt(0);
                viewHolder.f6355tv = (TextView) viewGroup2.getChildAt(1);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.iv.setImageDrawable(this.items.get(i).icon);
            viewHolder.f6355tv.setText(this.items.get(i).name);
            return view;
        }
    }

    public class Item {
        public Intent i;
        public Drawable icon;
        public String name;

        public Item(String str, Drawable drawable, Intent intent) {
            this.name = str;
            this.icon = drawable;
            this.i = intent;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x01d9 A[LOOP:1: B:21:0x01d3->B:23:0x01d9, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public FileChooseDialog(android.content.Context r18, android.app.Activity r19, android.content.Intent r20) {
        /*
            Method dump skipped, instruction units count: 529
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.adapter.ui.FileChooseDialog.<init>(android.content.Context, android.app.Activity, android.content.Intent):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View createView(Context context, final Item item) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(item.icon);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int i = this.onedp * 50;
        linearLayout.addView(imageView, new LinearLayout.LayoutParams(i, i));
        TextView textView = new TextView(context);
        textView.setText(item.name);
        textView.setTextSize(this.onesp * 4);
        textView.setGravity(17);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = this.onedp * 10;
        linearLayout.addView(textView, layoutParams);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: io.dcloud.common.adapter.ui.FileChooseDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (item.i.getAction() == null || !(item.i.getAction().equals("android.media.action.IMAGE_CAPTURE") || item.i.getAction().equals("android.media.action.VIDEO_CAPTURE"))) {
                    FileChooseDialog.this.activity.startActivityForResult(item.i, 1);
                } else {
                    PermissionUtil.requestSystemPermissions(FileChooseDialog.this.activity, new String[]{"android.permission.CAMERA"}, PermissionUtil.getRequestCode(), new PermissionUtil.Request() { // from class: io.dcloud.common.adapter.ui.FileChooseDialog.2.1
                        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                        public void onDenied(String str) {
                        }

                        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
                        public void onGranted(String str) {
                            FileChooseDialog.this.activity.startActivityForResult(item.i, 2);
                        }
                    });
                }
            }
        });
        return linearLayout;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        attributes.height = -2;
        View decorView = getWindow().getDecorView();
        int i = this.onedp;
        decorView.setPadding(0, i * 20, 0, i * 10);
        getWindow().getDecorView().setBackgroundColor(-1);
        getWindow().setAttributes(attributes);
    }
}
