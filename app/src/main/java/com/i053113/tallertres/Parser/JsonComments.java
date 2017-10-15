package com.i053113.tallertres.Parser;

import com.i053113.tallertres.Models.Comments;
import com.i053113.tallertres.Models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class JsonComments {


// crear un metodo que se encargue del procesado de informacion

    public static List<Comments> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        //crear una lista
        List<Comments> commentsList = new ArrayList<>();

// recorreer la lista con un ciclo for
        for (int i = 0 ; i <jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            // asignamos valores

            Comments comments = new Comments();

            comments.setId(item.getInt("id"));
            comments.setPostId(item.getString("postId"));
            comments.setEmail(item.getString("email"));
            comments.setBody(item.getString("body"));



            commentsList.add(comments);
        }
        // que dede retornar
        return  commentsList;

    }
}
