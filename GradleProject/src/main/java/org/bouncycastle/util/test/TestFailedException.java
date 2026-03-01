package org.bouncycastle.util.test;

/* JADX INFO: loaded from: classes3.dex */
public class TestFailedException extends RuntimeException {
    public TestResult _result;

    public TestFailedException(TestResult testResult) {
        this._result = testResult;
    }

    public TestResult getResult() {
        return this._result;
    }
}
