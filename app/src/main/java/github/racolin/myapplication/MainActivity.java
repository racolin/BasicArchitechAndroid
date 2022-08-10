package github.racolin.myapplication;

import static android.content.Intent.ACTION_VIEW;
import static android.content.Intent.CATEGORY_BROWSABLE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.Room;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import github.racolin.myapplication.adapter.WordAdapter;
import github.racolin.myapplication.databinding.ActivityMainBinding;
import github.racolin.myapplication.entity.Word;
import github.racolin.myapplication.repository.WordRepository;
import github.racolin.myapplication.viewmodel.WordViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    WordViewModel viewModel;
    private ActivityMainBinding binding;
    private WordAdapter adapter;
    @Inject
    WordRepository r1;
    @Inject
    WordRepository r2;

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String word = result.getData().getStringExtra("word");
//                        viewModel.insert(new Word(word));
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("debug", r1.toString());
        Log.d("debug", r2.toString());

        viewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.submitList(words);
            }
        });

        adapter = new WordAdapter(new WordAdapter.WordDiff());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        initListener();
    }

    private void initListener() {
        binding.fab.setOnClickListener((view -> {
          Intent intent = new Intent(this, NewWordActivity.class);
          launcher.launch(intent);
        }));
    }
}