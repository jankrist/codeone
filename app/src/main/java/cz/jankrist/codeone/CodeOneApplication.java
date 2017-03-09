package cz.jankrist.codeone;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by jankristdev@gmail.com on 08.03.2017.
 */

public class CodeOneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
