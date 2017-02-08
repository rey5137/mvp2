package com.rey.mvp2;

/**
 * Created by Rey on 2/8/2017.
 */

public interface PresenterManager {

    <P extends Presenter> P getPresenter(Object key, Class<P> presenterClass);

    void releasePresenter(Object key);
}
