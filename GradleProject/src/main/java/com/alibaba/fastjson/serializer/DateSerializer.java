package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class DateSerializer implements ObjectSerializer {
    public static final DateSerializer instance = new DateSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        char[] charArray;
        SerializeWriter writer = jSONSerializer.getWriter();
        if (obj == null) {
            writer.writeNull();
            return;
        }
        if (writer.isEnabled(SerializerFeature.WriteClassName) && obj.getClass() != type) {
            if (obj.getClass() == Date.class) {
                writer.write("new Date(");
                writer.writeLongAndChar(((Date) obj).getTime(), Operators.BRACKET_END);
                return;
            }
            writer.write(Operators.BLOCK_START);
            writer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            jSONSerializer.write(obj.getClass().getName());
            writer.writeFieldValue(',', "val", ((Date) obj).getTime());
            writer.write(Operators.BLOCK_END);
            return;
        }
        Date date = (Date) obj;
        if (writer.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormat = jSONSerializer.getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT);
            }
            writer.writeString(dateFormat.format(date));
            return;
        }
        long time = date.getTime();
        if (!jSONSerializer.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            writer.writeLong(time);
            return;
        }
        if (jSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes)) {
            writer.append(Operators.SINGLE_QUOTE);
        } else {
            writer.append(Operators.QUOTE);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        int i7 = calendar.get(14);
        if (i7 != 0) {
            charArray = "0000-00-00T00:00:00.000".toCharArray();
            IOUtils.getChars(i7, 23, charArray);
            IOUtils.getChars(i6, 19, charArray);
            IOUtils.getChars(i5, 16, charArray);
            IOUtils.getChars(i4, 13, charArray);
            IOUtils.getChars(i3, 10, charArray);
            IOUtils.getChars(i2, 7, charArray);
            IOUtils.getChars(i, 4, charArray);
        } else if (i6 == 0 && i5 == 0 && i4 == 0) {
            charArray = "0000-00-00".toCharArray();
            IOUtils.getChars(i3, 10, charArray);
            IOUtils.getChars(i2, 7, charArray);
            IOUtils.getChars(i, 4, charArray);
        } else {
            charArray = "0000-00-00T00:00:00".toCharArray();
            IOUtils.getChars(i6, 19, charArray);
            IOUtils.getChars(i5, 16, charArray);
            IOUtils.getChars(i4, 13, charArray);
            IOUtils.getChars(i3, 10, charArray);
            IOUtils.getChars(i2, 7, charArray);
            IOUtils.getChars(i, 4, charArray);
        }
        writer.write(charArray);
        if (jSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes)) {
            writer.append(Operators.SINGLE_QUOTE);
        } else {
            writer.append(Operators.QUOTE);
        }
    }
}
