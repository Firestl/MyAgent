package com.sangfor.sdk.sandbox.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import supwisdom.jb1;
import supwisdom.t91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ChooserActivity extends ResolverActivity {
    public Bundle n;
    public IntentSender o;

    @Override // com.sangfor.sdk.sandbox.ui.ResolverActivity
    public Intent a(ActivityInfo activityInfo, Intent intent) {
        Bundle bundle;
        Bundle bundle2 = this.n;
        if (bundle2 == null || (bundle = bundle2.getBundle(activityInfo.packageName)) == null) {
            return intent;
        }
        Intent intent2 = new Intent(intent);
        intent2.putExtras(bundle);
        return intent2;
    }

    @Override // com.sangfor.sdk.sandbox.ui.ResolverActivity
    public void b(Intent intent) {
        ComponentName component;
        if (this.o == null || (component = intent.getComponent()) == null) {
            return;
        }
        try {
            this.o.sendIntent(this, -1, new Intent().putExtra("android.intent.extra.CHOSEN_COMPONENT", component), null, null);
        } catch (IntentSender.SendIntentException e2) {
            t91.b("ChooserActivity", "Unable to launch supplied IntentSender to report the chosen component: " + e2);
        }
    }

    @Override // com.sangfor.sdk.sandbox.ui.ResolverActivity
    public boolean b() {
        return true;
    }

    public final void d(Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
            intent.addFlags(134742016);
        }
    }

    @Override // com.sangfor.sdk.sandbox.ui.ResolverActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent[] intentArr;
        Intent intent = getIntent();
        Parcelable parcelableExtra = intent.getParcelableExtra("android.intent.extra.INTENT");
        if (!(parcelableExtra instanceof Intent)) {
            t91.d("ChooserActivity", "Target is not an intent: " + parcelableExtra);
            finish();
            super.onCreate(null);
            return;
        }
        Intent intent2 = (Intent) parcelableExtra;
        if (intent2 != null) {
            d(intent2);
        }
        this.n = intent.getBundleExtra("android.intent.extra.REPLACEMENT_EXTRAS");
        CharSequence charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.TITLE");
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.intent.extra.INITIAL_INTENTS");
        if (parcelableArrayExtra != null) {
            Intent[] intentArr2 = new Intent[parcelableArrayExtra.length];
            for (int i = 0; i < parcelableArrayExtra.length; i++) {
                if (!(parcelableArrayExtra[i] instanceof Intent)) {
                    t91.d("ChooserActivity", "Initial intent #" + i + " not an Intent: " + parcelableArrayExtra[i]);
                    finish();
                    super.onCreate(null);
                    return;
                }
                Intent intent3 = (Intent) parcelableArrayExtra[i];
                d(intent3);
                intentArr2[i] = intent3;
            }
            intentArr = intentArr2;
        } else {
            intentArr = null;
        }
        this.o = (IntentSender) intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER");
        super.a(bundle, intent2, charSequenceExtra, jb1.c.X1, intentArr, null, false);
    }
}
