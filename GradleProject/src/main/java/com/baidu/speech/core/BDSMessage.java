package com.baidu.speech.core;

import com.baidu.speech.core.BDSParamBase;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BDSMessage {
    public long m_dataOffset;
    public byte[] m_messageData;
    public String m_messageName;
    public HashMap<String, BDSParamBase> m_messageParams;

    public String toString() {
        StringBuilder sb;
        String str = this.m_messageName;
        Set<Map.Entry<String, BDSParamBase>> setEntrySet = this.m_messageParams.entrySet();
        String string = str + " messageParamsCount=" + this.m_messageParams.size() + " messageParams:{  ";
        for (Map.Entry<String, BDSParamBase> entry : setEntrySet) {
            String key = entry.getKey();
            if (key.endsWith("int")) {
                sb = new StringBuilder();
                sb.append(string);
                sb.append(" (");
                sb.append(entry.getKey());
                sb.append(" , ");
                sb.append(((BDSParamBase.BDSIntParam) entry.getValue()).iValue);
            } else if (key.endsWith("string")) {
                sb = new StringBuilder();
                sb.append(string);
                sb.append(" (");
                sb.append(entry.getKey());
                sb.append(" , ");
                sb.append(((BDSParamBase.BDSObjectParam) entry.getValue()).iValue);
            } else if (key.endsWith("float")) {
                sb = new StringBuilder();
                sb.append(string);
                sb.append(" (");
                sb.append(entry.getKey());
                sb.append(" , ");
                sb.append(((BDSParamBase.BDSFloatParam) entry.getValue()).iValue);
            } else if (key.endsWith("bool")) {
                sb = new StringBuilder();
                sb.append(string);
                sb.append(" (");
                sb.append(entry.getKey());
                sb.append(" , ");
                sb.append(((BDSParamBase.BDSBooleanParam) entry.getValue()).iValue);
            }
            sb.append(") ");
            string = sb.toString();
        }
        return string + "  } ";
    }
}
