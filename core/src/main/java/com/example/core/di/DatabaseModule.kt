package com.example.core.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.core.data.source.local.room.GameDao
import com.example.core.data.source.local.room.GameDatabase
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context):GameDatabase {
        val passphrase = SQLiteDatabase.getBytes(
            "dicodingexpertsubmission2wildanfajrialfarabi".toCharArray()
        )
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "Game.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

        @Provides
        fun provideGameDao(gameDatabase:GameDatabase):GameDao = gameDatabase.gameDao()

        @Provides
        fun providePreferences(@ApplicationContext context:Context) :SharedPreferences {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val keyGenSpec = KeyGenParameterSpec.Builder(
                    MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build()
                val masterKey = MasterKey.Builder(context)
                    .setKeyGenParameterSpec(keyGenSpec)
                    .build()
                return EncryptedSharedPreferences.create(
                    context,
                    "LoginSession",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            } else {
                return SecurePreferences(
                    context,
                    "dicodingexpertsubmission2wildanfajrialfarabi",
                "login_prefs")
            }
        }
}