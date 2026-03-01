package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.FilterUtils;
import com.alibaba.fastjson.util.DeserializeBeanInfo;
import com.alibaba.fastjson.util.FieldInfo;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    public DeserializeBeanInfo beanInfo;
    public final Class<?> clazz;
    public final Map<String, FieldDeserializer> feildDeserializerMap;
    public final List<FieldDeserializer> fieldDeserializers;
    public final List<FieldDeserializer> sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    private void addFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        String strIntern = fieldInfo.getName().intern();
        FieldDeserializer fieldDeserializerCreateFieldDeserializer = createFieldDeserializer(parserConfig, cls, fieldInfo);
        this.feildDeserializerMap.put(strIntern, fieldDeserializerCreateFieldDeserializer);
        this.fieldDeserializers.add(fieldDeserializerCreateFieldDeserializer);
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        return parserConfig.createFieldDeserializer(parserConfig, cls, fieldInfo);
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        if (this.beanInfo.getDefaultConstructor() == null) {
            return null;
        }
        try {
            Constructor<?> defaultConstructor = this.beanInfo.getDefaultConstructor();
            Object objNewInstance = defaultConstructor.getParameterTypes().length == 0 ? defaultConstructor.newInstance(new Object[0]) : defaultConstructor.newInstance(defaultJSONParser.getContext().getObject());
            if (defaultJSONParser.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.getFieldList()) {
                    if (fieldInfo.getFieldClass() == String.class) {
                        try {
                            fieldInfo.set(objNewInstance, "");
                        } catch (Exception e2) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e2);
                        }
                    }
                }
            }
            return objNewInstance;
        } catch (Exception e3) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.token() != 14) {
            throw new JSONException("error");
        }
        T t = (T) createInstance(defaultJSONParser, type);
        int size = this.sortedFieldDeserializers.size();
        int i = 0;
        while (i < size) {
            char c = i == size + (-1) ? Operators.ARRAY_END : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers.get(i);
            Class<?> fieldClass = fieldDeserializer.getFieldClass();
            if (fieldClass == Integer.TYPE) {
                fieldDeserializer.setValue((Object) t, lexer.scanInt(c));
            } else if (fieldClass == String.class) {
                fieldDeserializer.setValue((Object) t, lexer.scanString(c));
            } else if (fieldClass == Long.TYPE) {
                fieldDeserializer.setValue(t, lexer.scanLong(c));
            } else if (fieldClass.isEnum()) {
                fieldDeserializer.setValue(t, lexer.scanEnum(fieldClass, defaultJSONParser.getSymbolTable(), c));
            } else {
                lexer.nextToken(14);
                fieldDeserializer.setValue(t, defaultJSONParser.parseObject(fieldDeserializer.getFieldType()));
                if (c == ']') {
                    if (lexer.token() != 15) {
                        throw new JSONException("syntax error");
                    }
                    lexer.nextToken(16);
                } else if (c == ',' && lexer.token() != 16) {
                    throw new JSONException("syntax error");
                }
            }
            i++;
        }
        lexer.nextToken(16);
        return t;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public Map<String, FieldDeserializer> getFieldDeserializerMap() {
        return this.feildDeserializerMap;
    }

    public final boolean isSupportArrayToBean(JSONLexer jSONLexer) {
        return Feature.isEnabled(this.beanInfo.getParserFeatures(), Feature.SupportArrayToBean) || jSONLexer.isEnabled(Feature.SupportArrayToBean);
    }

    public void parseExtra(DefaultJSONParser defaultJSONParser, Object obj, String str) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        if (lexer.isEnabled(Feature.IgnoreNotMatch)) {
            lexer.nextTokenWithColon();
            Type extratype = FilterUtils.getExtratype(defaultJSONParser, obj, str);
            FilterUtils.processExtra(defaultJSONParser, obj, str, extratype == null ? defaultJSONParser.parse() : defaultJSONParser.parseObject(extratype));
        } else {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + str);
        }
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        JSONLexer lexer = defaultJSONParser.getLexer();
        FieldDeserializer value = this.feildDeserializerMap.get(str);
        if (value == null) {
            Iterator<Map.Entry<String, FieldDeserializer>> it = this.feildDeserializerMap.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, FieldDeserializer> next = it.next();
                if (next.getKey().equalsIgnoreCase(str)) {
                    value = next.getValue();
                    break;
                }
            }
        }
        if (value == null) {
            parseExtra(defaultJSONParser, obj, str);
            return false;
        }
        lexer.nextTokenWithColon(value.getFastMatchToken());
        value.parseField(defaultJSONParser, obj, type, map);
        return true;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this.feildDeserializerMap = new IdentityHashMap();
        this.fieldDeserializers = new ArrayList();
        this.sortedFieldDeserializers = new ArrayList();
        this.clazz = cls;
        DeserializeBeanInfo deserializeBeanInfoComputeSetters = DeserializeBeanInfo.computeSetters(cls, type);
        this.beanInfo = deserializeBeanInfoComputeSetters;
        Iterator<FieldInfo> it = deserializeBeanInfoComputeSetters.getFieldList().iterator();
        while (it.hasNext()) {
            addFieldDeserializer(parserConfig, cls, it.next());
        }
        Iterator<FieldInfo> it2 = this.beanInfo.getSortedFieldList().iterator();
        while (it2.hasNext()) {
            this.sortedFieldDeserializers.add(this.feildDeserializerMap.get(it2.next().getName().intern()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01c1, code lost:
    
        r0 = com.alibaba.fastjson.util.TypeUtils.loadClass(r4);
        r0 = (T) r20.getConfig().getDeserializer(r0).deserialze(r20, r0, r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01d1, code lost:
    
        if (r3 == null) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01d3, code lost:
    
        r3.setObject(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01d6, code lost:
    
        r20.setContext(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01d9, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0313, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r10.token()));
     */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0322  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r20, java.lang.reflect.Type r21, java.lang.Object r22, java.lang.Object r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 814
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
