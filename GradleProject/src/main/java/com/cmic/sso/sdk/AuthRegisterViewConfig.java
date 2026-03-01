package com.cmic.sso.sdk;

import android.view.View;
import com.cmic.sso.sdk.utils.rglistener.CustomInterface;

/* JADX INFO: loaded from: classes.dex */
public class AuthRegisterViewConfig {
    public CustomInterface customInterface;
    public int rootViewId;
    public View view;

    public static class Builder {
        public CustomInterface customInterface;
        public int rootViewId;
        public View view;

        public AuthRegisterViewConfig build() {
            return new AuthRegisterViewConfig(this);
        }

        public Builder setCustomInterface(CustomInterface customInterface) {
            this.customInterface = customInterface;
            return this;
        }

        public Builder setRootViewId(int i) {
            this.rootViewId = i;
            return this;
        }

        public Builder setView(View view) {
            this.view = view;
            return this;
        }
    }

    public static class RootViewId {
        public static final int ROOT_VIEW_ID_BODY = 0;
        public static final int ROOT_VIEW_ID_TITLE_BAR = 1;
    }

    public AuthRegisterViewConfig(Builder builder) {
        this.customInterface = builder.customInterface;
        this.view = builder.view;
        this.rootViewId = builder.rootViewId;
    }

    public CustomInterface getCustomInterface() {
        return this.customInterface;
    }

    public int getRootViewId() {
        return this.rootViewId;
    }

    public View getView() {
        return this.view;
    }
}
