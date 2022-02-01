package kg.geektech.android2.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.android2.App;
import kg.geektech.android2.MainActivity;
import kg.geektech.android2.R;
import kg.geektech.android2.databinding.FragmentHomeBinding;
import kg.geektech.android2.models.News;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NewsAdapter();
        adapter.addItems(App.getAppDatabase().newsDao().getAll());
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(view1 -> {
            openFragment();
        });
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        News news = (News) result.getSerializable("news");
                        Log.e("TAG", "Успешно" + news.getTitle());
                        adapter.setNewsList(news);
                    }
                });
        initRv();

    }

    private void initRv() {
        binding.newsRv.setAdapter(adapter);

        adapter.setOnItemClick(new NewsAdapter.onItemClick() {
            @Override
            public void onClick(int pos) {

            }

            @Override
            public void onLongClick(int pos) {
                new AlertDialog.Builder(requireContext()).setTitle("Delete?").setMessage("Are you sure?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                News news = adapter.getNews(pos);
                                App.getAppDatabase().newsDao().delete(news);
                                adapter.removeItem(pos);
                            }

                        })
                        .show();
            }
        });


    }


    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_clear) {
//
//        }
        if (item.getItemId() == R.id.action_sort) {
            adapter.setNewsList(App.getAppDatabase().newsDao().sortAll());
            binding.newsRv.setAdapter(adapter);
        }

        if (item.getItemId() == R.id.action_exit) {
            mainActivity.finish();

        }
        return super.onOptionsItemSelected(item);

    }
}

