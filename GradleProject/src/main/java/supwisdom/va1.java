package supwisdom;

import android.content.Intent;
import java.util.Objects;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class va1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Intent f9498a;
    public final int b = a();

    public va1(Intent intent) {
        this.f9498a = intent;
    }

    public final int a() {
        int iHashCode = this.f9498a.getAction() != null ? 0 + this.f9498a.getAction().hashCode() : 0;
        if (this.f9498a.getType() != null) {
            iHashCode += this.f9498a.getType().hashCode();
        }
        if (this.f9498a.getPackage() != null) {
            iHashCode += this.f9498a.getPackage().hashCode();
        }
        if (this.f9498a.getComponent() != null) {
            iHashCode += this.f9498a.getComponent().hashCode();
        }
        return this.f9498a.getCategories() != null ? iHashCode + this.f9498a.getCategories().hashCode() : iHashCode;
    }

    public boolean equals(Object obj) {
        if (obj instanceof va1) {
            return a(((va1) obj).f9498a);
        }
        return false;
    }

    public int hashCode() {
        return this.b;
    }

    public boolean a(Intent intent) {
        if (intent != null && Objects.equals(this.f9498a.getAction(), intent.getAction()) && Objects.equals(this.f9498a.getType(), intent.getType()) && Objects.equals(this.f9498a.getPackage(), intent.getPackage()) && Objects.equals(this.f9498a.getComponent(), intent.getComponent())) {
            return Objects.equals(this.f9498a.getCategories(), intent.getCategories());
        }
        return false;
    }
}
