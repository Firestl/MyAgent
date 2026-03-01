package com.supwisdom.superapp.entity.response;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class PersonalInformationBean implements Serializable {
    public Alipay alipay;
    public Boolean completed;
    public Dingtalk dingtalk;
    public Openweixin openweixin;
    public SecurityEmailAddress portrait;
    public Qq qq;
    public Realname realname;
    public SecurityEmailAddress securityEmailAddress;
    public SecurityEmailAddress securityMobile;
    public SecurityQuestion securityQuestion;
    public Workweixin workweixin;

    public static class Alipay implements Serializable {
        public Boolean completed;
        public String federatedUrl;
        public Boolean forced;
        public Object info;
        public Boolean required;

        public String getFederatedUrl() {
            return this.federatedUrl;
        }

        public Object getInfo() {
            return this.info;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setFederatedUrl(String str) {
            this.federatedUrl = str;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(Object obj) {
            this.info = obj;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public static class Dingtalk implements Serializable {
        public Boolean completed;
        public String federatedUrl;
        public Boolean forced;
        public Object info;
        public Boolean required;

        public String getFederatedUrl() {
            return this.federatedUrl;
        }

        public Object getInfo() {
            return this.info;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setFederatedUrl(String str) {
            this.federatedUrl = str;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(Object obj) {
            this.info = obj;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public static class Openweixin implements Serializable {
        public Boolean completed;
        public String federatedUrl;
        public Boolean forced;
        public Object info;
        public Boolean required;

        public String getFederatedUrl() {
            return this.federatedUrl;
        }

        public Object getInfo() {
            return this.info;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setFederatedUrl(String str) {
            this.federatedUrl = str;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(Object obj) {
            this.info = obj;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public static class Qq implements Serializable {
        public Boolean completed;
        public String federatedUrl;
        public Boolean forced;
        public Object info;
        public Boolean required;

        public String getFederatedUrl() {
            return this.federatedUrl;
        }

        public Object getInfo() {
            return this.info;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setFederatedUrl(String str) {
            this.federatedUrl = str;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(Object obj) {
            this.info = obj;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public static class Realname implements Serializable {
        public Boolean completed;
        public Boolean forced;
        public Boolean required;
        public Ways ways;

        public static class Ways implements Serializable {
            public Boolean certificateNumberEnabled;
            public Boolean faceverifyEnabled;
            public Boolean identityPicEnabled;
            public Boolean passwordEnabled;
            public Boolean preMobileEnabled;

            public Boolean isCertificateNumberEnabled() {
                return this.certificateNumberEnabled;
            }

            public Boolean isFaceverifyEnabled() {
                return this.faceverifyEnabled;
            }

            public Boolean isIdentityPicEnabled() {
                return this.identityPicEnabled;
            }

            public Boolean isPasswordEnabled() {
                return this.passwordEnabled;
            }

            public Boolean isPreMobileEnabled() {
                return this.preMobileEnabled;
            }

            public void setCertificateNumberEnabled(Boolean bool) {
                this.certificateNumberEnabled = bool;
            }

            public void setFaceverifyEnabled(Boolean bool) {
                this.faceverifyEnabled = bool;
            }

            public void setIdentityPicEnabled(Boolean bool) {
                this.identityPicEnabled = bool;
            }

            public void setPasswordEnabled(Boolean bool) {
                this.passwordEnabled = bool;
            }

            public void setPreMobileEnabled(Boolean bool) {
                this.preMobileEnabled = bool;
            }
        }

        public Ways getWays() {
            return this.ways;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }

        public void setWays(Ways ways) {
            this.ways = ways;
        }
    }

    public static class SecurityEmailAddress implements Serializable {
        public Boolean completed;
        public Boolean forced;
        public String info;
        public Boolean required;

        public String getInfo() {
            return this.info;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(String str) {
            this.info = str;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public static class SecurityQuestion implements Serializable {
        public Boolean completed;
        public Boolean forced;
        public Object info;
        public ArrayList<String> questionList;
        public Boolean required;

        public Object getInfo() {
            return this.info;
        }

        public ArrayList<String> getQuestionList() {
            return this.questionList;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(Object obj) {
            this.info = obj;
        }

        public void setQuestionList(ArrayList<String> arrayList) {
            this.questionList = arrayList;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public static class Workweixin implements Serializable {
        public Boolean completed;
        public String federatedUrl;
        public Boolean forced;
        public Object info;
        public Boolean required;

        public String getFederatedUrl() {
            return this.federatedUrl;
        }

        public Object getInfo() {
            return this.info;
        }

        public Boolean isCompleted() {
            return this.completed;
        }

        public Boolean isForced() {
            return this.forced;
        }

        public Boolean isRequired() {
            return this.required;
        }

        public void setCompleted(Boolean bool) {
            this.completed = bool;
        }

        public void setFederatedUrl(String str) {
            this.federatedUrl = str;
        }

        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        public void setInfo(Object obj) {
            this.info = obj;
        }

        public void setRequired(Boolean bool) {
            this.required = bool;
        }
    }

    public Alipay getAlipay() {
        return this.alipay;
    }

    public Dingtalk getDingtalk() {
        return this.dingtalk;
    }

    public Openweixin getOpenweixin() {
        return this.openweixin;
    }

    public SecurityEmailAddress getPortrait() {
        return this.portrait;
    }

    public Qq getQq() {
        return this.qq;
    }

    public Realname getRealname() {
        return this.realname;
    }

    public SecurityEmailAddress getSecurityEmailAddress() {
        return this.securityEmailAddress;
    }

    public SecurityEmailAddress getSecurityMobile() {
        return this.securityMobile;
    }

    public SecurityQuestion getSecurityQuestion() {
        return this.securityQuestion;
    }

    public Workweixin getWorkweixin() {
        return this.workweixin;
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public void setAlipay(Alipay alipay) {
        this.alipay = alipay;
    }

    public void setCompleted(Boolean bool) {
        this.completed = bool;
    }

    public void setDingtalk(Dingtalk dingtalk) {
        this.dingtalk = dingtalk;
    }

    public void setOpenweixin(Openweixin openweixin) {
        this.openweixin = openweixin;
    }

    public void setPortrait(SecurityEmailAddress securityEmailAddress) {
        this.portrait = securityEmailAddress;
    }

    public void setQq(Qq qq) {
        this.qq = qq;
    }

    public void setRealname(Realname realname) {
        this.realname = realname;
    }

    public void setSecurityEmailAddress(SecurityEmailAddress securityEmailAddress) {
        this.securityEmailAddress = securityEmailAddress;
    }

    public void setSecurityMobile(SecurityEmailAddress securityEmailAddress) {
        this.securityMobile = securityEmailAddress;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setWorkweixin(Workweixin workweixin) {
        this.workweixin = workweixin;
    }
}
