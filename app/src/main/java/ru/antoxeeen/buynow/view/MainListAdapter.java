package ru.antoxeeen.buynow.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;
import ru.antoxeeen.buynow.repository.MainList;

class MainListAdapter extends ListAdapter<MainList, MainListAdapter.MainListHolder> {
    private OnItemClickListener listener;

    public MainListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<MainList> DIFF_CALLBACK = new DiffUtil.ItemCallback<MainList>() {
        @Override
        public boolean areItemsTheSame(@NonNull MainList oldItem, @NonNull MainList newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MainList oldItem, @NonNull MainList newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    @NonNull
    @Override
    public MainListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MainListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListHolder holder, int position) {
        MainList currentMainList = getItem(position);
        holder.textViewTitle.setText(currentMainList.getName());
    }

    public MainList getMainListAt(int position) {
        return getItem(position);
    }

    class MainListHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;

        public MainListHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_list);

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

    public interface OnItemClickListener {
        void onItemClick(MainList mainList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
