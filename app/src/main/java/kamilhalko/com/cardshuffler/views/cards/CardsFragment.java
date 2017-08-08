package kamilhalko.com.cardshuffler.views.cards;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import kamilhalko.com.cardshuffler.R;
import kamilhalko.com.cardshuffler.base.BaseFragment;
import kamilhalko.com.cardshuffler.data.models.Deck;
import kamilhalko.com.cardshuffler.data.network.Resource;

public class CardsFragment extends BaseFragment {
    @Inject CardsViewModel cardsViewModel;
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
        getActivityComponent().inject(this);
        init();
        return view;
    }

    private void init() {
        getExtras();
        cardsViewModel.downloadDecks(decksNumber);
        cardsViewModel.getSubject().doOnNext(new Consumer<Resource<Deck>>() {
            @Override
            public void accept(@NonNull Resource<Deck> deckResource) throws Exception {
                switch (deckResource.status) {
                    case SUCCESS:
                        Log.i("Decks", new Gson().toJson(deckResource.data));
                    case LOADING:
                        Log.i("Decks", "Loading");
                    case ERROR:
                        Log.i("Decks", "Error");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cardsViewModel.onDetach();
    }
}
