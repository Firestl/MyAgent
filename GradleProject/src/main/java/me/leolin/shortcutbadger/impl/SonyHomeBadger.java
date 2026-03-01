package me.leolin.shortcutbadger.impl;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public class SonyHomeBadger {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f6782a = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");

    /* JADX INFO: renamed from: me.leolin.shortcutbadger.impl.SonyHomeBadger$1, reason: invalid class name */
    public class AnonymousClass1 extends AsyncQueryHandler {
        public AnonymousClass1(SonyHomeBadger sonyHomeBadger, ContentResolver contentResolver) {
            super(contentResolver);
        }
    }
}
