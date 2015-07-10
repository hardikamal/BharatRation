package com.ashinetech.bharatration;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by Vignesh on 10-07-2015.
 */
public class SuggestionProvider  extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "com.ashinetech.bharatration.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
