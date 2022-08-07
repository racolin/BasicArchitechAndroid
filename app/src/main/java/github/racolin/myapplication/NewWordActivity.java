package github.racolin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import github.racolin.myapplication.databinding.ActivityNewWordBinding;

public class NewWordActivity extends AppCompatActivity {

    private ActivityNewWordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListener();
    }

    private void initListener() {
        binding.buttonSave.setOnClickListener(view -> {
            if (!binding.editWord.getText().toString().equals("")) {
                Intent intent = new Intent();
                intent.putExtra("word", binding.editWord.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, R.string.type, Toast.LENGTH_SHORT).show();
            }
        });
    }
}