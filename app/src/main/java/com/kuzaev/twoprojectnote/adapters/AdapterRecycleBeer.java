package com.kuzaev.twoprojectnote.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kuzaev.twoprojectnote.R;
import com.kuzaev.twoprojectnote.object.Beer;

import java.util.List;

public class AdapterRecycleBeer extends RecyclerView.Adapter<AdapterRecycleBeer.BeerViewHolder> {

    List<Beer> listBeer;
    private OnBeerClickListener onBeerClickListener;

    public void setOnBeerClickListener(OnBeerClickListener onBeerClickListener) {
        this.onBeerClickListener = onBeerClickListener;
    }

    public AdapterRecycleBeer(List<Beer> listBeer) {
        this.listBeer = listBeer;
    }

    public interface OnBeerClickListener {
        void onLongClick(int position);
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder holder, int position) {
        Beer beer = listBeer.get(position);
        holder.titleBeer.setText(beer.getTitleBeer());
        holder.priceBeer.setText(String.format("%s руб.", beer.getPriceBeer()));
        holder.ratingBeer.setText(String.valueOf(beer.getRatingBeer()));
    }

    @Override
    public int getItemCount() {
        return listBeer.size();
    }

    class BeerViewHolder extends RecyclerView.ViewHolder {

        private TextView titleBeer;
        private TextView priceBeer;
        private TextView ratingBeer;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            titleBeer = itemView.findViewById(R.id.titleBeer);
            priceBeer = itemView.findViewById(R.id.priceBeer);
            ratingBeer = itemView.findViewById(R.id.ratingBeer);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onBeerClickListener != null) {
                        onBeerClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    public List<Beer> getListBeer() {
        return listBeer;
    }

    public void setListBeer(List<Beer> listBeer) {
        this.listBeer = listBeer;
        notifyDataSetChanged();
    }
}
