package alimojarrad.rivaltest

import alimojarrad.rivaltest.services.api.API
import alimojarrad.rivaltest.services.api.ApiManager
import alimojarrad.rivaltest.services.api.ApiTokenType
import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import timber.log.Timber



class MyApplication : Application() {
    companion object {
        private const val TAG = "MyApplication"
        lateinit var appContext: Context
            private set
    }


    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
        API.initAuth(ApiManager(appContext.resources.getString(R.string.base_url),ApiTokenType.OPEN))
    }

}