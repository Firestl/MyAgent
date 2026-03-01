package com.sangfor.sdk.auth;

import com.sangfor.sdk.utils.SFLogN;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import supwisdom.i71;
import supwisdom.l81;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class Sangfor_b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<Sangfor_c, String> f3893a;
    public Map<Class<?>, d> b;
    public final List<b> c;
    public final Comparator<b> d;

    /* JADX INFO: compiled from: Proguard */
    public enum Sangfor_c {
        EVENT_AUTH_SUCCESSED,
        EVENT_AUTH_FAILED,
        EVENT_AUTH_PROGRESS,
        EVENT_UNSUPPORT_SECOND_AUTH,
        EVENT_LOGOUT,
        EVENT_LOGOUT_USER_ACTIVE,
        EVENT_JUMP_PAGE,
        EVENT_SSO_VERIFY_CODE_SUCCESS,
        EVENT_SSO_VERIFY_CODE_CANCEL
    }

    /* JADX INFO: compiled from: Proguard */
    public class a implements Comparator<b> {
        public a(Sangfor_b sangfor_b) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            return bVar2.a() - bVar.a();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public interface b {
        int a();

        void a(Sangfor_c sangfor_c, l81 l81Var);
    }

    /* JADX INFO: compiled from: Proguard */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Sangfor_b f3894a = new Sangfor_b(null);
    }

    /* JADX INFO: compiled from: Proguard */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Sangfor_c f3895a;
        public l81 b;
    }

    public /* synthetic */ Sangfor_b(a aVar) {
        this();
    }

    public static Sangfor_b b() {
        return c.f3894a;
    }

    public final void a() {
        this.f3893a.put(Sangfor_c.EVENT_UNSUPPORT_SECOND_AUTH, i71.b.n4);
    }

    public void c(b bVar) {
        synchronized (this.c) {
            this.c.remove(bVar);
        }
    }

    public Sangfor_b() {
        this.f3893a = new HashMap();
        this.b = new HashMap();
        this.c = new ArrayList();
        this.d = new a(this);
        a();
    }

    public final void a(b bVar) {
        Class<?> cls = bVar.getClass();
        SFLogN.c("AuthResultEventBus", "handleStickyEvent EventListener:" + cls.getSimpleName());
        synchronized (this.b) {
            if (!this.b.containsKey(cls)) {
                SFLogN.c("AuthResultEventBus", String.format("EventListener{%s} is not sticky event", bVar.getClass().getSimpleName()));
                return;
            }
            d dVarRemove = this.b.remove(cls);
            SFLogN.c("AuthResultEventBus", "mStickyEventMaps size: " + this.b.size());
            if (dVarRemove == null) {
                SFLogN.b("AuthResultEventBus", "stickyEvent called failed", "stickyEvent is null");
            } else {
                bVar.a(dVarRemove.f3895a, dVarRemove.b);
            }
        }
    }

    public void b(b bVar) {
        if (bVar == null) {
            SFLogN.b("AuthResultEventBus", "registerListener failed", "params listener is null");
            return;
        }
        synchronized (this.c) {
            if (!this.c.contains(bVar)) {
                this.c.add(bVar);
            }
            Collections.sort(this.c, this.d);
        }
        a(bVar);
    }
}
