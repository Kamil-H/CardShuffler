package kamilhalko.com.cardshuffler.views.welcome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import kamilhalko.com.cardshuffler.base.BaseFragment;
import kamilhalko.com.cardshuffler.R;

public class WelcomeFragment extends BaseFragment {
    @Inject WelcomeViewModel viewModel;
    private SeekBar seekBar;
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
        view = inflater.inflate(R.layout.fragment_welcome, container, false);
        getActivityComponent().inject(this);
        init();
        return view;
    }

    private void init() {
        setUpView();
        setUpObservables();
    }

    private void setUpView() {
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setValue(seekBar.getProgress());
            }
        });
    }

    private void setUpObservables() {
        viewModel.getChosenValue().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                if (callback != null) {
                    callback.onDecksNumberChosen(integer);
                }
            }
        });

        viewModel.getIsError().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {

            }
        });
    }

    public interface OnDecksNumberChosenListener {
        void onDecksNumberChosen(int number);
    }
}
