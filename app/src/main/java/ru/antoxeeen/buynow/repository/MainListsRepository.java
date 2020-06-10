package ru.antoxeeen.buynow.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class MainListsRepository {
    private MainListDao mainListDao;
    private LiveData<List<MainList>> allMainLists;

    public MainListsRepository(Application application){
        GoodsListDatabase database = GoodsListDatabase.getInstance(application);
        mainListDao = database.mainListDao();
        allMainLists = mainListDao.getAllMainList();
    }

    public void insertMainList(MainList mainList){
        new InsertMainListAsyncTask(mainListDao).execute(mainList);
    }

    public void updateMainList(MainList mainList){
        new UpdateMainListAsyncTask(mainListDao).execute(mainList);
    }

    public void deleteMainList(MainList mainList){
        new DeleteMainListAsyncTask(mainListDao).execute(mainList);
    }

    public void deleteAllMainList(){
        new DeleteAllMainListAsyncTask(mainListDao).execute();
    }

    public LiveData<List<MainList>> getAllMainLists(){
        return allMainLists;
    }

    private static class InsertMainListAsyncTask extends AsyncTask<MainList, Void, Void>{

        private MainListDao mainListDao;

        private InsertMainListAsyncTask(MainListDao mainListDao){
            this.mainListDao =  mainListDao;
        }

        @Override
        protected Void doInBackground(MainList... mainLists) {
            mainListDao.insert(mainLists[0]);
            return null;
        }
    }

    private static class UpdateMainListAsyncTask extends AsyncTask<MainList, Void, Void>{

        private MainListDao mainListDao;

        private UpdateMainListAsyncTask(MainListDao mainListDao){
            this.mainListDao =  mainListDao;
        }

        @Override
        protected Void doInBackground(MainList... mainLists) {
            mainListDao.update(mainLists[0]);
            return null;
        }
    }

    private static class DeleteMainListAsyncTask extends AsyncTask<MainList, Void, Void>{

        private MainListDao mainListDao;

        private DeleteMainListAsyncTask(MainListDao mainListDao){
            this.mainListDao =  mainListDao;
        }

        @Override
        protected Void doInBackground(MainList... mainLists) {
            mainListDao.delete(mainLists[0]);
            return null;
        }
    }

    private static class DeleteAllMainListAsyncTask extends AsyncTask<Void, Void, Void>{

        private MainListDao mainListDao;

        private DeleteAllMainListAsyncTask(MainListDao mainListDao){
            this.mainListDao =  mainListDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mainListDao.deleteAllList();
            return null;
        }
    }

}
