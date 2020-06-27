package ru.antoxeeen.buynow.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import ru.antoxeeen.buynow.repository.GoodsList;
import ru.antoxeeen.buynow.viewmodel.GoodsListsViewModel;

public class AddEditGoodsActivity extends AppCompatActivity {

    private GoodsListAdapter adapter;
    private EditText editText_listName;
    private EditText editText_goods;
    private int list_id;
    private String list_name;
    private ImageView imageView_add_goods;
    private Button add_goods_button;
    private RecyclerView recyclerView;
    private GoodsListsViewModel goodsListViewModel;
    public static final String EXTRA_ID =
            "ru.antoxeeen.buynow.view.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "ru.antoxeeen.buynow.view.EXTRA_TITLE";
    public static final String EXTRA_NEW_LIST =
            "ru.antoxeeen.buynow.view.EXTRA_NEW_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);

        editText_listName = findViewById(R.id.edit_text_title);
        editText_goods = findViewById(R.id.edit_text_add_goods);
        //imageView_add_goods = findViewById(R.id.image_view_add_goods);
        add_goods_button = findViewById(R.id.add_goods_button);

        recyclerView = findViewById(R.id.goods_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new GoodsListAdapter();
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        goodsListViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(GoodsListsViewModel.class);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_NEW_LIST)){
            setTitle("Edit goods");
        }
        list_name = intent.getStringExtra(EXTRA_TITLE);
        list_id = intent.getIntExtra(EXTRA_ID, -1);
        editText_listName.setText(list_name);
        goodsListViewModel.setListId(list_id);
        setTitle("Add goods");

        goodsListViewModel.getAllGoodsList().observe(this, new Observer<List<GoodsList>>() {
            @Override
            public void onChanged(List<GoodsList> goodsLists) {
                adapter.setList(goodsLists);
            }
        });

        add_goods_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsList goodsList = new GoodsList("name", list_id);
                goodsListViewModel.insertGoodsList(goodsList);
                //add_goods();
            }
        });

        /*imageView_add_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_goods();
            }
        });*/

    }

    private void add_goods() {
        String goods_name = editText_goods.getText().toString();
        if (goods_name.trim().isEmpty()) {
            Toast.makeText(this, "Insert goods", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        goodsListViewModel.insertGoodsList(new GoodsList(goods_name, list_id));
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

}