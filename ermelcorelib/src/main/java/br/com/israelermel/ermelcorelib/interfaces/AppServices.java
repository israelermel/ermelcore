package br.com.israelermel.ermelcorelib.interfaces;

import android.app.Activity;

import br.com.israelermel.ermelcorelib.utils.DialogsUtils;

public interface AppServices {
    Activity getActivity();

    DialogsUtils getDialogUtils();
}
