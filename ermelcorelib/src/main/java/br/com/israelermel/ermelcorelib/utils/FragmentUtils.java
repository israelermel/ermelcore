package br.com.israelermel.ermelcorelib.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class FragmentUtils {

    public void carregaFragment(FragmentManager fragmentManager, Fragment fragment, int contentFragment, String name) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.addToBackStack(name);
            transaction.replace(contentFragment, fragment);
        }

        transaction.commit();
    }

    public static void carregaFragment(FragmentManager fragmentManager, Fragment fragment, int contentFragment) {
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(contentFragment, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

}
