package com.i053113.tallertres.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i053113.tallertres.Models.Comments;
import com.i053113.tallertres.Models.Users;
import com.i053113.tallertres.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class CommentsAdapter extends  RecyclerView.Adapter<CommentsAdapter.ViewHolder> {


   // pregunta de quiz
    // para que sirve un adpater en recicler view?
    //  R= sirve para  inyectar una vista dentro de mi componente


    List<Comments> commentsList = new ArrayList<>();
    Context context;


    public CommentsAdapter(List<Comments> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // configura el viewholder
        // obtener el archivo item.xml
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pantalla_comments,parent, false);

        // pasar los componentes del archivo item.xml para hacer referencia de ellos
         ViewHolder  viewHolder=  new ViewHolder(item);

        // retornar el viewhorlder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // encargado de trabajar los componentes del item
        holder.textViewId.setText(Integer.toString(commentsList.get(position).getId()));
        holder.textViewPostId.setText(commentsList.get(position).getPostId());
        holder.textViewEmail.setText(commentsList.get(position).getEmail());
        holder.textViewBody.setText(commentsList.get(position).getBody());

    }


    // el tamaño de los datos que llegan
    @Override
    public int getItemCount() {

        return commentsList.size();
    }

    // es una clase que hace referencia a los componenetes (item.xml)

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewId;
        TextView textViewPostId;
        TextView textViewEmail;
        TextView textViewBody;


        public ViewHolder(View item) {
            super(item);

            imageView = (ImageView) item.findViewById(R.id.imageuno);
            textViewId= (TextView) item.findViewById(R.id.id_tv_item_id_user);
            textViewPostId= (TextView) item.findViewById(R.id.id_tv_item_id_postid);
            textViewEmail = (TextView) item.findViewById(R.id.id_tv_item_id_email);
            textViewBody = (TextView) item.findViewById(R.id.id_tv_item_id_body1);
        }
    }

}


