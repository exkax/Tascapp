package kg.geektech.android2.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

import kg.geektech.android2.R;
import kg.geektech.android2.databinding.FragmentHomeBinding;
import kg.geektech.android2.models.News;

public class HomeFragment extends Fragment  {


    private FragmentHomeBinding binding;
    private NewsAdapter adapter = new NewsAdapter();

    private ArrayList<News> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        initFragmentResultListener();
        initRv();

    }


    private void initFragmentResultListener() {
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result)
                    {
                        News news = (News) result.getSerializable("news");
                        Log.e("TAG","Успешно" + news.getTitle());
                        list.add(news);
                        adapter.setNewsList(list);
                        binding.newsRv.setAdapter(adapter);
                        initRv();

                    }
                });
    }

    private void initRv() {
        adapter = new NewsAdapter();
        adapter.setOnItemClick(new NewsAdapter.onItemClick() {
            @Override
            public void onLongClick(int pos) {
                newsAlertDialog(pos);
            }

            @Override
            public void onClick(int pos) {

            }
        });


    }

    private void newsAlertDialog(int pos) {
        new AlertDialog.Builder(getContext()).setTitle("Delete?").setMessage("Are you sure?").setNegativeButton("No", null).
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                                News news;
//                                news = adapter.getNews(position);
                        Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show();
                        adapter.deleteNews(pos);

                        }

                }).show();
        Toast.makeText(requireContext(), "alosolsal", Toast.LENGTH_SHORT).show();
    }

    private void initListeners() {
        binding.fab.setOnClickListener(view1 -> {
            openFragment();
        });


    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

}