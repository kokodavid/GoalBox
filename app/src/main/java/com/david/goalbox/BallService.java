package com.david.goalbox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BallService {

    public static void findSeason(String Season, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BALL_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BALL_SEASON_QUERY_PARAMETER, Season);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("X-RapidAPI-Key", Constants.BallToken)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Leagues> processResults(Response response){
        ArrayList<Leagues> ligi = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject leaguesJSON = new JSONObject(jsonData);
            JSONArray leaguesJSONa = leaguesJSON.getJSONArray("api");
            if (response.isSuccessful()){
                for (int i = 0; i < leaguesJSONa.length(); i++){
                    JSONObject leagJSON = leaguesJSONa.getJSONObject(i);
                    String name = leagJSON.getString("name");
                    String country = leagJSON.getString("country");
                    String season = leagJSON.getString("season");
                    String start = leagJSON.getString("season_start");
                    String end = leagJSON.getString("season_end");
                    String url = leagJSON.getString("logo");




                    Leagues lig = new Leagues(name,country,season, start, end,url
                    );
                    ligi.add(lig);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ligi;
    }


}