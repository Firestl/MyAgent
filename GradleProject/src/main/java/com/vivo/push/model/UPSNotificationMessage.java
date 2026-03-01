package com.vivo.push.model;

import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class UPSNotificationMessage {
    public static final int CUSTOM = 3;
    public static final int ISMACROREPLACE = 1;
    public static final int OPENACTIVITY = 4;
    public static final int OPENAPP = 1;
    public static final int OPENURL = 2;
    public static final int UNKNOWN = 0;
    public String mAdClickCheckUrl;
    public int mCompatibleType;
    public String mContent;
    public String mCoverUrl;
    public String mIconUrl;
    public int mIsMacroReplace;
    public long mMsgId;
    public int mNotifyType;
    public Map<String, String> mParams = new HashMap();
    public String mPurePicUrl;
    public boolean mShowTime;
    public String mSkipContent;
    public int mSkipType;
    public int mTargetType;
    public String mTitle;
    public String mTragetContent;

    public void clearCoverUrl() {
        this.mIconUrl = "";
    }

    public void clearPurePicUrl() {
        this.mPurePicUrl = "";
    }

    public String getAdClickCheckUrl() {
        return this.mAdClickCheckUrl;
    }

    public int getCompatibleType() {
        return this.mCompatibleType;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getCoverUrl() {
        return this.mCoverUrl;
    }

    public String getIconUrl() {
        return this.mIconUrl;
    }

    public int getIsMacroReplace() {
        return this.mIsMacroReplace;
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public int getNotifyType() {
        return this.mNotifyType;
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public String getPurePicUrl() {
        return this.mPurePicUrl;
    }

    public String getSkipContent() {
        return this.mSkipContent;
    }

    public int getSkipType() {
        return this.mSkipType;
    }

    public int getTargetType() {
        return this.mTargetType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTragetContent() {
        return this.mTragetContent;
    }

    public boolean isMacroReplace() {
        return this.mIsMacroReplace == 1;
    }

    public boolean isShowTime() {
        return this.mShowTime;
    }

    public void setAdClickCheckUrl(String str) {
        this.mAdClickCheckUrl = str;
    }

    public void setCompatibleType(int i) {
        this.mCompatibleType = i;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public void setCoverUrl(String str) {
        this.mCoverUrl = str;
    }

    public void setIconUrl(String str) {
        this.mIconUrl = str;
    }

    public void setIsMacroReplace(int i) {
        this.mIsMacroReplace = i;
    }

    public void setMsgId(long j) {
        this.mMsgId = j;
    }

    public void setNotifyType(int i) {
        this.mNotifyType = i;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    public void setPurePicUrl(String str) {
        this.mPurePicUrl = str;
    }

    public void setShowTime(boolean z) {
        this.mShowTime = z;
    }

    public void setSkipContent(String str) {
        this.mSkipContent = str;
    }

    public void setSkipType(int i) {
        this.mSkipType = i;
    }

    public void setTargetType(int i) {
        this.mTargetType = i;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setTragetContext(String str) {
        this.mTragetContent = str;
    }

    public String toString() {
        return "UPSNotificationMessage{mTargetType=" + this.mTargetType + ", mTragetContent='" + this.mTragetContent + Operators.SINGLE_QUOTE + ", mTitle='" + this.mTitle + Operators.SINGLE_QUOTE + ", mContent='" + this.mContent + Operators.SINGLE_QUOTE + ", mNotifyType=" + this.mNotifyType + ", mPurePicUrl='" + this.mPurePicUrl + Operators.SINGLE_QUOTE + ", mIconUrl='" + this.mIconUrl + Operators.SINGLE_QUOTE + ", mCoverUrl='" + this.mCoverUrl + Operators.SINGLE_QUOTE + ", mSkipContent='" + this.mSkipContent + Operators.SINGLE_QUOTE + ", mSkipType=" + this.mSkipType + ", mShowTime=" + this.mShowTime + ", mMsgId=" + this.mMsgId + ", mParams=" + this.mParams + Operators.BLOCK_END;
    }
}
