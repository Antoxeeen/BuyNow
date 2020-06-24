package ru.antoxeeen.buynow.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;
import ru.antoxeeen.buynow.repository.MainList;
import ru.antoxeeen.buynow.viewmodel.MainListsViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainListsViewModel viewModel;
    FloatingActionButton floating_button_add_list;
    public static final int ADD_GOODS_REQUEST = 1;

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

        floating_button_add_list = findViewById(R.id.button_add_list);
        floating_button_add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddGoodsActivity.class);
                startActivityForResult(intent, ADD_GOODS_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_GOODS_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddGoodsActivity.EXTRA_TITLE);
            MainList mainList = new MainList(title);
            viewModel.insertMainList(mainList);
        }
    }
}