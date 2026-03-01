package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class JniResponseBean {
    public MessageBean message;

    public static class MessageBean {
        public int retcode;
        public String retmsg;

        public String toString() {
            return "MessageBean{retcode=" + this.retcode + ", retmsg='" + this.retmsg + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public String toErrorString() {
        if (this.message == null) {
            return "JniResponseBean.MessageBean==null";
        }
        return this.message.retcode + "/" + this.message.retmsg;
    }

    public String toString() {
        return "JniResponseBean{message=" + this.message + Operators.BLOCK_END;
    }
}
