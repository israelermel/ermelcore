package br.com.israelermel.ermelcorelib.interfaces;

import android.app.Activity;

import br.com.israelermel.core.utils.DialogsUtils;
import br.com.israelermel.core.utils.FragmentProvider;
import br.com.israelermel.core.utils.LogProvider;
import br.com.israelermel.core.utils.Navigator;
import br.com.israelermel.core.utils.ResourceProvider;
import io.reactivex.disposables.Disposable;

public interface IViewModel<T extends BaseDao, E extends MessageException, N extends Navigator> {
    ResourceProvider getResource();

    LogProvider getLog();

    Activity getActivity();

    FragmentProvider getFragment();

    DialogsUtils getDialog();

    void onPause();

    void onResume();

    void onDestroy();

    E getException();

    T getServiceDao();

    N getNavigator();

    String getString(int res);

    void clearComposite();

    void addDisposable(Disposable d);
}
