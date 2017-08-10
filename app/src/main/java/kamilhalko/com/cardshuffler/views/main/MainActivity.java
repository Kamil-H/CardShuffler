package kamilhalko.com.cardshuffler.views.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import kamilhalko.com.cardshuffler.base.BaseActivity;
import kamilhalko.com.cardshuffler.R;
import kamilhalko.com.cardshuffler.databinding.ActivityMainBinding;
import kamilhalko.com.cardshuffler.views.cards.CardsFragment;
import kamilhalko.com.cardshuffler.views.welcome.WelcomeFragment;

public class MainActivity extends BaseActivity implements WelcomeFragment.OnDecksNumberChosenListener {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        displayWelcomeFragment();
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
