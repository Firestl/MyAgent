package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R;
import com.taobao.weex.common.Constants;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintLayoutStates.java */
/* JADX INFO: loaded from: classes.dex */
public class d7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConstraintLayout f7297a;
    public e7 b;
    public int c = -1;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SparseArray<a> f7298e = new SparseArray<>();
    public SparseArray<e7> f = new SparseArray<>();
    public f7 g = null;

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7299a;
        public ArrayList<b> b = new ArrayList<>();
        public int c;
        public e7 d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.State_android_id) {
                    this.f7299a = typedArrayObtainStyledAttributes.getResourceId(index, this.f7299a);
                } else if (index == R.styleable.State_constraints) {
                    this.c = typedArrayObtainStyledAttributes.getResourceId(index, this.c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.c);
                    context.getResources().getResourceName(this.c);
                    if (Constants.Name.LAYOUT.equals(resourceTypeName)) {
                        e7 e7Var = new e7();
                        this.d = e7Var;
                        e7Var.a(context, this.c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public void a(b bVar) {
            this.b.add(bVar);
        }

        public int a(float f, float f2) {
            for (int i = 0; i < this.b.size(); i++) {
                if (this.b.get(i).a(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: compiled from: ConstraintLayoutStates.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f7300a;
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7301e;
        public e7 f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f7300a = Float.NaN;
            this.b = Float.NaN;
            this.c = Float.NaN;
            this.d = Float.NaN;
            this.f7301e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.Variant_constraints) {
                    this.f7301e = typedArrayObtainStyledAttributes.getResourceId(index, this.f7301e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f7301e);
                    context.getResources().getResourceName(this.f7301e);
                    if (Constants.Name.LAYOUT.equals(resourceTypeName)) {
                        e7 e7Var = new e7();
                        this.f = e7Var;
                        e7Var.a(context, this.f7301e);
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.d = typedArrayObtainStyledAttributes.getDimension(index, this.d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.b = typedArrayObtainStyledAttributes.getDimension(index, this.b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.c = typedArrayObtainStyledAttributes.getDimension(index, this.c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.f7300a = typedArrayObtainStyledAttributes.getDimension(index, this.f7300a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f, float f2) {
            if (!Float.isNaN(this.f7300a) && f < this.f7300a) {
                return false;
            }
            if (!Float.isNaN(this.b) && f2 < this.b) {
                return false;
            }
            if (Float.isNaN(this.c) || f <= this.c) {
                return Float.isNaN(this.d) || f2 <= this.d;
            }
            return false;
        }
    }

    public d7(Context context, ConstraintLayout constraintLayout, int i) {
        this.f7297a = constraintLayout;
        a(context, i);
    }

    public void a(int i, float f, float f2) {
        int iA;
        int i2 = this.c;
        if (i2 == i) {
            a aVarValueAt = i == -1 ? this.f7298e.valueAt(0) : this.f7298e.get(i2);
            int i3 = this.d;
            if ((i3 == -1 || !aVarValueAt.b.get(i3).a(f, f2)) && this.d != (iA = aVarValueAt.a(f, f2))) {
                e7 e7Var = iA == -1 ? this.b : aVarValueAt.b.get(iA).f;
                int i4 = iA == -1 ? aVarValueAt.c : aVarValueAt.b.get(iA).f7301e;
                if (e7Var == null) {
                    return;
                }
                this.d = iA;
                f7 f7Var = this.g;
                if (f7Var != null) {
                    f7Var.b(-1, i4);
                }
                e7Var.b(this.f7297a);
                f7 f7Var2 = this.g;
                if (f7Var2 != null) {
                    f7Var2.a(-1, i4);
                    return;
                }
                return;
            }
            return;
        }
        this.c = i;
        a aVar = this.f7298e.get(i);
        int iA2 = aVar.a(f, f2);
        e7 e7Var2 = iA2 == -1 ? aVar.d : aVar.b.get(iA2).f;
        int i5 = iA2 == -1 ? aVar.c : aVar.b.get(iA2).f7301e;
        if (e7Var2 == null) {
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =" + f + ", " + f2);
            return;
        }
        this.d = iA2;
        f7 f7Var3 = this.g;
        if (f7Var3 != null) {
            f7Var3.b(i, i5);
        }
        e7Var2.b(this.f7297a);
        f7 f7Var4 = this.g;
        if (f7Var4 != null) {
            f7Var4.a(i, i5);
        }
    }

    public void a(f7 f7Var) {
        this.g = f7Var;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        a aVar = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    byte b2 = -1;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                b2 = 4;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                b2 = 2;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b2 = 1;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                b2 = 0;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                b2 = 3;
                            }
                            break;
                    }
                    if (b2 != 0 && b2 != 1) {
                        if (b2 == 2) {
                            aVar = new a(context, xml);
                            this.f7298e.put(aVar.f7299a, aVar);
                        } else if (b2 == 3) {
                            b bVar = new b(context, xml);
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        } else if (b2 != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            a(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public final void a(Context context, XmlPullParser xmlPullParser) {
        e7 e7Var = new e7();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if ("id".equals(xmlPullParser.getAttributeName(i))) {
                String attributeValue = xmlPullParser.getAttributeValue(i);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue != null && attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                e7Var.a(context, xmlPullParser);
                this.f.put(identifier, e7Var);
                return;
            }
        }
    }
}
