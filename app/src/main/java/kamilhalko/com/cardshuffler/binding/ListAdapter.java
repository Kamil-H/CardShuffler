package kamilhalko.com.cardshuffler.binding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import kamilhalko.com.cardshuffler.R;
import kamilhalko.com.cardshuffler.databinding.ItemVariantBinding;
import kamilhalko.com.cardshuffler.views.cards.CardVariantsValidator;

public class ListAdapter {
    @BindingAdapter({"entries"})
    public static void setEntries(ViewGroup viewGroup, List<CardVariantsValidator.VariantType> entries) {
        viewGroup.removeAllViews();
        if (entries != null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (CardVariantsValidator.VariantType entry : entries) {
                ItemVariantBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_variant, viewGroup, true);
                binding.setText(entry.getDescription(viewGroup.getContext()));
            }
        }
    }
}
