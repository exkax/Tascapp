package kg.geektech.android2;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import kg.geektech.android2.databinding.FragmentBoardBinding;
import kg.geektech.android2.databinding.FragmentProfileBinding;
import kg.geektech.android2.ui.board.BoardAdapter;


public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;
    private BoardAdapter adapter;
    private String[] tabText = {"first", "second", "third",};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();

    }

    private void skip() {
        NavController navController = Navigation.findNavController(
                requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    private void initViewPager() {
        adapter = new BoardAdapter();
        binding.boardVp.setAdapter(adapter);
//        new TabLayoutMediator(binding.tabLayout, binding.boardVp, (tab, position) -> {
//            tab.setText(tabText[position]);
//
//        }).attach();
    }

}