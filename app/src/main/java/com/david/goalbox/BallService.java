package com.david.goalbox;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

}