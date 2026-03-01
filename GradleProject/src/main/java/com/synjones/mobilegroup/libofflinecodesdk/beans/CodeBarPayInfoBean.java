package com.synjones.mobilegroup.libofflinecodesdk.beans;

import com.taobao.weex.el.parse.Operators;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.rp1;

/* JADX INFO: loaded from: classes2.dex */
public class CodeBarPayInfoBean extends CommonBaseResponse<List<DataBean>> {

    public static class DataBean {
        public int accinfo_balance;
        public String account;
        public String bandacc;
        public boolean bankRelation;
        public String code;
        public int db_balance;
        public int elec_accamt;
        public String expdate;
        public String icon;
        public int id;
        public int lostflag;
        public String name;
        public String payacc;
        public int payid;
        public String payif;
        public String paytype;
        public int status;
        public int unsettle_amount;
        public String voucher;
        public int voucherStatus;

        public JSONObject toJsonObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("account", rp1.a(this.account));
            jSONObject.put("paytype", rp1.a(this.paytype));
            jSONObject.put("payacc", rp1.a(this.payacc));
            jSONObject.put("bankRelation", this.bankRelation);
            jSONObject.put("name", rp1.a(this.name));
            jSONObject.put("code", rp1.a(this.code));
            jSONObject.put("payif", rp1.a(this.payif));
            jSONObject.put("icon", rp1.a(this.icon));
            jSONObject.put("status", this.status);
            jSONObject.put("payid", this.payid);
            jSONObject.put("voucher", rp1.a(this.voucher));
            jSONObject.put("voucherStatus", this.voucherStatus);
            jSONObject.put("lostflag", this.lostflag);
            jSONObject.put("expdate", rp1.a(this.expdate));
            jSONObject.put("bandacc", rp1.a(this.bandacc));
            jSONObject.put("unsettle_amount", this.unsettle_amount);
            jSONObject.put("db_balance", this.db_balance);
            jSONObject.put("elec_accamt", this.elec_accamt);
            jSONObject.put("accinfo_balance", this.accinfo_balance);
            jSONObject.put("id", this.id);
            return jSONObject;
        }

        public String toString() {
            return "DataBean{account='" + this.account + Operators.SINGLE_QUOTE + ", paytype='" + this.paytype + Operators.SINGLE_QUOTE + ", payacc='" + this.payacc + Operators.SINGLE_QUOTE + ", bankRelation=" + this.bankRelation + ", name='" + this.name + Operators.SINGLE_QUOTE + ", code='" + this.code + Operators.SINGLE_QUOTE + ", payif='" + this.payif + Operators.SINGLE_QUOTE + ", icon='" + this.icon + Operators.SINGLE_QUOTE + ", status=" + this.status + ", payid=" + this.payid + ", voucher='" + this.voucher + Operators.SINGLE_QUOTE + ", voucherStatus=" + this.voucherStatus + ", lostflag=" + this.lostflag + ", expdate='" + this.expdate + Operators.SINGLE_QUOTE + ", bandacc='" + this.bandacc + Operators.SINGLE_QUOTE + ", unsettle_amount=" + this.unsettle_amount + ", db_balance=" + this.db_balance + ", elec_accamt=" + this.elec_accamt + ", accinfo_balance=" + this.accinfo_balance + ", id=" + this.id + Operators.BLOCK_END;
        }
    }
}
