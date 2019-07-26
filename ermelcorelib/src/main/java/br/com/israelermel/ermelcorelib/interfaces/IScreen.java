package br.com.israelermel.ermelcorelib.interfaces;

import android.app.Activity;

import br.com.israelermel.ermelcorelib.abstractions.AndroidServices;

public interface IScreen<T extends Activity, E extends MessageException, N extends Navigator> {

    AndroidServices getAndroid();

    E getMessageException();

    T getBaseAcitivity();

    void initInject();

    boolean isActionBarHide();

    boolean isShowBackButton();

    N getNavigator();
}
