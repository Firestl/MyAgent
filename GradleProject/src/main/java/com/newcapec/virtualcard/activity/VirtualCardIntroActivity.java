package com.newcapec.virtualcard.activity;

import a.a.a.a.g;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.newcapec.virtualcard.R;

/* JADX INFO: loaded from: classes2.dex */
public class VirtualCardIntroActivity extends g {
    public static void a(Context context) {
        context.startActivity(new Intent(context, (Class<?>) VirtualCardIntroActivity.class));
    }

    public void onClickBack(View view) {
        finish();
    }

    @Override // a.a.a.a.g, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.virtual_card_activity_intro);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
    }
}
