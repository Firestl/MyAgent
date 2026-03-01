package supwisdom;

import android.content.Context;
import com.huawei.hms.push.constant.RemoteMessageConst;
import io.dcloud.common.DHInterface.IApp;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConfigXmlParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class zu0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<av0> f10034a = new ArrayList<>();
    public boolean b = false;
    public String c = "";
    public String d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f10035e = "";
    public boolean f = false;

    public ArrayList<av0> a() {
        return this.f10034a;
    }

    public void b(XmlPullParser xmlPullParser) {
        String name = xmlPullParser.getName();
        if (name.equals(IApp.ConfigProperty.CONFIG_FEATURE)) {
            this.b = true;
            this.c = xmlPullParser.getAttributeValue(null, "name");
            return;
        }
        if (this.b && name.equals(RemoteMessageConst.MessageBody.PARAM)) {
            String attributeValue = xmlPullParser.getAttributeValue(null, "name");
            this.f10035e = attributeValue;
            if (attributeValue.equals("service")) {
                this.c = xmlPullParser.getAttributeValue(null, "value");
                return;
            }
            if (this.f10035e.equals("package") || this.f10035e.equals("android-package")) {
                this.d = xmlPullParser.getAttributeValue(null, "value");
            } else if (this.f10035e.equals("onload")) {
                this.f = "true".equals(xmlPullParser.getAttributeValue(null, "value"));
            }
        }
    }

    public void c(XmlPullParser xmlPullParser) {
        int next = -1;
        while (next != 1) {
            if (next == 2) {
                b(xmlPullParser);
            } else if (next == 3) {
                a(xmlPullParser);
            }
            try {
                next = xmlPullParser.next();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (XmlPullParserException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void a(Context context) {
        int identifier = context.getResources().getIdentifier("wjbconfig", "xml", context.getClass().getPackage().getName());
        if (identifier == 0 && (identifier = context.getResources().getIdentifier("wjbconfig", "xml", context.getPackageName())) == 0) {
            return;
        }
        c(context.getResources().getXml(identifier));
    }

    public void a(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getName().equals(IApp.ConfigProperty.CONFIG_FEATURE)) {
            this.f10034a.add(new av0(this.c, this.d, this.f));
            this.c = "";
            this.d = "";
            this.b = false;
            this.f = false;
        }
    }
}
