package github.racolin.myapplication;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import github.racolin.myapplication.database.WordRoomDatabase;

@Module
@InstallIn(ActivityComponent.class)
public class MiniModule {
    @Provides
    WordRoomDatabase getDatabase(@ApplicationContext Context context) {
        return WordRoomDatabase.getDatabase(context);
    }
}

