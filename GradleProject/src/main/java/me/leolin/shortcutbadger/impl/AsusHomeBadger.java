package me.leolin.shortcutbadger.impl;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public class AsusHomeBadger {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f6781a = Uri.parse("content://com.android.badge/");

    /* JADX INFO: renamed from: me.leolin.shortcutbadger.impl.AsusHomeBadger$1, reason: invalid class name */
    public class AnonymousClass1 extends AsyncQueryHandler {
        public AnonymousClass1(AsusHomeBadger asusHomeBadger, ContentResolver contentResolver) {
            super(contentResolver);
        }
    }
}
