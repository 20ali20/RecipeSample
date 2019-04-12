package alimojarrad.rivaltest.models

import alimojarrad.rivaltest.BaseModel


data class Recepie(
    var recipe_id: Int? = null,
    var recipe_name: String? = null,
    var recipe_instructions: String? = null,
    var recipe_image: Image? = null
) : BaseModel()


