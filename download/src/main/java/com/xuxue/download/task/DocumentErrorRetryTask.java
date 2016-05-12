package com.xuxue.download.task;

/**
 * Created by HanHan on 2016/5/6.
 */
public class DocumentErrorRetryTask extends DocumentTask implements ErrorRetryTask {

    private int maxError;

    private int errorTimes;

    @Override
    public int getMaxError() {
        return maxError;
    }


    @Override
    public void setMaxError(int maxError) {
        this.maxError = maxError;
    }

    @Override
    public int getErrorTimes() {
        return errorTimes;
    }

    @Override
    public void setErrorTimes(int times) {
        this.errorTimes = times;
    }

    @Override
    public void addErrorTimes() {
        errorTimes += 1;
    }

    @Override
    public boolean isEnabled() {
        return errorTimes <= maxError;
    }
}
