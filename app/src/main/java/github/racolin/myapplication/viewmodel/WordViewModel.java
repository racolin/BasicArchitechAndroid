package github.racolin.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import github.racolin.myapplication.entity.Word;
import github.racolin.myapplication.repository.WordRepository;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private final LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(getApplication());
        mAllWords = mWordRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mWordRepository.insert(word);
    }

    public void delete(Word word) {
        mWordRepository.delete(word);
    }

    public void update(Word word) {
        mWordRepository.update(word);
    }
}
