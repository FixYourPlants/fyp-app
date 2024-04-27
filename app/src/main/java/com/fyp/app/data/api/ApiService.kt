package com.fyp.app.data.api

import com.fyp.app.data.model.Illness
import com.fyp.app.data.model.Plant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("api/v1/plants/")
    suspend fun getPlants():ArrayList<Plant>
    @GET("plants/{plantId}")
    suspend fun getPlantById(@Path("plantId") plantId:Int): Plant
    @GET("plants/{userId}/fav")
    suspend fun getFavPlants(@Path("userId") userId:Int):ArrayList<Plant>
    @POST("plants/{userId}/fav/{plantId}")
    suspend fun addFavPlant(@Path("userId") userId:Int, @Path("plantId") plantId:Int):Plant



    @GET("illness/list")
    suspend fun getIllnesses():ArrayList<Illness>
    @GET("illnes/{illnesId}")
    suspend fun getIllness():Illness

    /*
FUNCIONES DE LA API
fun login(username, token)
fun logout(username)

fun getPlants() -> List<Plants>
fun getPlant(id) -> Plant
fun getFavoritePlants(idUser) -> List<Plants>
fun addPlantFavorites(idPlant,idUser) -> None

fun getOpinions(idPlant) -> List<Opinions>
fun postOpinion(idPlant,idUser, opinion) -> None

fun getIllnesses() -> List<Illness>
fun getIllness(id) -> Illness

fun getPlaguesAlert() -> TODO; still WIP

fun getDiaries(idUser) -> TODO; still WIP
fun getPages(idDiary) -> TODO; still WIP
fun getPage(id) -> TODO; still WIP
fun postPage(idDiary, page) -> TODO; still WIP

fun getUserProfile(id) -> User
fun updateUserProfile(id, user) -> None
fun getHistory(idUser) -> TODO; still WIP
fun updatePassword(idUser, oldPassword, newPassword) -> None

 */
}