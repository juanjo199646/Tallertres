package com.i053113.tallertres.Parser;

import com.i053113.tallertres.Models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class JsonUsers {

    // crear un metodo que se encargue del procesado de informacion

    public static List<Users> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        //crear una lista
        List<Users> usersList = new ArrayList<>();

// recorreer la lista con un ciclo for
        for (int i = 0 ; i <jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            JSONObject activity_pantalla_post =item.getJSONObject("address");
            JSONObject activity_pantalla_comments =item.getJSONObject("company");





            // asignamos valores

            Users users = new Users();

            users.setId(item.getInt("id"));
            users.setName(item.getString("name"));
            users.setUsername(item.getString("username"));
            users.setAddress(activity_pantalla_post.getString("city"));
            users.setCompany(activity_pantalla_comments.getString("name"));



            usersList.add(users);
        }
        // que dede retornar
        return  usersList;

    }
}


