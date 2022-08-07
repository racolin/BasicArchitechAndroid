package github.racolin.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import github.racolin.myapplication.R;
import github.racolin.myapplication.entity.Word;

public class WordHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public WordHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public static WordHolder create(ViewGroup parent) {
        return new WordHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false));
    }

    public void bind(Word word) {
        textView.setText(word.getWord());
    }
}
