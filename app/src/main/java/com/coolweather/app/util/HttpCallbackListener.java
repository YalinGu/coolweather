package com.coolweather.app.util;

/**
 * Created by GuYL on 2016/4/15.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
