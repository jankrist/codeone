package cz.jankrist.codeone.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cz.jankrist.codeone.R;
import cz.jankrist.codeone.view.fragment.navigation.CodeReaderFragment;
import cz.jankrist.codeone.view.fragment.navigation.DashboardFragment;
import cz.jankrist.codeone.view.fragment.navigation.HomeFragment;
import cz.jankrist.codeone.view.fragment.navigation.NavigationTabFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this::onNavigateTabItemClick);
    }

    private boolean onNavigateTabItemClick(MenuItem item){
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showNavigationTabFragment(new HomeFragment());
                return true;
            case R.id.navigation_dashboard:
                showNavigationTabFragment(new DashboardFragment());
                return true;
            case R.id.navigation_code_reader:
                showNavigationTabFragment(new CodeReaderFragment());
                return true;
        }
        return false;
    }



    private void showNavigationTabFragment(NavigationTabFragment tabFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, tabFragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }

}
