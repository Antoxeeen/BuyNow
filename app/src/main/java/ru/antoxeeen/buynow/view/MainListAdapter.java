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
import ru.antoxeeen.buynow.repository.MainList;

class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {
    private List<MainList> mainList = new ArrayList<>();

    @NonNull
    @Override
    public MainListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new  MainListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListHolder holder, int position) {
        MainList currentMainList = mainList.get(position);
        holder.textViewTitle.setText(currentMainList.getName());
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public void setList(List<MainList> mainList){
        this.mainList = mainList;
        notifyDataSetChanged();
    }

    static class MainListHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;

        public MainListHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_list);
        }
    }
}
