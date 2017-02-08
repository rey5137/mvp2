package com.rey.mvp2.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rey.mvp2.Presenter;
import com.rey.mvp2.PresenterManager;

/**
 * Created by Rey on 2/8/2017.
 */

public abstract class MvpActivity<P extends Presenter> extends AppCompatActivity {

    protected abstract PresenterManager getPresenterManager();
    protected abstract Object getPresenterKey();
    protected abstract Class<P> getPresenterClass();

    private volatile MvpDelegate<P> mvpDelegate;

    private MvpDelegate<P> getMvpDelegate(){
        if(mvpDelegate == null){
            synchronized (MvpDelegate.class){
                if(mvpDelegate == null)
                    mvpDelegate = new MvpDelegate<>(getPresenterManager(), getPresenterKey(), getPresenterClass());
            }
        }
        return mvpDelegate;
    }

    protected P getPresenter(){
        return getMvpDelegate().getPresenter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }
}
