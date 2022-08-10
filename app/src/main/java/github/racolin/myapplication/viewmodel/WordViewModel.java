package github.racolin.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ActivityScoped;
import github.racolin.myapplication.entity.Word;
import github.racolin.myapplication.repository.WordRepository;

@ActivityScoped
public class WordViewModel extends ViewModel {

    private WordRepository mWordRepository;
    private final LiveData<List<Word>> mAllWords;

    @Inject
    public WordViewModel(WordRepository wordRepository) {
        mWordRepository = wordRepository;
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
