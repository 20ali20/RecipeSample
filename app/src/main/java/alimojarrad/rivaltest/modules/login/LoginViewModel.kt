package alimojarrad.rivaltest.modules.login



import alimojarrad.rivaltest.models.Login
import alimojarrad.rivaltest.models.SR_LOGIN
import alimojarrad.rivaltest.models.ServerResponse
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
open class LoginViewModel : ViewModel() {





    var serverResponse = MutableLiveData<ServerResponse>()
    private var disposable = CompositeDisposable()


    fun login(login: Login) {
        disposable.add(
                alimojarrad.rivaltest.services.api.usecases.authentication.Login.execute(login)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    if (it.isSuccessful) {
                                        it.body()?.let {
                                            serverResponse.value = ServerResponse(true, "", SR_LOGIN)
                                        }
                                    } else {
                                        serverResponse.value = ServerResponse(false, "We could not process your request because ${Utils.network.getErrorMessage(it)}", SR_LOGIN)
                                    }
                                },
                                {
                                    Timber.e(it)
                                    serverResponse.value = ServerResponse(false, "We could not process your request.", SR_LOGIN)
                                }
                        )
        )
    }



    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}


