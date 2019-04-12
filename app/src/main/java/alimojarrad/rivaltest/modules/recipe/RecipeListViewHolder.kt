package alimojarrad.rivaltest.modules.recipe

import alimojarrad.rivaltest.R
import alimojarrad.rivaltest.models.Recepie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sidecarhealth.modules.common.AdapterClickListener
import com.sidecarhealth.modules.common.views.BaseViewHolder
import kotlinx.android.synthetic.main.item_recipe_list.view.*

class RecipeListViewHolder(itemView: View, val itemListeners: AdapterClickListener) : BaseViewHolder<Recepie>(itemView, itemListeners) {

    override fun getView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_list, parent, false)
    }


    override fun bind(item: Recepie, position: Int) {
        itemView.irl_name.text = item.recipe_name
        Glide.with(itemView).load(item.recipe_image?.sm_url?:item.recipe_image?.lg_url).into(itemView.irl_image).apply {
            RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.v_error)
        }

        itemView.setOnClickListener {
            itemListeners.let {
                it.onClick(itemView, position)
            }
        }


    }
}
