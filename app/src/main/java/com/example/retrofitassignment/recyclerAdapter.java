package com.example.retrofitassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.holder> {

    RecyclerView rv;
    private List<model> data;

    public recyclerAdapter(RecyclerView rv, List<model> data) {
        this.rv = rv;
        this.data = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_items,parent,false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.txt.setText(data.get(position).getTitle());
        Picasso.Builder builder=new Picasso.Builder(rv.getContext());
        builder.downloader(new OkHttp3Downloader(rv.getContext()));
        builder.build().load(data.get(position).getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView txt;
        ImageView img;

        public holder(@NonNull View itemView) {
            super(itemView);

            txt=(TextView)itemView.findViewById(R.id.txtView);
            img=(ImageView) itemView.findViewById(R.id.imgView);
        }
    }

}
