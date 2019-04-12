package alimojarrad.rivaltest.modules.recipe.detail


import alimojarrad.rivaltest.R
import alimojarrad.rivaltest.models.Recepie
import alimojarrad.rivaltest.models.ServerResponse
import alimojarrad.rivaltest.modules.recipe.RecipeViewModel
import alimojarrad.rivaltest.services.navigation.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sidecarhealth.modules.common.PopupMessage
import kotlinx.android.synthetic.main.fragment_recipe_detail.*


class RecipeDetailFragment : BaseFragment() {

    companion object {
        const val dataKey = "dataKey"
        fun newInstance(recepie: Recepie): RecipeDetailFragment {
            val fragment = RecipeDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(dataKey, recepie)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: RecipeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupInteractions()
        setupViewModel()
        retrieveRecipeDetail()?.let {
            viewModel.getRecipeDetail((it.recipe_id?:0).toString())
        }?:run{
            PopupMessage.createPopupMessage(context!!,"We are having some technical difficulties. Please try again later.")
        }
        this@RecipeDetailFragment.router?.setFragmentReady()
    }


    private fun setupViews() {

    }

    private fun retrieveRecipeDetail() : Recepie?{
        return arguments?.getSerializable(dataKey) as? Recepie
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)

        val recepieObserver = Observer<Recepie> {

            updateView(it)
        }

        val serverResponseObserver = Observer<ServerResponse> {
            it?.let {
                if (it.isSuccessful!!) {

                } else {
                    PopupMessage.createPopupMessage(context!!, it.reason ?: "")
                }
            }
        }
        viewModel.recipe.observe(this, recepieObserver)
        viewModel.serverResponse.observe(this, serverResponseObserver)
    }

    private fun setupInteractions() {
        rd_back.setOnClickListener { this@RecipeDetailFragment.router?.dismiss() }
    }

    private fun updateView(recepie: Recepie) {
        rd_title.text = recepie.recipe_name ?: ""
        retrieveRecipeDetail()?.let {
            Glide.with(this).load(it.recipe_image?.lg_url ?: it.recipe_image?.sm_url).into(rd_image).apply {
                RequestOptions()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.v_error)
            }
        }

        rd_body.text = recepie.recipe_instructions ?: ""
    }


}