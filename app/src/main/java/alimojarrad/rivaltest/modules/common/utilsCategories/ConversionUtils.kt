package alimojarrad.rivaltest.modules.common.utilsCategories

import android.content.Context


object ConversionUtils {



    fun getDpFromInt(context: Context, dpValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        val pixels = (dpValue * scale + 0.5f)
        return pixels.toInt()
    }

}