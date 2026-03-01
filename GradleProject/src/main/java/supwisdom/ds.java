package supwisdom;

import com.google.gson.JsonElement;

/* JADX INFO: compiled from: ClaimImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class ds extends bs {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final JsonElement f7374a;

    public ds(JsonElement jsonElement) {
        this.f7374a = jsonElement;
    }

    @Override // supwisdom.cs
    public String a() {
        if (this.f7374a.isJsonPrimitive()) {
            return this.f7374a.getAsString();
        }
        return null;
    }
}
