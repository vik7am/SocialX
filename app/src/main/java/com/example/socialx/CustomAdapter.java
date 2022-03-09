package com.example.socialx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialx.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView textTitle, textSource, text_time, text_description;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            text_time = itemView.findViewById(R.id.text_time);
            textSource = itemView.findViewById(R.id.text_source);
            text_description = itemView.findViewById(R.id.text_description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(Context context, List<NewsHeadlines> headlines) {
        this.context = context;
        this.headlines = headlines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textTitle.setText(headlines.get(position).getTitle());
        holder.textSource.setText(headlines.get(position).getSource().getName());
        holder.text_time.setText(headlines.get(position).getPublishedAt().substring(11,16));
        holder.text_description.setText(headlines.get(position).getDescription());
        if(headlines.get(position).getUrlToImage() != null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
