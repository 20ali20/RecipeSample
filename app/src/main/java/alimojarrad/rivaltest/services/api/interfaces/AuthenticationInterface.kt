package alimojarrad.rivaltest.services.api.interfaces

import alimojarrad.rivaltest.models.Authentication
import alimojarrad.rivaltest.models.Login
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by amojarrad on 2/2/18.
 */
interface AuthenticationInterface {

    @POST("login")
    fun Login(@Body login : Login) : Single<Response<Authentication>>

}