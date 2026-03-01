package com.efs.sdk.base.protocol;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsLog implements ILogProtocol {
    public String cp = "none";
    public byte de = 1;
    public String logType;

    public AbsLog(String str) {
        this.logType = str;
    }

    @Override // com.efs.sdk.base.protocol.ILogProtocol
    public String getLogType() {
        return this.logType;
    }

    public boolean isCp() {
        return !this.cp.equals("none");
    }

    public boolean isDe() {
        return this.de != 1;
    }

    public void setCp(String str) {
        this.cp = str;
    }

    public void setDe(byte b) {
        this.de = b;
    }
}
