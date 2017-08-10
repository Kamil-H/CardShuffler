package kamilhalko.com.cardshuffler.views.cards;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import kamilhalko.com.cardshuffler.base.BaseFragment;
import kamilhalko.com.cardshuffler.data.models.CardsResponse;
import kamilhalko.com.cardshuffler.data.network.Resource;
import kamilhalko.com.cardshuffler.data.network.error.ApiError;
import kamilhalko.com.cardshuffler.databinding.FragmentCardsBinding;

public class CardsFragment extends BaseFragment {
    @Inject CardsViewModel cardsViewModel;
    private FragmentCardsBinding binding;
    private int decksNumber = 1;
    private CardsAdapter cardsAdapter;

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
        binding = FragmentCardsBinding.inflate(inflater, container, false);
        getActivityComponent().inject(this);
        binding.setViewModel(cardsViewModel);
        init();
        return binding.getRoot();
    }

    private void init() {
        getExtras();
        setUpRecyclerView();
        initObservers();
        cardsViewModel.downloadDecks(decksNumber);
    }

    private void initObservers() {
        cardsViewModel.getCards().subscribe(new Consumer<Resource<CardsResponse>>() {
            @Override
            public void accept(@NonNull Resource<CardsResponse> cardsResponseResource) throws Exception {
                cardsAdapter.setCardList(cardsResponseResource.data.getCards());
            }
        });

        cardsViewModel.getError().subscribe(new Consumer<ApiError>() {
            @Override
            public void accept(@NonNull ApiError apiError) throws Exception {
                onError(apiError.getErrorType().getMessage(getBaseActivity()));
            }
        });
    }

    private void setUpRecyclerView() {
        cardsAdapter = new CardsAdapter();
        binding.recyclerView.setAdapter(cardsAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3));
    }

    public void onError(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cardsViewModel.onDetach();
    }
}
