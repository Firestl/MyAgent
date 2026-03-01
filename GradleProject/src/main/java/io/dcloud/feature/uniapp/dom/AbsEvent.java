package io.dcloud.feature.uniapp.dom;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.dom.binding.JSONUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import supwisdom.j4;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsEvent extends ArrayList<String> implements Serializable, Cloneable {
    public static final String EVENT_KEY_ARGS = "params";
    public static final String EVENT_KEY_TYPE = "type";
    public static final long serialVersionUID = -8186587029452440107L;
    public j4 mEventBindingArgs;
    public j4<String, List<Object>> mEventBindingArgsValues;

    private void addBindingArgsEvent(String str, Object obj) {
        if (!contains(str)) {
            add(str);
        }
        if (obj != null) {
            if (this.mEventBindingArgs == null) {
                this.mEventBindingArgs = new j4();
            }
            this.mEventBindingArgs.put(str, ELUtils.bindingBlock(obj));
        }
    }

    private String addBindingEvent(JSONObject jSONObject) {
        String string = jSONObject.getString("type");
        Object obj = jSONObject.get("params");
        if (string != null) {
            addBindingArgsEvent(string, obj);
        }
        return string;
    }

    public static String getEventName(Object obj) {
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).getString("type");
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public void addEvent(Object obj) {
        if (!(obj instanceof CharSequence)) {
            if (obj instanceof JSONObject) {
                addBindingEvent((JSONObject) obj);
            }
        } else {
            if (JSONUtils.isJSON(obj.toString())) {
                addEvent(JSONUtils.toJSON(obj.toString()));
                return;
            }
            String string = obj.toString();
            if (contains(string)) {
                return;
            }
            add(string);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        j4 j4Var = this.mEventBindingArgs;
        if (j4Var != null) {
            j4Var.clear();
        }
        j4<String, List<Object>> j4Var2 = this.mEventBindingArgsValues;
        if (j4Var2 != null) {
            j4Var2.clear();
        }
        super.clear();
    }

    @Override // java.util.ArrayList
    public abstract AbsEvent clone();

    public j4 getEventBindingArgs() {
        return this.mEventBindingArgs;
    }

    public j4<String, List<Object>> getEventBindingArgsValues() {
        return this.mEventBindingArgsValues;
    }

    public void parseStatements() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i < size(); i++) {
            String str = get(i);
            if (JSONUtils.isJSON(str)) {
                set(i, addBindingEvent(JSONUtils.toJSON(str)));
            }
        }
    }

    public void putEventBindingArgsValue(String str, List<Object> list) {
        if (this.mEventBindingArgsValues == null) {
            this.mEventBindingArgsValues = new j4<>();
        }
        if (list == null) {
            this.mEventBindingArgsValues.remove(str);
        } else {
            this.mEventBindingArgsValues.put(str, list);
        }
    }

    public boolean remove(String str) {
        j4 j4Var = this.mEventBindingArgs;
        if (j4Var != null) {
            j4Var.remove(str);
        }
        j4<String, List<Object>> j4Var2 = this.mEventBindingArgsValues;
        if (j4Var2 != null) {
            j4Var2.remove(str);
        }
        return super.remove((Object) str);
    }

    public void setEventBindingArgs(j4 j4Var) {
        this.mEventBindingArgs = j4Var;
    }

    public void setEventBindingArgsValues(j4<String, List<Object>> j4Var) {
        this.mEventBindingArgsValues = j4Var;
    }
}
