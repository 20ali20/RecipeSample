package alimojarrad.rivaltest.models

import alimojarrad.rivaltest.BaseModel


data class Media(
    var recipe_id: Int? = null,
    var img_urls: alimojarrad.rivaltest.models.Image? = null
) : BaseModel()


data class Image(
    var sm_url: String? = null,
    var lg_url: String? = null
) : BaseModel()


