package ru.antoxeeen.buynow.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;
import ru.antoxeeen.buynow.repository.GoodsList;

class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListHolder> {
    private List<GoodsList> goodsListList = new ArrayList<>();

    @NonNull
    @Override
    public GoodsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item, parent, false);
        return new GoodsListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsListHolder holder, int position) {
        GoodsList currentGoodsList = goodsListList.get(position);
        holder.textViewGoods.setText(currentGoodsList.getGoods());
    }

    @Override
    public int getItemCount() {
        return goodsListList.size();
    }

    static class GoodsListHolder extends RecyclerView.ViewHolder {
        private TextView textViewGoods;

        public GoodsListHolder(@NonNull View itemView) {
            super(itemView);
            textViewGoods = itemView.findViewById(R.id.text_view_goods);
        }
    }
}
