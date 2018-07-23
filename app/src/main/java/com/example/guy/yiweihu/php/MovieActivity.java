package com.example.guy.yiweihu.php;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.guy.yiweihu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity  extends AppCompatActivity implements  Runnable{

    private static final String TAG = MovieActivity.class.getSimpleName();
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.php_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        movies = new ArrayList<>();
        getMoviesFromDB(1);

        gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);

        adapter = new MoviesAdapter(this, movies);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (gridLayout.findLastCompletelyVisibleItemPosition() == movies.size() - 1) {
                    getMoviesFromDB(movies.get(movies.size() - 1).getId());
                }

            }
        });
    }


    private void getMoviesFromDB(int id) {

//        Toast.makeText(MovieActivity.this, Toast.LENGTH_LONG, id).show();
        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {

            @Override
            protected Void doInBackground(Integer... movieIds) {



                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http:///localhost/zhiweibao/login/movie.php?Id=" + movieIds[0])
                                .build();
                        try {
                            Response response = client.newCall(request).execute();

                            JSONArray array = new JSONArray(response.body().string());

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);

                                Movie movie = new Movie(object.getInt("id"), object.getString("movie_name"),
                                        object.getString("movie_image"), object.getString("movie_genre"));


                                MovieActivity.this.movies.add(movie);

                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }
    @Override
    public void run() {
        getMoviesFromDB(1);

    }


}
