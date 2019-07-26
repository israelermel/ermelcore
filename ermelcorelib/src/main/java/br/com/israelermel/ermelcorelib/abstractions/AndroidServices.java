package br.com.israelermel.ermelcorelib.abstractions;

import android.app.Activity;

import br.com.israelermel.ermelcorelib.interfaces.AppServices;
import br.com.israelermel.ermelcorelib.interfaces.MessageException;
import br.com.israelermel.ermelcorelib.interfaces.Navigator;
import br.com.israelermel.ermelcorelib.utils.DialogsUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AndroidServices implements AppServices {


    private Activity activity;
    private DialogsUtils dialogProvider;
    private Navigator navigator;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public static AndroidServices create(Activity activity, MessageException messageException, Navigator navigator) {

        AndroidServices androidServices = new AndroidServices();
        androidServices.setActivity(activity);

        androidServices.setDialogProvider(new DialogsUtils(activity));
        androidServices.setNavigator(navigator);
        return androidServices;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    private void setDialogProvider(DialogsUtils dialogProvider) {
        this.dialogProvider = dialogProvider;
    }

    @Override
    public DialogsUtils getDialogUtils() {
        return dialogProvider;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    // Composite Manager
    public void addDisposable(Disposable disposable) {
        if (getCompositeDisposable().isDisposed()) {
            return;
        }

        getCompositeDisposable().add(disposable);

    }

    public void clearComposite() {
        getCompositeDisposable().clear();
    }

    public CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    public void disposeComposite() {
        if (getCompositeDisposable().isDisposed()) {
            return;
        }

        getCompositeDisposable().dispose();
    }


}
