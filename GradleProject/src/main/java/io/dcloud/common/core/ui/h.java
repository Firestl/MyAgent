package io.dcloud.common.core.ui;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IJsInterface;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.TestUtil;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class h implements IJsInterface, IReflectAble {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f6403e = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsMgr f6404a;
    public IWebview b;
    public String c;
    public MessageHandler.IMessages d = new a();

    public class a implements MessageHandler.IMessages {
        public a() {
        }

        @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
        public void execute(Object obj) {
            Object[] objArr = (Object[]) obj;
            h.this.exec(String.valueOf(objArr[0]), String.valueOf(objArr[1]), (JSONArray) objArr[2]);
        }
    }

    public h(IWebview iWebview) {
        this.f6404a = null;
        this.b = null;
        this.c = null;
        this.b = iWebview;
        this.c = iWebview.obtainFrameView().obtainApp().obtainAppId();
        this.f6404a = this.b.obtainFrameView().obtainWindowMgr();
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    public String exec(String str, String str2, String str3) {
        return exec(str, str2, JSONUtil.createJSONArray(str3));
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    public void forceStop(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f6404a.processEvent(IMgr.MgrType.WindowMgr, 20, str);
    }

    @Override // io.dcloud.common.DHInterface.IJsInterface
    public String prompt(String str, String str2) {
        if (!f6403e) {
            TestUtil.record("JsInterfaceImpl", Thread.currentThread());
            f6403e = true;
        }
        String strExec = null;
        if (str2 != null && str2.length() > 3 && str2.substring(0, 4).equals("pdr:")) {
            try {
                JSONArray jSONArray = new JSONArray(str2.substring(4));
                String string = jSONArray.getString(0);
                String string2 = jSONArray.getString(1);
                boolean z = jSONArray.getBoolean(2);
                JSONArray jSONArrayCreateJSONArray = JSONUtil.createJSONArray(str);
                if (z) {
                    MessageHandler.sendMessage(this.d, new Object[]{string, string2, jSONArrayCreateJSONArray});
                } else {
                    strExec = exec(string, string2, jSONArrayCreateJSONArray);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return strExec;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.dcloud.common.DHInterface.IJsInterface
    public String exec(String str, String str2, JSONArray jSONArray) {
        if (this.b.getContext() == null) {
            return "";
        }
        try {
            String lowerCase = str.toLowerCase();
            "io.dcloud.HBuilder".equals(this.b.getContext().getPackageName());
            return String.valueOf(this.f6404a.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[]{this.b, lowerCase, str2, jSONArray}));
        } catch (Exception e2) {
            Logger.w("JsInterfaceImpl.exec pApiFeatureName=" + str + ";pActionName=" + str2 + ";pArgs=" + String.valueOf(jSONArray), e2);
            return null;
        }
    }
}
