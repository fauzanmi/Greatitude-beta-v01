package dev.fathoor.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.fathoor.core.data.local.dao.user.LocalUserDao
import dev.fathoor.core.data.local.dao.user.SessionDao
import dev.fathoor.core.data.local.room.LocalDatabase
import dev.fathoor.core.util.CoreConstant.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Context
    ): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalUserDao(
        database: LocalDatabase
    ): LocalUserDao {
        return database.localUserDao()
    }

    @Provides
    @Singleton
    fun provideSessionDao(
        database: LocalDatabase
    ): SessionDao {
        return database.sessionDao()
    }
}
