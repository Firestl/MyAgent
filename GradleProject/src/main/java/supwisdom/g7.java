package supwisdom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import androidx.constraintlayout.widget.R;
import com.taobao.weex.common.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: StateSet.java */
/* JADX INFO: loaded from: classes.dex */
public class g7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7694a = -1;
    public int b = -1;
    public int c = -1;
    public SparseArray<a> d = new SparseArray<>();

    /* JADX INFO: compiled from: StateSet.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7695a;
        public ArrayList<b> b = new ArrayList<>();
        public int c;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.State_android_id) {
                    this.f7695a = typedArrayObtainStyledAttributes.getResourceId(index, this.f7695a);
                } else if (index == R.styleable.State_constraints) {
                    this.c = typedArrayObtainStyledAttributes.getResourceId(index, this.c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.c);
                    context.getResources().getResourceName(this.c);
                    Constants.Name.LAYOUT.equals(resourceTypeName);
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

    /* JADX INFO: compiled from: StateSet.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f7696a;
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7697e;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f7696a = Float.NaN;
            this.b = Float.NaN;
            this.c = Float.NaN;
            this.d = Float.NaN;
            this.f7697e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.Variant_constraints) {
                    this.f7697e = typedArrayObtainStyledAttributes.getResourceId(index, this.f7697e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f7697e);
                    context.getResources().getResourceName(this.f7697e);
                    Constants.Name.LAYOUT.equals(resourceTypeName);
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.d = typedArrayObtainStyledAttributes.getDimension(index, this.d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.b = typedArrayObtainStyledAttributes.getDimension(index, this.b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.c = typedArrayObtainStyledAttributes.getDimension(index, this.c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.f7696a = typedArrayObtainStyledAttributes.getDimension(index, this.f7696a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean a(float f, float f2) {
            if (!Float.isNaN(this.f7696a) && f < this.f7696a) {
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

    public g7(Context context, XmlPullParser xmlPullParser) {
        new SparseArray();
        a(context, xmlPullParser);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.StateSet);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R.styleable.StateSet_defaultState) {
                this.f7694a = typedArrayObtainStyledAttributes.getResourceId(index, this.f7694a);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        a aVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 0) {
                    xmlPullParser.getName();
                } else if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    byte b2 = -1;
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                b2 = 2;
                            }
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                b2 = 0;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b2 = 1;
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
                            aVar = new a(context, xmlPullParser);
                            this.d.put(aVar.f7695a, aVar);
                        } else if (b2 != 3) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b bVar = new b(context, xmlPullParser);
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    public int b(int i, int i2, float f, float f2) {
        int iA;
        if (i == i2) {
            a aVarValueAt = i2 == -1 ? this.d.valueAt(0) : this.d.get(this.b);
            if (aVarValueAt == null) {
                return -1;
            }
            return ((this.c == -1 || !aVarValueAt.b.get(i).a(f, f2)) && i != (iA = aVarValueAt.a(f, f2))) ? iA == -1 ? aVarValueAt.c : aVarValueAt.b.get(iA).f7697e : i;
        }
        a aVar = this.d.get(i2);
        if (aVar == null) {
            return -1;
        }
        int iA2 = aVar.a(f, f2);
        return iA2 == -1 ? aVar.c : aVar.b.get(iA2).f7697e;
    }

    public int a(int i, int i2, int i3) {
        return b(-1, i, i2, i3);
    }

    public int a(int i, int i2, float f, float f2) {
        a aVar = this.d.get(i2);
        if (aVar == null) {
            return i2;
        }
        if (f != -1.0f && f2 != -1.0f) {
            b bVar = null;
            for (b bVar2 : aVar.b) {
                if (bVar2.a(f, f2)) {
                    if (i == bVar2.f7697e) {
                        return i;
                    }
                    bVar = bVar2;
                }
            }
            if (bVar != null) {
                return bVar.f7697e;
            }
            return aVar.c;
        }
        if (aVar.c == i) {
            return i;
        }
        Iterator<b> it = aVar.b.iterator();
        while (it.hasNext()) {
            if (i == it.next().f7697e) {
                return i;
            }
        }
        return aVar.c;
    }
}
