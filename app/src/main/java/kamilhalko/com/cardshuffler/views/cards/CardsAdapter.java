package kamilhalko.com.cardshuffler.views.cards;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kamilhalko.com.cardshuffler.R;
import kamilhalko.com.cardshuffler.data.models.Card;
import kamilhalko.com.cardshuffler.databinding.CardItemBinding;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {
    private List<Card> cardList = new ArrayList<>();

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Card card = cardList.get(holder.getAdapterPosition());
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardItemBinding binding;

        public ViewHolder(CardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Card card) {
            binding.setItem(card);
            binding.executePendingBindings();
        }
    }
}
