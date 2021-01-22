package com.example.testebliss.repository.googlerepos

import com.example.testebliss.data.network.Result
import com.example.testebliss.data.network.services.GoogleReposService
import com.example.testebliss.models.GoogleRepos

class GoogleReposRepositoryImpl(private val googleReposService: GoogleReposService) :
    GoogleReposRepository {

    override suspend fun getGoogleRepos(googleRepo: String, page:Int, size:Int): Result<List<GoogleRepos>> {
        val response = googleReposService.getGoogleRepos(googleRepo, page, size)
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}