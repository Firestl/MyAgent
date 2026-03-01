package com.supwisdom.superapp.entity.response;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class PersonalInformationResponse implements Serializable {
    public DetailInformation alipay;
    public DetailInformation dingtalk;
    public String nonce;
    public DetailInformation openweixin;
    public DetailInformation portrait;
    public DetailInformation qq;
    public PerfectionDetail realname;
    public DetailInformation securityEmailAddress;
    public DetailInformation securityMobile;
    public DetailInformation securityQuestion;
    public ArrayList<String> sort;
    public DetailInformation workweixin;

    public static class DetailInformation implements Serializable {
        public boolean completed;
        public boolean forced;
        public String info;
        public ArrayList<String> questionList;
        public boolean required;

        public String getInfo() {
            return this.info;
        }

        public ArrayList<String> getQuestionList() {
            return this.questionList;
        }

        public boolean isCompleted() {
            return this.completed;
        }

        public boolean isForced() {
            return this.forced;
        }

        public boolean isRequired() {
            return this.required;
        }

        public void setCompleted(boolean z) {
            this.completed = z;
        }

        public void setForced(boolean z) {
            this.forced = z;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public void setQuestionList(ArrayList<String> arrayList) {
            this.questionList = arrayList;
        }

        public void setRequired(boolean z) {
            this.required = z;
        }
    }

    public static class PerfectionDetail implements Serializable {
        public boolean completed;
        public boolean forced;
        public boolean required;
        public a ways;

        public static class a {
            public boolean a() {
                throw null;
            }
        }

        public a getWays() {
            return this.ways;
        }

        public boolean isCompleted() {
            return this.completed;
        }

        public boolean isForced() {
            return this.forced;
        }

        public boolean isRequired() {
            return this.required;
        }

        public void setCompleted(boolean z) {
            this.completed = z;
        }

        public void setForced(boolean z) {
            this.forced = z;
        }

        public void setRequired(boolean z) {
            this.required = z;
        }

        public void setWays(a aVar) {
            this.ways = aVar;
        }
    }

    public PersonalInformationResponse(DetailInformation detailInformation, DetailInformation detailInformation2, DetailInformation detailInformation3, DetailInformation detailInformation4, DetailInformation detailInformation5, DetailInformation detailInformation6, DetailInformation detailInformation7, DetailInformation detailInformation8, DetailInformation detailInformation9, String str, PerfectionDetail perfectionDetail, ArrayList<String> arrayList) {
        this.dingtalk = detailInformation;
        this.qq = detailInformation2;
        this.openweixin = detailInformation3;
        this.workweixin = detailInformation4;
        this.alipay = detailInformation5;
        this.securityEmailAddress = detailInformation6;
        this.securityMobile = detailInformation7;
        this.securityQuestion = detailInformation8;
        this.portrait = detailInformation9;
        this.nonce = str;
        this.realname = perfectionDetail;
        this.sort = arrayList;
    }

    public DetailInformation getAlipay() {
        return this.alipay;
    }

    public DetailInformation getDingtalk() {
        return this.dingtalk;
    }

    public String getNonce() {
        return this.nonce;
    }

    public DetailInformation getOpenweixin() {
        return this.openweixin;
    }

    public DetailInformation getPortrait() {
        return this.portrait;
    }

    public DetailInformation getQq() {
        return this.qq;
    }

    public PerfectionDetail getRealname() {
        return this.realname;
    }

    public DetailInformation getSecurityEmailAddress() {
        return this.securityEmailAddress;
    }

    public DetailInformation getSecurityMobile() {
        return this.securityMobile;
    }

    public DetailInformation getSecurityQuestion() {
        return this.securityQuestion;
    }

    public ArrayList<String> getSort() {
        return this.sort;
    }

    public DetailInformation getWorkweixin() {
        return this.workweixin;
    }

    public void setAlipay(DetailInformation detailInformation) {
        this.alipay = detailInformation;
    }

    public void setDingtalk(DetailInformation detailInformation) {
        this.dingtalk = detailInformation;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setOpenweixin(DetailInformation detailInformation) {
        this.openweixin = detailInformation;
    }

    public void setPortrait(DetailInformation detailInformation) {
        this.portrait = detailInformation;
    }

    public void setQq(DetailInformation detailInformation) {
        this.qq = detailInformation;
    }

    public void setRealname(PerfectionDetail perfectionDetail) {
        this.realname = perfectionDetail;
    }

    public void setSecurityEmailAddress(DetailInformation detailInformation) {
        this.securityEmailAddress = detailInformation;
    }

    public void setSecurityMobile(DetailInformation detailInformation) {
        this.securityMobile = detailInformation;
    }

    public void setSecurityQuestion(DetailInformation detailInformation) {
        this.securityQuestion = detailInformation;
    }

    public void setSort(ArrayList<String> arrayList) {
        this.sort = arrayList;
    }

    public void setWorkweixin(DetailInformation detailInformation) {
        this.workweixin = detailInformation;
    }
}
