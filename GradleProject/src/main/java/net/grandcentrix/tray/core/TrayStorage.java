package net.grandcentrix.tray.core;

import supwisdom.sr1;
import supwisdom.ur1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TrayStorage implements sr1<ur1> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6785a;
    public Type b;

    public enum Type {
        UNDEFINED,
        USER,
        DEVICE
    }

    public TrayStorage(String str, Type type) {
        this.f6785a = str;
        this.b = type;
    }

    public String a() {
        return this.f6785a;
    }

    public Type b() {
        return this.b;
    }
}
