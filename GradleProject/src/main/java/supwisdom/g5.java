package supwisdom;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: KeyFrames.java */
/* JADX INFO: loaded from: classes.dex */
public class g5 {
    public static HashMap<String, Constructor<? extends b5>> b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<Integer, ArrayList<b5>> f7685a = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends b5>> map = new HashMap<>();
        b = map;
        try {
            map.put("KeyAttribute", c5.class.getConstructor(new Class[0]));
            b.put("KeyPosition", h5.class.getConstructor(new Class[0]));
            b.put("KeyCycle", e5.class.getConstructor(new Class[0]));
            b.put("KeyTimeCycle", j5.class.getConstructor(new Class[0]));
            b.put("KeyTrigger", k5.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e2) {
            Log.e("KeyFrames", "unable to load", e2);
        }
    }

    public g5(Context context, XmlPullParser xmlPullParser) {
        b5 b5VarNewInstance;
        Exception e2;
        b5 b5Var = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (b.containsKey(name)) {
                        try {
                            b5VarNewInstance = b.get(name).newInstance(new Object[0]);
                            try {
                                b5VarNewInstance.a(context, Xml.asAttributeSet(xmlPullParser));
                                a(b5VarNewInstance);
                            } catch (Exception e3) {
                                e2 = e3;
                                Log.e("KeyFrames", "unable to create ", e2);
                            }
                        } catch (Exception e4) {
                            b5VarNewInstance = b5Var;
                            e2 = e4;
                        }
                        b5Var = b5VarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && b5Var != null && b5Var.d != null) {
                        ConstraintAttribute.a(context, xmlPullParser, b5Var.d);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        } catch (XmlPullParserException e6) {
            e6.printStackTrace();
        }
    }

    public final void a(b5 b5Var) {
        if (!this.f7685a.containsKey(Integer.valueOf(b5Var.b))) {
            this.f7685a.put(Integer.valueOf(b5Var.b), new ArrayList<>());
        }
        this.f7685a.get(Integer.valueOf(b5Var.b)).add(b5Var);
    }

    public void a(m5 m5Var) {
        ArrayList<b5> arrayList = this.f7685a.get(Integer.valueOf(m5Var.b));
        if (arrayList != null) {
            m5Var.a(arrayList);
        }
        ArrayList<b5> arrayList2 = this.f7685a.get(-1);
        if (arrayList2 != null) {
            for (b5 b5Var : arrayList2) {
                if (b5Var.a(((ConstraintLayout.LayoutParams) m5Var.f8359a.getLayoutParams()).U)) {
                    m5Var.a(b5Var);
                }
            }
        }
    }
}
