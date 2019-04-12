package alimojarrad.rivaltest.models

import alimojarrad.rivaltest.BaseModel


data class Authentication (
    var user_token : String?=null
):BaseModel()


data class Login(
    var email: String?=null,
    var password: String?=null
)