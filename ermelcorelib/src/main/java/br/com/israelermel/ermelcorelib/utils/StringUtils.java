package br.com.israelermel.ermelcorelib.utils;

import java.text.Normalizer;

/**
 * Created by israelermel on 28/02/17.
 */
public class StringUtils {

    public static String stripAccents(String s)
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public static String normalizeString(String s) {
        String sNormalizeAccents = stripAccents(s);
        sNormalizeAccents = sNormalizeAccents.replace(" ", "_");
        return sNormalizeAccents;
    }
}
