package ru.antoxeeen.buynow.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;
import ru.antoxeeen.buynow.repository.MainList;
import ru.antoxeeen.buynow.viewmodel.MainListsViewModel;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainListsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MainListAdapter adapter = new MainListAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainListsViewModel.class);
        viewModel.getAllMainLists().observe(this, new Observer<List<MainList>>() {
            @Override
            public void onChanged(List<MainList> mainLists) {
                adapter.setList(mainLists);
            }
        });
    }

}