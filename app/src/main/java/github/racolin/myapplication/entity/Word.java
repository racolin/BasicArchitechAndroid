package github.racolin.myapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;


@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;
    public Word(@NonNull String word) {
        this.mWord = word;
    }

    public String getWord() {
        return mWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return mWord.equals(word.mWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mWord);
    }
}
