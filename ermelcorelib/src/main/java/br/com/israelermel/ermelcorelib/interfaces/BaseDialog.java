package br.com.israelermel.ermelcorelib.interfaces;

import com.afollestad.materialdialogs.MaterialDialog;

import io.reactivex.Single;

public interface BaseDialog {
    MaterialDialog getProgress();
    void showProgress();
    void hideProgress();
    Single<String> input(String prefill);
}
