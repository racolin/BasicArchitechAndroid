package github.racolin.myapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import github.racolin.myapplication.entity.Word;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Update
    void update(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
}
