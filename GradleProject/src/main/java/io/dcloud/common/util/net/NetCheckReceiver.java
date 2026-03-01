package io.dcloud.common.util.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;

/* JADX INFO: loaded from: classes2.dex */
public class NetCheckReceiver extends BroadcastReceiver {
    public static final String netACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String simACTION = "android.intent.action.SIM_STATE_CHANGED";
    public AbsMgr mNetMgr;

    public NetCheckReceiver(AbsMgr absMgr) {
        this.mNetMgr = null;
        this.mNetMgr = absMgr;
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
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 1, new Object[]{ISysEventListener.SysEventType.onDeviceNetChanged, null, null});
            } else if (action.equals(simACTION)) {
                this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 1, new Object[]{ISysEventListener.SysEventType.onSimStateChanged, null, null});
            }
        }
    }
}
