package com.heytap.msp.push.mode;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class DataMessage extends BaseMode {
    public String mAppPackage;
    public String mContent;
    public String mDescription;
    public String mMessageID;
    public int mNotifyID;
    public String mTaskID = "";
    public String mTitle;

    public String getAppPackage() {
        return this.mAppPackage;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getMessageID() {
        return this.mMessageID;
    }

    public int getNotifyID() {
        return this.mNotifyID;
    }

    public String getTaskID() {
        return this.mTaskID;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @Override // com.heytap.msp.push.mode.BaseMode
    public int getType() {
        return 4103;
    }

    public void setAppPackage(String str) {
        this.mAppPackage = str;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setMessageID(String str) {
        this.mMessageID = str;
    }

    public void setNotifyID(int i) {
        this.mNotifyID = i;
    }

    public void setTaskID(int i) {
        this.mTaskID = String.valueOf(i);
    }

    public void setTaskID(String str) {
        this.mTaskID = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String toString() {
        return "DataMessage{mMessageID='" + this.mMessageID + Operators.SINGLE_QUOTE + "mAppPackage='" + this.mAppPackage + Operators.SINGLE_QUOTE + ", mTaskID='" + this.mTaskID + Operators.SINGLE_QUOTE + "mTitle='" + this.mTitle + Operators.SINGLE_QUOTE + "mNotifyID='" + this.mNotifyID + Operators.SINGLE_QUOTE + ", mContent='" + this.mContent + Operators.SINGLE_QUOTE + ", mDescription='" + this.mDescription + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
