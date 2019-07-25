package br.com.israelermel.ermelcorelib.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class FragmentUtils {

    public static void carregaFragment(FragmentManager fragmentManager, Fragment fragment, int contentFragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(contentFragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

}
