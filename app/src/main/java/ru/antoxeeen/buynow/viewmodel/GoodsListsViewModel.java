package ru.antoxeeen.buynow.viewmodel;
import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.antoxeeen.buynow.repository.GoodsList;
import ru.antoxeeen.buynow.repository.GoodsRepository;

public class GoodsListsViewModel extends AndroidViewModel {
    private GoodsRepository goodsRepository;
    private LiveData<List<GoodsList>> allGoods;
    private int listId;

    public GoodsListsViewModel(@NonNull Application application) {
        super(application);
        goodsRepository = new GoodsRepository(application, listId);
        allGoods = goodsRepository.getAllGoods();

    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public void insertGoodsList(GoodsList goodsList){
        goodsRepository.insertGoodsList(goodsList);
    }

    public void updateGoodsList(GoodsList goodsList){
        goodsRepository.updateGoodsList(goodsList);
    }

    public void deleteGoodsList(GoodsList goodsList){
        goodsRepository.deleteGoodsList(goodsList);
    }

    public LiveData<List<GoodsList>> getAllGoodsList(){
        return allGoods;
    }
}
