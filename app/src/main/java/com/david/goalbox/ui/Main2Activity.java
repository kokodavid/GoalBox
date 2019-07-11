package com.david.goalbox.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.david.goalbox.R;
import com.david.goalbox.adapters.LeagueAdapter;
import com.david.goalbox.models.Leagues;
import com.david.goalbox.services.BallService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Main2Activity extends AppCompatActivity {


    RecyclerView leagueRecyclerView;


    private LeagueAdapter leagueAdapter;

    private ArrayList<Leagues> mLeagues = new ArrayList<>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Leagues");
        leagueRecyclerView = findViewById(R.id.league_recycler_view);

        createProgressDialog();

        ButterKnife.bind(this);
        initRecyclerView();

    }

    public void initRecyclerView()
    {
        progressDialog.show();
        final BallService footballService = new BallService();
        footballService.getAllLeagues(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Main2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        progressDialog.hide();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mLeagues= footballService.processGetAllLeaguesResults(response);

                Main2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.hide();
                        leagueAdapter = new LeagueAdapter(mLeagues, Main2Activity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Main2Activity.this);
                        leagueRecyclerView.setLayoutManager(layoutManager);
                        leagueRecyclerView.setAdapter(leagueAdapter);
                    }
                });
            }
        });
    }

    private void createProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Fetching Leagues...");
        progressDialog.setCancelable(false);
    }
}
