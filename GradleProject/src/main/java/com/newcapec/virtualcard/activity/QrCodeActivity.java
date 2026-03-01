package com.newcapec.virtualcard.activity;

import a.a.a.a.g;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.newcapec.virtualcard.R;
import java.util.HashMap;
import supwisdom.b0;
import supwisdom.d0;
import supwisdom.ne;
import supwisdom.v;

/* JADX INFO: loaded from: classes2.dex */
public class QrCodeActivity extends g {
    public ImageView b;
    public BroadcastReceiver c;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            QrCodeActivity.this.finish();
        }
    }

    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            QrCodeActivity.this.e(intent.getStringExtra("code"));
        }
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) QrCodeActivity.class);
        intent.putExtra("code", str);
        context.startActivity(intent);
    }

    public final void e(String str) {
        if (isFinishing()) {
            return;
        }
        try {
            d0.b("code = " + str);
            int i = (b0.f6999a.getResources().getDisplayMetrics().widthPixels * 4) / 5;
            int iA = v.e().a();
            HashMap map = new HashMap();
            map.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i, map);
            int width = bitMatrixEncode.getWidth();
            int height = bitMatrixEncode.getHeight();
            int i2 = width * height;
            int[] iArr = new int[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                iArr[i3] = -1;
            }
            for (int i4 = 0; i4 < height; i4++) {
                for (int i5 = 0; i5 < width; i5++) {
                    if (bitMatrixEncode.get(i5, i4)) {
                        iArr[(i4 * width) + i5] = iA;
                    }
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            this.b.setImageBitmap(bitmapCreateBitmap);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onClickBack(View view) {
        finish();
    }

    @Override // a.a.a.a.g, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.virtual_card_activity_qr_code);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        a();
        ImageView imageView = (ImageView) findViewById(R.id.iv_qr_code);
        this.b = imageView;
        imageView.setOnClickListener(new a());
        e(getIntent().getStringExtra("code"));
        this.c = new b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_qr_code");
        ne.a(this).a(this.c, intentFilter);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            if (this.c != null) {
                ne.a(this).a(this.c);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
