package kg.geektech.android2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.Objects;

import kg.geektech.android2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id
                        .profileFragment).build();
        navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController,
                appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        openBoardFragment(navController);
        ArrayList<Integer> listFragment = new ArrayList<>();
        listFragment.add(R.id.navigation_home);
        listFragment.add(R.id.navigation_dashboard);
        listFragment.add(R.id.navigation_notifications);
        listFragment.add(R.id.profileFragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (listFragment.contains(destination.getId())) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.GONE);
            }

            if (destination.getId() == R.id.boardFragment) {
                getSupportActionBar().hide();
            } else {
                getSupportActionBar().show();
            }

        });



    }

    private void openBoardFragment(NavController navController) {
        navController.navigate(R.id.boardFragment);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}