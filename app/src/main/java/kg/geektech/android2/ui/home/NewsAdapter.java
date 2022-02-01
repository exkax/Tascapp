package kg.geektech.android2.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.android2.App;
import kg.geektech.android2.R;
import kg.geektech.android2.databinding.ItemNewsBinding;
import kg.geektech.android2.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList = new ArrayList<>();
    private onItemClick onItemClick;
    private ItemNewsBinding binding;
    private News news;

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setNewsList(News news) {
        newsList.add(news);
        notifyItemInserted(newsList.size());
    }

    public News getNews(int pos) {
        return newsList.get(pos);
    }
    public void removeItem(int pos) {
        newsList.remove(pos);
        notifyItemRemoved(pos);
    }

    public void setNewsList(List<News> list) {
        this.newsList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
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

    public void addItems(List<News> list) {
        this.newsList = list;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            itemView.getRoot().setOnClickListener(v -> {
                onItemClick.onClick(getAdapterPosition());
            });
            itemView.getRoot().setOnLongClickListener(v -> {
                onItemClick.onLongClick(getAdapterPosition());
                return true;
            });

        }


        public void onBind(News news) {
            binding.titleTv.setText(news.getTitle());
            binding.dateTv.setText(news.getCreatedAt());
        }

    }


    public interface onItemClick {
        void onClick(int pos);
        void onLongClick(int pos);
    }
}
