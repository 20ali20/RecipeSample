package alimojarrad.rivaltest

import alimojarrad.rivaltest.modules.login.LoginActivity
import alimojarrad.rivaltest.services.navigation.BaseActivity
import android.os.Bundle


/**
 * Created by amojarrad on 1/29/18.
 */
class SplashActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginActivity.startActivity(this)
    }


}