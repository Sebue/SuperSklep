package pl.sebastian.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum LangProvider {
    INSTANCE;

    public static final String BUNDLE_NAME = "languages";
    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());

    public void setLanguage(Locale locale) {
        this.bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public String getMessage(String template, String... params) {
        return MessageFormat.format(bundle.getString(template), params);
    }
}
