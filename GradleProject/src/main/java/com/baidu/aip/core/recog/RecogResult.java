package com.baidu.aip.core.recog;

import com.tencent.open.SocialConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RecogResult {
    public static final int ERROR_NONE = 0;
    public String desc;
    public String origalJson;
    public String origalResult;
    public String resultType;
    public String[] resultsRecognition;
    public String sn;
    public int error = -1;
    public int subError = -1;

    public static RecogResult parseJson(String str) {
        RecogResult recogResult = new RecogResult();
        recogResult.setOrigalJson(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("error");
            int iOptInt2 = jSONObject.optInt("sub_error");
            recogResult.setError(iOptInt);
            recogResult.setDesc(jSONObject.optString(SocialConstants.PARAM_APP_DESC));
            recogResult.setResultType(jSONObject.optString("result_type"));
            recogResult.setSubError(iOptInt2);
            if (iOptInt == 0) {
                recogResult.setOrigalResult(jSONObject.getString("origin_result"));
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("results_recognition");
                if (jSONArrayOptJSONArray != null) {
                    int length = jSONArrayOptJSONArray.length();
                    String[] strArr = new String[length];
                    for (int i = 0; i < length; i++) {
                        strArr[i] = jSONArrayOptJSONArray.getString(i);
                    }
                    recogResult.setResultsRecognition(strArr);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return recogResult;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getError() {
        return this.error;
    }

    public String getOrigalJson() {
        return this.origalJson;
    }

    public String getOrigalResult() {
        return this.origalResult;
    }

    public String getResultType() {
        return this.resultType;
    }

    public String[] getResultsRecognition() {
        return this.resultsRecognition;
    }

    public String getSn() {
        return this.sn;
    }

    public int getSubError() {
        return this.subError;
    }

    public boolean hasError() {
        return this.error != 0;
    }

    public boolean isFinalResult() {
        return "final_result".equals(this.resultType);
    }

    public boolean isNluResult() {
        return "nlu_result".equals(this.resultType);
    }

    public boolean isPartialResult() {
        return "partial_result".equals(this.resultType);
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setError(int i) {
        this.error = i;
    }

    public void setOrigalJson(String str) {
        this.origalJson = str;
    }

    public void setOrigalResult(String str) {
        this.origalResult = str;
    }

    public void setResultType(String str) {
        this.resultType = str;
    }

    public void setResultsRecognition(String[] strArr) {
        this.resultsRecognition = strArr;
    }

    public void setSn(String str) {
        this.sn = str;
    }

    public void setSubError(int i) {
        this.subError = i;
    }
}
