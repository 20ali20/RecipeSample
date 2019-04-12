package alimojarrad.rivaltest.services.api.usecases.recepie

import alimojarrad.rivaltest.models.Media
import alimojarrad.rivaltest.models.Recepie
import alimojarrad.rivaltest.models.Results
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import retrofit2.Response

/**
 * Created by amojarrad on 2/2/18.
 */
object GetBothRecepiesAndImages  {
     fun execute(): Observable<Response<Results<Recepie>>> {
        return Observable.zip(
            GetRecepies.execute().toObservable(),
            GetImages.execute().toObservable(),
            BiFunction<Response<Results<Recepie>>,
                    Response<Results<Media>>,
                    Response<Results<Recepie>>>
            { recipes, images ->

                if(recipes.isSuccessful){
                    if(images.isSuccessful){
                        recipes.body()?.results?.let {
                            it.forEach { recipe->
                                images.body()?.results?.let {
                                    it.find { it.recipe_id == recipe.recipe_id }?.let {
                                        recipe.recipe_image = it.img_urls
                                    }
                                }
                            }
                        }
                        recipes
                    }else{
                        return@BiFunction recipes
                    }
                }else{
                    return@BiFunction recipes
                }
            })
    }
}
