package com.xuxue.download.task;

/**
 * Created by HanHan on 2016/5/6.
 */
public interface ErrorRetryTask extends Task {

    int getMaxError();

    void setMaxError(int maxError);

    int getErrorTimes();

    void setErrorTimes(int times);

    void addErrorTimes();

    boolean isEnabled();

}
