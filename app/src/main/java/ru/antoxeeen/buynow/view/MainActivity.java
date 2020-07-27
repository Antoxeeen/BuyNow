package ru.antoxeeen.buynow.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;
import ru.antoxeeen.buynow.repository.MainList;
import ru.antoxeeen.buynow.viewmodel.GoodsListsViewModel;
import ru.antoxeeen.buynow.viewmodel.MainListsViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainListsViewModel viewModel;
    FloatingActionButton floating_button_add_list;
    private RecyclerView recyclerView;
    private MainListAdapter adapter;
    public static final int EDIT_GOODS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariable();



        viewModel.getAllMainLists().observe(this, new Observer<List<MainList>>() {
            @Override
            public void onChanged(List<MainList> mainLists) {
                adapter.submitList(mainLists);
            }
        });

        floating_button_add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainList mainList = new MainList("Новый список");
                viewModel.insertMainList(mainList);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteMainList(adapter.getMainListAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new MainListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MainList mainList) {
                Intent intent = new Intent(MainActivity.this,
                        AddEditGoodsActivity.class);
                int listId = mainList.getId();
                String listName = mainList.getName();
                intent.putExtra(AddEditGoodsActivity.EXTRA_ID, listId);
                intent.putExtra(AddEditGoodsActivity.EXTRA_TITLE, listName);
                startActivityForResult(intent, EDIT_GOODS_REQUEST);
            }
        });
    }

    private void initVariable() {
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MainListAdapter();
        recyclerView.setAdapter(adapter);
        floating_button_add_list = findViewById(R.id.button_add_list);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainListsViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_GOODS_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditGoodsActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(AddEditGoodsActivity.EXTRA_TITLE);
            MainList mainList = new MainList(title);
            mainList.setId(id);
            viewModel.updateMainList(mainList);

        } else {
            Toast.makeText(this, "not saved", Toast.LENGTH_SHORT).show();
        }
    }
}