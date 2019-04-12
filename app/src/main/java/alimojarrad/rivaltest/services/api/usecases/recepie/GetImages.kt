package alimojarrad.rivaltest.services.api.usecases.recepie

import alimojarrad.rivaltest.models.Media
import alimojarrad.rivaltest.models.Results
import alimojarrad.rivaltest.services.api.API
import io.reactivex.Single
import retrofit2.Response

/**
 * Created by amojarrad on 2/2/18.
 */
object GetImages  {
     fun execute(): Single<Response<Results<Media>>> {
        return API.recepie.getImages()
    }
}
