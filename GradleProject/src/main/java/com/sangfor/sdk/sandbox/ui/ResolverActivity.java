package com.sangfor.sdk.sandbox.ui;

import android.R;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sangfor.sdk.base.BaseActivity;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import supwisdom.ab1;
import supwisdom.bb1;
import supwisdom.jb1;
import supwisdom.t91;
import supwisdom.ua1;
import supwisdom.xa1;
import supwisdom.ya1;
import supwisdom.za1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ResolverActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ya1 f3943a;
    public xa1 b;
    public h c;
    public PackageManager d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3944e;
    public boolean f;
    public ListView g;
    public Button h;
    public Button i;
    public int j;
    public int k = -1;
    public Intent l;
    public ComponentName m;

    /* JADX INFO: compiled from: Proguard */
    public class a implements ya1.a {
        public a() {
        }

        @Override // supwisdom.ya1.a
        public void a() {
            ResolverActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResolverActivity resolverActivity = ResolverActivity.this;
            resolverActivity.Sangfor_a(resolverActivity.h);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ResolverActivity resolverActivity = ResolverActivity.this;
            resolverActivity.Sangfor_a(resolverActivity.i);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ResolveInfo f3948a;
        public CharSequence b;
        public Drawable c;
        public CharSequence d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Intent f3949e;

        public d(ResolveInfo resolveInfo, CharSequence charSequence, CharSequence charSequence2, Intent intent) {
            this.f3948a = resolveInfo;
            this.b = charSequence;
            this.d = charSequence2;
            this.f3949e = intent;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class e implements AdapterView.OnItemLongClickListener {
        public e() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            int headerViewsCount = i - ResolverActivity.this.g.getHeaderViewsCount();
            if (headerViewsCount < 0) {
                return false;
            }
            ResolverActivity.this.b(ResolverActivity.this.c.b(headerViewsCount, true));
            return true;
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public final class h extends BaseAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent[] f3953a;
        public final List<ResolveInfo> b;
        public ResolveInfo c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public List<ResolveInfo> f3954e;
        public boolean g;
        public int f = -1;
        public List<d> d = new ArrayList();

        public h(Context context, Intent[] intentArr, List<ResolveInfo> list, boolean z) {
            this.f3953a = intentArr;
            this.b = list;
            this.g = z;
            e();
        }

        public d a() {
            int i;
            if (!this.g || (i = this.f) < 0) {
                return null;
            }
            return this.d.get(i);
        }

        public int b() {
            int i;
            if (!this.g || (i = this.f) < 0) {
                return -1;
            }
            return i;
        }

        public void c() {
            e();
            notifyDataSetChanged();
            if (getCount() == 0) {
                ResolverActivity.this.finish();
            }
        }

        public boolean d() {
            return this.g && this.f >= 0;
        }

        public final void e() {
            CharSequence charSequenceLoadLabel;
            this.c = ResolverActivity.this.b.a(ResolverActivity.this.l);
            this.d.clear();
            List<ResolveInfo> listQueryIntentActivities = this.b;
            if (listQueryIntentActivities != null) {
                this.f3954e = listQueryIntentActivities;
            } else {
                listQueryIntentActivities = ResolverActivity.this.d.queryIntentActivities(ResolverActivity.this.l, 65536 | (this.g ? 64 : 0));
                this.f3954e = listQueryIntentActivities;
            }
            List<ResolveInfo> list = listQueryIntentActivities;
            for (int size = list.size() - 1; size >= 0; size--) {
                ActivityInfo activityInfo = list.get(size).activityInfo;
                if (!a(ResolverActivity.this.d, activityInfo)) {
                    t91.d("ResolverActivity", "should not show component: " + activityInfo);
                    if (list == this.f3954e) {
                        this.f3954e = new ArrayList(this.f3954e);
                    }
                    list.remove(size);
                }
            }
            int size2 = list.size();
            if (size2 > 0) {
                ResolveInfo resolveInfo = list.get(0);
                int i = size2;
                for (int i2 = 1; i2 < i; i2++) {
                    ResolveInfo resolveInfo2 = list.get(i2);
                    if (resolveInfo.priority != resolveInfo2.priority || resolveInfo.isDefault != resolveInfo2.isDefault) {
                        while (i2 < i) {
                            if (this.f3954e == list) {
                                this.f3954e = new ArrayList(this.f3954e);
                            }
                            list.remove(i2);
                            i--;
                        }
                    }
                }
                if (i > 1) {
                    ResolverActivity resolverActivity = ResolverActivity.this;
                    Collections.sort(list, resolverActivity.new i(resolverActivity, resolverActivity.l));
                }
                if (this.f3953a != null) {
                    int i3 = 0;
                    while (true) {
                        Intent[] intentArr = this.f3953a;
                        if (i3 >= intentArr.length) {
                            break;
                        }
                        Intent intent = intentArr[i3];
                        if (intent != null) {
                            ActivityInfo activityInfoResolveActivityInfo = intent.resolveActivityInfo(ResolverActivity.this.d, 0);
                            if (a(ResolverActivity.this.d, activityInfoResolveActivityInfo)) {
                                ResolveInfo resolveInfo3 = new ResolveInfo();
                                resolveInfo3.activityInfo = activityInfoResolveActivityInfo;
                                if (intent instanceof LabeledIntent) {
                                    LabeledIntent labeledIntent = (LabeledIntent) intent;
                                    resolveInfo3.resolvePackageName = labeledIntent.getSourcePackage();
                                    resolveInfo3.labelRes = labeledIntent.getLabelResource();
                                    resolveInfo3.nonLocalizedLabel = labeledIntent.getNonLocalizedLabel();
                                    resolveInfo3.icon = labeledIntent.getIconResource();
                                    charSequenceLoadLabel = resolveInfo3.loadLabel(ResolverActivity.this.getPackageManager());
                                } else {
                                    charSequenceLoadLabel = a(activityInfoResolveActivityInfo) ? jb1.c.c2 : resolveInfo3.loadLabel(ResolverActivity.this.getPackageManager());
                                }
                                a(ResolverActivity.this.new d(resolveInfo3, charSequenceLoadLabel, null, intent));
                            } else {
                                t91.d("ResolverActivity", "should not show: " + intent);
                            }
                        }
                        i3++;
                    }
                }
                ResolveInfo resolveInfo4 = list.get(0);
                CharSequence charSequenceLoadLabel2 = resolveInfo4.loadLabel(ResolverActivity.this.d);
                ResolverActivity.this.f = false;
                ResolveInfo resolveInfo5 = resolveInfo4;
                CharSequence charSequence = charSequenceLoadLabel2;
                int i4 = 0;
                for (int i5 = 1; i5 < i; i5++) {
                    if (charSequence == null) {
                        charSequence = resolveInfo5.activityInfo.packageName;
                    }
                    ResolveInfo resolveInfo6 = list.get(i5);
                    CharSequence charSequenceLoadLabel3 = resolveInfo6.loadLabel(ResolverActivity.this.d);
                    if (charSequenceLoadLabel3 == null) {
                        charSequenceLoadLabel3 = resolveInfo6.activityInfo.packageName;
                    }
                    CharSequence charSequence2 = charSequenceLoadLabel3;
                    if (!charSequence2.equals(charSequence)) {
                        a(list, i4, i5 - 1, resolveInfo5, charSequence);
                        i4 = i5;
                        resolveInfo5 = resolveInfo6;
                        charSequence = charSequence2;
                    }
                }
                a(list, i4, i - 1, resolveInfo5, charSequence);
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = this.d.size();
            return (!this.g || this.f < 0) ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = view;
            if (view == null) {
                ab1 ab1Var = new ab1(viewGroup.getContext());
                ab1Var.setTag(new j(ab1Var));
                view2 = ab1Var;
            }
            a(view2, getItem(i));
            return view2;
        }

        public ResolveInfo b(int i, boolean z) {
            return (z ? getItem(i) : this.d.get(i)).f3948a;
        }

        public final boolean a(ActivityInfo activityInfo) {
            return ResolverActivity.this.m.equals(new ComponentName(activityInfo.packageName, activityInfo.name));
        }

        public final boolean a(PackageManager packageManager, ActivityInfo activityInfo) {
            if (activityInfo == null || !activityInfo.enabled) {
                return false;
            }
            if (TextUtils.equals(ResolverActivity.this.getPackageName(), activityInfo.packageName)) {
                return true;
            }
            if (activityInfo.exported) {
                return TextUtils.isEmpty(activityInfo.permission) || packageManager.checkPermission(activityInfo.permission, ResolverActivity.this.getPackageName()) == 0;
            }
            return false;
        }

        public final void a(List<ResolveInfo> list, int i, int i2, ResolveInfo resolveInfo, CharSequence charSequence) {
            boolean z = true;
            if ((i2 - i) + 1 != 1) {
                ResolverActivity.this.f = true;
                CharSequence charSequenceLoadLabel = resolveInfo.activityInfo.applicationInfo.loadLabel(ResolverActivity.this.d);
                boolean z2 = charSequenceLoadLabel == null;
                if (!z2) {
                    HashSet hashSet = new HashSet();
                    hashSet.add(charSequenceLoadLabel);
                    int i3 = i + 1;
                    while (true) {
                        if (i3 > i2) {
                            z = z2;
                            break;
                        }
                        CharSequence charSequenceLoadLabel2 = list.get(i3).activityInfo.applicationInfo.loadLabel(ResolverActivity.this.d);
                        if (charSequenceLoadLabel2 == null || hashSet.contains(charSequenceLoadLabel2)) {
                            break;
                        }
                        hashSet.add(charSequenceLoadLabel2);
                        i3++;
                    }
                    hashSet.clear();
                    z2 = z;
                }
                while (i <= i2) {
                    ResolveInfo resolveInfo2 = list.get(i);
                    if (z2) {
                        a(ResolverActivity.this.new d(resolveInfo2, charSequence, resolveInfo2.activityInfo.packageName, null));
                    } else {
                        ResolverActivity resolverActivity = ResolverActivity.this;
                        a(resolverActivity.new d(resolveInfo2, charSequence, resolveInfo2.activityInfo.applicationInfo.loadLabel(resolverActivity.d), null));
                    }
                    a(resolveInfo2);
                    i++;
                }
                return;
            }
            a(ResolverActivity.this.new d(resolveInfo, charSequence, null, null));
            a(resolveInfo);
        }

        public final void a(ResolveInfo resolveInfo) {
            ResolveInfo resolveInfo2 = this.c;
            if (resolveInfo2 != null && resolveInfo2.activityInfo.packageName.equals(resolveInfo.activityInfo.packageName) && this.c.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                this.f = this.d.size() - 1;
            }
        }

        public final void a(d dVar) {
            this.d.add(dVar);
        }

        public Intent a(int i, boolean z) {
            return ResolverActivity.this.a(z ? getItem(i) : this.d.get(i));
        }

        @Override // android.widget.Adapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d getItem(int i) {
            int i2;
            if (this.g && (i2 = this.f) >= 0 && i >= i2) {
                i++;
            }
            return this.d.get(i);
        }

        public final void a(View view, d dVar) {
            j jVar = (j) view.getTag();
            jVar.f3956a.setText(dVar.b);
            if (ResolverActivity.this.f) {
                jVar.b.setVisibility(0);
                jVar.b.setText(dVar.d);
            } else {
                jVar.b.setVisibility(8);
            }
            if (dVar.c == null) {
                ResolverActivity.this.new g().execute(dVar);
            }
            jVar.c.setImageDrawable(dVar.c);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class i implements Comparator<ResolveInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Collator f3955a;
        public final boolean b;

        public i(Context context, Intent intent) {
            this.f3955a = Collator.getInstance(context.getResources().getConfiguration().locale);
            String scheme = intent.getScheme();
            this.b = "http".equals(scheme) || "https".equals(scheme);
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            boolean zA;
            if (this.b && (zA = ResolverActivity.a(resolveInfo.match)) != ResolverActivity.a(resolveInfo2.match)) {
                return zA ? -1 : 1;
            }
            CharSequence charSequenceLoadLabel = resolveInfo.loadLabel(ResolverActivity.this.d);
            if (charSequenceLoadLabel == null) {
                charSequenceLoadLabel = resolveInfo.activityInfo.name;
            }
            CharSequence charSequenceLoadLabel2 = resolveInfo2.loadLabel(ResolverActivity.this.d);
            if (charSequenceLoadLabel2 == null) {
                charSequenceLoadLabel2 = resolveInfo2.activityInfo.name;
            }
            return this.f3955a.compare(charSequenceLoadLabel.toString(), charSequenceLoadLabel2.toString());
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f3956a;
        public TextView b;
        public ImageView c;

        public j(ab1 ab1Var) {
            this.f3956a = ab1Var.g();
            this.b = ab1Var.e();
            this.c = ab1Var.f();
        }
    }

    public static final boolean a(int i2) {
        int i3 = i2 & 268369920;
        return i3 >= 3145728 && i3 <= 5242880;
    }

    public void Sangfor_a(View view) {
        a(this.f3944e ? this.g.getCheckedItemPosition() : this.c.b(), view == this.h, this.f3944e);
        c();
    }

    public Intent a(ActivityInfo activityInfo, Intent intent) {
        return intent;
    }

    public void b(Intent intent) {
    }

    public boolean b() {
        return false;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle, e(), null, null, null, null, true);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        int headerViewsCount = i2 - this.g.getHeaderViewsCount();
        if (headerViewsCount < 0) {
            return;
        }
        int checkedItemPosition = this.g.getCheckedItemPosition();
        boolean z = checkedItemPosition != -1;
        if (!this.f3944e || (z && this.k == checkedItemPosition)) {
            a(headerViewsCount, false, true);
            return;
        }
        a(z, checkedItemPosition, true);
        this.i.setEnabled(z);
        if (z) {
            this.g.smoothScrollToPosition(checkedItemPosition);
        }
        this.k = checkedItemPosition;
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        this.c.c();
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.f3944e) {
            int checkedItemPosition = this.g.getCheckedItemPosition();
            boolean z = checkedItemPosition != -1;
            this.k = checkedItemPosition;
            a(z, checkedItemPosition, true);
            this.i.setEnabled(z);
            if (z) {
                this.g.setSelection(checkedItemPosition);
            }
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        if ((getIntent().getFlags() & 268435456) == 0 || isChangingConfigurations()) {
            return;
        }
        finish();
    }

    public void b(ResolveInfo resolveInfo) {
        startActivity(new Intent().setAction("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", resolveInfo.activityInfo.packageName, null)).addFlags(524288));
    }

    public void c() {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    public final void d() {
        setTheme(R.style.Theme.Holo.Light.NoActionBar);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.argb(140, 0, 0, 0)));
        window.requestFeature(1);
    }

    public final Intent e() {
        Intent intent = new Intent(getIntent());
        intent.setComponent(null);
        intent.setFlags(intent.getFlags() & (-8388609));
        return intent;
    }

    /* JADX INFO: compiled from: Proguard */
    public class f extends AsyncTask<d, Void, d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ImageView f3951a;

        public f(ImageView imageView) {
            this.f3951a = imageView;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d doInBackground(d... dVarArr) {
            d dVar = dVarArr[0];
            if (dVar.c == null) {
                dVar.c = ResolverActivity.this.a(dVar.f3948a);
            }
            return dVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(d dVar) {
            this.f3951a.setImageDrawable(dVar.c);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class g extends AsyncTask<d, Void, d> {
        public g() {
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d doInBackground(d... dVarArr) {
            d dVar = dVarArr[0];
            if (dVar.c == null) {
                dVar.c = ResolverActivity.this.a(dVar.f3948a);
            }
            return dVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(d dVar) {
            ResolverActivity.this.c.notifyDataSetChanged();
        }
    }

    public void a(Bundle bundle, Intent intent, CharSequence charSequence, CharSequence charSequence2, Intent[] intentArr, List<ResolveInfo> list, boolean z) {
        boolean z2;
        d();
        super.onCreate(bundle);
        this.d = getPackageManager();
        this.b = ua1.g().a();
        this.m = new ComponentName(this, (Class<?>) ResolverActivity.class);
        this.j = ((ActivityManager) getSystemService("activity")).getLauncherLargeIconDensity();
        this.l = new Intent(intent);
        h hVar = new h(this, intentArr, list, z);
        this.c = hVar;
        if (hVar.d()) {
            this.f3943a = new bb1(this, true);
            z2 = true;
            z = false;
        } else {
            this.f3943a = new bb1(this, false);
            z2 = false;
        }
        this.f3944e = z;
        int size = this.c.d.size();
        if (size > 1 || (size == 1 && b())) {
            setContentView((View) this.f3943a);
            ListView listViewA = this.f3943a.a();
            this.g = listViewA;
            if (z2) {
                listViewA.addHeaderView(new za1(this));
            }
            this.g.setAdapter((ListAdapter) this.c);
            this.g.setOnItemClickListener(this);
            this.g.setOnItemLongClickListener(new e());
            if (z) {
                this.g.setChoiceMode(1);
            }
        } else if (size == 1) {
            c(this.c.a(0, false));
            finish();
            return;
        } else {
            setContentView((View) this.f3943a);
            this.f3943a.g().setVisibility(0);
            ListView listViewA2 = this.f3943a.a();
            this.g = listViewA2;
            listViewA2.setVisibility(8);
        }
        this.f3943a.a(new a());
        getWindow().clearFlags(65792);
        if (charSequence == null) {
            charSequence = a(intent.getAction(), charSequence2);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            TextView textViewD = this.f3943a.d();
            if (textViewD != null) {
                textViewD.setText(charSequence);
            }
            setTitle(charSequence);
        }
        ImageView imageViewB = this.f3943a.b();
        d dVarA = this.c.a();
        if (imageViewB != null && dVarA != null) {
            new f(imageViewB).execute(dVarA);
        }
        if (z || this.c.d()) {
            LinearLayout linearLayoutF = this.f3943a.f();
            if (linearLayoutF != null) {
                linearLayoutF.setVisibility(0);
                this.h = this.f3943a.e();
                this.i = this.f3943a.c();
                this.h.setOnClickListener(new b());
                this.i.setOnClickListener(new c());
            } else {
                this.f3944e = false;
            }
        }
        if (this.c.d()) {
            a(true, this.c.b(), false);
            this.i.setEnabled(true);
        }
    }

    public void c(Intent intent) {
        intent.addFlags(33554432);
        int flags = intent.getFlags() & 3;
        if (flags != 0) {
            try {
                Uri uriA = a(intent);
                if (uriA != null) {
                    grantUriPermission(intent.getComponent().getPackageName(), uriA, flags);
                }
            } catch (Exception unused) {
            }
        }
        startActivity(intent);
        b(intent);
    }

    public CharSequence a(String str, CharSequence charSequence) {
        if (this.c.d()) {
            return "android.intent.action.VIEW".equals(str) ? String.format(jb1.c.R1, this.c.a().b) : "android.intent.action.EDIT".equals(str) ? String.format(jb1.c.T1, this.c.a().b) : "android.intent.action.SEND".equals(str) ? String.format(jb1.c.V1, this.c.a().b) : String.format(jb1.c.P1, this.c.a().b);
        }
        if ("android.intent.action.VIEW".equals(str)) {
            return jb1.c.Q1;
        }
        if ("android.intent.action.EDIT".equals(str)) {
            return jb1.c.S1;
        }
        if ("android.intent.action.SEND".equals(str)) {
            return jb1.c.U1;
        }
        return jb1.c.O1;
    }

    public Drawable a(Resources resources, int i2) {
        try {
            return resources.getDrawableForDensity(i2, this.j);
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    public Drawable a(ResolveInfo resolveInfo) {
        Drawable drawableA;
        try {
            String str = resolveInfo.resolvePackageName;
            if (str != null && resolveInfo.icon != 0 && (drawableA = a(this.d.getResourcesForApplication(str), resolveInfo.icon)) != null) {
                return drawableA;
            }
            int iconResource = resolveInfo.getIconResource();
            if (iconResource != 0) {
                Drawable drawableA2 = a(this.d.getResourcesForApplication(resolveInfo.activityInfo.packageName), iconResource);
                if (drawableA2 != null) {
                    return drawableA2;
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            t91.a("ResolverActivity", "Couldn't find resources for package", e2);
        }
        return resolveInfo.loadIcon(this.d);
    }

    public final void a(boolean z, int i2, boolean z2) {
        this.h.setEnabled(z);
    }

    public void a(int i2, boolean z, boolean z2) {
        if (isFinishing()) {
            return;
        }
        a(this.c.b(i2, z2), this.c.a(i2, z2), z);
        finish();
    }

    public void a(ResolveInfo resolveInfo, Intent intent, boolean z) {
        String strResolveType;
        if ((this.f3944e || this.c.d()) && this.c.f3954e != null) {
            IntentFilter intentFilter = new IntentFilter();
            if (intent.getAction() != null) {
                intentFilter.addAction(intent.getAction());
            }
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                Iterator<String> it = categories.iterator();
                while (it.hasNext()) {
                    intentFilter.addCategory(it.next());
                }
            }
            intentFilter.addCategory("android.intent.category.DEFAULT");
            int i2 = resolveInfo.match & 268369920;
            Uri data = intent.getData();
            if (i2 == 6291456 && (strResolveType = intent.resolveType(this)) != null) {
                try {
                    intentFilter.addDataType(strResolveType);
                } catch (IntentFilter.MalformedMimeTypeException e2) {
                    t91.b("ResolverActivity", "ResolverActivity", e2);
                    intentFilter = null;
                }
            }
            if (data != null && data.getScheme() != null && (i2 != 6291456 || (!"file".equals(data.getScheme()) && !"content".equals(data.getScheme())))) {
                intentFilter.addDataScheme(data.getScheme());
                Iterator<IntentFilter.AuthorityEntry> itAuthoritiesIterator = resolveInfo.filter.authoritiesIterator();
                if (itAuthoritiesIterator != null) {
                    while (true) {
                        if (!itAuthoritiesIterator.hasNext()) {
                            break;
                        }
                        IntentFilter.AuthorityEntry next = itAuthoritiesIterator.next();
                        if (next.match(data) >= 0) {
                            int port = next.getPort();
                            intentFilter.addDataAuthority(next.getHost(), port >= 0 ? Integer.toString(port) : null);
                        }
                    }
                }
                Iterator<PatternMatcher> itPathsIterator = resolveInfo.filter.pathsIterator();
                if (itPathsIterator != null) {
                    String path = data.getPath();
                    while (true) {
                        if (path == null || !itPathsIterator.hasNext()) {
                            break;
                        }
                        PatternMatcher next2 = itPathsIterator.next();
                        if (next2.match(path)) {
                            intentFilter.addDataPath(next2.getPath(), next2.getType());
                            break;
                        }
                    }
                }
            }
            if (intentFilter != null) {
                int size = this.c.f3954e.size();
                ComponentName[] componentNameArr = new ComponentName[size];
                int i3 = 0;
                for (int i4 = 0; i4 < size; i4++) {
                    ResolveInfo resolveInfo2 = this.c.f3954e.get(i4);
                    ActivityInfo activityInfo = resolveInfo2.activityInfo;
                    componentNameArr[i4] = new ComponentName(activityInfo.packageName, activityInfo.name);
                    int i5 = resolveInfo2.match;
                    if (i5 > i3) {
                        i3 = i5;
                    }
                }
                if (z) {
                    this.b.a(this.l, resolveInfo);
                } else {
                    this.b.b(this.l, resolveInfo);
                }
            }
        }
        if (intent != null) {
            c(intent);
        }
    }

    public final Uri a(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            return data;
        }
        ClipData clipData = intent.getClipData();
        if (clipData == null || clipData.getItemCount() <= 0) {
            return null;
        }
        return clipData.getItemAt(0).getUri();
    }

    public Intent a(d dVar) {
        Intent intentA = dVar.f3949e;
        if (intentA == null) {
            intentA = a(dVar.f3948a.activityInfo, this.l);
        }
        Intent intent = new Intent(intentA);
        intent.addFlags(50331648);
        ActivityInfo activityInfo = dVar.f3948a.activityInfo;
        intent.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
        return intent;
    }
}
