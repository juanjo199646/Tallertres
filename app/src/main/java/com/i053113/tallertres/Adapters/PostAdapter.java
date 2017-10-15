package com.i053113.tallertres.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i053113.tallertres.CommentsActivity;
import com.i053113.tallertres.MainActivity;
import com.i053113.tallertres.Models.Post;
import com.i053113.tallertres.Models.Users;
import com.i053113.tallertres.PostActivity;
import com.i053113.tallertres.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    // pregunta de quiz
    // para que sirve un adpater en recicler view?
    //  R= sirve para  inyectar una vista dentro de mi componente


    List<Post> postList = new ArrayList<>();
    Context context;


    public PostAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // configura el viewholder
        // obtener el archivo item.xml
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pantalla_post,parent, false);

        // pasar los componentes del archivo item.xml para hacer referencia de ellos
       ViewHolder viewHolder =  new ViewHolder(item);

        // retornar el viewhorlder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewId.setText(Integer.toString(postList.get(position).getId()));
        holder.textViewUserId.setText(postList.get(position).getUserId());
        holder.textViewTitle.setText(postList.get(position).getTitle());
        holder.textViewBody.setText(postList.get(position).getBody());


    }




    // el tamaño de los datos que llegan
    @Override
    public int getItemCount() {

        return postList.size();
    }

    // es una clase que hace referencia a los componenetes (item.xml)

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textViewId;
        TextView textViewUserId;
        TextView textViewTitle;
        TextView textViewBody;


        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);

            imageView = (ImageView) item.findViewById(R.id.imageuno);
            textViewId= (TextView) item.findViewById(R.id.id_tv_item_id_user);
            textViewUserId = (TextView) item.findViewById(R.id.id_tv_item_id_userid);
            textViewTitle = (TextView) item.findViewById(R.id.id_tv_item_id_title);
            textViewBody = (TextView) item.findViewById(R.id.id_tv_item_id_body);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, CommentsActivity.class);
            intent.putExtra("postId", postList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);
        }

    }



}
