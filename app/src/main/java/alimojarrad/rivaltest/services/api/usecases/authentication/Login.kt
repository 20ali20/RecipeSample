package alimojarrad.rivaltest.services.api.usecases.authentication

import alimojarrad.rivaltest.models.Authentication
import alimojarrad.rivaltest.models.Login
import alimojarrad.rivaltest.services.api.API
import com.sidecarhealth.services.storage.AuthStorage
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by amojarrad on 2/2/18.
 */
object Login  {
     fun execute(login: Login): Single<Response<Authentication>> {

        return API.authentication.Login(login).map {
            it.body()?.let {
                AuthStorage.saveAuthenticationInfo(it)
            }
            it
        }
    }
}
