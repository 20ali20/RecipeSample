package alimojarrad.rivaltest.modules.recipe



import alimojarrad.rivaltest.models.*
import alimojarrad.rivaltest.services.api.usecases.recepie.GetBothRecepiesAndImages
import alimojarrad.rivaltest.services.api.usecases.recepie.GetImages
import alimojarrad.rivaltest.services.api.usecases.recepie.GetRecepieDetail
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sidecarhealth.modules.common.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


/**
 * Created by amojarrad on 2/20/18.
 */
open class RecipeViewModel : ViewModel() {

    var recipes = MutableLiveData<ArrayList<Recepie>>()
    var recipe = MutableLiveData<Recepie>()
    var images = MutableLiveData<ArrayList<Media>>()



    var serverResponse = MutableLiveData<ServerResponse>()
    private var disposable = CompositeDisposable()



    fun getRecipes() {
        disposable.add(
                GetBothRecepiesAndImages.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    if (it.isSuccessful) {
                                        it.body()?.let {
                                            this.recipes.value = it.results
                                            serverResponse.value = ServerResponse(true, "", SR_GETRECIPE)
                                        }
                                    } else {
                                        serverResponse.value = ServerResponse(false, "We could not process your request because ${Utils.network.getErrorMessage(it)}", SR_GETRECIPE)
                                    }
                                },
                                {
                                    Timber.e(it)
                                    serverResponse.value = ServerResponse(false, "We could not process your request.", SR_GETRECIPE)
                                }
                        )
        )
    }

    fun getRecipeDetail(recipeId : String) {
        disposable.add(
            GetRecepieDetail.execute(recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isSuccessful) {
                            it.body()?.let {
                                this.recipe.value = it
                                serverResponse.value = ServerResponse(true, "", SR_GETRECIPE)
                            }
                        } else {
                            serverResponse.value = ServerResponse(false, "We could not process your request because ${Utils.network.getErrorMessage(it)}", SR_GETRECIPE)
                        }
                    },
                    {
                        Timber.e(it)
                        serverResponse.value = ServerResponse(false, "We could not process your request.", SR_GETRECIPE)
                    }
                )
        )
    }

    fun getImages() {
        disposable.add(
            GetImages.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.isSuccessful) {
                            it.body()?.let {
                                this.images.value = it.results
                                serverResponse.value = ServerResponse(true, "", SR_GETIMAGE)
                            }
                        } else {
                            serverResponse.value = ServerResponse(false, "We could not process your request because ${Utils.network.getErrorMessage(it)}", SR_GETIMAGE)
                        }
                    },
                    {
                        Timber.e(it)
                        serverResponse.value = ServerResponse(false, "We could not process your request.", SR_GETIMAGE)
                    }
                )
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}


