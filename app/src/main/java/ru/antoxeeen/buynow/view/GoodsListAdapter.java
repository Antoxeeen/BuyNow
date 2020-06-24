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
import ru.antoxeeen.buynow.repository.MainList;

class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListHolder> {
    private List<GoodsList> goodsList = new ArrayList<>();

    @NonNull
    @Override
    public GoodsListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goods_item, parent, false);
        return new GoodsListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsListHolder holder, int position) {
        GoodsList currentGoodsList = goodsList.get(position);
        holder.textViewGoods.setText(currentGoodsList.getGoods());
    }

    public void setList(List<GoodsList> goodsLists){
        this.goodsList = goodsLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    static class GoodsListHolder extends RecyclerView.ViewHolder {
        private TextView textViewGoods;

        public GoodsListHolder(@NonNull View itemView) {
            super(itemView);
            textViewGoods = itemView.findViewById(R.id.text_view_goods);
        }
    }
}
