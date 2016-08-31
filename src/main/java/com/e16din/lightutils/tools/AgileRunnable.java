package com.e16din.lightutils.tools;

public abstract class AgileRunnable implements Runnable {

    private boolean mCanceled;

    public void cancel() {
        mCanceled = true;
    }

    @Override
    public void run() {
        if (!mCanceled) {
            onRun();
        }
    }

    public abstract void onRun();

    public boolean isCanceled() {
        return mCanceled;
    }
}