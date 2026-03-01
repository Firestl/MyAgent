package com.vivo.push;

import android.content.Intent;
import com.vivo.push.b.r;
import com.vivo.push.b.t;
import com.vivo.push.b.u;
import com.vivo.push.c.af;
import com.vivo.push.c.y;

/* JADX INFO: compiled from: PushClientFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements IPushClientFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public af f5604a = new af();

    @Override // com.vivo.push.IPushClientFactory
    public final y createReceiveTask(o oVar) {
        return af.b(oVar);
    }

    @Override // com.vivo.push.IPushClientFactory
    public final o createReceiverCommand(Intent intent) {
        o uVar;
        int intExtra = intent.getIntExtra("command", -1);
        if (intExtra < 0) {
            intExtra = intent.getIntExtra("method", -1);
        }
        if (intExtra == 20) {
            uVar = new u();
        } else if (intExtra != 2016) {
            switch (intExtra) {
                case 1:
                case 2:
                    uVar = new t(intExtra);
                    break;
                case 3:
                    uVar = new com.vivo.push.b.o();
                    break;
                case 4:
                    uVar = new com.vivo.push.b.q();
                    break;
                case 5:
                    uVar = new com.vivo.push.b.p();
                    break;
                case 6:
                    uVar = new r();
                    break;
                case 7:
                    uVar = new com.vivo.push.b.n();
                    break;
                case 8:
                    uVar = new com.vivo.push.b.m();
                    break;
                case 9:
                    uVar = new com.vivo.push.b.k();
                    break;
                case 10:
                case 11:
                    uVar = new com.vivo.push.b.i(intExtra);
                    break;
                case 12:
                    uVar = new com.vivo.push.b.j();
                    break;
                default:
                    uVar = null;
                    break;
            }
        } else {
            uVar = new com.vivo.push.b.l();
        }
        if (uVar != null) {
            a aVarA = a.a(intent);
            if (aVarA == null) {
                com.vivo.push.util.o.b("PushCommand", "bundleWapper is null");
            } else {
                uVar.b(aVarA);
            }
        }
        return uVar;
    }

    @Override // com.vivo.push.IPushClientFactory
    public final l createTask(o oVar) {
        return af.a(oVar);
    }
}
