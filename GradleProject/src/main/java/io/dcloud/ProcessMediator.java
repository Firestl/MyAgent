package io.dcloud;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.util.PdrUtil;
import supwisdom.ne;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessMediator extends Activity implements IReflectAble {
    public static final int CODE_REQUEST = 1000;
    public static final int CODE_RESULT = 1001;
    public static final String LOGIC_CLASS = "__class__";
    public static final String PROCESS_ACTIVITY_SOURCE = "process_activity_source";
    public static final String REQ_DATA = "req";
    public static final String RESULT_ACTION = "mediator_process_result_action";
    public static final String RESULT_DATA = "result";
    public static final String STYLE_DATA = "style";
    public static boolean isMultiProcess = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6317a;
    public String b = "";
    public BroadcastReceiver c = new a();

    public interface Logic extends IReflectAble {
        void exec(Context context, Intent intent);
    }

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ProcessMediator.this.b)) {
                ProcessMediator.this.onResult(intent);
            }
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra(LOGIC_CLASS);
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        isMultiProcess = true;
        String stringExtra2 = getIntent().getStringExtra("transaction");
        if (getIntent().hasExtra(PROCESS_ACTIVITY_SOURCE)) {
            this.f6317a = getIntent().getStringExtra(PROCESS_ACTIVITY_SOURCE);
        }
        IntentFilter intentFilter = new IntentFilter();
        boolean zIsEmpty = TextUtils.isEmpty(stringExtra2);
        String str = RESULT_ACTION;
        if (!zIsEmpty) {
            str = RESULT_ACTION + stringExtra2;
        }
        this.b = str;
        intentFilter.addAction(str);
        ne.a(this).a(this.c, intentFilter);
        try {
            Object objNewInstance = Class.forName(stringExtra).newInstance();
            if (objNewInstance instanceof Logic) {
                ((Logic) objNewInstance).exec(this, getIntent());
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f6317a = null;
        isMultiProcess = false;
        ne.a(this).a(this.c);
    }

    public void onResult(Intent intent) {
        setResult(1001, intent);
        finishActivity(1000);
        finish();
        if (PdrUtil.isEmpty(this.f6317a)) {
            return;
        }
        Intent intent2 = new Intent();
        intent2.setClassName(getPackageName(), this.f6317a);
        startActivity(intent2);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
