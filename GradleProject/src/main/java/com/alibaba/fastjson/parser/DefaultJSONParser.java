package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.CollectionResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ListResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class DefaultJSONParser extends AbstractJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public static final Set<Class<?>> primitiveClasses;
    public ParserConfig config;
    public ParseContext context;
    public ParseContext[] contextArray;
    public int contextArrayIndex;
    public DateFormat dateFormat;
    public String dateFormatPattern;
    public List<ExtraProcessor> extraProcessors;
    public List<ExtraTypeProvider> extraTypeProviders;
    public final Object input;
    public final JSONLexer lexer;
    public int resolveStatus;
    public List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }

        public ParseContext getContext() {
            return this.context;
        }

        public FieldDeserializer getFieldDeserializer() {
            return this.fieldDeserializer;
        }

        public ParseContext getOwnerContext() {
            return this.ownerContext;
        }

        public String getReferenceValue() {
            return this.referenceValue;
        }

        public void setFieldDeserializer(FieldDeserializer fieldDeserializer) {
            this.fieldDeserializer = fieldDeserializer;
        }

        public void setOwnerContext(ParseContext parseContext) {
            this.ownerContext = parseContext;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        primitiveClasses = hashSet;
        hashSet.add(Boolean.TYPE);
        primitiveClasses.add(Byte.TYPE);
        primitiveClasses.add(Short.TYPE);
        primitiveClasses.add(Integer.TYPE);
        primitiveClasses.add(Long.TYPE);
        primitiveClasses.add(Float.TYPE);
        primitiveClasses.add(Double.TYPE);
        primitiveClasses.add(Boolean.class);
        primitiveClasses.add(Byte.class);
        primitiveClasses.add(Short.class);
        primitiveClasses.add(Integer.class);
        primitiveClasses.add(Long.class);
        primitiveClasses.add(Float.class);
        primitiveClasses.add(Double.class);
        primitiveClasses.add(BigInteger.class);
        primitiveClasses.add(BigDecimal.class);
        primitiveClasses.add(String.class);
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        this.contextArray[i] = parseContext;
    }

    public final void accept(int i) {
        JSONLexer lexer = getLexer();
        if (lexer.token() == i) {
            lexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(lexer.token()));
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus == 1) {
            if (!(collection instanceof List)) {
                ResolveTask lastResolveTask = getLastResolveTask();
                lastResolveTask.setFieldDeserializer(new CollectionResolveFieldDeserializer(this, collection));
                lastResolveTask.setOwnerContext(this.context);
                setResolveStatus(0);
                return;
            }
            int size = collection.size() - 1;
            ResolveTask lastResolveTask2 = getLastResolveTask();
            lastResolveTask2.setFieldDeserializer(new ListResolveFieldDeserializer(this, (List) collection, size));
            lastResolveTask2.setOwnerContext(this.context);
            setResolveStatus(0);
        }
    }

    public void checkMapResolve(Map map, String str) {
        if (this.resolveStatus == 1) {
            MapResolveFieldDeserializer mapResolveFieldDeserializer = new MapResolveFieldDeserializer(map, str);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.setFieldDeserializer(mapResolveFieldDeserializer);
            lastResolveTask.setOwnerContext(this.context);
            setResolveStatus(0);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        JSONLexer lexer = getLexer();
        try {
            if (isEnabled(Feature.AutoCloseSource) && lexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(lexer.token()));
            }
        } finally {
            lexer.close();
        }
    }

    public void config(Feature feature, boolean z) {
        getLexer().config(feature, z);
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public ParseContext getContext() {
        return this.context;
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern);
        }
        return this.dateFormat;
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraProcessor> getExtraProcessorsDirect() {
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public List<ExtraTypeProvider> getExtraTypeProvidersDirect() {
        return this.extraTypeProviders;
    }

    public String getInput() {
        Object obj = this.input;
        return obj instanceof char[] ? new String((char[]) this.input) : obj.toString();
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(r0.size() - 1);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].getPath())) {
                return this.contextArray[i].getObject();
            }
        }
        return null;
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void handleResovleTask(Object obj) {
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i);
            FieldDeserializer fieldDeserializer = resolveTask.getFieldDeserializer();
            if (fieldDeserializer != null) {
                Object object = resolveTask.getOwnerContext() != null ? resolveTask.getOwnerContext().getObject() : null;
                String referenceValue = resolveTask.getReferenceValue();
                fieldDeserializer.setValue(object, referenceValue.startsWith(Operators.DOLLAR_STR) ? getObject(referenceValue) : resolveTask.getContext().getObject());
            }
        }
    }

    public boolean isEnabled(Feature feature) {
        return getLexer().isEnabled(feature);
    }

    public Object parse() {
        return parse(null);
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (!Object.class.equals(type3)) {
                ArrayList arrayList2 = new ArrayList();
                parseArray((Class<?>) type3, (Collection) arrayList2);
                return arrayList2;
            }
            if (wildcardType.getLowerBounds().length == 0) {
                return parse();
            }
            throw new JSONException("not support type : " + type);
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public Object parseKey() {
        if (this.lexer.token() != 18) {
            return parse(null);
        }
        String strStringVal = this.lexer.stringVal();
        this.lexer.nextToken(16);
        return strStringVal;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0203, code lost:
    
        if ((r18 instanceof java.lang.Integer) != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0205, code lost:
    
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0215, code lost:
    
        return r16.config.getDeserializer(r6).deserialze(r16, r6, r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01bc, code lost:
    
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01c5, code lost:
    
        if (r3.token() != 13) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01c7, code lost:
    
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01ca, code lost:
    
        r0 = r16.config.getDeserializer(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01d2, code lost:
    
        if ((r0 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01d4, code lost:
    
        r5 = ((com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r0).createInstance(r16, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01db, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01dc, code lost:
    
        if (r5 != null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01e0, code lost:
    
        if (r6 != java.lang.Cloneable.class) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01e2, code lost:
    
        r5 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01e8, code lost:
    
        r5 = r6.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01ef, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01f0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01f8, code lost:
    
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01f9, code lost:
    
        setResolveStatus(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01ff, code lost:
    
        if (r16.context == null) goto L103;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0191 A[Catch: all -> 0x049d, TryCatch #1 {all -> 0x049d, blocks: (B:14:0x0046, B:17:0x0059, B:21:0x0075, B:74:0x0191, B:75:0x0197, B:77:0x01a4, B:79:0x01b2, B:81:0x01bc, B:83:0x01c7, B:84:0x01ca, B:86:0x01d4, B:91:0x01e2, B:92:0x01e8, B:96:0x01f1, B:97:0x01f8, B:98:0x01f9, B:100:0x0201, B:102:0x0205, B:103:0x0208, B:108:0x021a, B:110:0x0224, B:112:0x0233, B:114:0x0239, B:116:0x0245, B:119:0x024a, B:121:0x0250, B:141:0x02b6, B:143:0x02bc, B:146:0x02c5, B:147:0x02ca, B:123:0x025b, B:125:0x0263, B:127:0x026d, B:128:0x0272, B:129:0x027e, B:132:0x0287, B:134:0x028d, B:135:0x0292, B:137:0x0298, B:138:0x029e, B:139:0x02aa, B:148:0x02cb, B:149:0x02e9, B:151:0x02ec, B:152:0x02f0, B:156:0x02fd, B:159:0x0307, B:161:0x0316, B:163:0x0321, B:164:0x0329, B:165:0x032c, B:176:0x0356, B:178:0x035f, B:180:0x0368, B:183:0x0378, B:184:0x0396, B:171:0x033a, B:173:0x0344, B:175:0x0353, B:174:0x0349, B:187:0x039b, B:189:0x03af, B:192:0x03b6, B:195:0x03c0, B:196:0x03c5, B:199:0x03ca, B:201:0x03cf, B:205:0x03da, B:207:0x03e1, B:209:0x03e7, B:212:0x03ef, B:213:0x03f2, B:215:0x0401, B:218:0x040e, B:219:0x0411, B:221:0x0417, B:224:0x0421, B:227:0x042a, B:228:0x0444, B:216:0x0409, B:229:0x0445, B:231:0x0454, B:232:0x0458, B:234:0x0461, B:237:0x0468, B:240:0x0472, B:241:0x0490, B:24:0x0087, B:25:0x00a5, B:27:0x00a8, B:32:0x00b9, B:34:0x00c1, B:37:0x00d1, B:38:0x00e9, B:39:0x00ea, B:40:0x00ef, B:49:0x0100, B:51:0x010d, B:53:0x0116, B:56:0x011f, B:57:0x013d, B:52:0x0112, B:63:0x0147, B:65:0x014f, B:68:0x0160, B:69:0x0180, B:70:0x0181, B:71:0x0186, B:72:0x0187, B:242:0x0491, B:243:0x0496, B:244:0x0497, B:245:0x049c), top: B:250:0x0046, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a4 A[Catch: all -> 0x049d, TryCatch #1 {all -> 0x049d, blocks: (B:14:0x0046, B:17:0x0059, B:21:0x0075, B:74:0x0191, B:75:0x0197, B:77:0x01a4, B:79:0x01b2, B:81:0x01bc, B:83:0x01c7, B:84:0x01ca, B:86:0x01d4, B:91:0x01e2, B:92:0x01e8, B:96:0x01f1, B:97:0x01f8, B:98:0x01f9, B:100:0x0201, B:102:0x0205, B:103:0x0208, B:108:0x021a, B:110:0x0224, B:112:0x0233, B:114:0x0239, B:116:0x0245, B:119:0x024a, B:121:0x0250, B:141:0x02b6, B:143:0x02bc, B:146:0x02c5, B:147:0x02ca, B:123:0x025b, B:125:0x0263, B:127:0x026d, B:128:0x0272, B:129:0x027e, B:132:0x0287, B:134:0x028d, B:135:0x0292, B:137:0x0298, B:138:0x029e, B:139:0x02aa, B:148:0x02cb, B:149:0x02e9, B:151:0x02ec, B:152:0x02f0, B:156:0x02fd, B:159:0x0307, B:161:0x0316, B:163:0x0321, B:164:0x0329, B:165:0x032c, B:176:0x0356, B:178:0x035f, B:180:0x0368, B:183:0x0378, B:184:0x0396, B:171:0x033a, B:173:0x0344, B:175:0x0353, B:174:0x0349, B:187:0x039b, B:189:0x03af, B:192:0x03b6, B:195:0x03c0, B:196:0x03c5, B:199:0x03ca, B:201:0x03cf, B:205:0x03da, B:207:0x03e1, B:209:0x03e7, B:212:0x03ef, B:213:0x03f2, B:215:0x0401, B:218:0x040e, B:219:0x0411, B:221:0x0417, B:224:0x0421, B:227:0x042a, B:228:0x0444, B:216:0x0409, B:229:0x0445, B:231:0x0454, B:232:0x0458, B:234:0x0461, B:237:0x0468, B:240:0x0472, B:241:0x0490, B:24:0x0087, B:25:0x00a5, B:27:0x00a8, B:32:0x00b9, B:34:0x00c1, B:37:0x00d1, B:38:0x00e9, B:39:0x00ea, B:40:0x00ef, B:49:0x0100, B:51:0x010d, B:53:0x0116, B:56:0x011f, B:57:0x013d, B:52:0x0112, B:63:0x0147, B:65:0x014f, B:68:0x0160, B:69:0x0180, B:70:0x0181, B:71:0x0186, B:72:0x0187, B:242:0x0491, B:243:0x0496, B:244:0x0497, B:245:0x049c), top: B:250:0x0046, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object parseObject(java.util.Map r17, java.lang.Object r18) {
        /*
            Method dump skipped, instruction units count: 1186
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public void popContext() {
        if (isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.getParentContext();
        ParseContext[] parseContextArr = this.contextArray;
        int i = this.contextArrayIndex;
        parseContextArr[i - 1] = null;
        this.contextArrayIndex = i - 1;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public void setContext(ParseContext parseContext) {
        if (isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = parseContext;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public Object parse(Object obj) {
        JSONLexer lexer = getLexer();
        int i = lexer.token();
        if (i == 2) {
            Number numberIntegerValue = lexer.integerValue();
            lexer.nextToken();
            return numberIntegerValue;
        }
        if (i == 3) {
            Number numberDecimalValue = lexer.decimalValue(isEnabled(Feature.UseBigDecimal));
            lexer.nextToken();
            return numberDecimalValue;
        }
        if (i == 4) {
            String strStringVal = lexer.stringVal();
            lexer.nextToken(16);
            if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(strStringVal);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        return jSONScanner.getCalendar().getTime();
                    }
                } finally {
                    jSONScanner.close();
                }
            }
            return strStringVal;
        }
        if (i == 12) {
            return parseObject(new JSONObject(), obj);
        }
        if (i == 14) {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONArray;
        }
        switch (i) {
            case 6:
                lexer.nextToken();
                return Boolean.TRUE;
            case 7:
                lexer.nextToken();
                return Boolean.FALSE;
            case 8:
                lexer.nextToken();
                return null;
            case 9:
                lexer.nextToken(18);
                if (lexer.token() != 18) {
                    throw new JSONException("syntax error");
                }
                lexer.nextToken(10);
                accept(10);
                long jLongValue = lexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(jLongValue);
            default:
                switch (i) {
                    case 20:
                        if (lexer.isBlankInput()) {
                            return null;
                        }
                        throw new JSONException("unterminated json string, pos " + lexer.getBufferPosition());
                    case 21:
                        lexer.nextToken();
                        HashSet hashSet = new HashSet();
                        parseArray(hashSet, obj);
                        return hashSet;
                    case 22:
                        lexer.nextToken();
                        TreeSet treeSet = new TreeSet();
                        parseArray(treeSet, obj);
                        return treeSet;
                    case 23:
                        lexer.nextToken();
                        return null;
                    default:
                        throw new JSONException("syntax error, pos " + lexer.getBufferPosition());
                }
        }
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(str, new JSONScanner(str, i), parserConfig);
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public final void accept(int i, int i2) {
        JSONLexer lexer = getLexer();
        if (lexer.token() == i) {
            lexer.nextToken(i2);
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(lexer.token()));
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        if (this.lexer.token() == 21 || this.lexer.token() == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token() == 14) {
            if (Integer.TYPE != type) {
                if (String.class == type) {
                    deserializer = StringCodec.instance;
                    this.lexer.nextToken(4);
                } else {
                    deserializer = this.config.getDeserializer(type);
                    this.lexer.nextToken(deserializer.getFastMatchToken());
                }
            } else {
                deserializer = IntegerCodec.instance;
                this.lexer.nextToken(2);
            }
            ParseContext context = getContext();
            setContext(collection, obj);
            int i = 0;
            while (true) {
                try {
                    if (isEnabled(Feature.AllowArbitraryCommas)) {
                        while (this.lexer.token() == 16) {
                            this.lexer.nextToken();
                        }
                    }
                    if (this.lexer.token() == 15) {
                        setContext(context);
                        this.lexer.nextToken(16);
                        return;
                    }
                    Object objDeserialze = null;
                    if (Integer.TYPE != type) {
                        if (String.class == type) {
                            if (this.lexer.token() == 4) {
                                objDeserialze = this.lexer.stringVal();
                                this.lexer.nextToken(16);
                            } else {
                                Object obj2 = parse();
                                if (obj2 != null) {
                                    objDeserialze = obj2.toString();
                                }
                            }
                            collection.add(objDeserialze);
                        } else {
                            if (this.lexer.token() == 8) {
                                this.lexer.nextToken();
                            } else {
                                objDeserialze = deserializer.deserialze(this, type, Integer.valueOf(i));
                            }
                            collection.add(objDeserialze);
                            checkListResolve(collection);
                        }
                    } else {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    i++;
                } catch (Throwable th) {
                    setContext(context);
                    throw th;
                }
            }
        } else {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token()));
        }
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        ParseContext parseContext2 = new ParseContext(parseContext, obj, obj2);
        this.context = parseContext2;
        addContext(parseContext2);
        return this.context;
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArray = new ParseContext[8];
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.getSymbolTable();
        jSONLexer.nextToken(12);
    }

    public Object[] parseArray(Type[] typeArr) {
        Object objCast;
        Class<?> componentType;
        boolean zIsArray;
        int i = 8;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        int i2 = 14;
        if (this.lexer.token() == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token() == 15) {
                    this.lexer.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(2);
            int i3 = 0;
            while (i3 < typeArr.length) {
                if (this.lexer.token() == i) {
                    this.lexer.nextToken(16);
                    objCast = null;
                } else {
                    Type type = typeArr[i3];
                    if (type != Integer.TYPE && type != Integer.class) {
                        if (type == String.class) {
                            if (this.lexer.token() == 4) {
                                objCast = this.lexer.stringVal();
                                this.lexer.nextToken(16);
                            } else {
                                objCast = TypeUtils.cast(parse(), type, this.config);
                            }
                        } else {
                            if (i3 == typeArr.length - 1 && (type instanceof Class)) {
                                Class cls = (Class) type;
                                zIsArray = cls.isArray();
                                componentType = cls.getComponentType();
                            } else {
                                componentType = null;
                                zIsArray = false;
                            }
                            if (zIsArray && this.lexer.token() != i2) {
                                ArrayList arrayList = new ArrayList();
                                ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                                int fastMatchToken = deserializer.getFastMatchToken();
                                if (this.lexer.token() != 15) {
                                    while (true) {
                                        arrayList.add(deserializer.deserialze(this, type, null));
                                        if (this.lexer.token() != 16) {
                                            break;
                                        }
                                        this.lexer.nextToken(fastMatchToken);
                                    }
                                    if (this.lexer.token() != 15) {
                                        throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                                    }
                                }
                                objCast = TypeUtils.cast(arrayList, type, this.config);
                            } else {
                                objCast = this.config.getDeserializer(type).deserialze(this, type, null);
                            }
                        }
                    } else if (this.lexer.token() == 2) {
                        objCast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i3] = objCast;
                if (this.lexer.token() == 15) {
                    break;
                }
                if (this.lexer.token() == 16) {
                    if (i3 == typeArr.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i3++;
                    i = 8;
                    i2 = 14;
                } else {
                    throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                }
            }
            if (this.lexer.token() == 15) {
                this.lexer.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error");
        }
        throw new JSONException("syntax error : " + this.lexer.tokenName());
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    public final void parseArray(Collection collection, Object obj) {
        Number numberDecimalValue;
        JSONLexer lexer = getLexer();
        if (lexer.token() == 21 || lexer.token() == 22) {
            lexer.nextToken();
        }
        if (lexer.token() == 14) {
            lexer.nextToken(4);
            ParseContext context = getContext();
            setContext(collection, obj);
            int i = 0;
            while (true) {
                try {
                    if (isEnabled(Feature.AllowArbitraryCommas)) {
                        while (lexer.token() == 16) {
                            lexer.nextToken();
                        }
                    }
                    int i2 = lexer.token();
                    Object object = null;
                    object = null;
                    if (i2 == 2) {
                        Number numberIntegerValue = lexer.integerValue();
                        lexer.nextToken(16);
                        object = numberIntegerValue;
                    } else if (i2 == 3) {
                        if (lexer.isEnabled(Feature.UseBigDecimal)) {
                            numberDecimalValue = lexer.decimalValue(true);
                        } else {
                            numberDecimalValue = lexer.decimalValue(false);
                        }
                        object = numberDecimalValue;
                        lexer.nextToken(16);
                    } else if (i2 == 4) {
                        String strStringVal = lexer.stringVal();
                        lexer.nextToken(16);
                        object = strStringVal;
                        if (lexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                            JSONScanner jSONScanner = new JSONScanner(strStringVal);
                            Object time = strStringVal;
                            if (jSONScanner.scanISO8601DateIfMatch()) {
                                time = jSONScanner.getCalendar().getTime();
                            }
                            jSONScanner.close();
                            object = time;
                        }
                    } else if (i2 == 6) {
                        Boolean bool = Boolean.TRUE;
                        lexer.nextToken(16);
                        object = bool;
                    } else if (i2 == 7) {
                        Boolean bool2 = Boolean.FALSE;
                        lexer.nextToken(16);
                        object = bool2;
                    } else if (i2 == 8) {
                        lexer.nextToken(4);
                    } else if (i2 == 12) {
                        object = parseObject(new JSONObject(), Integer.valueOf(i));
                    } else {
                        if (i2 == 20) {
                            throw new JSONException("unclosed jsonArray");
                        }
                        if (i2 == 23) {
                            lexer.nextToken(4);
                        } else if (i2 == 14) {
                            JSONArray jSONArray = new JSONArray();
                            parseArray(jSONArray, Integer.valueOf(i));
                            object = jSONArray;
                        } else if (i2 != 15) {
                            object = parse();
                        } else {
                            lexer.nextToken(16);
                            return;
                        }
                    }
                    collection.add(object);
                    checkListResolve(collection);
                    if (lexer.token() == 16) {
                        lexer.nextToken(4);
                    }
                    i++;
                } finally {
                    setContext(context);
                }
            }
        } else {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(lexer.token()) + ", pos " + lexer.pos());
        }
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject((Type) cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (this.lexer.token() == 4) {
            type = TypeUtils.unwrap(type);
            if (type == byte[].class) {
                T t = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t;
            }
            if (type == char[].class) {
                String strStringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) strStringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, null);
        } catch (JSONException e2) {
            throw e2;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public void parseObject(Object obj) {
        Object objDeserialze;
        Class<?> cls = obj.getClass();
        Map<String, FieldDeserializer> fieldDeserializers = this.config.getFieldDeserializers(cls);
        if (this.lexer.token() != 12 && this.lexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
        while (true) {
            String strScanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (strScanSymbol == null) {
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token() != 16 || !isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            FieldDeserializer value = fieldDeserializers.get(strScanSymbol);
            if (value == null && strScanSymbol != null) {
                Iterator<Map.Entry<String, FieldDeserializer>> it = fieldDeserializers.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<String, FieldDeserializer> next = it.next();
                    if (strScanSymbol.equalsIgnoreCase(next.getKey())) {
                        value = next.getValue();
                        break;
                    }
                }
            }
            if (value == null) {
                if (isEnabled(Feature.IgnoreNotMatch)) {
                    this.lexer.nextTokenWithColon();
                    parse();
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + strScanSymbol);
                }
            } else {
                Class<?> fieldClass = value.getFieldClass();
                Type fieldType = value.getFieldType();
                if (fieldClass == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = IntegerCodec.instance.deserialze(this, fieldType, null);
                } else if (fieldClass == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    objDeserialze = StringCodec.deserialze(this);
                } else if (fieldClass == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = LongCodec.instance.deserialze(this, fieldType, null);
                } else {
                    ObjectDeserializer deserializer = this.config.getDeserializer(fieldClass, fieldType);
                    this.lexer.nextTokenWithColon(deserializer.getFastMatchToken());
                    objDeserialze = deserializer.deserialze(this, fieldType, null);
                }
                value.setValue(obj, objDeserialze);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, null);
    }

    public JSONObject parseObject() {
        JSONObject jSONObject = new JSONObject();
        parseObject((Map) jSONObject);
        return jSONObject;
    }
}
