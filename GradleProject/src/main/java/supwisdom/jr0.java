package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import supwisdom.oq0;

/* JADX INFO: compiled from: Reader.java */
/* JADX INFO: loaded from: classes.dex */
public interface jr0 {
    long a() throws IOException;

    @Deprecated
    <T> T a(Class<T> cls, xp0 xp0Var) throws IOException;

    @Deprecated
    <T> T a(lr0<T> lr0Var, xp0 xp0Var) throws IOException;

    void a(List<Integer> list) throws IOException;

    <T> void a(List<T> list, lr0<T> lr0Var, xp0 xp0Var) throws IOException;

    <K, V> void a(Map<K, V> map, oq0.a<K, V> aVar, xp0 xp0Var) throws IOException;

    long b() throws IOException;

    <T> T b(Class<T> cls, xp0 xp0Var) throws IOException;

    <T> T b(lr0<T> lr0Var, xp0 xp0Var) throws IOException;

    void b(List<Integer> list) throws IOException;

    @Deprecated
    <T> void b(List<T> list, lr0<T> lr0Var, xp0 xp0Var) throws IOException;

    int c() throws IOException;

    void c(List<Long> list) throws IOException;

    void d(List<Integer> list) throws IOException;

    boolean d() throws IOException;

    long e() throws IOException;

    void e(List<Long> list) throws IOException;

    int f() throws IOException;

    void f(List<Long> list) throws IOException;

    int g() throws IOException;

    void g(List<Long> list) throws IOException;

    int getTag();

    int h() throws IOException;

    void h(List<Integer> list) throws IOException;

    long i() throws IOException;

    void i(List<Integer> list) throws IOException;

    String j() throws IOException;

    void j(List<Integer> list) throws IOException;

    int k() throws IOException;

    void k(List<Boolean> list) throws IOException;

    ByteString l() throws IOException;

    void l(List<String> list) throws IOException;

    int m() throws IOException;

    void m(List<String> list) throws IOException;

    void n(List<Float> list) throws IOException;

    boolean n() throws IOException;

    int o() throws IOException;

    void o(List<ByteString> list) throws IOException;

    long p() throws IOException;

    void p(List<Double> list) throws IOException;

    String q() throws IOException;

    void q(List<Long> list) throws IOException;

    double readDouble() throws IOException;

    float readFloat() throws IOException;
}
