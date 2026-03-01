package supwisdom;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class n1 extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class<?>[] f8461e;
    public static final Class<?>[] f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f8462a;
    public final Object[] b;
    public Context c;
    public Object d;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public static class a implements MenuItem.OnMenuItemClickListener {
        public static final Class<?>[] c = {MenuItem.class};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f8463a;
        public Method b;

        public a(Object obj, String str) {
            this.f8463a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.b = cls.getMethod(str, c);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.b.invoke(this.f8463a, menuItem)).booleanValue();
                }
                this.b.invoke(this.f8463a, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    static {
        Class<?>[] clsArr = {Context.class};
        f8461e = clsArr;
        f = clsArr;
    }

    public n1(Context context) {
        super(context);
        this.c = context;
        Object[] objArr = {context};
        this.f8462a = objArr;
        this.b = objArr;
    }

    public final void a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals(AbsoluteConst.EVENTS_MENU)) {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != 2) {
                if (eventType == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z2 && name2.equals(str)) {
                        str = null;
                        z2 = false;
                    } else if (name2.equals("group")) {
                        bVar.d();
                    } else if (name2.equals(AbsoluteConst.XML_ITEM)) {
                        if (!bVar.c()) {
                            pa paVar = bVar.A;
                            if (paVar == null || !paVar.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals(AbsoluteConst.EVENTS_MENU)) {
                        z = true;
                    }
                }
            } else if (!z2) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.a(attributeSet);
                } else if (name3.equals(AbsoluteConst.XML_ITEM)) {
                    bVar.b(attributeSet);
                } else if (name3.equals(AbsoluteConst.EVENTS_MENU)) {
                    a(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z2 = true;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    @Override // android.view.MenuInflater
    public void inflate(int i, Menu menu) {
        if (!(menu instanceof f9)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.c.getResources().getLayout(i);
                    a(layout, Xml.asAttributeSet(layout), menu);
                } catch (IOException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (XmlPullParserException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } finally {
            if (layout != null) {
                layout.close();
            }
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    public class b {
        public pa A;
        public CharSequence B;
        public CharSequence C;
        public ColorStateList D = null;
        public PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Menu f8464a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8465e;
        public boolean f;
        public boolean g;
        public boolean h;
        public int i;
        public int j;
        public CharSequence k;
        public CharSequence l;
        public int m;
        public char n;
        public int o;
        public char p;
        public int q;
        public int r;
        public boolean s;
        public boolean t;
        public boolean u;
        public int v;
        public int w;
        public String x;
        public String y;
        public String z;

        public b(Menu menu) {
            this.f8464a = menu;
            d();
        }

        public void a(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = n1.this.c.obtainStyledAttributes(attributeSet, R.styleable.MenuGroup);
            this.b = typedArrayObtainStyledAttributes.getResourceId(R.styleable.MenuGroup_android_id, 0);
            this.c = typedArrayObtainStyledAttributes.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
            this.d = typedArrayObtainStyledAttributes.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
            this.f8465e = typedArrayObtainStyledAttributes.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.f = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_visible, true);
            this.g = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void b(AttributeSet attributeSet) {
            p3 p3VarA = p3.a(n1.this.c, attributeSet, R.styleable.MenuItem);
            this.i = p3VarA.g(R.styleable.MenuItem_android_id, 0);
            this.j = (p3VarA.d(R.styleable.MenuItem_android_menuCategory, this.c) & (-65536)) | (p3VarA.d(R.styleable.MenuItem_android_orderInCategory, this.d) & 65535);
            this.k = p3VarA.e(R.styleable.MenuItem_android_title);
            this.l = p3VarA.e(R.styleable.MenuItem_android_titleCondensed);
            this.m = p3VarA.g(R.styleable.MenuItem_android_icon, 0);
            this.n = a(p3VarA.d(R.styleable.MenuItem_android_alphabeticShortcut));
            this.o = p3VarA.d(R.styleable.MenuItem_alphabeticModifiers, 4096);
            this.p = a(p3VarA.d(R.styleable.MenuItem_android_numericShortcut));
            this.q = p3VarA.d(R.styleable.MenuItem_numericModifiers, 4096);
            if (p3VarA.g(R.styleable.MenuItem_android_checkable)) {
                this.r = p3VarA.a(R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.r = this.f8465e;
            }
            this.s = p3VarA.a(R.styleable.MenuItem_android_checked, false);
            this.t = p3VarA.a(R.styleable.MenuItem_android_visible, this.f);
            this.u = p3VarA.a(R.styleable.MenuItem_android_enabled, this.g);
            this.v = p3VarA.d(R.styleable.MenuItem_showAsAction, -1);
            this.z = p3VarA.d(R.styleable.MenuItem_android_onClick);
            this.w = p3VarA.g(R.styleable.MenuItem_actionLayout, 0);
            this.x = p3VarA.d(R.styleable.MenuItem_actionViewClass);
            String strD = p3VarA.d(R.styleable.MenuItem_actionProviderClass);
            this.y = strD;
            boolean z = strD != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (pa) a(this.y, n1.f, n1.this.b);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = p3VarA.e(R.styleable.MenuItem_contentDescription);
            this.C = p3VarA.e(R.styleable.MenuItem_tooltipText);
            if (p3VarA.g(R.styleable.MenuItem_iconTintMode)) {
                this.E = y2.a(p3VarA.d(R.styleable.MenuItem_iconTintMode, -1), this.E);
            } else {
                this.E = null;
            }
            if (p3VarA.g(R.styleable.MenuItem_iconTint)) {
                this.D = p3VarA.a(R.styleable.MenuItem_iconTint);
            } else {
                this.D = null;
            }
            p3VarA.b();
            this.h = false;
        }

        public boolean c() {
            return this.h;
        }

        public void d() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.f8465e = 0;
            this.f = true;
            this.g = true;
        }

        public final char a(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        public final void a(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i = this.v;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.z != null) {
                if (!n1.this.c.isRestricted()) {
                    menuItem.setOnMenuItemClickListener(new a(n1.this.a(), this.z));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            if (this.r >= 2) {
                if (menuItem instanceof y1) {
                    ((y1) menuItem).c(true);
                } else if (menuItem instanceof z1) {
                    ((z1) menuItem).a(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) a(str, n1.f8461e, n1.this.f8462a));
                z = true;
            }
            int i2 = this.w;
            if (i2 > 0) {
                if (!z) {
                    menuItem.setActionView(i2);
                } else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            pa paVar = this.A;
            if (paVar != null) {
                wa.a(menuItem, paVar);
            }
            wa.a(menuItem, this.B);
            wa.b(menuItem, this.C);
            wa.a(menuItem, this.n, this.o);
            wa.b(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                wa.a(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                wa.a(menuItem, colorStateList);
            }
        }

        public SubMenu b() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f8464a.addSubMenu(this.b, this.i, this.j, this.k);
            a(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public void a() {
            this.h = true;
            a(this.f8464a.add(this.b, this.i, this.j, this.k));
        }

        public final <T> T a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, n1.this.c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }
    }

    public Object a() {
        if (this.d == null) {
            this.d = a(this.c);
        }
        return this.d;
    }

    public final Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }
}
