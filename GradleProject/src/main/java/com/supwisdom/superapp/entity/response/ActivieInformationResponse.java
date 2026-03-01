package com.supwisdom.superapp.entity.response;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class ActivieInformationResponse implements Serializable {
    public ActivationModeConfig activationModeConfig;
    public ActivationTypeConfig activationTypeConfig;
    public ActivationValidateConfig activationValidateConfig;
    public String nonce;
    public SecurityBindingValidateConfig securityBindingValidateConfig;

    public static class ActivationModeConfig implements Serializable {
        public boolean alipayEnabled;
        public boolean faceVerifyEnabled;
        public boolean mobileEnabled;
        public boolean preMobileVerifyEnabled;

        public boolean isAlipayEnabled() {
            return this.alipayEnabled;
        }

        public boolean isFaceVerifyEnabled() {
            return this.faceVerifyEnabled;
        }

        public boolean isMobileEnabled() {
            return this.mobileEnabled;
        }

        public boolean isPreMobileVerifyEnabled() {
            return this.preMobileVerifyEnabled;
        }

        public void setAlipayEnabled(boolean z) {
            this.alipayEnabled = z;
        }

        public void setFaceVerifyEnabled(boolean z) {
            this.faceVerifyEnabled = z;
        }

        public void setMobileEnabled(boolean z) {
            this.mobileEnabled = z;
        }

        public void setPreMobileVerifyEnabled(boolean z) {
            this.preMobileVerifyEnabled = z;
        }
    }

    public static class ActivationTypeConfig implements Serializable {
        public boolean secureEmailAddressEnabled;
        public boolean secureMobileEnabled;

        public boolean isSecureEmailAddressEnabled() {
            return this.secureEmailAddressEnabled;
        }

        public boolean isSecureMobileEnabled() {
            return this.secureMobileEnabled;
        }

        public void setSecureEmailAddressEnabled(boolean z) {
            this.secureEmailAddressEnabled = z;
        }

        public void setSecureMobileEnabled(boolean z) {
            this.secureMobileEnabled = z;
        }
    }

    public static class ActivationValidateConfig implements Serializable {
        public boolean validateAccountEnabled;
        public boolean validateIdentityNoEnabled;
        public boolean validateNameEnabled;

        public boolean isValidateAccountEnabled() {
            return this.validateAccountEnabled;
        }

        public boolean isValidateIdentityNoEnabled() {
            return this.validateIdentityNoEnabled;
        }

        public boolean isValidateNameEnabled() {
            return this.validateNameEnabled;
        }

        public void setValidateAccountEnabled(boolean z) {
            this.validateAccountEnabled = z;
        }

        public void setValidateIdentityNoEnabled(boolean z) {
            this.validateIdentityNoEnabled = z;
        }

        public void setValidateNameEnabled(boolean z) {
            this.validateNameEnabled = z;
        }
    }

    public static class SecurityBindingValidateConfig implements Serializable {
        public boolean validateEmailAddressExistEnabled;
        public boolean validateMobileExistEnabled;

        public boolean isValidateEmailAddressExistEnabled() {
            return this.validateEmailAddressExistEnabled;
        }

        public boolean isValidateMobileExistEnabled() {
            return this.validateMobileExistEnabled;
        }

        public void setValidateEmailAddressExistEnabled(boolean z) {
            this.validateEmailAddressExistEnabled = z;
        }

        public void setValidateMobileExistEnabled(boolean z) {
            this.validateMobileExistEnabled = z;
        }
    }

    public ActivationModeConfig getActivationModeConfig() {
        return this.activationModeConfig;
    }

    public ActivationTypeConfig getActivationTypeConfig() {
        return this.activationTypeConfig;
    }

    public ActivationValidateConfig getActivationValidateConfig() {
        return this.activationValidateConfig;
    }

    public String getNonce() {
        return this.nonce;
    }

    public SecurityBindingValidateConfig getSecurityBindingValidateConfig() {
        return this.securityBindingValidateConfig;
    }

    public void setActivationModeConfig(ActivationModeConfig activationModeConfig) {
        this.activationModeConfig = activationModeConfig;
    }

    public void setActivationTypeConfig(ActivationTypeConfig activationTypeConfig) {
        this.activationTypeConfig = activationTypeConfig;
    }

    public void setActivationValidateConfig(ActivationValidateConfig activationValidateConfig) {
        this.activationValidateConfig = activationValidateConfig;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setSecurityBindingValidateConfig(SecurityBindingValidateConfig securityBindingValidateConfig) {
        this.securityBindingValidateConfig = securityBindingValidateConfig;
    }
}
