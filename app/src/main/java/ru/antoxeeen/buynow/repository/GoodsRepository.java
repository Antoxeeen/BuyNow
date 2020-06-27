package ru.antoxeeen.buynow.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class GoodsRepository {

    GoodsListDao goodsListDao;
    private LiveData<List<GoodsList>> goods;

    public GoodsRepository(Application application, int listId) {
        GoodsListDatabase database = GoodsListDatabase.getInstance(application);
        goodsListDao = database.goodsListDao();
        goods = goodsListDao.getAllGoods(listId);
    }

    public void insertGoodsList(GoodsList goodsList){
        new InsertGoodsListAsyncTask(goodsListDao).execute(goodsList);
    }

    public void updateGoodsList(GoodsList goodsList){
        new UpdateGoodsListAsyncTask(goodsListDao).execute(goodsList);
    }

    public void deleteGoodsList(GoodsList goodsList){
        new DeleteGoodsListAsyncTask(goodsListDao).execute(goodsList);
    }

    public LiveData<List<GoodsList>> getAllGoods(){
        return goods;
    }

    private static class InsertGoodsListAsyncTask extends AsyncTask<GoodsList, Void, Void> {

        private GoodsListDao goodsListDao;

        private InsertGoodsListAsyncTask(GoodsListDao goodsListDao){
            this.goodsListDao =  goodsListDao;
        }

        @Override
        protected Void doInBackground(GoodsList... goodsLists) {
            goodsListDao.insert(goodsLists[0]);
            return null;
        }
    }

    private static class UpdateGoodsListAsyncTask extends AsyncTask<GoodsList, Void, Void>{

        private GoodsListDao goodsListDao;

        private UpdateGoodsListAsyncTask(GoodsListDao goodsListDao){
            this.goodsListDao =  goodsListDao;
        }

        @Override
        protected Void doInBackground(GoodsList... goodsLists) {
            goodsListDao.update(goodsLists[0]);
            return null;
        }
    }

    private static class DeleteGoodsListAsyncTask extends AsyncTask<GoodsList, Void, Void>{

        private GoodsListDao goodsListDao;

        private DeleteGoodsListAsyncTask(GoodsListDao goodsListDao){
            this.goodsListDao =  goodsListDao;
        }

        @Override
        protected Void doInBackground(GoodsList... goodsLists) {
            goodsListDao.delete(goodsLists[0]);
            return null;
        }
    }

}
