package alimojarrad.rivaltest.services.api.usecases.recepie

import alimojarrad.rivaltest.models.Recepie
import alimojarrad.rivaltest.models.Results
import alimojarrad.rivaltest.services.api.API
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by amojarrad on 2/2/18.
 */
object GetRecepies  {
     fun execute(): Single<Response<Results<Recepie>>> {
        return API.recepie.getRecepie()
    }
}
