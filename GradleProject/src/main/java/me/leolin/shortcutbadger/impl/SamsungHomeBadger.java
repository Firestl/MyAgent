package me.leolin.shortcutbadger.impl;

import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public class SamsungHomeBadger {
    public SamsungHomeBadger() {
        if (Build.VERSION.SDK_INT >= 21) {
            new DefaultBadger();
        }
    }
}
