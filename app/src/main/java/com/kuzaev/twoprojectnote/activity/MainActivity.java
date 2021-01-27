package com.kuzaev.twoprojectnote.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kuzaev.twoprojectnote.R;
import com.kuzaev.twoprojectnote.adapters.AdapterRecycleBeer;
import com.kuzaev.twoprojectnote.object.Beer;
import com.kuzaev.twoprojectnote.viewModels.MainViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Beer> listBeer;
    private AdapterRecycleBeer adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        {
            listBeer = new ArrayList<>();
            recyclerView = findViewById(R.id.recycleView);
            viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MainViewModel.class);
        }
        adapterInit();
        getData();
        itemTouche();

    }


    public void itemTouche() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeIntPosition(viewHolder.getAdapterPosition(), adapter);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeIntPosition(int position, AdapterRecycleBeer adapter) {
        Beer beer = adapter.getListBeer().get(position);
        viewModel.deleteBeer(beer);
    }

    public void adapterInit() {
        adapter = new AdapterRecycleBeer(listBeer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnBeerClickListener(new AdapterRecycleBeer.OnBeerClickListener() {
            @Override
            public void onLongClick(int position) {
                viewModel.deleteBeer(adapter.getListBeer().get(position));
                Intent intent = new Intent(getApplicationContext(), AddBeerActivity.class);
                intent.putExtra("beer", (Serializable) adapter.getListBeer().get(position));
                startActivity(intent);
            }
        });
    }

    private void getData() {
        LiveData<List<Beer>> notesFromDB = viewModel.getListLiveDataBeers();
        notesFromDB.observe(this, new Observer<List<Beer>>() {
            @Override
            public void onChanged(List<Beer> notesFromLiveData) {
                adapter.setListBeer(notesFromLiveData);
            }
        });
    }

    public void addItemBeer(View view) {
        startActivity(new Intent(this, AddBeerActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}