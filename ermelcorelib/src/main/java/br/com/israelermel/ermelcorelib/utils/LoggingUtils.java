package br.com.israelermel.ermelcorelib.utils;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

/**
 * Classe Utilitaria para enviar logs ao Crashlytics no Firebase
 * @author israelermel
 * @see "https://firebase.google.com/docs/crashlytics/customize-crash-reports?authuser=1&platform=android"
 */
public class LoggingUtils {

    public static void logE(Exception e) {
        Timber.e(e);
    }

    public static void logE(Throwable e) {
        Timber.e(e);
    }

    public static void logUsuario(String idUsuario) {
        Crashlytics.setUserIdentifier(idUsuario);
    }

}
