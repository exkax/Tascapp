package kg.geektech.android2;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import kg.geektech.android2.databinding.ActivityMainBinding;
import kg.geektech.android2.databinding.FragmentHomeBinding;
import kg.geektech.android2.databinding.FragmentNewsBinding;
import kg.geektech.android2.models.News;

@RequiresApi(api = Build.VERSION_CODES.O)

public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;

    private News news;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return  binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       initListener();

    }

    private void initListener() {
        binding.btnSave.setOnClickListener(view1 -> {
            save();
        });
    }

    private void save() {
        Bundle bundle = new Bundle();
        String title = binding.editText.getText().toString();

        if (title.isEmpty()){
            Toast.makeText(requireContext(), "TYPE", Toast.LENGTH_SHORT).show();

        }

            long createdAd = System.currentTimeMillis();
            ZonedDateTime time = Instant.ofEpochMilli(createdAd).atZone(ZoneId.of("Asia/Bishkek"));
            String format = time.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"));
            news = new News(title, format);

        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}