package alimojarrad.rivaltest.modules.recipe


import alimojarrad.rivaltest.R
import alimojarrad.rivaltest.models.Recepie
import alimojarrad.rivaltest.models.ServerResponse
import alimojarrad.rivaltest.modules.recipe.detail.RecipeDetailFragment
import alimojarrad.rivaltest.services.navigation.BaseFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sidecarhealth.modules.common.AdapterClickListener
import com.sidecarhealth.modules.common.PopupMessage
import com.sidecarhealth.modules.common.adapters.nonPaginated.NonPaginatedAdapterRecylerview
import com.sidecarhealth.modules.common.customItemDecoration.CustomDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_recipe.*
import timber.log.Timber


class WrapContentLinearLayoutManager(context: Context) : LinearLayoutManager(context) {
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Timber.e("CAUGHT $e")
        } catch (e: IllegalStateException) {
            Timber.e("CAUGHT $e")
        } catch (e: IllegalArgumentException) {
            Timber.e("CAUGHT $e")
        }
    }

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}

class RecipeFragment : BaseFragment() {

    companion object {
        fun newInstance(): RecipeFragment {
            return RecipeFragment()
        }
    }

    private lateinit var viewModel: RecipeViewModel
    private lateinit var adapter: NonPaginatedAdapterRecylerview<Recepie>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupInteractions()
        setupRecipeList()
        setupViewModel()
        viewModel.getRecipes()
        recipe_swiprefresh.isRefreshing = true
        this@RecipeFragment.router?.setFragmentReady()
    }


    private fun setupViews() {

    }

    private fun setupRecipeList() {
        val fragTag = "dialog"
        val itemListener = (object : AdapterClickListener {
            override fun onClick(v: View, position: Int) {
                try {
                    adapter.getItem(position)?.let {
                        this@RecipeFragment.router?.display(RecipeDetailFragment.newInstance(it))
                    }


                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        })
        recipe_recyclerview.layoutManager = WrapContentLinearLayoutManager(context!!)
        val dividerItemDecoration = CustomDividerItemDecoration(resources.getDrawable(R.drawable.s_divider, null))
        recipe_recyclerview.addItemDecoration(dividerItemDecoration)
        adapter = NonPaginatedAdapterRecylerview(
            context!!,
            R.layout.item_recipe_list,
            RecipeListViewHolder::class.java,
            itemListener
        )
        recipe_recyclerview.adapter = adapter
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)

        val RecipeObserver = Observer<ArrayList<Recepie>> {
            updateView(it)
        }

        val serverResponseObserver = Observer<ServerResponse> {
            recipe_swiprefresh.isRefreshing = false

            if (it.isSuccessful!!) {

            } else {
                PopupMessage.createPopupMessage(context!!, it.reason ?: "")
            }
        }

        viewModel.recipes.observe(this, RecipeObserver)
        viewModel.serverResponse.observe(this, serverResponseObserver)
    }

    private fun setupInteractions() {
        recipe_swiprefresh.setOnRefreshListener {
            viewModel.getRecipes()
        }
    }

    private fun updateView(recipes: ArrayList<Recepie>) {
        recipe_title.text = "Recipes"
        adapter.updateList(recipes)
    }

}