package retrofit2;

import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import supwisdom.bt1;
import supwisdom.dt1;
import supwisdom.et1;
import supwisdom.gs1;
import supwisdom.hs1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes3.dex */
public final class OkHttpCall<T> implements Call<T> {
    public final Object[] args;
    public final gs1.a callFactory;
    public volatile boolean canceled;

    @GuardedBy("this")
    @Nullable
    public Throwable creationFailure;

    @GuardedBy("this")
    public boolean executed;

    @GuardedBy("this")
    @Nullable
    public gs1 rawCall;
    public final RequestFactory requestFactory;
    public final Converter<et1, T> responseConverter;

    public static final class ExceptionCatchingResponseBody extends et1 {
        public final et1 delegate;
        public final BufferedSource delegateSource;

        @Nullable
        public IOException thrownException;

        public ExceptionCatchingResponseBody(et1 et1Var) {
            this.delegate = et1Var;
            this.delegateSource = Okio.buffer(new ForwardingSource(et1Var.source()) { // from class: retrofit2.OkHttpCall.ExceptionCatchingResponseBody.1
                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer buffer, long j) throws IOException {
                    try {
                        return super.read(buffer, j);
                    } catch (IOException e2) {
                        ExceptionCatchingResponseBody.this.thrownException = e2;
                        throw e2;
                    }
                }
            });
        }

        @Override // supwisdom.et1, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // supwisdom.et1
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // supwisdom.et1
        public xs1 contentType() {
            return this.delegate.contentType();
        }

        @Override // supwisdom.et1
        public BufferedSource source() {
            return this.delegateSource;
        }

        public void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    public static final class NoContentResponseBody extends et1 {
        public final long contentLength;

        @Nullable
        public final xs1 contentType;

        public NoContentResponseBody(@Nullable xs1 xs1Var, long j) {
            this.contentType = xs1Var;
            this.contentLength = j;
        }

        @Override // supwisdom.et1
        public long contentLength() {
            return this.contentLength;
        }

        @Override // supwisdom.et1
        public xs1 contentType() {
            return this.contentType;
        }

        @Override // supwisdom.et1
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    public OkHttpCall(RequestFactory requestFactory, Object[] objArr, gs1.a aVar, Converter<et1, T> converter) {
        this.requestFactory = requestFactory;
        this.args = objArr;
        this.callFactory = aVar;
        this.responseConverter = converter;
    }

    private gs1 createRawCall() throws IOException {
        gs1 gs1VarA = this.callFactory.a(this.requestFactory.create(this.args));
        if (gs1VarA != null) {
            return gs1VarA;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    @Override // retrofit2.Call
    public void cancel() {
        gs1 gs1Var;
        this.canceled = true;
        synchronized (this) {
            gs1Var = this.rawCall;
        }
        if (gs1Var != null) {
            gs1Var.cancel();
        }
    }

    @Override // retrofit2.Call
    public void enqueue(final Callback<T> callback) {
        gs1 gs1Var;
        Throwable th;
        Utils.checkNotNull(callback, "callback == null");
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            gs1Var = this.rawCall;
            th = this.creationFailure;
            if (gs1Var == null && th == null) {
                try {
                    gs1 gs1VarCreateRawCall = createRawCall();
                    this.rawCall = gs1VarCreateRawCall;
                    gs1Var = gs1VarCreateRawCall;
                } catch (Throwable th2) {
                    th = th2;
                    Utils.throwIfFatal(th);
                    this.creationFailure = th;
                }
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            gs1Var.cancel();
        }
        gs1Var.a(new hs1() { // from class: retrofit2.OkHttpCall.1
            private void callFailure(Throwable th3) {
                try {
                    callback.onFailure(OkHttpCall.this, th3);
                } catch (Throwable th4) {
                    Utils.throwIfFatal(th4);
                    th4.printStackTrace();
                }
            }

            @Override // supwisdom.hs1
            public void onFailure(gs1 gs1Var2, IOException iOException) {
                callFailure(iOException);
            }

            @Override // supwisdom.hs1
            public void onResponse(gs1 gs1Var2, dt1 dt1Var) {
                try {
                    try {
                        callback.onResponse(OkHttpCall.this, OkHttpCall.this.parseResponse(dt1Var));
                    } catch (Throwable th3) {
                        Utils.throwIfFatal(th3);
                        th3.printStackTrace();
                    }
                } catch (Throwable th4) {
                    Utils.throwIfFatal(th4);
                    callFailure(th4);
                }
            }
        });
    }

    @Override // retrofit2.Call
    public Response<T> execute() throws IOException {
        gs1 gs1VarCreateRawCall;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            if (this.creationFailure != null) {
                if (this.creationFailure instanceof IOException) {
                    throw ((IOException) this.creationFailure);
                }
                if (this.creationFailure instanceof RuntimeException) {
                    throw ((RuntimeException) this.creationFailure);
                }
                throw ((Error) this.creationFailure);
            }
            gs1VarCreateRawCall = this.rawCall;
            if (gs1VarCreateRawCall == null) {
                try {
                    gs1VarCreateRawCall = createRawCall();
                    this.rawCall = gs1VarCreateRawCall;
                } catch (IOException | Error | RuntimeException e2) {
                    Utils.throwIfFatal(e2);
                    this.creationFailure = e2;
                    throw e2;
                }
            }
        }
        if (this.canceled) {
            gs1VarCreateRawCall.cancel();
        }
        return parseResponse(gs1VarCreateRawCall.execute());
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            if (this.rawCall == null || !this.rawCall.isCanceled()) {
                z = false;
            }
        }
        return z;
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public Response<T> parseResponse(dt1 dt1Var) throws IOException {
        et1 et1VarA = dt1Var.a();
        dt1.a aVarH = dt1Var.h();
        aVarH.a(new NoContentResponseBody(et1VarA.contentType(), et1VarA.contentLength()));
        dt1 dt1VarA = aVarH.a();
        int iC = dt1VarA.c();
        if (iC < 200 || iC >= 300) {
            try {
                return Response.error(Utils.buffer(et1VarA), dt1VarA);
            } finally {
                et1VarA.close();
            }
        }
        if (iC == 204 || iC == 205) {
            et1VarA.close();
            return Response.success((Object) null, dt1VarA);
        }
        ExceptionCatchingResponseBody exceptionCatchingResponseBody = new ExceptionCatchingResponseBody(et1VarA);
        try {
            return Response.success(this.responseConverter.convert(exceptionCatchingResponseBody), dt1VarA);
        } catch (RuntimeException e2) {
            exceptionCatchingResponseBody.throwIfCaught();
            throw e2;
        }
    }

    @Override // retrofit2.Call
    public synchronized bt1 request() {
        gs1 gs1Var = this.rawCall;
        if (gs1Var != null) {
            return gs1Var.request();
        }
        if (this.creationFailure != null) {
            if (this.creationFailure instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.creationFailure);
            }
            if (this.creationFailure instanceof RuntimeException) {
                throw ((RuntimeException) this.creationFailure);
            }
            throw ((Error) this.creationFailure);
        }
        try {
            gs1 gs1VarCreateRawCall = createRawCall();
            this.rawCall = gs1VarCreateRawCall;
            return gs1VarCreateRawCall.request();
        } catch (IOException e2) {
            this.creationFailure = e2;
            throw new RuntimeException("Unable to create request.", e2);
        } catch (Error e3) {
            e = e3;
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        } catch (RuntimeException e4) {
            e = e4;
            Utils.throwIfFatal(e);
            this.creationFailure = e;
            throw e;
        }
    }

    @Override // retrofit2.Call
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.requestFactory, this.args, this.callFactory, this.responseConverter);
    }
}
