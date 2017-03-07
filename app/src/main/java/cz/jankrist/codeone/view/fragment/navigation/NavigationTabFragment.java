package cz.jankrist.codeone.view.fragment.navigation;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by jankristdev@gmail.com on 07.03.2017.
 */

public abstract class NavigationTabFragment extends Fragment {

    protected abstract @StringRes int getTitle();
}
