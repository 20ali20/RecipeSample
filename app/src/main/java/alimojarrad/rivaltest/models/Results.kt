package alimojarrad.rivaltest.models

import alimojarrad.rivaltest.BaseModel


data class Results<T> (
    var results : ArrayList<T>?=null
):BaseModel()


