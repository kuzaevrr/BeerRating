package com.kuzaev.twoprojectnote.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.kuzaev.twoprojectnote.R;
import com.kuzaev.twoprojectnote.object.Beer;
import com.kuzaev.twoprojectnote.viewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddBeerActivity extends AppCompatActivity {

    private EditText editText;
    private Spinner spinnerPrice;
    private Spinner spinnerRating;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        {
            editText = findViewById(R.id.editTextTextPersonName);
            spinnerPrice = findViewById(R.id.spinnerPrice);
            spinnerRating = findViewById(R.id.spinnerRating);
            mainViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MainViewModel.class);
        }



        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            integers.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, integers);
        spinnerPrice.setAdapter(adapter);

        Beer beer;
        if((beer = beer())!=null){
            editText.setText(beer.getTitleBeer());
            int spinnerPosition = adapter.getPosition(beer.getPriceBeer());
            spinnerPrice.setSelection(spinnerPosition);
            spinnerRating.setSelection(beer.getRatingBeer()-1);
        }

    }



    public Beer beer(){
        Beer beer = (Beer) getIntent().getSerializableExtra("beer");
        return beer;
    }

    public void addItem(View view) {
        String title = editText.getText().toString().trim();
        int price = spinnerPrice.getSelectedItemPosition() + 1;
        int rating = spinnerRating.getSelectedItemPosition() +1 ;
        mainViewModel.insertBeer(new Beer(title, price, rating));
        finish();
    }
}