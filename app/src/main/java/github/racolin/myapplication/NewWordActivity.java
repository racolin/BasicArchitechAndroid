package github.racolin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import github.racolin.myapplication.databinding.ActivityNewWordBinding;
import github.racolin.myapplication.repository.WordRepository;

@AndroidEntryPoint
public class NewWordActivity extends AppCompatActivity {

    private ActivityNewWordBinding binding;
    @Inject
    WordRepository r1;
    @Inject
    WordRepository r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d("debug", r1.toString());
        Log.d("debug", r2.toString());

        initListener();
    }

    private void initListener() {
        binding.buttonSave.setOnClickListener(view -> {
//            if (!binding.editWord.getText().toString().equals("")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
//                intent.putExtra("word", binding.editWord.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//            } else {
//                Toast.makeText(this, R.string.type, Toast.LENGTH_SHORT).show();
//            }
        });
    }
}