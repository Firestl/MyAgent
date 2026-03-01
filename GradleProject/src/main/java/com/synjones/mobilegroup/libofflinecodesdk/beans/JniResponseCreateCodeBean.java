package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class JniResponseCreateCodeBean {
    public MessageBean message;

    public static class MessageBean {
        public String qrcode_data;
        public int retcode;
        public String retmsg;

        public String toString() {
            return "MessageBean{retcode=" + this.retcode + ", retmsg='" + this.retmsg + Operators.SINGLE_QUOTE + ", qrcode_data='" + this.qrcode_data + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public String toString() {
        return "JniResponseCreateCodeBean{message=" + this.message + Operators.BLOCK_END;
    }
}
