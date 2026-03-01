package io.dcloud.feature.uniapp.dom;

import android.text.Layout;
import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.binding.ELUtils;
import com.taobao.weex.ui.component.WXTextDecoration;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import supwisdom.j4;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsStyle implements Map<String, Object>, Cloneable {
    public static final int UNSET = -1;
    public static final long serialVersionUID = 611132641365274134L;
    public j4<String, Object> mBindingStyle;
    public Map<String, Object> mPesudoResetStyleMap;
    public Map<String, Map<String, Object>> mPesudoStyleMap;
    public Map<String, Object> mStyles;

    public AbsStyle() {
        this.mStyles = new j4();
    }

    private boolean addBindingStyleIfStatement(String str, Object obj) {
        if (!ELUtils.isBinding(obj)) {
            return false;
        }
        if (this.mBindingStyle == null) {
            this.mBindingStyle = new j4<>();
        }
        this.mBindingStyle.put(str, ELUtils.bindingBlock(obj));
        return true;
    }

    public static String getFontFamily(Map<String, Object> map) {
        Object obj;
        if (map == null || (obj = map.get(Constants.Name.FONT_FAMILY)) == null) {
            return null;
        }
        return obj.toString();
    }

    public static int getFontSize(Map<String, Object> map, int i, float f) {
        float realPxByWidth;
        if (map == null) {
            realPxByWidth = WXViewUtils.getRealPxByWidth(i, f);
        } else {
            int i2 = WXUtils.getInt(map.get(Constants.Name.FONT_SIZE));
            if (i2 > 0) {
                i = i2;
            }
            realPxByWidth = WXViewUtils.getRealPxByWidth(i, f);
        }
        return (int) realPxByWidth;
    }

    public static int getFontStyle(Map<String, Object> map) {
        Object obj;
        return (map == null || (obj = map.get(Constants.Name.FONT_STYLE)) == null || !obj.toString().equals(Constants.Value.ITALIC)) ? 0 : 2;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int getFontWeight(Map<String, Object> map) {
        Object obj;
        if (map == null || (obj = map.get(Constants.Name.FONT_WEIGHT)) == null) {
            return 0;
        }
        String string = obj.toString();
        byte b = -1;
        switch (string.hashCode()) {
            case 53430:
                if (string.equals("600")) {
                    b = 0;
                }
                break;
            case 54391:
                if (string.equals("700")) {
                    b = 1;
                }
                break;
            case 55352:
                if (string.equals("800")) {
                    b = 2;
                }
                break;
            case 56313:
                if (string.equals("900")) {
                    b = 3;
                }
                break;
            case 3029637:
                if (string.equals(Constants.Value.BOLD)) {
                    b = 4;
                }
                break;
        }
        return (b == 0 || b == 1 || b == 2 || b == 3 || b == 4) ? 1 : 0;
    }

    public static int getLineHeight(Map<String, Object> map, float f) {
        int i;
        if (map != null && (i = WXUtils.getInt(map.get(Constants.Name.LINE_HEIGHT))) > 0) {
            return (int) WXViewUtils.getRealPxByWidth(i, f);
        }
        return -1;
    }

    public static int getLines(Map<String, Object> map) {
        return WXUtils.getInt(map.get(Constants.Name.LINES));
    }

    public static Layout.Alignment getTextAlignment(Map<String, Object> map) {
        return getTextAlignment(map, false);
    }

    public static String getTextColor(Map<String, Object> map) {
        Object obj;
        return (map == null || (obj = map.get("color")) == null) ? "" : obj.toString();
    }

    public static WXTextDecoration getTextDecoration(Map<String, Object> map) {
        Object obj;
        if (map == null || (obj = map.get(Constants.Name.TEXT_DECORATION)) == null) {
            return WXTextDecoration.NONE;
        }
        String string = obj.toString();
        byte b = -1;
        int iHashCode = string.hashCode();
        if (iHashCode != -1171789332) {
            if (iHashCode != -1026963764) {
                if (iHashCode == 3387192 && string.equals("none")) {
                    b = 2;
                }
            } else if (string.equals("underline")) {
                b = 0;
            }
        } else if (string.equals("line-through")) {
            b = 1;
        }
        return b != 0 ? b != 1 ? b != 2 ? WXTextDecoration.INVALID : WXTextDecoration.NONE : WXTextDecoration.LINETHROUGH : WXTextDecoration.UNDERLINE;
    }

    public static TextUtils.TruncateAt getTextOverflow(Map<String, Object> map) {
        if (TextUtils.equals(Constants.Name.ELLIPSIS, (String) map.get(Constants.Name.TEXT_OVERFLOW))) {
            return TextUtils.TruncateAt.END;
        }
        return null;
    }

    private void initPesudoMapsIfNeed(Map<? extends String, ?> map) {
        if (this.mPesudoStyleMap == null) {
            this.mPesudoStyleMap = new j4();
        }
        if (this.mPesudoResetStyleMap == null) {
            this.mPesudoResetStyleMap = new j4();
        }
        if (this.mPesudoResetStyleMap.isEmpty()) {
            this.mPesudoResetStyleMap.putAll(map);
        }
    }

    private Map<String, Object> parseBindingStylesStatements(Map map) {
        if (map != null && map.size() != 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (addBindingStyleIfStatement((String) entry.getKey(), entry.getValue())) {
                    Map<String, Map<String, Object>> map2 = this.mPesudoStyleMap;
                    if (map2 != null) {
                        map2.remove(entry.getKey());
                    }
                    Map<String, Object> map3 = this.mPesudoResetStyleMap;
                    if (map3 != null) {
                        map3.remove(entry.getKey());
                    }
                    it.remove();
                }
            }
        }
        return map;
    }

    @Override // java.util.Map
    public void clear() {
        this.mStyles.clear();
    }

    @Override // 
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract AbsStyle mo31clone();

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.mStyles.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.mStyles.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.mStyles.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.mStyles.equals(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.mStyles.get(obj);
    }

    public String getBackgroundColor() {
        Object obj = get("backgroundColor");
        return obj == null ? "" : obj.toString();
    }

    public j4<String, Object> getBindingStyle() {
        return this.mBindingStyle;
    }

    public String getBlur() {
        if (get(Constants.Name.FILTER) == null) {
            return null;
        }
        return get(Constants.Name.FILTER).toString().trim();
    }

    public String getBorderColor() {
        Object obj = get(Constants.Name.BORDER_COLOR);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public float getBorderRadius() {
        float f = WXUtils.getFloat(get(Constants.Name.BORDER_RADIUS));
        if (WXUtils.isUndefined(f)) {
            return Float.NaN;
        }
        return f;
    }

    public String getBorderStyle() {
        Object obj = get(Constants.Name.BORDER_STYLE);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public float getBottom() {
        float f = WXUtils.getFloat(get("bottom"));
        if (WXUtils.isUndefined(f)) {
            return Float.NaN;
        }
        return f;
    }

    public float getLeft() {
        float f = WXUtils.getFloat(get("left"));
        if (WXUtils.isUndefined(f)) {
            return Float.NaN;
        }
        return f;
    }

    public float getOpacity() {
        Object obj = get("opacity");
        if (obj == null) {
            return 1.0f;
        }
        return WXUtils.getFloat(obj);
    }

    public String getOverflow() {
        Object obj = get(Constants.Name.OVERFLOW);
        return obj == null ? "visible" : obj.toString();
    }

    public Map<String, Object> getPesudoResetStyles() {
        if (this.mPesudoResetStyleMap == null) {
            this.mPesudoResetStyleMap = new j4();
        }
        return this.mPesudoResetStyleMap;
    }

    public Map<String, Map<String, Object>> getPesudoStyles() {
        if (this.mPesudoStyleMap == null) {
            this.mPesudoStyleMap = new j4();
        }
        return this.mPesudoStyleMap;
    }

    public float getRight() {
        float f = WXUtils.getFloat(get("right"));
        if (WXUtils.isUndefined(f)) {
            return Float.NaN;
        }
        return f;
    }

    public int getTimeFontSize(int i) {
        int i2 = WXUtils.getInt(get("timeFontSize"));
        return i2 <= 0 ? i : i2;
    }

    public float getTop() {
        float f = WXUtils.getFloat(get("top"));
        if (WXUtils.isUndefined(f)) {
            return Float.NaN;
        }
        return f;
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.mStyles.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.mStyles.isEmpty();
    }

    public boolean isFixed() {
        Object obj = get("position");
        if (obj == null) {
            return false;
        }
        return obj.toString().equals(Constants.Value.FIXED);
    }

    public boolean isSticky() {
        Object obj = get("position");
        if (obj == null) {
            return false;
        }
        return obj.toString().equals("sticky");
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.mStyles.keySet();
    }

    public void parseStatements() {
        Map<String, Object> map = this.mStyles;
        if (map != null) {
            this.mStyles = parseBindingStylesStatements(map);
        }
    }

    public <T extends String, V> void processPesudoClasses(Map<T, V> map) {
        j4 j4Var = null;
        for (Map.Entry<T, V> entry : map.entrySet()) {
            T key = entry.getKey();
            int iIndexOf = key.indexOf(com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR);
            if (iIndexOf > 0) {
                initPesudoMapsIfNeed(map);
                String strSubstring = key.substring(iIndexOf);
                if (strSubstring.equals(Constants.PSEUDO.ENABLED)) {
                    String strSubstring2 = key.substring(0, iIndexOf);
                    if (j4Var == null) {
                        j4Var = new j4();
                    }
                    j4Var.put(strSubstring2, entry.getValue());
                    this.mPesudoResetStyleMap.put(strSubstring2, entry.getValue());
                } else {
                    String strReplace = strSubstring.replace(Constants.PSEUDO.ENABLED, "");
                    Map<String, Object> j4Var2 = this.mPesudoStyleMap.get(strReplace);
                    if (j4Var2 == null) {
                        j4Var2 = new j4<>();
                        this.mPesudoStyleMap.put(strReplace, j4Var2);
                    }
                    j4Var2.put(key.substring(0, iIndexOf), entry.getValue());
                }
            }
        }
        if (j4Var == null || j4Var.isEmpty()) {
            return;
        }
        this.mStyles.putAll(j4Var);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.mStyles.putAll(map);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.mStyles.remove(obj);
    }

    @Override // java.util.Map
    public int size() {
        return this.mStyles.size();
    }

    public String toString() {
        return this.mStyles.toString();
    }

    public void updateStyle(Map<? extends String, ?> map, boolean z) {
        parseBindingStylesStatements(map);
        putAll(map, z);
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.mStyles.values();
    }

    public static Layout.Alignment getTextAlignment(Map<String, Object> map, boolean z) {
        Layout.Alignment alignment = z ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL;
        String str = (String) map.get(Constants.Name.TEXT_ALIGN);
        return TextUtils.equals("left", str) ? Layout.Alignment.ALIGN_NORMAL : TextUtils.equals("center", str) ? Layout.Alignment.ALIGN_CENTER : TextUtils.equals("right", str) ? Layout.Alignment.ALIGN_OPPOSITE : alignment;
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        if (addBindingStyleIfStatement(str, obj)) {
            return null;
        }
        return this.mStyles.put(str, obj);
    }

    public void putAll(Map<? extends String, ?> map, boolean z) {
        this.mStyles.putAll(map);
        if (z) {
            return;
        }
        processPesudoClasses(map);
    }

    public AbsStyle(Map<String, Object> map) {
        this.mStyles = map;
        processPesudoClasses(map);
    }

    public AbsStyle(Map<String, Object> map, boolean z) {
        this();
        putAll(map, z);
    }
}
