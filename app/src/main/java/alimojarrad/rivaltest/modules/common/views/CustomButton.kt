package alimojarrad.rivaltest.modules.common.views

import alimojarrad.rivaltest.R
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.Button


class CustomButton(context: Context?, attrs: AttributeSet?) : Button(context,attrs) {
    private var prevDrawables : Array<Drawable>?=null
    private  val loadingDrawable  = resources.getDrawable(R.drawable.loading_rotation,null)
    private var objectAnimator : ObjectAnimator?=null




    fun showLoading(color : Int?=null){
     prevDrawables = compoundDrawables
        isEnabled = false
        color?.let {
            loadingDrawable.setColorFilter(it, PorterDuff.Mode.MULTIPLY)
        }
        objectAnimator = ObjectAnimator.ofInt(loadingDrawable,"level",0, 10000)
        objectAnimator!!.repeatCount = ValueAnimator.INFINITE
        objectAnimator!!.interpolator = LinearInterpolator()
        setCompoundDrawablesWithIntrinsicBounds(
                prevDrawables?.get(0),prevDrawables?.get(1),loadingDrawable,prevDrawables?.get(3))
        objectAnimator!!.start()
//        loadingAnimation.start()


    }

    fun stopLoading(){
//        loadingAnimation.stop()
        prevDrawables?.let{
            setCompoundDrawablesWithIntrinsicBounds(it[0],it[1],it[2],it[3])
        }
        objectAnimator?.let {
            it.removeAllListeners()
            it.end()
            it.cancel()
        }
        isEnabled = true

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopLoading()
        prevDrawables = null
        objectAnimator = null
    }


}