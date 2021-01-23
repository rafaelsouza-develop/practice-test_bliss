package com.example.testebliss.data.local

import androidx.room.*
import com.example.testebliss.models.RepoUserName

@Dao
interface ReposDao {

    @Query("SELECT * FROM repos_user_name")
    fun getAllRepos(): List<RepoUserName>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(repo: RepoUserName)

    @Update
    fun updateRepo(repo: RepoUserName)

    @Delete
    fun deleteRepo(repo: RepoUserName)
}