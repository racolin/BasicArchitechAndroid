package github.racolin.myapplication;

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

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import github.racolin.myapplication.adapter.WordAdapter;
import github.racolin.myapplication.databinding.ActivityMainBinding;
import github.racolin.myapplication.entity.Word;
import github.racolin.myapplication.viewmodel.WordViewModel;

public class MainActivity extends AppCompatActivity {

    private WordViewModel viewModel;
    private ActivityMainBinding binding;
    private WordAdapter adapter;

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String word = result.getData().getStringExtra("word");
                        viewModel.insert(new Word(word));
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, ViewModelProvider
                    .AndroidViewModelFactory
                    .getInstance(getApplication()))
                .get(WordViewModel.class);

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
            launcher.launch(new Intent(MainActivity.this, NewWordActivity.class));
        }));
    }
}