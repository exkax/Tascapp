package kg.geektech.android2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektech.android2.R;
import kg.geektech.android2.databinding.ItemNewsBinding;
import kg.geektech.android2.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> newsList = new ArrayList<>();

    private onItemClick onItemClick;
    private ItemNewsBinding binding;



    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setNewsList(ArrayList<News> newsL) {
        this.newsList = newsL;
    }

    public void deleteNews(int pos) {
        this.newsList.remove(pos);
        notifyItemRemoved(pos);
    }

    public  News  getNews(int pos){
        return newsList.get(pos);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,
        false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(newsList.get(position));

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            initListeners();


            itemView.getRoot().setOnLongClickListener(v -> {
                onItemClick.onLongClick(getAdapterPosition());
                return true;
            });

            itemView.getRoot().setOnClickListener(v -> {
                onItemClick.onClick(getAdapterPosition());
            });

        }


        public void onBind(News news) {
             binding.titleTv.setText(news.getTitle());
             binding.dateTv.setText(news.getCreatedAt());
        }
        private void initListeners() {
            binding.getRoot().setOnLongClickListener(view ->
                    true);binding.getRoot().setOnClickListener (v -> {

            });

        }
    }


    public interface onItemClick{
        void onClick(int pos);
        void onLongClick(int pos);

    }
}
