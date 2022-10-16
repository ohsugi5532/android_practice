package com.practice.hiltapplicationSample.infrastructures.dao

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "joe_schmoe")
data class JoeSchmoe (
    @PrimaryKey val name: String,
    @ColumnInfo(name = "svg") val svg: String,
)

@Dao
interface JoeSchmoeDao {
    @Query("SELECT * FROM joe_schmoe")
    suspend fun findAll(): List<JoeSchmoe>

    @Insert
    suspend fun insert(joeSchmoe: JoeSchmoe)

    @Delete
    suspend fun delete(joeSchmoe: JoeSchmoe)
}
