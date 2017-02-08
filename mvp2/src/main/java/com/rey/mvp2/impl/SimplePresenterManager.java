package com.rey.mvp2.impl;

import com.rey.mvp2.Presenter;
import com.rey.mvp2.PresenterFactory;
import com.rey.mvp2.PresenterManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rey on 2/8/2017.
 */

public class SimplePresenterManager implements PresenterManager{

    private final PresenterFactory factory;
    private final Map<Object, Presenter> presenterMap = new HashMap<>();

    public SimplePresenterManager(PresenterFactory factory){
        this.factory = factory;
    }

    @Override
    public <P extends Presenter> P getPresenter(Object key, Class<P> presenterClass) {
        synchronized (this){
            Presenter presenter = presenterMap.get(key);
            if(presenter == null){
                presenter = factory.getPresenter(key, presenterClass);
                presenter.onCreate();
                presenterMap.put(key, presenter);
            }
            return (P)presenter;
        }
    }

    @Override
    public void releasePresenter(Object key) {
        synchronized (this){
            Presenter presenter = presenterMap.remove(key);
            if(presenter != null)
                presenter.onDestroy();
        }
    }
}
