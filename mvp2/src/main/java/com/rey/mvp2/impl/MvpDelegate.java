package com.rey.mvp2.impl;

import com.rey.mvp2.Presenter;
import com.rey.mvp2.PresenterManager;

/**
 * Created by Rey on 2/8/2017.
 */
public class MvpDelegate<P extends Presenter> {

    private PresenterManager manager;
    private Object key;

    private P presenter;
    private boolean isDestroyedBySystem = false;

    public MvpDelegate(PresenterManager manager, Object key, Class<P> presenterClass){
        this.manager = manager;
        this.key = key;
        presenter = manager.getPresenter(key, presenterClass);
    }

    public P getPresenter(){
        return presenter;
    }

    public void onSaveState(){
        isDestroyedBySystem = true;
    }

    public void onDestroy(){
        if(!isDestroyedBySystem)
            manager.releasePresenter(key);
    }
}
