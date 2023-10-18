package org.digitalcampus.oppiamobile.data.user.db.dao

import androidx.room.Dao;
import androidx.room.Query;
import org.digitalcampus.oppiamobile.data.user.db.entity.PreferencesEntity


@Dao
public interface PreferencesDao {

    @Query("SELECT * FROM user_preference")
    fun getAll() : List<PreferencesEntity>

    @Query("SELECT * FROM user_preference WHERE username = :username")
    fun getAllForUser(username : String) : List<PreferencesEntity>

    @Query("SELECT * FROM user_preference WHERE username = :username AND preference = :preferenceKey")
    fun getUserPreference(username: String , preferenceKey: String ) : PreferencesEntity

}
