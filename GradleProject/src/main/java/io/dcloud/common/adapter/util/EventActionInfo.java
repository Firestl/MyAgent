package io.dcloud.common.adapter.util;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class EventActionInfo {
    public String evalJs;
    public String eventAction;
    public Map<String, Object> params;

    public EventActionInfo(String str) {
        this.eventAction = str;
    }

    public String getEvalJs() {
        return this.evalJs;
    }

    public String getEventAction() {
        return this.eventAction;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public EventActionInfo(String str, Map<String, Object> map) {
        this.eventAction = str;
        this.params = map;
    }

    public EventActionInfo(String str, String str2) {
        this.eventAction = str;
        this.evalJs = str2;
    }

    public EventActionInfo(String str, String str2, Map<String, Object> map) {
        this.eventAction = str;
        this.evalJs = str2;
        this.params = map;
    }
}
