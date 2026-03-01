package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.Base64;
import com.alibaba.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes.dex */
public final class JSONReaderScanner extends JSONLexerBase {
    public static int BUF_INIT_LEN = 8192;
    public static final ThreadLocal<SoftReference<char[]>> BUF_REF_LOCAL = new ThreadLocal<>();
    public char[] buf;
    public int bufLength;
    public Reader reader;

    public JSONReaderScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.buf, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        System.arraycopy(this.buf, i, cArr, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        return Base64.decodeFast(this.buf, this.np + 1, this.sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        int i2 = this.bufLength;
        if (i >= i2) {
            if (i2 == -1) {
                if (i < this.sp) {
                    return this.buf[i];
                }
                return (char) 26;
            }
            int i3 = this.bp;
            int i4 = i2 - i3;
            if (i4 > 0) {
                char[] cArr = this.buf;
                System.arraycopy(cArr, i3, cArr, 0, i4);
            }
            try {
                int i5 = this.reader.read(this.buf, i4, this.buf.length - i4);
                this.bufLength = i5;
                if (i5 == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                }
                if (i5 == -1) {
                    return (char) 26;
                }
                this.bufLength = i5 + i4;
                int i6 = this.bp;
                i -= i6;
                this.np -= i6;
                this.bp = 0;
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        }
        return this.buf[i];
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        BUF_REF_LOCAL.set(new SoftReference<>(this.buf));
        this.buf = null;
        IOUtils.close(this.reader);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final void copyTo(int i, int i2, char[] cArr) {
        System.arraycopy(this.buf, i, cArr, 0, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        int i2 = i - this.bp;
        while (c != charAt(this.bp + i2)) {
            if (c == 26) {
                return -1;
            }
            i2++;
        }
        return i2 + this.bp;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bufLength == -1) {
            return true;
        }
        int i = this.bp;
        char[] cArr = this.buf;
        if (i != cArr.length) {
            return this.ch == 26 && i + 1 == cArr.length;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        int i2 = this.bufLength;
        if (i >= i2) {
            if (i2 == -1) {
                return (char) 26;
            }
            int i3 = this.sp;
            if (i3 > 0) {
                int i4 = i2 - i3;
                if (this.ch == '\"') {
                    i4--;
                }
                char[] cArr = this.buf;
                System.arraycopy(cArr, i4, cArr, 0, this.sp);
            }
            this.np = -1;
            int i5 = this.sp;
            this.bp = i5;
            try {
                int length = this.buf.length - i5;
                if (length == 0) {
                    char[] cArr2 = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, cArr2, 0, this.buf.length);
                    this.buf = cArr2;
                    length = cArr2.length - i5;
                }
                int i6 = this.reader.read(this.buf, this.bp, length);
                this.bufLength = i6;
                if (i6 == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                }
                if (i6 == -1) {
                    this.ch = (char) 26;
                    return (char) 26;
                }
                this.bufLength = i6 + this.bp;
                i = i5;
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        }
        char c = this.buf[i];
        this.ch = c;
        return c;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        int i = this.np;
        if (i == -1) {
            i = 0;
        }
        char cCharAt = charAt((this.sp + i) - 1);
        int i2 = this.sp;
        if (cCharAt == 'L' || cCharAt == 'S' || cCharAt == 'B' || cCharAt == 'F' || cCharAt == 'D') {
            i2--;
        }
        return new String(this.buf, i, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (this.hasSpecial) {
            return new String(this.sbuf, 0, this.sp);
        }
        int i = this.np + 1;
        if (i < 0) {
            throw new IllegalStateException();
        }
        if (i <= this.buf.length - this.sp) {
            return new String(this.buf, i, this.sp);
        }
        throw new IllegalStateException();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (i2 >= 0) {
            return new String(this.buf, i, i2);
        }
        throw new StringIndexOutOfBoundsException(i2);
    }

    public JSONReaderScanner(String str, int i) {
        this(new StringReader(str), i);
    }

    public JSONReaderScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader) {
        this(reader, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader, int i) {
        this.reader = reader;
        this.features = i;
        SoftReference<char[]> softReference = BUF_REF_LOCAL.get();
        if (softReference != null) {
            this.buf = softReference.get();
            BUF_REF_LOCAL.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[BUF_INIT_LEN];
        }
        try {
            this.bufLength = reader.read(this.buf);
            this.bp = -1;
            next();
            if (this.ch == 65279) {
                next();
            }
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public JSONReaderScanner(char[] cArr, int i, int i2) {
        this(new CharArrayReader(cArr, 0, i), i2);
    }
}
