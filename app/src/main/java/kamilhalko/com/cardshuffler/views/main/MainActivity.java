package kamilhalko.com.cardshuffler.views.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import kamilhalko.com.cardshuffler.R;
import kamilhalko.com.cardshuffler.views.cards.CardsFragment;
import kamilhalko.com.cardshuffler.views.welcome.WelcomeFragment;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnDecksNumberChosenListener {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        setUpToolbar();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(fragmentManager.getBackStackEntryCount() > 1);
                }
            }
        });

        displayWelcomeFragment();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    private void displayFragment(Fragment fragment, int containerId) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(fragment.getClass().getName())
                .replace(containerId, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    private void displayCardsFragment(int number) {
        displayFragment(CardsFragment.getInstance(number), R.id.container);
    }

    private void displayWelcomeFragment() {
        displayFragment(WelcomeFragment.getInstance(), R.id.container);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void onDecksNumberChosen(int number) {
        displayCardsFragment(number);
    }
}
