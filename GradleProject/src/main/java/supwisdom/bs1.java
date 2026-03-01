package supwisdom;

import android.content.Context;
import android.net.Uri;
import io.dcloud.common.constant.AbsoluteConst;
import net.grandcentrix.tray.core.TrayStorage;

/* JADX INFO: compiled from: TrayUri.java */
/* JADX INFO: loaded from: classes3.dex */
public class bs1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f7093a;
    public final Uri b;
    public Context c;

    public bs1(Context context) {
        this.c = context;
        this.f7093a = yr1.b(context);
        this.b = yr1.c(context);
    }

    public a a() {
        return new a(this.c);
    }

    /* JADX INFO: compiled from: TrayUri.java */
    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7094a;
        public String b;
        public String c;
        public TrayStorage.Type d = TrayStorage.Type.UNDEFINED;

        public a(Context context) {
            bs1.this.c = context.getApplicationContext();
        }

        public Uri a() {
            Uri.Builder builderBuildUpon = (this.f7094a ? bs1.this.b : bs1.this.f7093a).buildUpon();
            String str = this.c;
            if (str != null) {
                builderBuildUpon.appendPath(str);
            }
            String str2 = this.b;
            if (str2 != null) {
                builderBuildUpon.appendPath(str2);
            }
            TrayStorage.Type type = this.d;
            if (type != TrayStorage.Type.UNDEFINED) {
                builderBuildUpon.appendQueryParameter("backup", TrayStorage.Type.USER.equals(type) ? "true" : AbsoluteConst.FALSE);
            }
            return builderBuildUpon.build();
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a a(boolean z) {
            this.f7094a = z;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a a(TrayStorage.Type type) {
            this.d = type;
            return this;
        }
    }
}
