package alimojarrad.rivaltest.services.api

import alimojarrad.rivaltest.services.api.interfaces.AuthenticationInterface
import alimojarrad.rivaltest.services.api.interfaces.RecepieInterface

/**
 * Created by amojarrad on 1/29/18.
 */
object API {

    lateinit var authentication: AuthenticationInterface
    lateinit var recepie : RecepieInterface

//    lateinit var care : CareInterface


    fun initAuth(apiManager: ApiManager){
        authentication = apiManager.createAPI(AuthenticationInterface::class.java)
    }

    fun initApis(apiManager: ApiManager){
        recepie = apiManager.createAPI(RecepieInterface::class.java)
    }


}