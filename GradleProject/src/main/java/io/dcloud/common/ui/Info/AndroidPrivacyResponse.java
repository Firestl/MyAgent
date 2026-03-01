package io.dcloud.common.ui.Info;

import io.dcloud.common.DHInterface.IReflectAble;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidPrivacyResponse implements IReflectAble {
    public String buttonAccept;
    public String buttonRefuse;
    public String message;
    public String prompt;
    public SecondDTO second = new SecondDTO();
    public StylesDTO styles = new StylesDTO();
    public String title;
    public String version;

    public static class SecondDTO implements IReflectAble {
        public String buttonAccept;
        public String buttonRefuse;
        public String message;
        public String title;
    }

    public static class StylesDTO implements IReflectAble {
        public String backgroundColor;
        public String borderRadius;
        public ButtonAcceptDTO buttonAccept;
        public ButtonRefuseDTO buttonRefuse;
        public TitleDTO title;

        public static class ButtonAcceptDTO implements IReflectAble {
            public String color;
        }

        public static class ButtonRefuseDTO implements IReflectAble {
            public String color;
        }

        public static class TitleDTO implements IReflectAble {
            public String color;
        }
    }
}
