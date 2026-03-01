package com.supwisdom.superapp.service.model;

/* JADX INFO: loaded from: classes2.dex */
public class AppVersionInfo {
    public static int STATUS_TYPE_FORCE_UPDATE = 1;
    public static int STATUS_TYPE_NO_UPDATE = 3;
    public static int STATUS_TYPE_UPDATE = 2;
    public UpdateInfo appUpdateDescriptionDTO;
    public int updateStatus;

    public static class UpdateInfo {
        public String currentVersion;
        public String description;
        public String downloadUrl;
    }
}
