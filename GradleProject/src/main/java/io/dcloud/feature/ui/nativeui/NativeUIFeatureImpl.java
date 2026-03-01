package io.dcloud.feature.ui.nativeui;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.AndroidResources;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.constant.DOMException;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.DialogUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.RuningAcitvityUtil;
import io.dcloud.feature.nativeObj.photoview.LongClickEventManager;
import io.dcloud.feature.ui.nativeui.a;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class NativeUIFeatureImpl implements IFeature {
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6639e;
    public int f;
    public int h;
    public int i;
    public AbsMgr j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, io.dcloud.feature.ui.nativeui.c> f6638a = null;
    public HashMap<String, io.dcloud.feature.ui.nativeui.a> b = null;
    public DatePickerDialog c = null;
    public TimePickerDialog g = null;

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ byte f6640a;
        public final /* synthetic */ EditText b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f6641e;
        public final /* synthetic */ AlertDialog f;

        public a(NativeUIFeatureImpl nativeUIFeatureImpl, byte b, EditText editText, IWebview iWebview, String str, int i, AlertDialog alertDialog) {
            this.f6640a = b;
            this.b = editText;
            this.c = iWebview;
            this.d = str;
            this.f6641e = i;
            this.f = alertDialog;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.f6640a == 2) {
                String jSONableString = JSONUtil.toJSONableString(this.b.getText().toString());
                Deprecated_JSUtil.execCallback(this.c, this.d, "{index:" + this.f6641e + ",message:" + jSONableString + Operators.BLOCK_END_STR, JSUtil.OK, true, false);
            }
            if (this.f6640a == 1) {
                Deprecated_JSUtil.execCallback(this.c, this.d, String.valueOf(this.f6641e), JSUtil.OK, true, false);
            }
            this.f.dismiss();
        }
    }

    public class b implements DialogInterface.OnKeyListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ byte f6642a;
        public final /* synthetic */ EditText b;
        public final /* synthetic */ IWebview c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ AlertDialog f6643e;

        public b(NativeUIFeatureImpl nativeUIFeatureImpl, byte b, EditText editText, IWebview iWebview, String str, AlertDialog alertDialog) {
            this.f6642a = b;
            this.b = editText;
            this.c = iWebview;
            this.d = str;
            this.f6643e = alertDialog;
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 1 || i != 4) {
                return false;
            }
            if (this.f6642a == 2) {
                String jSONableString = JSONUtil.toJSONableString(this.b.getText().toString());
                Deprecated_JSUtil.execCallback(this.c, this.d, "{index:-1,message:" + jSONableString + Operators.BLOCK_END_STR, JSUtil.OK, true, false);
            }
            if (this.f6642a == 1) {
                Deprecated_JSUtil.execCallback(this.c, this.d, String.valueOf(-1), JSUtil.OK, true, false);
            }
            this.f6643e.dismiss();
            return true;
        }
    }

    public class c extends LongClickEventManager.OnLongClickListener {
        public c(NativeUIFeatureImpl nativeUIFeatureImpl, IWebview iWebview, String str) {
            super(iWebview, str);
        }

        @Override // io.dcloud.feature.nativeObj.photoview.LongClickEventManager.OnLongClickListener
        public void onLongClickListener(JSONObject jSONObject) {
            Deprecated_JSUtil.execCallback(getPwebview(), getCallbackIds(), jSONObject.toString(), JSUtil.OK, true, true);
        }
    }

    public class d implements a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6644a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public d(IWebview iWebview, String str, String str2) {
            this.f6644a = iWebview;
            this.b = str;
            this.c = str2;
        }

        @Override // io.dcloud.feature.ui.nativeui.a.b
        public void initCancelText(TextView textView) {
        }

        @Override // io.dcloud.feature.ui.nativeui.a.b
        public void initTextItem(int i, TextView textView, String str) {
        }

        @Override // io.dcloud.feature.ui.nativeui.a.b
        public boolean onDismiss(int i) {
            HashMap<String, io.dcloud.feature.ui.nativeui.a> map = NativeUIFeatureImpl.this.b;
            if (map == null || !map.containsKey(this.c)) {
                return false;
            }
            NativeUIFeatureImpl.this.b.remove(this.c);
            return false;
        }

        @Override // io.dcloud.feature.ui.nativeui.a.b
        public void onItemClick(int i) {
            Deprecated_JSUtil.execCallback(this.f6644a, this.b, "" + i, JSUtil.OK, true, false);
        }
    }

    public class e implements DatePickerDialog.OnDateSetListener {
        public e() {
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            NativeUIFeatureImpl nativeUIFeatureImpl = NativeUIFeatureImpl.this;
            nativeUIFeatureImpl.d = i;
            nativeUIFeatureImpl.f6639e = i2;
            nativeUIFeatureImpl.f = i3;
        }
    }

    public class f extends DatePickerDialog {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6646a;
        public int b;
        public int c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f6647e;
        public final /* synthetic */ int f;
        public final /* synthetic */ boolean g;
        public final /* synthetic */ IWebview h;
        public final /* synthetic */ String i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(Context context, int i, DatePickerDialog.OnDateSetListener onDateSetListener, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, IWebview iWebview, String str) {
            super(context, i, onDateSetListener, i2, i3, i4);
            this.d = i5;
            this.f6647e = i6;
            this.f = i7;
            this.g = z;
            this.h = iWebview;
            this.i = str;
            this.f6646a = i5;
            this.b = i6;
            this.c = i7;
        }

        @Override // android.app.DatePickerDialog, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            super.onClick(dialogInterface, i);
            if (i == -2) {
                Deprecated_JSUtil.execCallback(this.h, this.i, DOMException.toJSON(-2, DOMException.MSG_USER_CANCEL), JSUtil.ERROR, true, false);
            } else if (i == -1) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                if (DeviceInfo.sDeviceSdkVer >= 21) {
                    NativeUIFeatureImpl nativeUIFeatureImpl = NativeUIFeatureImpl.this;
                    gregorianCalendar.set(nativeUIFeatureImpl.d, nativeUIFeatureImpl.f6639e, nativeUIFeatureImpl.f, 0, 0, 0);
                } else {
                    gregorianCalendar.set(this.f6646a, this.b, this.c, 0, 0, 0);
                }
                Deprecated_JSUtil.execCallback(this.h, this.i, String.valueOf(gregorianCalendar.getTime().getTime()), JSUtil.OK, true, false);
            }
            NativeUIFeatureImpl.this.a();
        }

        @Override // android.app.AlertDialog, android.app.Dialog
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            getWindow().setSoftInputMode(2);
        }

        @Override // android.app.DatePickerDialog, android.widget.DatePicker.OnDateChangedListener
        public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
            this.f6646a = i;
            this.b = i2;
            this.c = i3;
            if (this.g || DeviceInfo.sVersion_release.equals("4.0.3") || DeviceInfo.sVersion_release.equals("4.0.4")) {
                return;
            }
            super.onDateChanged(datePicker, i, i2, i3);
        }
    }

    public class g implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6648a;
        public final /* synthetic */ String b;

        public g(IWebview iWebview, String str) {
            this.f6648a = iWebview;
            this.b = str;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (NativeUIFeatureImpl.this.c != null) {
                Deprecated_JSUtil.execCallback(this.f6648a, this.b, DOMException.toJSON(-2, DOMException.MSG_USER_CANCEL), JSUtil.ERROR, true, false);
                NativeUIFeatureImpl.this.a();
            }
        }
    }

    public class h implements TimePickerDialog.OnTimeSetListener {
        public h() {
        }

        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            NativeUIFeatureImpl nativeUIFeatureImpl = NativeUIFeatureImpl.this;
            nativeUIFeatureImpl.h = i;
            nativeUIFeatureImpl.i = i2;
        }
    }

    public class i extends TimePickerDialog {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6650a;
        public int b;
        public final /* synthetic */ int c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f6651e;
        public final /* synthetic */ IWebview f;
        public final /* synthetic */ String g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i, int i2, boolean z, int i3, int i4, boolean z2, IWebview iWebview, String str) {
            super(context, onTimeSetListener, i, i2, z);
            this.c = i3;
            this.d = i4;
            this.f6651e = z2;
            this.f = iWebview;
            this.g = str;
            this.f6650a = i3;
            this.b = i4;
        }

        @Override // android.app.TimePickerDialog, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            super.onClick(dialogInterface, i);
            if (i == -2) {
                Deprecated_JSUtil.execCallback(this.f, this.g, DOMException.toJSON(-2, DOMException.MSG_USER_CANCEL), JSUtil.ERROR, true, false);
            } else if (i == -1) {
                Date date = new Date();
                if (DeviceInfo.sDeviceSdkVer >= 21) {
                    date.setHours(NativeUIFeatureImpl.this.h);
                    date.setMinutes(NativeUIFeatureImpl.this.i);
                } else {
                    date.setHours(this.f6650a);
                    date.setMinutes(this.b);
                }
                Deprecated_JSUtil.execCallback(this.f, this.g, String.valueOf(date.getTime()), JSUtil.OK, true, false);
            }
            NativeUIFeatureImpl.this.b();
        }

        @Override // android.app.AlertDialog, android.app.Dialog
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            getWindow().setSoftInputMode(2);
        }

        @Override // android.app.TimePickerDialog, android.widget.TimePicker.OnTimeChangedListener
        public void onTimeChanged(TimePicker timePicker, int i, int i2) {
            this.f6650a = i;
            this.b = i2;
            if (this.f6651e) {
                return;
            }
            setTitle(i + Constants.COLON_SEPARATOR + i2);
        }
    }

    public class j implements DialogInterface.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6652a;
        public final /* synthetic */ String b;

        public j(IWebview iWebview, String str) {
            this.f6652a = iWebview;
            this.b = str;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (NativeUIFeatureImpl.this.g != null) {
                Deprecated_JSUtil.execCallback(this.f6652a, this.b, DOMException.toJSON(-2, DOMException.MSG_USER_CANCEL), JSUtil.ERROR, true, false);
                NativeUIFeatureImpl.this.b();
            }
        }
    }

    public class k implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AlertDialog f6653a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;

        public k(NativeUIFeatureImpl nativeUIFeatureImpl, AlertDialog alertDialog, IWebview iWebview, String str) {
            this.f6653a = alertDialog;
            this.b = iWebview;
            this.c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            this.f6653a.dismiss();
            Deprecated_JSUtil.execCallback(this.b, this.c, "{index:0}", JSUtil.OK, true, false);
        }
    }

    public class l implements DialogInterface.OnKeyListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AlertDialog f6654a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String c;

        public l(NativeUIFeatureImpl nativeUIFeatureImpl, AlertDialog alertDialog, IWebview iWebview, String str) {
            this.f6654a = alertDialog;
            this.b = iWebview;
            this.c = str;
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 1 || i != 4) {
                return false;
            }
            this.f6654a.dismiss();
            Deprecated_JSUtil.execCallback(this.b, this.c, "{index:-1}", JSUtil.OK, true, false);
            return true;
        }
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void dispose(String str) {
        if (PdrUtil.isEmpty(str)) {
            this.j = null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0125  */
    @Override // io.dcloud.common.DHInterface.IFeature
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String execute(io.dcloud.common.DHInterface.IWebview r18, java.lang.String r19, java.lang.String[] r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.nativeui.NativeUIFeatureImpl.execute(io.dcloud.common.DHInterface.IWebview, java.lang.String, java.lang.String[]):java.lang.String");
    }

    @Override // io.dcloud.common.DHInterface.IFeature
    public void init(AbsMgr absMgr, String str) {
        this.j = absMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.g = null;
        this.h = 0;
        this.i = 0;
    }

    public void a(String str) {
        this.f6638a.remove(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0269  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(io.dcloud.common.DHInterface.IApp r21, io.dcloud.common.DHInterface.IWebview r22, java.lang.String r23, org.json.JSONObject r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.ui.nativeui.NativeUIFeatureImpl.a(io.dcloud.common.DHInterface.IApp, io.dcloud.common.DHInterface.IWebview, java.lang.String, org.json.JSONObject):void");
    }

    private void b(IWebview iWebview, String str, JSONObject jSONObject) {
        int i2;
        int i3;
        if (this.g != null) {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(5, ""), JSUtil.ERROR, true, false);
            return;
        }
        Activity topRuningActivity = RuningAcitvityUtil.getTopRuningActivity(iWebview.getActivity());
        boolean z = (jSONObject == null || jSONObject.isNull("title")) ? false : true;
        boolean z2 = PdrUtil.parseBoolean(JSONUtil.getString(jSONObject, AbsoluteConst.JSON_KEY_IS24HOUR), true, false);
        String string = JSONUtil.getString(jSONObject, "time");
        String string2 = JSONUtil.getString(jSONObject, "__minutes");
        String string3 = JSONUtil.getString(jSONObject, "__hours");
        if (PdrUtil.isEmpty(string3)) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            PdrUtil.isEmpty(string);
            if (z2) {
                i2 = gregorianCalendar.get(11);
            } else {
                i2 = gregorianCalendar.get(10);
            }
            i3 = gregorianCalendar.get(12);
        } else {
            i2 = Integer.parseInt(string3);
            i3 = Integer.parseInt(string2);
        }
        int i4 = i2;
        int i5 = i3;
        i iVar = new i(topRuningActivity, new h(), i4, i5, z2, i4, i5, z, iWebview, str);
        if (z) {
            iVar.setTitle(JSONUtil.getString(jSONObject, "title"));
        }
        iVar.setOnDismissListener(new j(iWebview, str));
        iVar.show();
        this.g = iVar;
    }

    private void a(IWebview iWebview, String str, JSONObject jSONObject) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.c != null) {
            Deprecated_JSUtil.execCallback(iWebview, str, DOMException.toJSON(5, ""), JSUtil.ERROR, true, false);
            return;
        }
        Activity topRuningActivity = RuningAcitvityUtil.getTopRuningActivity(iWebview.getActivity());
        int i6 = JSONUtil.getInt(jSONObject, AbsoluteConst.JSON_KEY_STARTYEAR);
        int i7 = JSONUtil.getInt(jSONObject, "startMonth");
        int i8 = JSONUtil.getInt(jSONObject, "startDay");
        int i9 = JSONUtil.getInt(jSONObject, AbsoluteConst.JSON_KEY_ENDYEAR);
        int i10 = JSONUtil.getInt(jSONObject, "endMonth");
        int i11 = JSONUtil.getInt(jSONObject, "endDay");
        int i12 = JSONUtil.getInt(jSONObject, "setYear");
        int i13 = JSONUtil.getInt(jSONObject, "setMonth");
        int i14 = JSONUtil.getInt(jSONObject, "setDay");
        if (i12 == 0) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date());
            int i15 = gregorianCalendar.get(1);
            int i16 = gregorianCalendar.get(2);
            i4 = gregorianCalendar.get(5);
            i2 = i15;
            i3 = i16;
        } else {
            i2 = i12;
            i3 = i13;
            i4 = i14;
        }
        boolean z = (jSONObject == null || jSONObject.isNull("title")) ? false : true;
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        f fVar = new f(topRuningActivity, 0, new e(), i2, i3, i4, i2, i3, i4, z, iWebview, str);
        if (DeviceInfo.sDeviceSdkVer >= 11) {
            if (i6 > 1900) {
                gregorianCalendar2.set(1, i6);
                i5 = 2;
                gregorianCalendar2.set(2, i7);
                gregorianCalendar2.set(5, i8);
                fVar.getDatePicker().setMinDate(gregorianCalendar2.getTimeInMillis());
            } else {
                i5 = 2;
            }
            if (i9 > 1900 && i9 >= i6) {
                gregorianCalendar2.set(1, i9);
                gregorianCalendar2.set(i5, i10);
                gregorianCalendar2.set(5, i11);
                fVar.getDatePicker().setMaxDate(gregorianCalendar2.getTimeInMillis());
            }
        }
        fVar.setOnDismissListener(new g(iWebview, str));
        if (z) {
            fVar.setTitle(JSONUtil.getString(jSONObject, "title"));
        }
        fVar.show();
        this.c = fVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.c = null;
        this.d = 0;
        this.f6639e = 0;
        this.f = 0;
    }

    private io.dcloud.feature.ui.nativeui.a a(String str, JSONObject jSONObject, String str2, IWebview iWebview) {
        io.dcloud.feature.ui.nativeui.a aVar;
        String strOptString = jSONObject.has("title") ? jSONObject.optString("title") : null;
        String strOptString2 = jSONObject.has(WXPickersModule.CANCEL) ? jSONObject.optString(WXPickersModule.CANCEL) : null;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("buttons");
        if (jSONArrayOptJSONArray == null || a(jSONArrayOptJSONArray, iWebview.obtainApp(), str2, iWebview)) {
            return null;
        }
        d dVar = new d(iWebview, str2, str);
        Activity topRuningActivity = RuningAcitvityUtil.getTopRuningActivity(iWebview.getActivity());
        topRuningActivity.setTheme(NativeUIR.ACTS_STYLE_ActionSheetStyleIOS7);
        int i2 = R.style.Theme.Light.NoTitleBar;
        if (iWebview.obtainApp().isFullScreen()) {
            i2 = R.style.Theme.Light.NoTitleBar.Fullscreen;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            aVar = new io.dcloud.feature.ui.nativeui.a(topRuningActivity);
        } else {
            aVar = new io.dcloud.feature.ui.nativeui.a(topRuningActivity, i2);
        }
        aVar.b(strOptString2);
        aVar.a(strOptString);
        aVar.a(jSONArrayOptJSONArray);
        aVar.a(dVar);
        aVar.a(true);
        aVar.j();
        return aVar;
    }

    private void a(byte b2, String str, JSONObject jSONObject, IWebview iWebview, String str2) {
        String[] strArr;
        String strOptString = jSONObject.optString(AbsoluteConst.JSON_KEY_VERTICAL_ALIGN, "center");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("buttons");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = JSONUtil.getString(jSONArrayOptJSONArray, i2);
            }
        } else {
            strArr = null;
        }
        String[] strArr2 = strArr;
        if (TextUtils.equals(strOptString, "center")) {
            a(b2, jSONObject.optString("title"), null, str, strArr2, null, iWebview, str2, 17);
        } else if (TextUtils.equals(strOptString, "top")) {
            a(b2, jSONObject.optString("title"), null, str, strArr2, null, iWebview, str2, 49);
        } else if (TextUtils.equals(strOptString, "bottom")) {
            a(b2, jSONObject.optString("title"), null, str, strArr2, null, iWebview, str2, 81);
        }
    }

    private void a(byte b2, String str, String str2, String str3, String[] strArr, String str4, IWebview iWebview, String str5) {
        a(b2, str, str2, str3, strArr, str4, iWebview, str5, 17);
    }

    private void a(byte b2, String str, String str2, String str3, String[] strArr, String str4, IWebview iWebview, String str5, int i2) {
        Activity topRuningActivity = RuningAcitvityUtil.getTopRuningActivity(iWebview.getActivity());
        int i3 = 0;
        if (b2 == 0) {
            if (a(str3, iWebview.obtainApp(), iWebview, str5)) {
                return;
            }
            AlertDialog alertDialogCreate = DialogUtil.initDialogTheme(topRuningActivity, true).create();
            if (strArr != null && PdrUtil.isEmpty(strArr[0])) {
                strArr[0] = AndroidResources.getString(R.string.ok);
            }
            if (!PdrUtil.isEmpty(str)) {
                alertDialogCreate.setTitle(str);
            }
            alertDialogCreate.setCanceledOnTouchOutside(false);
            alertDialogCreate.setMessage(str3);
            alertDialogCreate.setButton(strArr[0], new k(this, alertDialogCreate, iWebview, str5));
            alertDialogCreate.setOnKeyListener(new l(this, alertDialogCreate, iWebview, str5));
            alertDialogCreate.show();
            return;
        }
        AlertDialog alertDialogCreate2 = DialogUtil.initDialogTheme(topRuningActivity, true).create();
        alertDialogCreate2.setMessage(str3);
        EditText editText = null;
        if (b2 == 2) {
            editText = new EditText(topRuningActivity);
            if (str4 != null) {
                editText.setHint(str4);
            }
            alertDialogCreate2.setView(editText);
            Editable text = editText.getText();
            if (text instanceof Spannable) {
                Selection.setSelection(text, text.length());
            }
        }
        EditText editText2 = editText;
        if (b2 == 1) {
            Window window = alertDialogCreate2.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = i2;
            window.setAttributes(attributes);
        }
        if (!PdrUtil.isEmpty(str)) {
            alertDialogCreate2.setTitle(str);
        }
        alertDialogCreate2.setCanceledOnTouchOutside(false);
        alertDialogCreate2.setMessage(str3);
        String[] strArr2 = strArr == null ? new String[]{AndroidResources.getString(R.string.ok), AndroidResources.getString(R.string.cancel)} : strArr;
        String[] strArr3 = strArr2;
        if (a(b2, str3, strArr2, iWebview.obtainApp(), iWebview, str5)) {
            return;
        }
        while (i3 < strArr3.length && i3 < 3) {
            int i4 = i3;
            a aVar = new a(this, b2, editText2, iWebview, str5, i3, alertDialogCreate2);
            if (i4 == 0) {
                alertDialogCreate2.setButton(strArr3[i4], aVar);
            } else if (i4 == 1) {
                alertDialogCreate2.setButton3(strArr3[i4], aVar);
            } else if (i4 == 2) {
                alertDialogCreate2.setButton2(strArr3[i4], aVar);
            }
            i3 = i4 + 1;
        }
        alertDialogCreate2.setOnKeyListener(new b(this, b2, editText2, iWebview, str5, alertDialogCreate2));
        alertDialogCreate2.show();
        if (b2 != 2 || Build.FINGERPRINT.toLowerCase().contains("flyme")) {
            return;
        }
        DeviceInfo.showIME(editText2);
    }

    private boolean a(String str, IApp iApp, IWebview iWebview, String str2) {
        if (TextUtils.isEmpty(str) || !BaseInfo.ISAMU) {
            return false;
        }
        if (!str.contains("更新") && !str.contains("升级") && !str.contains("版本")) {
            return false;
        }
        try {
            Deprecated_JSUtil.execCallback(iWebview, str2, "{index:0}", JSUtil.OK, true, false);
            a(iApp);
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean a(byte b2, String str, String[] strArr, IApp iApp, IWebview iWebview, String str2) {
        if (!TextUtils.isEmpty(str) && strArr != null && BaseInfo.ISAMU) {
            for (int i2 = 0; i2 < strArr.length && i2 < 3; i2++) {
                String str3 = strArr[i2];
                if (str3.contains("更新") || str3.contains("升级") || ((str.contains("更新") || str.contains("升级") || str.contains("版本")) && (str3.equals(WXModalUIModule.OK) || str3.equals("是") || str3.equals("确定")))) {
                    try {
                        if (b2 == 2) {
                            Deprecated_JSUtil.execCallback(iWebview, str2, "{index:" + i2 + ",message:" + JSONUtil.toJSONableString("") + Operators.BLOCK_END_STR, JSUtil.OK, true, false);
                            a(iApp);
                            return true;
                        }
                        if (b2 == 1) {
                            Deprecated_JSUtil.execCallback(iWebview, str2, String.valueOf(i2), JSUtil.OK, true, false);
                            a(iApp);
                            return true;
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    private boolean a(JSONArray jSONArray, IApp iApp, String str, IWebview iWebview) {
        if (jSONArray != null && BaseInfo.ISAMU) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    String string = jSONArray.getJSONObject(i2).getString("title");
                    if (string.contains("更新") || string.contains("升级") || string.contains("版本")) {
                        Deprecated_JSUtil.execCallback(iWebview, str, "" + (i2 + 1), JSUtil.OK, true, false);
                        a(iApp);
                        return true;
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    private void a(IApp iApp) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "notify");
        jSONObject.put("appid", iApp.obtainOriginalAppId());
        jSONObject.put("version", iApp.obtainAppVersionName());
        Log.i(AbsoluteConst.HBUILDER_TAG, jSONObject.toString());
    }
}
