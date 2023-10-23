package org.digitalcampus.oppiamobile.data.config.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    /**
     * If the item is replaced, a new _id will be assigned automatically. It is internally a delete/insert operation
     * @return the row id assigned
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<T>)

    /**
     * If the item exists, the operation is aborted
     * @return the row id assigned
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIfNotExists(item: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIfNotExists(list: List<T>)

    /**
     * @return number of rows updated
     */
    @Update
    suspend fun update(vararg item: T): Int

    /**
     * @return number of rows updated
     */
    @Update
    suspend fun updateAll(list: List<T>): Int

    @Delete
    suspend fun delete(vararg item: T): Int

    @Delete
    suspend fun deleteAll(list: List<T>): Int
}