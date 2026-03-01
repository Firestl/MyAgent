package com.igexin.push.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.getui.gtc.base.GtcProvider;
import com.igexin.push.a.e;
import com.igexin.push.core.b.m;
import com.igexin.push.core.b.s;
import com.igexin.push.core.i.a.h;
import com.igexin.push.core.i.a.k;
import com.igexin.push.core.l;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.main.FeedbackImpl;
import com.igexin.sdk.message.GTPopupMessage;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class g extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3229a = false;
    public final String b = "popupAct";
    public final ArrayList<com.igexin.push.core.i.a.f> c = new ArrayList<>();

    /* JADX INFO: renamed from: com.igexin.push.a.g$1, reason: invalid class name */
    public class AnonymousClass1 implements c<m.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f3230a;
        public final /* synthetic */ PushTaskBean b;
        public final /* synthetic */ Context c;

        public AnonymousClass1(Activity activity, PushTaskBean pushTaskBean, Context context) {
            this.f3230a = activity;
            this.b = pushTaskBean;
            this.c = context;
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        private void a2(m.b bVar) {
            if (bVar.o.getAction().isClosePopup()) {
                this.f3230a.finish();
            }
            g.a(bVar, this.b, this.c);
        }

        @Override // com.igexin.push.a.c
        public final /* synthetic */ void a(m.b bVar) {
            m.b bVar2 = bVar;
            if (bVar2.o.getAction().isClosePopup()) {
                this.f3230a.finish();
            }
            g.a(bVar2, this.b, this.c);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.a.g$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f3231a;
        public final /* synthetic */ m.b b;

        public AnonymousClass2(c cVar, m.b bVar) {
            this.f3231a = cVar;
            this.b = bVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3231a.a(this.b);
        }
    }

    /* JADX INFO: renamed from: com.igexin.push.a.g$7, reason: invalid class name */
    public class AnonymousClass7 implements e.a<byte[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ m.b f3238a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ int c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ d f3239e;

        public AnonymousClass7(m.b bVar, Context context, int i, int i2, d dVar) {
            this.f3238a = bVar;
            this.b = context;
            this.c = i;
            this.d = i2;
            this.f3239e = dVar;
        }

        /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
        private void a2(byte[] bArr) {
            try {
                com.igexin.c.a.c.a.b("popupAct", "movie duration is 0 use glide " + this.f3238a.h);
                com.igexin.push.core.i.a.f fVarA = new com.igexin.push.core.i.a.a(this.b).a(ByteBuffer.wrap(bArr), this.c, this.d);
                fVarA.b();
                com.igexin.push.core.i.a.e eVarA = fVarA.c();
                this.f3239e.setImageDrawable(eVarA);
                k.a(!eVarA.d, "You cannot restart a currently running animation.");
                h hVar = eVarA.c.f3472a;
                k.a(hVar.c ? false : true, "Can't restart a running animation");
                hVar.d = true;
                if (hVar.i != null) {
                    hVar.i = null;
                }
                eVarA.start();
                g.this.c.add(fVarA);
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }

        @Override // com.igexin.push.a.e.a
        public final /* synthetic */ void a(byte[] bArr) {
            byte[] bArr2 = bArr;
            try {
                com.igexin.c.a.c.a.b("popupAct", "movie duration is 0 use glide " + this.f3238a.h);
                com.igexin.push.core.i.a.f fVarA = new com.igexin.push.core.i.a.a(this.b).a(ByteBuffer.wrap(bArr2), this.c, this.d);
                fVarA.b();
                com.igexin.push.core.i.a.e eVarA = fVarA.c();
                this.f3239e.setImageDrawable(eVarA);
                k.a(!eVarA.d, "You cannot restart a currently running animation.");
                h hVar = eVarA.c.f3472a;
                k.a(hVar.c ? false : true, "Can't restart a running animation");
                hVar.d = true;
                if (hVar.i != null) {
                    hVar.i = null;
                }
                eVarA.start();
                g.this.c.add(fVarA);
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }

        @Override // com.igexin.push.a.e.a
        public final void a(Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(com.igexin.push.core.b.m.b r4) {
        /*
            java.lang.String r4 = r4.b
            int r0 = r4.hashCode()
            r1 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2c
            r1 = 3317767(0x32a007, float:4.649182E-39)
            if (r0 == r1) goto L22
            r1 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r0 == r1) goto L18
            goto L36
        L18:
            java.lang.String r0 = "right"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L36
            r4 = 2
            goto L37
        L22:
            java.lang.String r0 = "left"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L36
            r4 = 0
            goto L37
        L2c:
            java.lang.String r0 = "center"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L36
            r4 = 1
            goto L37
        L36:
            r4 = -1
        L37:
            if (r4 == r3) goto L43
            if (r4 == r2) goto L3f
            r4 = 8388611(0x800003, float:1.1754948E-38)
            return r4
        L3f:
            r4 = 8388613(0x800005, float:1.175495E-38)
            return r4
        L43:
            r4 = 17
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.a.g.a(com.igexin.push.core.b.m$b):int");
    }

    private View a(final m.b bVar, final Context context, c<m.b> cVar) {
        int i;
        View textView;
        if (bVar != null) {
            switch (bVar.f3358a) {
                case "column":
                case "row":
                case "view":
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(!"row".equals(bVar.f3358a) ? 1 : 0);
                    View viewA = a(bVar, linearLayout, cVar);
                    if (bVar.g != null) {
                        for (i = 0; i < bVar.g.size(); i++) {
                            linearLayout.addView(a(bVar.g.get(i), context, cVar));
                        }
                    }
                    return viewA;
                case "image":
                case "image_button":
                    final d dVar = new d(context);
                    View viewA2 = a(bVar, dVar, cVar);
                    dVar.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    try {
                        if (!TextUtils.isEmpty(bVar.h)) {
                            if (bVar.h.endsWith(".gif")) {
                                final int iA = bVar.a();
                                final int iB = bVar.b();
                                e.b(bVar.h, new e.a<Movie>() { // from class: com.igexin.push.a.g.3
                                    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
                                    private void a2(Movie movie) {
                                        if (movie.duration() == 0) {
                                            g.a(g.this, bVar, context, iA, iB, dVar);
                                            return;
                                        }
                                        d dVar2 = dVar;
                                        dVar2.f3216a = movie;
                                        dVar2.b = 0L;
                                        dVar2.c = 0;
                                        dVar2.setLayerType(1, null);
                                        dVar2.setImageDrawable(null);
                                        dVar2.requestLayout();
                                        dVar2.invalidate();
                                        d dVar3 = dVar;
                                        if (dVar3.d) {
                                            dVar3.d = false;
                                            if (dVar3.f3216a != null) {
                                                dVar3.b = SystemClock.uptimeMillis() - ((long) dVar3.c);
                                                dVar3.invalidate();
                                            }
                                        }
                                    }

                                    @Override // com.igexin.push.a.e.a
                                    public final /* synthetic */ void a(Movie movie) {
                                        Movie movie2 = movie;
                                        if (movie2.duration() == 0) {
                                            g.a(g.this, bVar, context, iA, iB, dVar);
                                            return;
                                        }
                                        d dVar2 = dVar;
                                        dVar2.f3216a = movie2;
                                        dVar2.b = 0L;
                                        dVar2.c = 0;
                                        dVar2.setLayerType(1, null);
                                        dVar2.setImageDrawable(null);
                                        dVar2.requestLayout();
                                        dVar2.invalidate();
                                        d dVar3 = dVar;
                                        if (dVar3.d) {
                                            dVar3.d = false;
                                            if (dVar3.f3216a != null) {
                                                dVar3.b = SystemClock.uptimeMillis() - ((long) dVar3.c);
                                                dVar3.invalidate();
                                            }
                                        }
                                    }

                                    @Override // com.igexin.push.a.e.a
                                    public final void a(Throwable th) {
                                        com.igexin.c.a.c.a.a(th);
                                    }
                                });
                            } else {
                                e.a(bVar.h, bVar.a(), new e.a<Bitmap>() { // from class: com.igexin.push.a.g.4
                                    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
                                    private void a2(Bitmap bitmap) {
                                        dVar.setImageBitmap(bitmap);
                                    }

                                    @Override // com.igexin.push.a.e.a
                                    public final /* synthetic */ void a(Bitmap bitmap) {
                                        dVar.setImageBitmap(bitmap);
                                    }

                                    @Override // com.igexin.push.a.e.a
                                    public final void a(Throwable th) {
                                        com.igexin.c.a.c.a.a(th);
                                    }
                                });
                            }
                        }
                        break;
                    } catch (Throwable th) {
                        com.igexin.c.a.c.a.a(th);
                    }
                    return viewA2;
                case "button":
                    Button button = new Button(context);
                    button.setAllCaps(false);
                    textView = button;
                    break;
                case "label":
                    textView = new TextView(context);
                    break;
            }
            return a(bVar, textView, cVar);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View a(final com.igexin.push.core.b.m.b r6, final android.view.View r7, final com.igexin.push.a.c<com.igexin.push.core.b.m.b> r8) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.a.g.a(com.igexin.push.core.b.m$b, android.view.View, com.igexin.push.a.c):android.view.View");
    }

    private LinearLayout a(c cVar, m mVar, Context context) {
        m.b bVar = mVar.f3355a;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackgroundColor(bVar.m);
        GTPopupMessage gTPopupMessage = bVar.o;
        if (gTPopupMessage != null) {
            GTPopupMessage.GtAction action = gTPopupMessage.getAction();
            if (action.isClosePopup() || !m.a.closePopup.name().equals(action.getActionType())) {
                linearLayout.setOnClickListener(new AnonymousClass2(cVar, bVar));
            } else {
                linearLayout.setClickable(true);
                linearLayout.setFocusable(true);
            }
        }
        View viewA = a(mVar.b, context, (c<m.b>) cVar);
        if (viewA != null) {
            linearLayout.addView(viewA);
        }
        return linearLayout;
    }

    public static /* synthetic */ void a(g gVar, m.b bVar, Context context, int i, int i2, d dVar) {
        e.a(bVar.h, gVar.new AnonymousClass7(bVar, context, i, i2, dVar));
    }

    private void a(m.b bVar, Context context, int i, int i2, d dVar) {
        e.a(bVar.h, new AnonymousClass7(bVar, context, i, i2, dVar));
    }

    public static /* synthetic */ void a(m.b bVar, PushTaskBean pushTaskBean, Context context) {
        l lVarA = l.a();
        GTPopupMessage gTPopupMessage = bVar.o;
        Bundle bundle = new Bundle();
        bundle.setClassLoader(GTPopupMessage.class.getClassLoader());
        bundle.putInt("action", PushConsts.ACTION_POPUP_CLICKED);
        bundle.putSerializable(PushConsts.KEY_POPUP_CLICKED, gTPopupMessage);
        lVarA.a(bundle);
        FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, bVar.p, com.igexin.push.core.b.B);
        GTPopupMessage.GtAction action = bVar.o.getAction();
        String actionType = action.getActionType();
        if (m.a.intent.name().equals(actionType)) {
            new com.igexin.push.core.a.c.l();
            com.igexin.push.core.a.c.l.a(action.getIntent(), context);
            return;
        }
        if (!m.a.url.name().equals(actionType)) {
            m.a.closePopup.name().equals(actionType);
            return;
        }
        new com.igexin.push.core.a.c.m();
        String url = action.getUrl();
        try {
            if (TextUtils.isEmpty(url)) {
                return;
            }
            s sVar = new s();
            sVar.f3367a = url;
            com.igexin.push.core.a.c.m.a(sVar, com.igexin.push.core.b.A);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setFlags(268435456);
            intent.setPackage(sVar.d);
            intent.setData(Uri.parse(sVar.a()));
            context.startActivity(intent);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    public static void b(m.b bVar, PushTaskBean pushTaskBean, Context context) {
        l lVarA = l.a();
        GTPopupMessage gTPopupMessage = bVar.o;
        Bundle bundle = new Bundle();
        bundle.setClassLoader(GTPopupMessage.class.getClassLoader());
        bundle.putInt("action", PushConsts.ACTION_POPUP_CLICKED);
        bundle.putSerializable(PushConsts.KEY_POPUP_CLICKED, gTPopupMessage);
        lVarA.a(bundle);
        FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, bVar.p, com.igexin.push.core.b.B);
        GTPopupMessage.GtAction action = bVar.o.getAction();
        String actionType = action.getActionType();
        if (m.a.intent.name().equals(actionType)) {
            new com.igexin.push.core.a.c.l();
            com.igexin.push.core.a.c.l.a(action.getIntent(), context);
            return;
        }
        if (!m.a.url.name().equals(actionType)) {
            m.a.closePopup.name().equals(actionType);
            return;
        }
        new com.igexin.push.core.a.c.m();
        String url = action.getUrl();
        try {
            if (TextUtils.isEmpty(url)) {
                return;
            }
            s sVar = new s();
            sVar.f3367a = url;
            com.igexin.push.core.a.c.m.a(sVar, com.igexin.push.core.b.A);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setFlags(268435456);
            intent.setPackage(sVar.d);
            intent.setData(Uri.parse(sVar.a()));
            context.startActivity(intent);
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    private void c(Activity activity) {
        Intent intent = activity.getIntent();
        try {
            f3229a = true;
            Context applicationContext = activity.getApplicationContext();
            GtcProvider.setContext(applicationContext);
            try {
                if (Build.VERSION.SDK_INT != 26) {
                    if (1 == applicationContext.getResources().getConfiguration().orientation) {
                        activity.setRequestedOrientation(7);
                    } else {
                        activity.setRequestedOrientation(6);
                    }
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
            m mVar = (m) intent.getExtras().getSerializable("bean");
            PushTaskBean pushTaskBean = new PushTaskBean();
            pushTaskBean.setAppid(mVar.f3356e);
            pushTaskBean.setMessageId(mVar.f);
            pushTaskBean.setTaskId(mVar.g);
            pushTaskBean.setAppKey(mVar.h);
            m.a(applicationContext);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(activity, pushTaskBean, applicationContext);
            m.b bVar = mVar.f3355a;
            LinearLayout linearLayout = new LinearLayout(applicationContext);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            linearLayout.setBackgroundColor(bVar.m);
            if (bVar.o != null) {
                GTPopupMessage.GtAction action = bVar.o.getAction();
                if (action.isClosePopup() || !m.a.closePopup.name().equals(action.getActionType())) {
                    linearLayout.setOnClickListener(new AnonymousClass2(anonymousClass1, bVar));
                } else {
                    linearLayout.setClickable(true);
                    linearLayout.setFocusable(true);
                }
            }
            View viewA = a(mVar.b, applicationContext, anonymousClass1);
            if (viewA != null) {
                linearLayout.addView(viewA);
            }
            activity.setContentView(linearLayout);
            l lVarA = l.a();
            GTPopupMessage gTPopupMessage = mVar.j;
            Bundle bundle = new Bundle();
            bundle.putInt("action", PushConsts.ACTION_POPUP_SHOW);
            bundle.putSerializable(PushConsts.KEY_POPUP_SHOW, gTPopupMessage);
            lVarA.a(bundle);
            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, mVar.i, com.igexin.push.core.b.B);
        } catch (Throwable th2) {
            com.igexin.c.a.c.a.a(th2);
            activity.finish();
        }
    }

    @Override // com.igexin.push.a.b
    public final void a(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT == 26) {
                activity.finish();
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    @Override // com.igexin.push.a.b
    public final boolean a() {
        return false;
    }

    @Override // com.igexin.push.a.b
    public final void b(Activity activity) {
        Intent intent = activity.getIntent();
        try {
            f3229a = true;
            Context applicationContext = activity.getApplicationContext();
            GtcProvider.setContext(applicationContext);
            try {
                if (Build.VERSION.SDK_INT != 26) {
                    if (1 == applicationContext.getResources().getConfiguration().orientation) {
                        activity.setRequestedOrientation(7);
                    } else {
                        activity.setRequestedOrientation(6);
                    }
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
            m mVar = (m) intent.getExtras().getSerializable("bean");
            PushTaskBean pushTaskBean = new PushTaskBean();
            pushTaskBean.setAppid(mVar.f3356e);
            pushTaskBean.setMessageId(mVar.f);
            pushTaskBean.setTaskId(mVar.g);
            pushTaskBean.setAppKey(mVar.h);
            m.a(applicationContext);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(activity, pushTaskBean, applicationContext);
            m.b bVar = mVar.f3355a;
            LinearLayout linearLayout = new LinearLayout(applicationContext);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            linearLayout.setBackgroundColor(bVar.m);
            if (bVar.o != null) {
                GTPopupMessage.GtAction action = bVar.o.getAction();
                if (action.isClosePopup() || !m.a.closePopup.name().equals(action.getActionType())) {
                    linearLayout.setOnClickListener(new AnonymousClass2(anonymousClass1, bVar));
                } else {
                    linearLayout.setClickable(true);
                    linearLayout.setFocusable(true);
                }
            }
            View viewA = a(mVar.b, applicationContext, anonymousClass1);
            if (viewA != null) {
                linearLayout.addView(viewA);
            }
            activity.setContentView(linearLayout);
            l lVarA = l.a();
            GTPopupMessage gTPopupMessage = mVar.j;
            Bundle bundle = new Bundle();
            bundle.putInt("action", PushConsts.ACTION_POPUP_SHOW);
            bundle.putSerializable(PushConsts.KEY_POPUP_SHOW, gTPopupMessage);
            lVarA.a(bundle);
            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, mVar.i, com.igexin.push.core.b.B);
        } catch (Throwable th2) {
            com.igexin.c.a.c.a.a(th2);
            activity.finish();
        }
    }

    @Override // com.igexin.push.a.b
    public final boolean b() {
        return false;
    }

    @Override // com.igexin.push.a.b
    public final void c() {
    }

    @Override // com.igexin.push.a.b
    public final void d() {
    }

    @Override // com.igexin.push.a.b
    public final void e() {
    }

    @Override // com.igexin.push.a.b
    public final void f() {
    }

    @Override // com.igexin.push.a.b
    public final void g() {
    }

    @Override // com.igexin.push.a.b
    public final void h() {
    }

    @Override // com.igexin.push.a.b
    public final void i() {
        f3229a = false;
        for (com.igexin.push.core.i.a.f fVar : this.c) {
            if (fVar != null) {
                fVar.f();
            }
        }
        this.c.clear();
    }

    @Override // com.igexin.push.a.b
    public final void j() {
    }

    @Override // com.igexin.push.a.b
    public final void k() {
    }
}
