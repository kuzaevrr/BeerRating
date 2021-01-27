package com.kuzaev.twoprojectnote.viewModels;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kuzaev.twoprojectnote.database.BeersDatabase;
import com.kuzaev.twoprojectnote.object.Beer;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static BeersDatabase database;
    private LiveData<List<Beer>> listLiveDataBeers;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = BeersDatabase.getInstance(getApplication());
        listLiveDataBeers = database.beerDAO().getAllNote();
    }

    public LiveData<List<Beer>> getListLiveDataBeers() {
        return listLiveDataBeers;
    }

    public void deleteBeer(Beer beer) {
        new DeleteTask().execute(beer);
    }

    public void insertBeer(Beer beer) {
        new InsertTask().execute(beer);
    }

    public void deleteAllBeer() {
        new DeleteAllTask().execute();
    }

    private static class InsertTask extends AsyncTask<Beer, Void, Void> {

        @Override
        protected Void doInBackground(Beer... beers) {
            if (beers != null && beers.length > 0) {
                database.beerDAO().insertBeer(beers[0]);
            }
            return null; //
        }
    }

    private static class DeleteTask extends AsyncTask<Beer, Void, Void> {

        @Override
        protected Void doInBackground(Beer... beers) {
            if (beers != null && beers.length > 0) {
                database.beerDAO().deleteBeer(beers[0]);
            }
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... beers) {
            database.beerDAO().deletedAllBeer();
            return null;
        }
    }
}

