package ru.antoxeeen.buynow.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;
import ru.antoxeeen.buynow.repository.GoodsList;


class GoodsListAdapter extends ListAdapter<GoodsList, GoodsListAdapter.GoodsListHolder> {
    private onItemClickListener listener;

    public GoodsListAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<GoodsList> DIFF_CALLBACK = new DiffUtil.ItemCallback<GoodsList>() {
        @Override
        public boolean areItemsTheSame(@NonNull GoodsList oldItem, @NonNull GoodsList newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull GoodsList oldItem, @NonNull GoodsList newItem) {
            return oldItem.getGoods().equals(newItem.getGoods());
        }
    };

    @NonNull
    @Override
    public GoodsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item, parent, false);
        return new GoodsListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsListHolder holder, int position) {
        GoodsList currentGoodsList = getItem(position);
        holder.textViewGoods.setText(currentGoodsList.getGoods());
    }

    public GoodsList getGoodsListAt(int position) {
        return getItem(position);
    }

    class GoodsListHolder extends RecyclerView.ViewHolder {
        private TextView textViewGoods;

        public GoodsListHolder(@NonNull View itemView) {
            super(itemView);
            textViewGoods = itemView.findViewById(R.id.text_view_goods);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(GoodsList goodsList);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

}
