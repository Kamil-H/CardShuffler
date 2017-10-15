package kamilhalko.com.cardshuffler.views.welcome;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import kamilhalko.com.cardshuffler.base.BaseFragment;
import kamilhalko.com.cardshuffler.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends BaseFragment {
    @Inject WelcomeViewModel viewModel;
    private FragmentWelcomeBinding binding;
    private OnDecksNumberChosenListener callback;

    public static WelcomeFragment getInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnDecksNumberChosenListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        getActivityComponent().inject(this);
        init();
        return binding.getRoot();
    }

    private void init() {
        setUpView();
        initObservers();
    }

    private void setUpView() {
        binding.button.setOnClickListener(view -> viewModel.setValue(binding.seekBar.getProgress()));
    }

    private void initObservers() {
        viewModel.getError().subscribe(errorType -> onError(errorType.getText(getBaseActivity())));

        viewModel.getCount().subscribe(integer -> {
            if (callback != null) {
                callback.onDecksNumberChosen(integer);
            }
        });
    }

    public void onError(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
    }

    public interface OnDecksNumberChosenListener {
        void onDecksNumberChosen(int number);
    }
}
