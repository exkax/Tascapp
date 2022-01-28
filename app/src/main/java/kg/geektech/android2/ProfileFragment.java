package kg.geektech.android2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geektech.android2.databinding.FragmentHomeBinding;
import kg.geektech.android2.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ActivityResultLauncher<String> pf;
    private Prefs prefs;
    private Uri uri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return  binding.getRoot();
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.usernameEt.setText(prefs.getName());

        binding.ivProfile.setOnClickListener(view1 -> {
            initListener();
        });

    }

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            uri = result.getData().getData();
                            prefs.saveImageUri(String.valueOf(uri));
                            binding.ivProfile.setImageURI(uri);
                        }
                    }
                }
        );


    private void initListener() {
        Intent intent =  new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (prefs.getImageUri() != null) uri = Uri.parse(prefs.getImageUri());
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.ivProfile);
    }


    @Override
    public void onStop() {
        super.onStop();
        prefs.saveUserName(binding.usernameEt.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(requireContext()).load(uri).circleCrop().into(binding.ivProfile);

    }
}