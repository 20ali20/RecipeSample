package alimojarrad.rivaltest.services.api.interfaces

import alimojarrad.rivaltest.models.Media
import alimojarrad.rivaltest.models.Recepie
import alimojarrad.rivaltest.models.Results
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by amojarrad on 2/2/18.
 */
interface RecepieInterface {


    @GET("recipes")
    fun getRecepie(): Single<Response<Results<Recepie>>>

    @GET("recipes/{recipeId}")
    fun getRecipeDetail(@Path("recipeId") recipeId: String): Single<Response<Recepie>>

    @GET("recipe-images")
    fun getImages(): Single<Response<Results<Media>>>

}