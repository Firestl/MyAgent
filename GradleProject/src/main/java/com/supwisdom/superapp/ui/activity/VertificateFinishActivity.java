package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: loaded from: classes2.dex */
public class VertificateFinishActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Button f4389a;
    public TextView b;

    public /* synthetic */ void a(View view) {
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        setResult(-1);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_vertificate_finish);
        this.f4389a = (Button) findViewById(R.id.btn_complete);
        this.b = (TextView) findViewById(R.id.tv_content);
        this.f4389a.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.tl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9301a.a(view);
            }
        });
        int intExtra = getIntent().getIntExtra("type", 0);
        if (intExtra == 0) {
            this.b.setText(getResources().getString(R.string.reserve_mobile_and_colon) + getIntent().getStringExtra("content"));
            return;
        }
        if (intExtra == 1 || intExtra == 2) {
            this.b.setText(getResources().getString(R.string.identify_card) + getIntent().getStringExtra("content"));
            return;
        }
        if (intExtra == 3) {
            this.b.setVisibility(8);
            return;
        }
        this.b.setText(getResources().getString(R.string.account) + getIntent().getStringExtra("content"));
    }
}
