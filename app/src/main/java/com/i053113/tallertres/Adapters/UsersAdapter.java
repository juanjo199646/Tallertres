package com.i053113.tallertres.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i053113.tallertres.Models.Users;
import com.i053113.tallertres.PostActivity;
import com.i053113.tallertres.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    // pregunta de quiz
    // para que sirve un adpater en recicler view?
    //  R= sirve para  inyectar una vista dentro de mi componente


    List<Users> usersList = new ArrayList<>();
    Context context;


    public UsersAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // configura el viewholder
        // obtener el archivo item.xml
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pantalla_users,parent, false);

        // pasar los componentes del archivo item.xml para hacer referencia de ellos
        ViewHolder viewHolder =  new ViewHolder(item);

        // retornar el viewhorlder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // encargado de trabajar los componentes del item
        holder.textViewId.setText(Integer.toString(usersList.get(position).getId()));
        holder.textViewName.setText(usersList.get(position).getName());
        holder.textViewUsername.setText(usersList.get(position).getUsername());



    }


    // el tamaño de los datos que llegan
    @Override
    public int getItemCount() {

        return usersList.size();
    }

    // es una clase que hace referencia a los componenetes (item.xml)

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewId;
        TextView textViewName;
        TextView textViewUsername;


        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            imageView = (ImageView) item.findViewById(R.id.imageuno);
            textViewId = (TextView) item.findViewById(R.id.id_tv_item_id_user);
            textViewName = (TextView) item.findViewById(R.id.id_tv_item_id_name);
            textViewUsername = (TextView) item.findViewById(R.id.id_tv_item_id_username);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra("userId", usersList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);
        }

    }

}
