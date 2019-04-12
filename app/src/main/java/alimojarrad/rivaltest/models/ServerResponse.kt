package alimojarrad.rivaltest.models

import androidx.annotation.IntDef

/**
 * Created by amojarrad on 3/21/18.
 */
data class ServerResponse(
        var isSuccessful: Boolean? = false,
        var reason: String? = "",
        var type: Int? = SR_GETRECIPE

)

@Retention(AnnotationRetention.SOURCE)
@IntDef(
        SR_GETRECIPE,
        SR_GETIMAGE,
        SR_LOGIN
)
annotation class ServerResponseType

const val SR_GETRECIPE = 0
const val SR_GETIMAGE = 1
const val SR_LOGIN = 2



data class ErrorDom(
        var message: String? = null
)

