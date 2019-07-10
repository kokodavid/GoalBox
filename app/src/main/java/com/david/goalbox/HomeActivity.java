package com.david.goalbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
    public static final String TAG = HomeActivity.class.getSimpleName();
    public ArrayList<Leagues> mLigi = new ArrayList<>();
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    private LeagueListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String season = intent.getStringExtra("season");

        getSeason(season);

    }

    private void getSeason(String season) {
        final BallService BallService = new BallService();
        BallService.findSeason(season, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mLigi = BallService.processResults(response);

                HomeActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] LeagueNames = new String[mLigi.size()];
                        for (int i = 0; i < LeagueNames.length; i++) {
                            LeagueNames[i] = mLigi.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(HomeActivity.this,
                                android.R.layout.simple_list_item_1, LeagueNames);

                        mAdapter = new LeagueListAdapter(getApplicationContext(), mLigi);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}
