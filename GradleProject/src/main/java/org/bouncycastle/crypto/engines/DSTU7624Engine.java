package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.igexin.c.a.d.g;
import com.taobao.weex.wson.Wson;
import com.tencent.rtmp.TXLivePusher;
import com.umeng.analytics.pro.co;
import com.umeng.analytics.pro.db;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes3.dex */
public class DSTU7624Engine implements BlockCipher {
    public static final int ROUNDS_128 = 10;
    public static final int ROUNDS_256 = 14;
    public static final int ROUNDS_512 = 18;
    public static final byte[] S0 = {-88, 67, 95, 6, 107, 117, Wson.NUMBER_LONG_TYPE, 89, KeyFactorySpi.Ed448_type, -33, -121, -107, 23, -16, -40, 9, 109, -13, 29, -53, -55, 77, 44, -81, 121, co.k, -105, -3, KeyFactorySpi.x448_type, 75, 69, 57, 62, -35, -93, 79, -76, -74, -102, db.l, co.j, -65, 21, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, 25, -43, -83, 88, -92, -69, -95, -36, TXLivePusher.SEI_MSG_TYPE, -125, 55, 66, -28, 122, 50, -100, -52, -85, 74, -113, KeyFactorySpi.x25519_type, 4, 39, 46, -25, -30, 90, -106, 22, 35, 43, -62, Wson.NUMBER_BIG_DECIMAL_TYPE, Wson.BOOLEAN_TYPE_FALSE, 15, PSSSigner.TRAILER_IMPLICIT, -87, 71, 65, 52, 72, -4, -73, 106, -120, -91, 83, -122, -7, Wson.ARRAY_TYPE, -37, 56, Wson.MAP_TYPE, -61, 30, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, -11, 20, -97, 8, 85, -101, 76, -2, UTF8.S_P4B, 92, -38, 24, Wson.NUMBER_FLOAT_TYPE, -51, 125, PublicSuffixDatabase.EXCEPTION_MARKER, -80, 63, 27, -119, -1, -21, -124, Wson.NUMBER_INT_TYPE, 58, -99, -41, -45, KeyFactorySpi.Ed25519_type, Wson.NUMBER_BIG_INTEGER_TYPE, UTF8.S_P3B, -75, -34, 93, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, -96, -59, 2, -90, Wson.BOOLEAN_TYPE_TRUE, 45, 11, -94, 118, -77, -66, -50, -67, -82, -23, -118, 49, 28, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, 127, -5, 5, -63, 94, -112, 32, 61, co.h, -9, -22, 10, 13, 126, -8, UTF8.S_P4A, JSONLexer.EOI, -60, 7, 87, -72, 60, 98, -29, -56, -84, 82, Wson.NUMBER_DOUBLE_TYPE, 16, -48, -39, 19, 12, SharedPreferencesNewImpl.FINISH_MARK, 41, 81, -71, -49, -42, Wson.STRING_TYPE, -115, -127, 84, -64, -19, 78, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, g.n};
    public static final byte[] S1 = {-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, -42, -78, -46, -112, 23, -8, 66, 21, 86, -76, Wson.NUMBER_BIG_DECIMAL_TYPE, 28, -120, 67, -59, 92, 54, -70, -11, 87, Wson.NUMBER_BIG_INTEGER_TYPE, -115, 49, -10, Wson.NUMBER_DOUBLE_TYPE, 88, -98, -12, 34, -86, 117, 15, 2, -79, -33, 109, Wson.STRING_TYPE, 77, 124, 38, 46, -9, 8, 93, 68, 62, -97, 20, -56, -82, 84, 16, -40, PSSSigner.TRAILER_IMPLICIT, JSONLexer.EOI, 107, Wson.NUMBER_INT_TYPE, -13, -67, 51, -85, -6, -47, -101, 104, 78, 22, -107, -111, -18, 76, 99, -114, Wson.ARRAY_TYPE, -52, 60, 25, -95, -127, 73, Wson.MAP_TYPE, -39, KeyFactorySpi.x448_type, 55, UTF8.S_P4B, -54, -25, 43, 72, -3, -106, 69, -4, 65, SharedPreferencesNewImpl.FINISH_MARK, 13, 121, -27, -119, -116, -29, 32, 48, -36, -73, Wson.NUMBER_LONG_TYPE, 74, -75, 63, -105, -44, 98, 45, 6, -92, -91, -125, 95, 42, -38, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, db.l, 10, 61, 81, 125, -109, 27, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, -80, -104, 24, 50, KeyFactorySpi.Ed448_type, 75, -17, 59, KeyFactorySpi.Ed25519_type, -96, -28, UTF8.S_P3B, -1, -61, -87, -26, 120, -7, -117, Wson.NUMBER_FLOAT_TYPE, g.n, 30, 56, -31, -72, -88, co.k, 12, 35, 118, 29, 37, 36, 5, -15, KeyFactorySpi.x25519_type, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, TXLivePusher.SEI_MSG_TYPE, co.h, UTF8.S_P4A, 122, 47, Wson.BOOLEAN_TYPE_TRUE, 83, -77, 97, -81, 57, 53, -34, -51, co.j, -103, -84, -83, 114, 44, -35, -48, -121, -66, 94, -90, -20, 4, -58, 3, 52, -5, -37, 89, -74, -62, 1, -16, 90, -19, -89, Wson.BOOLEAN_TYPE_FALSE, PublicSuffixDatabase.EXCEPTION_MARKER, 127, -118, 39, -57, -64, 41, -41};
    public static final byte[] S2 = {-109, -39, -102, -75, -104, 34, 69, -4, -70, 106, -33, 2, -97, -36, 81, 89, 74, 23, 43, -62, -108, -12, -69, -93, 98, -28, KeyFactorySpi.Ed448_type, -44, -51, KeyFactorySpi.Ed25519_type, 22, -31, 73, 60, -64, -40, 92, -101, -83, -123, 83, -95, 122, -56, 45, co.k, -47, 114, -90, 44, -60, -29, 118, 120, -73, -76, 9, 59, db.l, 65, 76, -34, -78, -112, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, 78, SharedPreferencesNewImpl.FINISH_MARK, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, Wson.MAP_TYPE, 24, -105, -45, 54, -26, 72, 86, -127, -113, 119, -52, -100, -71, -30, -84, -72, 47, 21, -92, 124, -38, 56, 30, 11, 5, -42, 20, KeyFactorySpi.x25519_type, Wson.NUMBER_LONG_TYPE, 126, Wson.BOOLEAN_TYPE_FALSE, -3, -79, -27, UTF8.S_P4B, -81, 94, 51, -121, -55, -16, 93, 109, 63, -120, -115, -57, -9, 29, -23, -20, -19, g.n, 41, 39, -49, -103, -88, UTF8.S_P4A, 15, 55, 36, 40, 48, -107, -46, 62, Wson.ARRAY_TYPE, UTF8.S_P3B, -125, -77, Wson.NUMBER_INT_TYPE, 87, co.j, 7, 28, -118, PSSSigner.TRAILER_IMPLICIT, 32, -21, -50, -114, -85, -18, 49, -94, Wson.STRING_TYPE, -7, -54, 58, JSONLexer.EOI, -5, 13, -63, -2, -6, TXLivePusher.SEI_MSG_TYPE, KeyFactorySpi.x448_type, -67, -106, -35, 67, 82, -74, 8, -13, -82, -66, 25, -119, 50, 38, -80, -22, 75, Wson.NUMBER_DOUBLE_TYPE, -124, co.h, 107, -11, 121, -65, 1, 95, 117, 99, 27, 35, 61, 104, 42, Wson.NUMBER_BIG_DECIMAL_TYPE, -24, -111, -10, -1, 19, 88, -15, 71, 10, 127, -59, -89, -25, 97, 90, 6, Wson.NUMBER_FLOAT_TYPE, 68, 66, 4, -96, -37, 57, -122, 84, -86, -116, 52, PublicSuffixDatabase.EXCEPTION_MARKER, -117, -8, 12, Wson.BOOLEAN_TYPE_TRUE, Wson.NUMBER_BIG_INTEGER_TYPE};
    public static final byte[] S3 = {104, -115, -54, 77, Wson.STRING_TYPE, 75, 78, 42, -44, 82, 38, -77, 84, 30, 25, co.j, 34, 3, Wson.NUMBER_FLOAT_TYPE, 61, 45, 74, 83, -125, 19, -118, -73, -43, 37, 121, -11, -67, 88, 47, 13, 2, -19, 81, -98, 17, TXLivePusher.SEI_MSG_TYPE, 62, 85, 94, -47, 22, 60, Wson.BOOLEAN_TYPE_FALSE, KeyFactorySpi.Ed25519_type, 93, -13, 69, UTF8.S_P3B, -52, -24, -108, 86, 8, -50, JSONLexer.EOI, 58, -46, -31, -33, -75, 56, KeyFactorySpi.x25519_type, db.l, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, 20, -82, -18, -56, 72, -45, 48, -95, -110, 65, -79, 24, -60, 44, KeyFactorySpi.Ed448_type, 114, 68, 21, -3, 55, -66, 95, -86, -101, -120, -40, -85, -119, -100, -6, UTF8.S_P4B, -22, PSSSigner.TRAILER_IMPLICIT, 98, 12, 36, -90, -88, -20, Wson.NUMBER_BIG_INTEGER_TYPE, 32, -37, 124, 40, -35, -84, Wson.ARRAY_TYPE, 52, 126, 16, -15, Wson.MAP_TYPE, -113, 99, -96, 5, -102, 67, 119, PublicSuffixDatabase.EXCEPTION_MARKER, -65, 39, 9, -61, -97, -74, -41, 41, -62, -21, -64, -92, -117, -116, 29, -5, -1, -63, -78, -105, 46, -8, Wson.NUMBER_BIG_DECIMAL_TYPE, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, Wson.NUMBER_LONG_TYPE, -112, 0, -114, KeyFactorySpi.x448_type, UTF8.S_P4A, 1, -59, -38, 71, 63, -51, Wson.NUMBER_INT_TYPE, -94, -30, 122, -89, -58, -109, 15, 10, 6, -26, 43, -106, -93, 28, -81, 106, SharedPreferencesNewImpl.FINISH_MARK, -124, 57, -25, -80, co.h, -9, -2, -99, -121, 92, -127, 53, -34, -76, -91, -4, g.n, -17, -53, -69, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, Wson.BOOLEAN_TYPE_TRUE, -104, 59, 54, Wson.NUMBER_DOUBLE_TYPE, 109, -36, -16, 89, -87, 76, 23, 127, -111, -72, -55, 87, 27, co.k, 97};
    public static final byte[] T0 = {-92, -94, -87, -59, 78, -55, 3, -39, 126, 15, -46, -83, -25, -45, 39, Wson.ARRAY_TYPE, -29, -95, -24, -26, 124, 42, 85, 12, -122, 57, -41, -115, -72, SharedPreferencesNewImpl.FINISH_MARK, KeyFactorySpi.x448_type, 40, -51, -118, KeyFactorySpi.Ed25519_type, 86, 114, -7, -65, 79, Wson.STRING_TYPE, -23, -9, 87, 22, -84, UTF8.S_P4A, -64, -99, -73, 71, KeyFactorySpi.Ed448_type, UTF8.S_P4B, -60, Wson.BOOLEAN_TYPE_TRUE, 67, Wson.NUMBER_LONG_TYPE, co.j, -109, 119, -36, -50, 32, -116, -103, 95, 68, 1, -11, 30, -121, 94, 97, 44, 75, 29, -127, 21, -12, 35, -42, -22, -31, Wson.NUMBER_BIG_INTEGER_TYPE, -15, 127, -2, -38, 60, 7, 83, 106, -124, -100, -53, 2, -125, 51, -35, 53, -30, 89, 90, -104, -91, -110, Wson.NUMBER_DOUBLE_TYPE, 4, 6, 16, 77, 28, -105, 8, 49, -18, -85, 5, -81, 121, -96, 24, Wson.NUMBER_FLOAT_TYPE, 109, -4, -119, -44, -57, -1, -16, -49, 66, -111, -8, 104, 10, Wson.NUMBER_BIG_DECIMAL_TYPE, -114, -74, -3, -61, -17, 120, 76, -52, -98, 48, 46, PSSSigner.TRAILER_IMPLICIT, 11, 84, JSONLexer.EOI, -90, -69, 38, g.n, 72, -108, 50, 125, -89, 63, -82, 34, 61, Wson.BOOLEAN_TYPE_FALSE, -86, -10, 0, 93, -67, 74, co.k, 59, -76, 23, -117, -97, 118, -80, 36, -102, 37, 99, -37, -21, 122, 62, 92, -77, -79, 41, TXLivePusher.SEI_MSG_TYPE, -54, 88, KeyFactorySpi.x25519_type, -40, -88, 47, 117, -33, 20, -5, 19, 73, -120, -78, -20, -28, 52, 45, -106, -58, 58, -19, -107, db.l, -27, -123, 107, UTF8.S_P3B, PublicSuffixDatabase.EXCEPTION_MARKER, -101, 9, 25, 43, 82, -34, 69, -93, -6, 81, -62, -75, -47, -112, -71, -13, 55, -63, 13, -70, 65, 17, 56, Wson.MAP_TYPE, -66, -48, -43, Wson.NUMBER_INT_TYPE, 54, -56, 98, 27, co.h, -113};
    public static final byte[] T1 = {-125, TXLivePusher.SEI_MSG_TYPE, 42, -21, -23, -65, Wson.MAP_TYPE, -100, 52, -106, -115, -104, -71, Wson.NUMBER_INT_TYPE, -116, 41, 61, -120, 104, 6, 57, 17, 76, db.l, -96, 86, UTF8.S_P3B, -110, 21, PSSSigner.TRAILER_IMPLICIT, -77, -36, KeyFactorySpi.x448_type, -8, 38, -70, -66, -67, 49, -5, -61, -2, g.n, 97, -31, 122, 50, -46, KeyFactorySpi.Ed25519_type, 32, -95, 69, -20, -39, JSONLexer.EOI, 93, -76, -40, 9, -91, 85, -114, 55, 118, -87, Wson.NUMBER_BIG_INTEGER_TYPE, 16, 23, 54, Wson.NUMBER_BIG_DECIMAL_TYPE, -79, -107, 98, 89, Wson.BOOLEAN_TYPE_TRUE, -93, UTF8.S_P4A, 47, 75, -56, -48, -113, -51, -44, 60, -122, SharedPreferencesNewImpl.FINISH_MARK, 29, 35, -17, -12, 83, 25, 53, -26, 127, 94, -42, 121, 81, 34, 20, -9, 30, 74, 66, -101, 65, Wson.STRING_TYPE, 45, -63, 92, -90, -94, co.k, 46, -45, 40, -69, -55, -82, 106, -47, 90, 48, -112, -124, -7, -78, 88, -49, 126, -59, -53, -105, -28, 22, Wson.NUMBER_LONG_TYPE, -6, -80, 109, co.j, 82, -103, 13, 78, 3, -111, -62, 77, Wson.NUMBER_DOUBLE_TYPE, 119, -97, -35, -60, 73, -118, -102, 36, 56, -89, 87, -123, -57, 124, 125, -25, -10, -73, -84, 39, Wson.NUMBER_FLOAT_TYPE, -34, -33, 59, -41, -98, 43, 11, -43, 19, 117, -16, 114, -74, -99, 27, 1, 63, 68, -27, -121, -3, 7, -15, -85, -108, 24, -22, -4, 58, co.h, 95, 5, 84, -37, 0, -117, -29, 72, 12, -54, 120, -119, 10, -1, 62, Wson.ARRAY_TYPE, -127, -18, KeyFactorySpi.Ed448_type, -30, -38, 44, -72, -75, -52, KeyFactorySpi.x25519_type, -88, 107, -83, UTF8.S_P4B, -58, 8, 4, 2, -24, -11, 79, -92, -13, -64, -50, 67, 37, 28, PublicSuffixDatabase.EXCEPTION_MARKER, 51, 15, -81, 71, -19, Wson.BOOLEAN_TYPE_FALSE, 99, -109, -86};
    public static final byte[] T2 = {69, -44, 11, 67, -15, 114, -19, -92, -62, 56, -26, KeyFactorySpi.Ed448_type, -3, -74, 58, -107, UTF8.S_P4A, 68, 75, -30, Wson.BOOLEAN_TYPE_TRUE, 107, 30, 17, 90, -58, -76, -40, -91, -118, KeyFactorySpi.Ed25519_type, -93, -88, -6, 5, -39, -105, UTF8.S_P3B, -55, -112, -104, -113, -36, SharedPreferencesNewImpl.FINISH_MARK, 49, 44, 71, 106, -103, -82, -56, 127, -7, 79, 93, -106, KeyFactorySpi.x448_type, -12, -77, 57, PublicSuffixDatabase.EXCEPTION_MARKER, -38, -100, -123, -98, 59, -16, -65, -17, 6, -18, -27, 95, 32, 16, -52, 60, 84, 74, 82, -108, db.l, -64, 40, -10, 86, UTF8.S_P4B, -94, -29, 15, -20, -99, 36, -125, 126, -43, 124, -21, 24, -41, -51, -35, 120, -1, -37, -95, 9, -48, 118, -124, 117, -69, 29, JSONLexer.EOI, 47, -80, -2, -42, 52, 99, 53, -46, 42, 89, 109, 77, 119, -25, -114, 97, -49, -97, -50, 39, -11, g.n, -122, -57, -90, -5, -8, -121, -85, 98, 63, -33, 72, 0, 20, -102, -67, Wson.ARRAY_TYPE, 4, -110, 2, 37, Wson.NUMBER_BIG_DECIMAL_TYPE, 76, 83, 12, TXLivePusher.SEI_MSG_TYPE, 41, -81, 23, Wson.NUMBER_LONG_TYPE, 65, 48, -23, -109, 85, -9, -84, 104, 38, -60, 125, -54, 122, 62, -96, 55, 3, -63, 54, Wson.NUMBER_INT_TYPE, Wson.BOOLEAN_TYPE_FALSE, 8, 22, -89, PSSSigner.TRAILER_IMPLICIT, -59, -45, 34, -73, 19, Wson.NUMBER_FLOAT_TYPE, 50, -24, 87, -120, 43, -127, -78, 78, Wson.NUMBER_DOUBLE_TYPE, 28, -86, -111, 88, 46, -101, 92, 27, 81, Wson.STRING_TYPE, 66, 35, 1, KeyFactorySpi.x25519_type, -13, 13, -66, 61, 10, 45, co.j, Wson.NUMBER_BIG_INTEGER_TYPE, 51, 25, Wson.MAP_TYPE, 94, -22, -34, -117, -53, -87, -116, -115, -83, 73, co.h, -28, -70, -61, 21, -47, co.k, -119, -4, -79, -71, -75, 7, 121, -72, -31};
    public static final byte[] T3 = {-78, -74, 35, 17, -89, -120, -59, -90, 57, -113, -60, -24, Wson.STRING_TYPE, 34, 67, -61, co.h, 39, -51, 24, 81, 98, 45, -9, 92, db.l, 59, -3, -54, -101, 13, 15, 121, -116, 16, 76, Wson.BOOLEAN_TYPE_TRUE, 28, 10, -114, 124, -108, 7, -57, 94, 20, -95, PublicSuffixDatabase.EXCEPTION_MARKER, 87, UTF8.S_P4A, 78, -87, g.n, -39, -17, Wson.NUMBER_DOUBLE_TYPE, 65, -49, 60, -18, 46, 19, 41, -70, 52, 90, -82, -118, 97, 51, SharedPreferencesNewImpl.FINISH_MARK, -71, 85, -88, 21, 5, -10, 3, 6, 73, -75, 37, 9, 22, 12, 42, 56, -4, 32, -12, -27, 127, -41, 49, 43, Wson.BOOLEAN_TYPE_FALSE, KeyFactorySpi.x448_type, -1, 114, -122, -16, -93, 47, 120, 0, PSSSigner.TRAILER_IMPLICIT, -52, -30, -80, -15, 66, -76, 48, 95, UTF8.S_P4B, 4, -20, -91, -29, -117, -25, 29, -65, -124, Wson.MAP_TYPE, -26, -127, -8, -34, -40, -46, 23, -50, 75, 71, -42, Wson.NUMBER_INT_TYPE, Wson.NUMBER_LONG_TYPE, 25, -103, -102, 1, -77, -123, -79, -7, 89, -62, 55, -23, -56, -96, -19, 79, -119, 104, 109, -43, 38, -111, -121, 88, -67, -55, -104, -36, 117, -64, 118, -11, Wson.NUMBER_BIG_INTEGER_TYPE, 107, 126, -21, 82, -53, -47, Wson.ARRAY_TYPE, -97, 11, -37, UTF8.S_P3B, -110, JSONLexer.EOI, -6, -84, -28, -31, KeyFactorySpi.Ed448_type, co.j, Wson.NUMBER_BIG_DECIMAL_TYPE, -115, -105, -98, -107, -112, 93, -73, -63, -81, 84, -5, 2, co.k, 53, -69, 58, 77, -83, 44, 61, 86, 8, 27, 74, -109, 106, -85, -72, 122, TXLivePusher.SEI_MSG_TYPE, 125, -38, 63, -2, 62, -66, -22, -86, 68, -58, -48, 54, 72, KeyFactorySpi.Ed25519_type, -106, 119, 36, 83, -33, -13, -125, 40, 50, 69, 30, -92, -45, -94, Wson.NUMBER_FLOAT_TYPE, KeyFactorySpi.x25519_type, -100, -35, 99, -44, -99};
    public boolean forEncryption;
    public long[] internalState;
    public long[][] roundKeys;
    public int roundsAmount;
    public int wordsInBlock;
    public int wordsInKey;
    public long[] workingKey;

    public DSTU7624Engine(int i) throws IllegalArgumentException {
        if (i != 128 && i != 256 && i != 512) {
            throw new IllegalArgumentException("unsupported block length: only 128/256/512 are allowed");
        }
        int i2 = i >>> 6;
        this.wordsInBlock = i2;
        this.internalState = new long[i2];
    }

    private void addRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] + jArr[i2];
        }
    }

    private void decryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long jLittleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long jLittleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[][] jArr = this.roundKeys;
        int i3 = this.roundsAmount;
        long[] jArr2 = jArr[i3];
        long j = jLittleEndianToLong - jArr2[0];
        long j2 = jLittleEndianToLong2 - jArr2[1];
        while (true) {
            long jMixColumnInv = mixColumnInv(j);
            long jMixColumnInv2 = mixColumnInv(j2);
            int i4 = (int) jMixColumnInv;
            int i5 = (int) (jMixColumnInv >>> 32);
            int i6 = (int) jMixColumnInv2;
            int i7 = (int) (jMixColumnInv2 >>> 32);
            byte[] bArr3 = T0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = T1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = T2;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = T3;
            int i8 = (bArr6[i4 >>> 24] << 24) | ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8);
            long j3 = (((long) ((bArr6[i7 >>> 24] << 24) | (((bArr3[i7 & 255] & 255) | ((bArr4[(i7 >>> 8) & 255] & 255) << 8)) | ((bArr5[(i7 >>> 16) & 255] & 255) << 16)))) << 32) | (((long) i8) & 4294967295L);
            int i9 = (bArr6[i6 >>> 24] << 24) | (bArr3[i6 & 255] & 255) | ((bArr4[(i6 >>> 8) & 255] & 255) << 8) | ((bArr5[(i6 >>> 16) & 255] & 255) << 16);
            long j4 = (((long) ((bArr6[i5 >>> 24] << 24) | (((bArr3[i5 & 255] & 255) | ((bArr4[(i5 >>> 8) & 255] & 255) << 8)) | ((bArr5[(i5 >>> 16) & 255] & 255) << 16)))) << 32) | (((long) i9) & 4294967295L);
            i3--;
            if (i3 == 0) {
                long[] jArr3 = this.roundKeys[0];
                long j5 = j3 - jArr3[0];
                long j6 = j4 - jArr3[1];
                Pack.longToLittleEndian(j5, bArr2, i2);
                Pack.longToLittleEndian(j6, bArr2, i2 + 8);
                return;
            }
            long[] jArr4 = this.roundKeys[i3];
            long j7 = j3 ^ jArr4[0];
            long j8 = j4 ^ jArr4[1];
            j = j7;
            j2 = j8;
        }
    }

    private void encryptBlock_128(byte[] bArr, int i, byte[] bArr2, int i2) {
        long jLittleEndianToLong = Pack.littleEndianToLong(bArr, i);
        long jLittleEndianToLong2 = Pack.littleEndianToLong(bArr, i + 8);
        long[] jArr = this.roundKeys[0];
        long j = jLittleEndianToLong + jArr[0];
        long j2 = jLittleEndianToLong2 + jArr[1];
        int i3 = 0;
        while (true) {
            int i4 = (int) j;
            int i5 = (int) (j >>> 32);
            int i6 = (int) j2;
            int i7 = (int) (j2 >>> 32);
            byte[] bArr3 = S0;
            byte b = bArr3[i4 & 255];
            byte[] bArr4 = S1;
            byte b2 = bArr4[(i4 >>> 8) & 255];
            byte[] bArr5 = S2;
            byte b3 = bArr5[(i4 >>> 16) & 255];
            byte[] bArr6 = S3;
            int i8 = ((b3 & 255) << 16) | (b & 255) | ((b2 & 255) << 8) | (bArr6[i4 >>> 24] << 24);
            byte b4 = bArr3[i7 & 255];
            byte b5 = bArr4[(i7 >>> 8) & 255];
            byte b6 = bArr5[(i7 >>> 16) & 255];
            long j3 = (((long) ((bArr6[i7 >>> 24] << 24) | (((b4 & 255) | ((b5 & 255) << 8)) | ((b6 & 255) << 16)))) << 32) | (((long) i8) & 4294967295L);
            byte b7 = bArr3[i6 & 255];
            byte b8 = bArr4[(i6 >>> 8) & 255];
            byte b9 = bArr5[(i6 >>> 16) & 255];
            int i9 = (bArr6[i6 >>> 24] << 24) | (b7 & 255) | ((b8 & 255) << 8) | ((b9 & 255) << 16);
            byte b10 = bArr3[i5 & 255];
            byte b11 = bArr4[(i5 >>> 8) & 255];
            byte b12 = bArr5[(i5 >>> 16) & 255];
            int i10 = (bArr6[i5 >>> 24] << 24) | (b10 & 255) | ((b11 & 255) << 8) | ((b12 & 255) << 16);
            long jMixColumn = mixColumn(j3);
            long jMixColumn2 = mixColumn((((long) i10) << 32) | (((long) i9) & 4294967295L));
            i3++;
            int i11 = this.roundsAmount;
            if (i3 == i11) {
                long[] jArr2 = this.roundKeys[i11];
                long j4 = jMixColumn + jArr2[0];
                long j5 = jMixColumn2 + jArr2[1];
                Pack.longToLittleEndian(j4, bArr2, i2);
                Pack.longToLittleEndian(j5, bArr2, i2 + 8);
                return;
            }
            long[] jArr3 = this.roundKeys[i3];
            long j6 = jMixColumn ^ jArr3[0];
            j2 = jMixColumn2 ^ jArr3[1];
            j = j6;
        }
    }

    private void invShiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j5) & (-281470681808896L);
            long j9 = j4 ^ j8;
            long j10 = j5 ^ j8;
            long j11 = (j6 ^ j7) & (-281470681808896L);
            long j12 = j6 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-4294967296L);
            long j15 = j9 ^ j14;
            long j16 = (j10 ^ j13) & 281474976645120L;
            jArr2[0] = j15;
            jArr2[1] = j10 ^ j16;
            jArr2[2] = j12 ^ j14;
            jArr2[3] = j16 ^ j13;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j17 = jArr3[0];
        long j18 = jArr3[1];
        long j19 = jArr3[2];
        long j20 = jArr3[3];
        long j21 = jArr3[4];
        long j22 = jArr3[5];
        long j23 = jArr3[6];
        long j24 = jArr3[7];
        long j25 = (j17 ^ j18) & (-71777214294589696L);
        long j26 = j17 ^ j25;
        long j27 = j18 ^ j25;
        long j28 = (j19 ^ j20) & (-71777214294589696L);
        long j29 = j19 ^ j28;
        long j30 = j20 ^ j28;
        long j31 = (j21 ^ j22) & (-71777214294589696L);
        long j32 = j21 ^ j31;
        long j33 = j22 ^ j31;
        long j34 = (j23 ^ j24) & (-71777214294589696L);
        long j35 = j23 ^ j34;
        long j36 = j24 ^ j34;
        long j37 = (j26 ^ j29) & (-281470681808896L);
        long j38 = j26 ^ j37;
        long j39 = j29 ^ j37;
        long j40 = (j27 ^ j30) & 72056494543077120L;
        long j41 = j27 ^ j40;
        long j42 = j30 ^ j40;
        long j43 = (j32 ^ j35) & (-281470681808896L);
        long j44 = j32 ^ j43;
        long j45 = j35 ^ j43;
        long j46 = (j33 ^ j36) & 72056494543077120L;
        long j47 = j33 ^ j46;
        long j48 = j36 ^ j46;
        long j49 = (j38 ^ j44) & (-4294967296L);
        long j50 = j38 ^ j49;
        long j51 = j44 ^ j49;
        long j52 = (j41 ^ j47) & 72057594021150720L;
        long j53 = j41 ^ j52;
        long j54 = (j39 ^ j45) & 281474976645120L;
        long j55 = j39 ^ j54;
        long j56 = j54 ^ j45;
        long j57 = (j42 ^ j48) & 1099511627520L;
        jArr3[0] = j50;
        jArr3[1] = j53;
        jArr3[2] = j55;
        jArr3[3] = j42 ^ j57;
        jArr3[4] = j51;
        jArr3[5] = j47 ^ j52;
        jArr3[6] = j56;
        jArr3[7] = j48 ^ j57;
    }

    private void invSubBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = T0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = T1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = T2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            byte[] bArr4 = T3;
            jArr[i] = (((long) ((bArr4[i2 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16))) & 4294967295L) | (((long) ((((bArr[i3 & 255] & 255) | ((bArr2[(i3 >>> 8) & 255] & 255) << 8)) | ((bArr3[(i3 >>> 16) & 255] & 255) << 16)) | (bArr4[i3 >>> 24] << 24))) << 32);
        }
    }

    public static long mixColumn(long j) {
        long jMulX = mulX(j);
        long jRotate = rotate(8, j) ^ j;
        long jRotate2 = (jRotate ^ rotate(16, jRotate)) ^ rotate(48, j);
        return ((rotate(32, mulX2((j ^ jRotate2) ^ jMulX)) ^ jRotate2) ^ rotate(40, jMulX)) ^ rotate(48, jMulX);
    }

    public static long mixColumnInv(long j) {
        long jRotate = rotate(8, j) ^ j;
        long jRotate2 = (jRotate ^ rotate(32, jRotate)) ^ rotate(48, j);
        long j2 = jRotate2 ^ j;
        long jRotate3 = rotate(48, j);
        long jRotate4 = rotate(56, j);
        long jMulX = mulX(j2 ^ jRotate4) ^ rotate(56, j2);
        long jMulX2 = mulX(rotate(40, mulX(jMulX) ^ j) ^ (rotate(16, j2) ^ j)) ^ (j2 ^ jRotate3);
        return mulX(rotate(40, ((j ^ rotate(32, j2)) ^ jRotate4) ^ mulX(((jRotate3 ^ (rotate(24, j) ^ j2)) ^ jRotate4) ^ mulX(mulX(jMulX2) ^ rotate(16, jRotate2))))) ^ jRotate2;
    }

    private void mixColumns() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumn(jArr[i]);
        }
    }

    private void mixColumnsInv() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            jArr[i] = mixColumnInv(jArr[i]);
        }
    }

    public static long mulX(long j) {
        return (((j & (-9187201950435737472L)) >>> 7) * 29) ^ ((9187201950435737471L & j) << 1);
    }

    public static long mulX2(long j) {
        return (((j & 4629771061636907072L) >>> 6) * 29) ^ (((4557430888798830399L & j) << 2) ^ ((((-9187201950435737472L) & j) >>> 6) * 29));
    }

    public static long rotate(int i, long j) {
        return (j << (-i)) | (j >>> i);
    }

    private void rotateLeft(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        if (i == 2) {
            long j = jArr[0];
            long j2 = jArr[1];
            jArr2[0] = (j >>> 56) | (j2 << 8);
            jArr2[1] = (j << 8) | (j2 >>> 56);
            return;
        }
        if (i == 4) {
            long j3 = jArr[0];
            long j4 = jArr[1];
            long j5 = jArr[2];
            long j6 = jArr[3];
            jArr2[0] = (j4 >>> 24) | (j5 << 40);
            jArr2[1] = (j5 >>> 24) | (j6 << 40);
            jArr2[2] = (j6 >>> 24) | (j3 << 40);
            jArr2[3] = (j3 >>> 24) | (j4 << 40);
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long j7 = jArr[0];
        long j8 = jArr[1];
        long j9 = jArr[2];
        long j10 = jArr[3];
        long j11 = jArr[4];
        long j12 = jArr[5];
        long j13 = jArr[6];
        long j14 = jArr[7];
        jArr2[0] = (j9 >>> 24) | (j10 << 40);
        jArr2[1] = (j10 >>> 24) | (j11 << 40);
        jArr2[2] = (j11 >>> 24) | (j12 << 40);
        jArr2[3] = (j12 >>> 24) | (j13 << 40);
        jArr2[4] = (j13 >>> 24) | (j14 << 40);
        jArr2[5] = (j14 >>> 24) | (j7 << 40);
        jArr2[6] = (j7 >>> 24) | (j8 << 40);
        jArr2[7] = (j8 >>> 24) | (j9 << 40);
    }

    private void shiftRows() {
        int i = this.wordsInBlock;
        if (i == 2) {
            long[] jArr = this.internalState;
            long j = jArr[0];
            long j2 = jArr[1];
            long j3 = (-4294967296L) & (j ^ j2);
            jArr[0] = j ^ j3;
            jArr[1] = j3 ^ j2;
            return;
        }
        if (i == 4) {
            long[] jArr2 = this.internalState;
            long j4 = jArr2[0];
            long j5 = jArr2[1];
            long j6 = jArr2[2];
            long j7 = jArr2[3];
            long j8 = (j4 ^ j6) & (-4294967296L);
            long j9 = j4 ^ j8;
            long j10 = j6 ^ j8;
            long j11 = (j5 ^ j7) & 281474976645120L;
            long j12 = j5 ^ j11;
            long j13 = j7 ^ j11;
            long j14 = (j9 ^ j12) & (-281470681808896L);
            long j15 = (j10 ^ j13) & (-281470681808896L);
            jArr2[0] = j9 ^ j14;
            jArr2[1] = j12 ^ j14;
            jArr2[2] = j10 ^ j15;
            jArr2[3] = j13 ^ j15;
            return;
        }
        if (i != 8) {
            throw new IllegalStateException("unsupported block length: only 128/256/512 are allowed");
        }
        long[] jArr3 = this.internalState;
        long j16 = jArr3[0];
        long j17 = jArr3[1];
        long j18 = jArr3[2];
        long j19 = jArr3[3];
        long j20 = jArr3[4];
        long j21 = jArr3[5];
        long j22 = jArr3[6];
        long j23 = jArr3[7];
        long j24 = (j16 ^ j20) & (-4294967296L);
        long j25 = j16 ^ j24;
        long j26 = j20 ^ j24;
        long j27 = (j17 ^ j21) & 72057594021150720L;
        long j28 = j17 ^ j27;
        long j29 = j21 ^ j27;
        long j30 = (j18 ^ j22) & 281474976645120L;
        long j31 = j18 ^ j30;
        long j32 = j22 ^ j30;
        long j33 = (j19 ^ j23) & 1099511627520L;
        long j34 = j19 ^ j33;
        long j35 = j23 ^ j33;
        long j36 = (j25 ^ j31) & (-281470681808896L);
        long j37 = j25 ^ j36;
        long j38 = j31 ^ j36;
        long j39 = (j28 ^ j34) & 72056494543077120L;
        long j40 = j28 ^ j39;
        long j41 = j34 ^ j39;
        long j42 = (j26 ^ j32) & (-281470681808896L);
        long j43 = j26 ^ j42;
        long j44 = j32 ^ j42;
        long j45 = (j29 ^ j35) & 72056494543077120L;
        long j46 = j29 ^ j45;
        long j47 = j35 ^ j45;
        long j48 = (j37 ^ j40) & (-71777214294589696L);
        long j49 = j37 ^ j48;
        long j50 = j40 ^ j48;
        long j51 = (j38 ^ j41) & (-71777214294589696L);
        long j52 = j38 ^ j51;
        long j53 = j41 ^ j51;
        long j54 = (j43 ^ j46) & (-71777214294589696L);
        long j55 = j43 ^ j54;
        long j56 = j46 ^ j54;
        long j57 = (j44 ^ j47) & (-71777214294589696L);
        jArr3[0] = j49;
        jArr3[1] = j50;
        jArr3[2] = j52;
        jArr3[3] = j53;
        jArr3[4] = j55;
        jArr3[5] = j56;
        jArr3[6] = j44 ^ j57;
        jArr3[7] = j47 ^ j57;
    }

    private void subBytes() {
        for (int i = 0; i < this.wordsInBlock; i++) {
            long[] jArr = this.internalState;
            long j = jArr[i];
            int i2 = (int) j;
            int i3 = (int) (j >>> 32);
            byte[] bArr = S0;
            byte b = bArr[i2 & 255];
            byte[] bArr2 = S1;
            byte b2 = bArr2[(i2 >>> 8) & 255];
            byte[] bArr3 = S2;
            byte b3 = bArr3[(i2 >>> 16) & 255];
            byte[] bArr4 = S3;
            jArr[i] = (((long) ((bArr4[i2 >>> 24] << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16))) & 4294967295L) | (((long) ((((bArr[i3 & 255] & 255) | ((bArr2[(i3 >>> 8) & 255] & 255) << 8)) | ((bArr3[(i3 >>> 16) & 255] & 255) << 16)) | (bArr4[i3 >>> 24] << 24))) << 32);
        }
    }

    private void subRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] - jArr[i2];
        }
    }

    private void workingKeyExpandEven(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        int i3 = this.wordsInKey;
        long[] jArr3 = new long[i3];
        long[] jArr4 = new long[this.wordsInBlock];
        System.arraycopy(jArr, 0, jArr3, 0, i3);
        long j = 281479271743489L;
        int i4 = 0;
        while (true) {
            for (int i5 = 0; i5 < this.wordsInBlock; i5++) {
                jArr4[i5] = jArr2[i5] + j;
            }
            for (int i6 = 0; i6 < this.wordsInBlock; i6++) {
                this.internalState[i6] = jArr3[i6] + jArr4[i6];
            }
            subBytes();
            shiftRows();
            mixColumns();
            for (int i7 = 0; i7 < this.wordsInBlock; i7++) {
                long[] jArr5 = this.internalState;
                jArr5[i7] = jArr5[i7] ^ jArr4[i7];
            }
            subBytes();
            shiftRows();
            mixColumns();
            int i8 = 0;
            while (true) {
                i = this.wordsInBlock;
                if (i8 >= i) {
                    break;
                }
                long[] jArr6 = this.internalState;
                jArr6[i8] = jArr6[i8] + jArr4[i8];
                i8++;
            }
            System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i);
            if (this.roundsAmount == i4) {
                return;
            }
            if (this.wordsInBlock != this.wordsInKey) {
                i4 += 2;
                j <<= 1;
                for (int i9 = 0; i9 < this.wordsInBlock; i9++) {
                    jArr4[i9] = jArr2[i9] + j;
                }
                int i10 = 0;
                while (true) {
                    int i11 = this.wordsInBlock;
                    if (i10 >= i11) {
                        break;
                    }
                    this.internalState[i10] = jArr3[i11 + i10] + jArr4[i10];
                    i10++;
                }
                subBytes();
                shiftRows();
                mixColumns();
                for (int i12 = 0; i12 < this.wordsInBlock; i12++) {
                    long[] jArr7 = this.internalState;
                    jArr7[i12] = jArr7[i12] ^ jArr4[i12];
                }
                subBytes();
                shiftRows();
                mixColumns();
                int i13 = 0;
                while (true) {
                    i2 = this.wordsInBlock;
                    if (i13 >= i2) {
                        break;
                    }
                    long[] jArr8 = this.internalState;
                    jArr8[i13] = jArr8[i13] + jArr4[i13];
                    i13++;
                }
                System.arraycopy(this.internalState, 0, this.roundKeys[i4], 0, i2);
                if (this.roundsAmount == i4) {
                    return;
                }
            }
            i4 += 2;
            j <<= 1;
            long j2 = jArr3[0];
            for (int i14 = 1; i14 < i3; i14++) {
                jArr3[i14 - 1] = jArr3[i14];
            }
            jArr3[i3 - 1] = j2;
        }
    }

    private void workingKeyExpandKT(long[] jArr, long[] jArr2) {
        int i = this.wordsInBlock;
        long[] jArr3 = new long[i];
        long[] jArr4 = new long[i];
        long[] jArr5 = new long[i];
        this.internalState = jArr5;
        long j = jArr5[0];
        int i2 = this.wordsInKey;
        jArr5[0] = j + ((long) (i + i2 + 1));
        System.arraycopy(jArr, 0, jArr3, 0, i);
        if (i == i2) {
            System.arraycopy(jArr, 0, jArr4, 0, i);
        } else {
            int i3 = this.wordsInBlock;
            System.arraycopy(jArr, i3, jArr4, 0, i3);
        }
        int i4 = 0;
        while (true) {
            long[] jArr6 = this.internalState;
            if (i4 >= jArr6.length) {
                break;
            }
            jArr6[i4] = jArr6[i4] + jArr3[i4];
            i4++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i5 = 0;
        while (true) {
            long[] jArr7 = this.internalState;
            if (i5 >= jArr7.length) {
                break;
            }
            jArr7[i5] = jArr7[i5] ^ jArr4[i5];
            i5++;
        }
        subBytes();
        shiftRows();
        mixColumns();
        int i6 = 0;
        while (true) {
            long[] jArr8 = this.internalState;
            if (i6 >= jArr8.length) {
                subBytes();
                shiftRows();
                mixColumns();
                System.arraycopy(this.internalState, 0, jArr2, 0, this.wordsInBlock);
                return;
            }
            jArr8[i6] = jArr8[i6] + jArr3[i6];
            i6++;
        }
    }

    private void workingKeyExpandOdd() {
        for (int i = 1; i < this.roundsAmount; i += 2) {
            long[][] jArr = this.roundKeys;
            rotateLeft(jArr[i - 1], jArr[i]);
        }
    }

    private void xorRoundKey(int i) {
        long[] jArr = this.roundKeys[i];
        for (int i2 = 0; i2 < this.wordsInBlock; i2++) {
            long[] jArr2 = this.internalState;
            jArr2[i2] = jArr2[i2] ^ jArr[i2];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "DSTU7624";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.wordsInBlock << 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005b A[LOOP:0: B:26:0x0056->B:28:0x005b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064 A[EDGE_INSN: B:37:0x0064->B:29:0x0064 BREAK  A[LOOP:0: B:26:0x0056->B:28:0x005b], SYNTHETIC] */
    @Override // org.bouncycastle.crypto.BlockCipher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(boolean r5, org.bouncycastle.crypto.CipherParameters r6) throws java.lang.IllegalArgumentException {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.bouncycastle.crypto.params.KeyParameter
            if (r0 == 0) goto L8c
            r4.forEncryption = r5
            org.bouncycastle.crypto.params.KeyParameter r6 = (org.bouncycastle.crypto.params.KeyParameter) r6
            byte[] r5 = r6.getKey()
            int r6 = r5.length
            int r6 = r6 << 3
            int r0 = r4.wordsInBlock
            int r0 = r0 << 6
            r1 = 512(0x200, float:7.17E-43)
            r2 = 256(0x100, float:3.59E-43)
            r3 = 128(0x80, float:1.8E-43)
            if (r6 == r3) goto L28
            if (r6 == r2) goto L28
            if (r6 != r1) goto L20
            goto L28
        L20:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "unsupported key length: only 128/256/512 are allowed"
            r5.<init>(r6)
            throw r5
        L28:
            if (r6 == r0) goto L37
            int r0 = r0 * 2
            if (r6 != r0) goto L2f
            goto L37
        L2f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Unsupported key length"
            r5.<init>(r6)
            throw r5
        L37:
            if (r6 == r3) goto L44
            if (r6 == r2) goto L41
            if (r6 == r1) goto L3e
            goto L48
        L3e:
            r0 = 18
            goto L46
        L41:
            r0 = 14
            goto L46
        L44:
            r0 = 10
        L46:
            r4.roundsAmount = r0
        L48:
            int r0 = r6 >>> 6
            r4.wordsInKey = r0
            int r0 = r4.roundsAmount
            int r0 = r0 + 1
            long[][] r0 = new long[r0][]
            r4.roundKeys = r0
            r0 = 0
            r1 = 0
        L56:
            long[][] r2 = r4.roundKeys
            int r3 = r2.length
            if (r1 >= r3) goto L64
            int r3 = r4.wordsInBlock
            long[] r3 = new long[r3]
            r2[r1] = r3
            int r1 = r1 + 1
            goto L56
        L64:
            int r1 = r4.wordsInKey
            long[] r1 = new long[r1]
            r4.workingKey = r1
            int r2 = r5.length
            int r6 = r6 >>> 3
            if (r2 != r6) goto L84
            org.bouncycastle.util.Pack.littleEndianToLong(r5, r0, r1)
            int r5 = r4.wordsInBlock
            long[] r5 = new long[r5]
            long[] r6 = r4.workingKey
            r4.workingKeyExpandKT(r6, r5)
            long[] r6 = r4.workingKey
            r4.workingKeyExpandEven(r6, r5)
            r4.workingKeyExpandOdd()
            return
        L84:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Invalid key parameter passed to DSTU7624Engine init"
            r5.<init>(r6)
            throw r5
        L8c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Invalid parameter passed to DSTU7624Engine init"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.DSTU7624Engine.init(boolean, org.bouncycastle.crypto.CipherParameters):void");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException, DataLengthException {
        int i3;
        if (this.workingKey == null) {
            throw new IllegalStateException("DSTU7624Engine not initialised");
        }
        if (getBlockSize() + i > bArr.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (getBlockSize() + i2 > bArr2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        int i4 = 0;
        if (this.forEncryption) {
            if (this.wordsInBlock != 2) {
                Pack.littleEndianToLong(bArr, i, this.internalState);
                addRoundKey(0);
                while (true) {
                    subBytes();
                    shiftRows();
                    mixColumns();
                    i4++;
                    i3 = this.roundsAmount;
                    if (i4 == i3) {
                        break;
                    }
                    xorRoundKey(i4);
                }
                addRoundKey(i3);
                Pack.longToLittleEndian(this.internalState, bArr2, i2);
            } else {
                encryptBlock_128(bArr, i, bArr2, i2);
            }
        } else if (this.wordsInBlock != 2) {
            Pack.littleEndianToLong(bArr, i, this.internalState);
            subRoundKey(this.roundsAmount);
            int i5 = this.roundsAmount;
            while (true) {
                mixColumnsInv();
                invShiftRows();
                invSubBytes();
                i5--;
                if (i5 == 0) {
                    break;
                }
                xorRoundKey(i5);
            }
            subRoundKey(0);
            Pack.longToLittleEndian(this.internalState, bArr2, i2);
        } else {
            decryptBlock_128(bArr, i, bArr2, i2);
        }
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.internalState, 0L);
    }
}
