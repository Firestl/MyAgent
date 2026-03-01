package io.dcloud.common.DHInterface;

import android.view.ViewGroup;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IUniNView {
    void beginPullRefresh();

    void endPullToRefresh();

    String evalJs(String str, int i);

    boolean fireGlobalEvent(String str, Map<String, Object> map);

    String getType();

    void initRefresh(JSONObject jSONObject);

    void loadTemplate(JSONObject jSONObject);

    ViewGroup obtainMainView();

    void onDestroy();

    void reload();

    void titleNViewRefresh();
}
