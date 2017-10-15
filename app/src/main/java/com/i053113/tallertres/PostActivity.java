package com.i053113.tallertres;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i053113.tallertres.Adapters.PostAdapter;
import com.i053113.tallertres.Conections.HttpManager;
import com.i053113.tallertres.Models.Post;
import com.i053113.tallertres.Parser.JsonPost;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    List<Post> postList;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // hacer referencia de las variables de encima

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item_post);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item_post);
        toolbar = (Toolbar) findViewById(R.id.id_tb_toolbar);


        //orientacion vertical ( simpre estas tres lineaas)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        loadData(Integer.toString(getIntent().getExtras().getInt("userId")));

        showToolbar(getResources().getString(R.string.str_tb_title_p2),true);
    }

    public void showToolbar(String title , boolean upButton){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public void showPantalla2(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showPantalla2();
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pantalla_uno, menu);
        return true;
    }


    public void processData() {
        postAdapter = new PostAdapter(postList, getApplicationContext());
        recyclerView.setAdapter(postAdapter);
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


    public void loadData(String userId) {
        if (isOnLine()) {
           TaskUsers taskUsers = new TaskUsers();
            taskUsers.execute("https://jsonplaceholder.typicode.com/posts?userId="+ userId);
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
                postList = JsonPost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }



    }
}
