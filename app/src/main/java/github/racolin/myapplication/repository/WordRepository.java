package github.racolin.myapplication.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ActivityScoped;
import github.racolin.myapplication.dao.WordDao;
import github.racolin.myapplication.database.WordRoomDatabase;
import github.racolin.myapplication.entity.Word;
@ActivityScoped
public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    @Inject
    public WordRepository(WordRoomDatabase wordRoomDatabase) {
        mWordDao = wordRoomDatabase.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }

    public void delete(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.delete(word);
        });
    }

    public void update(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.update(word);
        });
    }
}
