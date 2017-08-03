package kamilhalko.com.cardshuffler.views.cards;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kamilhalko.com.cardshuffler.BaseFragment;
import kamilhalko.com.cardshuffler.R;

public class CardsFragment extends BaseFragment {
    private int decksNumber = 1;

    public static CardsFragment getInstance(int decksNumber) {
        CardsFragment cardsFragment = new CardsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("decksNumber", decksNumber);
        cardsFragment.setArguments(bundle);
        return cardsFragment;
    }

    private void getExtras() {
        if (getArguments() != null) {
            decksNumber = getArguments().getInt("decksNumber");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cards, container, false);
        getExtras();
        Log.i("decksNumber", ""+ decksNumber);
        return view;
    }

}
