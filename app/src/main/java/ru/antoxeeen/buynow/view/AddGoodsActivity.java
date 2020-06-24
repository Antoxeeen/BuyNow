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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import ru.antoxeeen.buynow.repository.GoodsList;
import ru.antoxeeen.buynow.viewmodel.GoodsListsViewModel;

public class AddGoodsActivity extends AppCompatActivity {

    private EditText editText_listName;
    private EditText editText_goods;
    private ImageView imageView_add_goods;
    private RecyclerView recyclerView;
    private GoodsListsViewModel goodsListViewModel;
    public static final String EXTRA_TITLE =
            "ru.antoxeeen.buynow.view.EXTRA_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);

        editText_listName = findViewById(R.id.edit_text_title);
        editText_goods = findViewById(R.id.edit_text_add_goods);
        imageView_add_goods = findViewById(R.id.image_view_add_goods);

        recyclerView = findViewById(R.id.goods_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final GoodsListAdapter adapter = new GoodsListAdapter();
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        setTitle("Add goods");

        goodsListViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(GoodsListsViewModel.class);
        goodsListViewModel.getAllGoodsList().observe(this, new Observer<List<GoodsList>>() {
            @Override
            public void onChanged(List<GoodsList> goodsLists) {
                adapter.setList(goodsLists);
            }
        });

        imageView_add_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_goods();
            }
        });

    }

    private void add_goods(){
        String goods_name = editText_goods.getText().toString();
        String title_name = editText_listName.getText().toString();
        goodsListViewModel.insertGoodsList(new GoodsList(goods_name,title_name));
    }

    private void save_goods(){
        String list_name = editText_listName.getText().toString();
        if (list_name == null || list_name.isEmpty()){
            Toast.makeText(this, "Введите название для списка", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, list_name);
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
        switch(item.getItemId()){
            case R.id.save_goods:
                save_goods();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}