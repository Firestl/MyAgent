package retrofit2.converter.scalars;

import java.io.IOException;
import retrofit2.Converter;
import supwisdom.et1;

/* JADX INFO: loaded from: classes3.dex */
public final class ScalarResponseBodyConverters {

    public static final class BooleanResponseBodyConverter implements Converter<et1, Boolean> {
        public static final BooleanResponseBodyConverter INSTANCE = new BooleanResponseBodyConverter();

        @Override // retrofit2.Converter
        public Boolean convert(et1 et1Var) throws IOException {
            return Boolean.valueOf(et1Var.string());
        }
    }

    public static final class ByteResponseBodyConverter implements Converter<et1, Byte> {
        public static final ByteResponseBodyConverter INSTANCE = new ByteResponseBodyConverter();

        @Override // retrofit2.Converter
        public Byte convert(et1 et1Var) throws IOException {
            return Byte.valueOf(et1Var.string());
        }
    }

    public static final class CharacterResponseBodyConverter implements Converter<et1, Character> {
        public static final CharacterResponseBodyConverter INSTANCE = new CharacterResponseBodyConverter();

        @Override // retrofit2.Converter
        public Character convert(et1 et1Var) throws IOException {
            String strString = et1Var.string();
            if (strString.length() == 1) {
                return Character.valueOf(strString.charAt(0));
            }
            throw new IOException("Expected body of length 1 for Character conversion but was " + strString.length());
        }
    }

    public static final class DoubleResponseBodyConverter implements Converter<et1, Double> {
        public static final DoubleResponseBodyConverter INSTANCE = new DoubleResponseBodyConverter();

        @Override // retrofit2.Converter
        public Double convert(et1 et1Var) throws IOException {
            return Double.valueOf(et1Var.string());
        }
    }

    public static final class FloatResponseBodyConverter implements Converter<et1, Float> {
        public static final FloatResponseBodyConverter INSTANCE = new FloatResponseBodyConverter();

        @Override // retrofit2.Converter
        public Float convert(et1 et1Var) throws IOException {
            return Float.valueOf(et1Var.string());
        }
    }

    public static final class IntegerResponseBodyConverter implements Converter<et1, Integer> {
        public static final IntegerResponseBodyConverter INSTANCE = new IntegerResponseBodyConverter();

        @Override // retrofit2.Converter
        public Integer convert(et1 et1Var) throws IOException {
            return Integer.valueOf(et1Var.string());
        }
    }

    public static final class LongResponseBodyConverter implements Converter<et1, Long> {
        public static final LongResponseBodyConverter INSTANCE = new LongResponseBodyConverter();

        @Override // retrofit2.Converter
        public Long convert(et1 et1Var) throws IOException {
            return Long.valueOf(et1Var.string());
        }
    }

    public static final class ShortResponseBodyConverter implements Converter<et1, Short> {
        public static final ShortResponseBodyConverter INSTANCE = new ShortResponseBodyConverter();

        @Override // retrofit2.Converter
        public Short convert(et1 et1Var) throws IOException {
            return Short.valueOf(et1Var.string());
        }
    }

    public static final class StringResponseBodyConverter implements Converter<et1, String> {
        public static final StringResponseBodyConverter INSTANCE = new StringResponseBodyConverter();

        @Override // retrofit2.Converter
        public String convert(et1 et1Var) throws IOException {
            return et1Var.string();
        }
    }
}
