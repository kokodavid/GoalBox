package com.david.goalbox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.goalbox.R;
import com.david.goalbox.models.Leagues;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeagueListAdapter extends RecyclerView.Adapter<LeagueListAdapter.RestaurantViewHolder> {
    private ArrayList<Leagues> mligis = new ArrayList<>();
    private Context mContext;

    public LeagueListAdapter(Context context, ArrayList<Leagues> restaurants) {
        mContext = context;
        mligis = restaurants;
    }

    @NonNull
    @Override
    public LeagueListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.league_list_item, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int i) {
        restaurantViewHolder.bindRestaurant(mligis.get(i));

    }

    @Override
    public int getItemCount() {
        return mligis.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ImageView)ImageView mImageView;
        @BindView(R.id.restaurantNameTextView)TextView mNameTextView;
        @BindView(R.id.countryTextView)TextView mCountryTextView;

        private Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRestaurant(Leagues league) {
            mNameTextView.setText(league.getName());
            mCountryTextView.setText(league.getCountry());

        }
    }
}