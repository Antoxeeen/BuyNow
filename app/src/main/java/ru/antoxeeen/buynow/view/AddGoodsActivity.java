package ru.antoxeeen.buynow.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.antoxeeen.buynow.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddGoodsActivity extends AppCompatActivity {

    private EditText editText_listName;
    private EditText editText_goods;
    private ImageView imageView_add_goods;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);

        editText_listName = findViewById(R.id.edit_text_title);
        editText_listName = findViewById(R.id.edit_text_add_goods);
        imageView_add_goods = findViewById(R.id.image_view_add_goods);

        recyclerView = findViewById(R.id.goods_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        setTitle("Add goods");


    }

    private void save_goods(){
        String list_name = editText_listName.getText().toString();
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