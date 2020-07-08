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

    public GoodsListsViewModel(@NonNull Application application) {
        super(application);
        goodsRepository = new GoodsRepository(application);
    }

    public void insertGoodsList(GoodsList goodsList) {

        goodsRepository.insertGoodsList(goodsList);
    }

    public void updateGoodsList(GoodsList goodsList) {
        goodsRepository.updateGoodsList(goodsList);
    }

    public void deleteGoodsList(GoodsList goodsList) {
        goodsRepository.deleteGoodsList(goodsList);
    }

    public void deleteAllGoodsList() {
        goodsRepository.deleteAllGoodsList();
    }

    public LiveData<List<GoodsList>> getGoodsListFromListId(int list_id) {
        return goodsRepository.getGoodsListFromListId(list_id);
    }
}
