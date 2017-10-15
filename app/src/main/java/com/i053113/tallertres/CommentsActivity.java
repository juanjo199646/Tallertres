package com.i053113.tallertres;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i053113.tallertres.Adapters.CommentsAdapter;
import com.i053113.tallertres.Adapters.UsersAdapter;
import com.i053113.tallertres.Conections.HttpManager;
import com.i053113.tallertres.Models.Comments;
import com.i053113.tallertres.Models.Users;
import com.i053113.tallertres.Parser.JsonComments;
import com.i053113.tallertres.Parser.JsonUsers;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CommentsActivity extends Activity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<Comments> commentsList;
    CommentsAdapter commentsAdapter;
    //PostAdapter postAdapter;
    // List<Post> postList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        // hacer referencia de las variables de encima

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item_comment);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item_comment);



        //orientacion vertical ( simpre estas tres lineaas)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("postId")));
    }

    public void processData() {
        commentsAdapter = new CommentsAdapter(commentsList, getApplicationContext());
        recyclerView.setAdapter(commentsAdapter);
    }


    public Boolean isOnLine() {
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }


    public void loadData(String postId) {
        if (isOnLine()) {
           TaskUsers taskUsers = new TaskUsers();
            taskUsers.execute("https://jsonplaceholder.typicode.com/comments?postId="+ postId);
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public class TaskUsers extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {

            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                commentsList = JsonComments.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }



    }
}
