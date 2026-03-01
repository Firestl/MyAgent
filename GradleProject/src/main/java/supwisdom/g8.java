package supwisdom;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ComplexColorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class g8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shader f7702a;
    public final ColorStateList b;
    public int c;

    public g8(Shader shader, ColorStateList colorStateList, int i) {
        this.f7702a = shader;
        this.b = colorStateList;
        this.c = i;
    }

    public static g8 a(Shader shader) {
        return new g8(shader, null, 0);
    }

    public static g8 b(int i) {
        return new g8(null, null, i);
    }

    public boolean c() {
        return this.f7702a != null;
    }

    public boolean d() {
        ColorStateList colorStateList;
        return this.f7702a == null && (colorStateList = this.b) != null && colorStateList.isStateful();
    }

    public boolean e() {
        return c() || this.c != 0;
    }

    public static g8 a(ColorStateList colorStateList) {
        return new g8(null, colorStateList, colorStateList.getDefaultColor());
    }

    public Shader b() {
        return this.f7702a;
    }

    public static g8 b(Resources resources, int i, Resources.Theme theme) {
        try {
            return a(resources, i, theme);
        } catch (Exception e2) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e2);
            return null;
        }
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public boolean a(int[] iArr) {
        if (d()) {
            ColorStateList colorStateList = this.b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.c) {
                this.c = colorForState;
                return true;
            }
        }
        return false;
    }

    public static g8 a(Resources resources, int i, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            String name = xml.getName();
            byte b = -1;
            int iHashCode = name.hashCode();
            if (iHashCode != 89650992) {
                if (iHashCode == 1191572447 && name.equals("selector")) {
                    b = 0;
                }
            } else if (name.equals("gradient")) {
                b = 1;
            }
            if (b == 0) {
                return a(f8.a(resources, xml, attributeSetAsAttributeSet, theme));
            }
            if (b == 1) {
                return a(i8.a(resources, xml, attributeSetAsAttributeSet, theme));
            }
            throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
        }
        throw new XmlPullParserException("No start tag found");
    }
}
