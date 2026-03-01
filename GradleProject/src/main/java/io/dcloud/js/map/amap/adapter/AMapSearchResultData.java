package io.dcloud.js.map.amap.adapter;

/* JADX INFO: loaded from: classes3.dex */
public class AMapSearchResultData {
    public String endCity;
    public boolean isError = false;
    public Object pEnd;
    public Object pStart;
    public String startCity;
    public int type;

    public AMapSearchResultData(int i, Object obj, String str, Object obj2, String str2) {
        this.type = i;
        this.pStart = obj;
        this.startCity = str;
        this.pEnd = obj2;
        this.endCity = str2;
    }

    public void setError(boolean z) {
        this.isError = z;
    }
}
