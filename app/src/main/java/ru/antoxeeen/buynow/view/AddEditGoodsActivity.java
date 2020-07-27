package ru.antoxeeen.buynow.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;

import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import ru.antoxeeen.buynow.repository.GoodsList;
import ru.antoxeeen.buynow.viewmodel.GoodsListsViewModel;

public class AddEditGoodsActivity extends AppCompatActivity
        implements EditGoodsDialog.EditGoodsDialogListener {

    private GoodsListAdapter adapter;
    private EditText editText_listName;
    private EditText editText_goods;
    private int list_id;
    private String list_name;
    private LiveData<List<GoodsList>> currentGoodsList;
    private List<GoodsList> goodsListsToDaD;
    private FloatingActionButton button_add_goods;
    private RecyclerView recyclerView;
    private GoodsListsViewModel goodsListViewModel;
    public static final String EXTRA_ID =
            "ru.antoxeeen.buynow.view.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "ru.antoxeeen.buynow.view.EXTRA_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        Objects.requireNonNull(getSupportActionBar())
                .setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        initVariable();

        Intent intent = getIntent();
        list_name = intent.getStringExtra(EXTRA_TITLE);
        list_id = intent.getIntExtra(EXTRA_ID, -1);
        editText_listName.setText(list_name);
        currentGoodsList = goodsListViewModel.getGoodsListFromListId(list_id);

        currentGoodsList.observe(this, new Observer<List<GoodsList>>() {
            @Override
            public void onChanged(List<GoodsList> goodsLists) {
                goodsListsToDaD = goodsLists;
                adapter.submitList(goodsListsToDaD);
            }
        });

        adapter.setOnItemClickListener(new GoodsListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(GoodsList goodsList) {
                openDialog(goodsList);
            }
        });

        button_add_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_goods();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
                | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {

                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
                GoodsList goodsList1 = goodsListsToDaD.get(fromPosition);
                GoodsList goodsList2 = goodsListsToDaD.get(toPosition);
                String goods1 = goodsList2.getGoods();
                goodsList2.setGoods(goodsList1.getGoods());
                goodsList1.setGoods(goods1);
                goodsListViewModel.updateGoodsList(goodsList2);
                goodsListViewModel.updateGoodsList(goodsList1);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                goodsListViewModel.deleteGoodsList(adapter.getGoodsListAt(viewHolder.getAdapterPosition()));
            }
        })
                .attachToRecyclerView(recyclerView);

    }

    private void initVariable() {
        editText_listName = findViewById(R.id.edit_text_title);
        editText_goods = findViewById(R.id.edit_text_add_goods);
        button_add_goods = findViewById(R.id.button_add_goods);
        recyclerView = findViewById(R.id.goods_recycler_view);
        adapter = new GoodsListAdapter();
        goodsListViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(GoodsListsViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        setTitle("Edit goods");
    }

    private void add_goods() {
        String goods_name = editText_goods.getText().toString();
        if (goods_name.trim().isEmpty()) {
            Toast.makeText(this, "Insert goods", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        GoodsList goodsList = new GoodsList(goods_name);
        goodsList.setListId(list_id);
        goodsListViewModel.insertGoodsList(goodsList);
        editText_goods.setText("");
    }

    private void save_goods() {
        list_name = editText_listName.getText().toString();
        if (list_name.trim().isEmpty()) {
            Toast.makeText(this, "Введите название для списка", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, list_name);
        data.putExtra(EXTRA_ID, list_id);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_goods:
                save_goods();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openDialog(GoodsList goodsList) {
        EditGoodsDialog editGoodsDialog = new EditGoodsDialog();
        Bundle args = new Bundle();
        args.putString("goods_name", goodsList.getGoods());
        args.putInt("goods_listId", goodsList.getListId());
        args.putInt("goods_id", goodsList.getId());

        editGoodsDialog.setArguments(args);
        editGoodsDialog.show(getSupportFragmentManager(), "edit");
    }

    @Override
    public void applyTexts(String goods_name, int list_id, int id) {
        GoodsList goodsList = new GoodsList(goods_name);
        goodsList.setListId(list_id);
        goodsList.setId(id);
        goodsListViewModel.updateGoodsList(goodsList);
    }
}