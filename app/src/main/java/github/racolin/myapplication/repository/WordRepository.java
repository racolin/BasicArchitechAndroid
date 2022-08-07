package github.racolin.myapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import github.racolin.myapplication.dao.WordDao;
import github.racolin.myapplication.database.WordRoomDatabase;
import github.racolin.myapplication.entity.Word;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
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
