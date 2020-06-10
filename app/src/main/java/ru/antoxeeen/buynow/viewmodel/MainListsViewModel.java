package ru.antoxeeen.buynow.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.antoxeeen.buynow.repository.MainList;
import ru.antoxeeen.buynow.repository.MainListsRepository;

public class MainListsViewModel extends AndroidViewModel {
    private MainListsRepository mainListsRepository;
    private LiveData<List<MainList>> allMainLists;

    public MainListsViewModel(@NonNull Application application) {
        super(application);
        mainListsRepository = new MainListsRepository(application);
        allMainLists = mainListsRepository.getAllMainLists();
    }

    public void insertMainList(MainList mainList){
        mainListsRepository.insertMainList(mainList);
    }

    public void updateMainList(MainList mainList){
        mainListsRepository.updateMainList(mainList);
    }

    public void deleteMainList(MainList mainList){
        mainListsRepository.deleteMainList(mainList);
    }

    public void deleteAllMainList(){
        mainListsRepository.deleteAllMainList();
    }

    public LiveData<List<MainList>> getAllMainLists(){
        return allMainLists;
    }
}
