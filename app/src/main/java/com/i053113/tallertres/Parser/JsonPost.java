package com.i053113.tallertres.Parser;

import com.i053113.tallertres.Models.Post;
import com.i053113.tallertres.Models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class JsonPost {


    // crear un metodo que se encargue del procesado de informacion

    public static List<Post> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        //crear una lista
        List<Post> postList = new ArrayList<>();

// recorreer la lista con un ciclo for
        for (int i = 0 ; i <jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            // asignamos valores

            Post post = new Post();

            post.setId(item.getInt("id"));
            post.setUserId(item.getString("userId"));
            post.setTitle(item.getString("title"));
            post.setBody(item.getString("body"));


            postList.add(post);
        }
        // que dede retornar
        return  postList;

    }
}
