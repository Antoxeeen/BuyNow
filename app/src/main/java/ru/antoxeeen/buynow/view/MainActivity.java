package ru.antoxeeen.buynow.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainListsViewModel.class);
        viewModel.getAllMainLists().observe(this, new Observer<List<MainList>>() {
            @Override
            public void onChanged(List<MainList> mainLists) {
                //update recyclerView
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

}