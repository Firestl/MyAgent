package supwisdom;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: PropertyBoxParserImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class ls extends gs {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Properties f8311a;

    public ls(String... strArr) {
        Pattern.compile("(.*)\\((.*?)\\)");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(ls.class.getResourceAsStream("/isoparser-default.properties"));
        try {
            Properties properties = new Properties();
            this.f8311a = properties;
            try {
                properties.load(bufferedInputStream);
                Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("isoparser-custom.properties");
                while (resources.hasMoreElements()) {
                    bufferedInputStream = new BufferedInputStream(resources.nextElement().openStream());
                    try {
                        this.f8311a.load(bufferedInputStream);
                        bufferedInputStream.close();
                    } finally {
                        bufferedInputStream.close();
                    }
                }
                for (String str : strArr) {
                    this.f8311a.load(new BufferedInputStream(getClass().getResourceAsStream(str)));
                }
                try {
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            }
        } catch (Throwable th) {
            try {
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }
}
