package com.david.goalbox.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.david.goalbox.R;
import com.david.goalbox.adapters.FixtureAdapter;
import com.david.goalbox.models.Fixtures;
import com.david.goalbox.services.BallService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FixturesActivity extends AppCompatActivity {



    RecyclerView fixturesRecyclerView;
    private FixtureAdapter fixtureAdapter;

    private ArrayList<Fixtures> mFixture = new ArrayList<>();

    SpotsDialog spotsDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        fixturesRecyclerView = findViewById(R.id.fixtures_recycler);

        setTitle("Fixtures");

        ButterKnife.bind(this);

        createProgressDialog();
        initRecyclerView(getIntent().getStringExtra("league_id"));
    }

    public void initRecyclerView(String leagueId)
    {
        final BallService footballService = new BallService();

        footballService.getFixtures(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                FixturesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spotsDialog.hide();

                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mFixture = footballService.processGetFixturesResults(response);

                FixturesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        fixtureAdapter = new FixtureAdapter(mFixture, FixturesActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FixturesActivity.this);
                        fixturesRecyclerView.setLayoutManager(layoutManager);
                        fixturesRecyclerView.setAdapter(fixtureAdapter);
                    }
                });
            }
        },leagueId);
    }

    private void createProgressDialog() {
        new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Loading Fixtures You can Stop once they appear")
                .build()
                .show();
    }


}
