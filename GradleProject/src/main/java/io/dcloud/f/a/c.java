package io.dcloud.f.a;

import android.app.Activity;
import android.text.TextUtils;
import io.dcloud.common.DHInterface.IMgr;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.BaseInfo;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f6475a;
    public ArrayList<e> b = new ArrayList<>();
    public ArrayList<String> c;

    public c(a aVar) {
        this.f6475a = null;
        ArrayList<String> arrayList = new ArrayList<>();
        BaseInfo.sRunningApp = arrayList;
        this.c = arrayList;
        this.f6475a = aVar;
    }

    public boolean a(e eVar, ISysEventListener.SysEventType sysEventType, Object obj) {
        boolean z = eVar == null;
        int size = this.b.size();
        e eVar2 = null;
        int i = size - 1;
        boolean zOnExecute = false;
        while (true) {
            if (i < 0) {
                break;
            }
            e eVar3 = this.b.get(i);
            if (!z ? eVar3 == eVar : z) {
                zOnExecute |= eVar3.onExecute(sysEventType, obj);
                if (zOnExecute && !e.a(sysEventType)) {
                    eVar2 = eVar3;
                    break;
                }
                eVar2 = eVar3;
            }
            i--;
        }
        if (zOnExecute || !sysEventType.equals(ISysEventListener.SysEventType.onKeyUp) || size <= 1 || eVar2 == null || ((Integer) ((Object[]) obj)[0]).intValue() != 4) {
            return zOnExecute;
        }
        this.f6475a.processEvent(IMgr.MgrType.WindowMgr, 20, eVar2);
        return true;
    }

    public e b(String str) {
        e next;
        Iterator<e> it = this.b.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (TextUtils.equals(next.obtainAppId(), str)) {
                break;
            }
        }
        Logger.d("AppCache", "removeWebApp " + next + ";mAppIdList=" + this.c);
        this.b.remove(next);
        this.c.remove(str);
        return next;
    }

    public e c() {
        ArrayList<e> arrayList = this.b;
        if (arrayList != null && arrayList.size() == 1) {
            return this.b.get(0);
        }
        ArrayList<e> arrayList2 = this.b;
        if (arrayList2 == null || arrayList2.size() < 1) {
            return null;
        }
        e eVar = this.b.get(0);
        long j = eVar.c1;
        for (int i = 1; i < this.b.size(); i++) {
            e eVar2 = this.b.get(i);
            long j2 = eVar2.c1;
            if (j < j2) {
                eVar = eVar2;
                j = j2;
            }
        }
        return eVar;
    }

    public e d() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        e eVar = null;
        for (int i = 0; i < this.b.size(); i++) {
            e eVar2 = this.b.get(i);
            long j = eVar2.c1;
            if (j < jCurrentTimeMillis) {
                eVar = eVar2;
                jCurrentTimeMillis = j;
            }
        }
        return eVar;
    }

    public int e() {
        return this.b.size();
    }

    public e b() {
        long j = 0;
        e eVar = null;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            e eVar2 = this.b.get(size);
            if (eVar2.q == 3) {
                long j2 = eVar2.c1;
                if (j2 > j) {
                    eVar = eVar2;
                    j = j2;
                }
            }
        }
        return eVar;
    }

    public e a(String str) {
        int iIndexOf = this.c.indexOf(str);
        if (iIndexOf >= 0) {
            return this.b.get(iIndexOf);
        }
        return null;
    }

    public void a(String str, e eVar) {
        this.c.add(str);
        this.b.add(eVar);
    }

    public e a(Activity activity, e eVar) {
        if (this.b.contains(eVar)) {
            return null;
        }
        System.currentTimeMillis();
        if (this.b.size() >= BaseInfo.s_Runing_App_Count_Max) {
            return d();
        }
        return null;
    }

    public void a() {
        this.b.clear();
        this.c.clear();
    }
}
