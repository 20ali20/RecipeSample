package alimojarrad.rivaltest.modules.login

import alimojarrad.rivaltest.MyApplication
import alimojarrad.rivaltest.R
import alimojarrad.rivaltest.models.Login
import alimojarrad.rivaltest.models.ServerResponse
import alimojarrad.rivaltest.modules.application.MainActivity
import alimojarrad.rivaltest.services.api.API
import alimojarrad.rivaltest.services.api.ApiManager
import alimojarrad.rivaltest.services.api.ApiTokenType
import alimojarrad.rivaltest.services.navigation.BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sidecarhealth.modules.common.PopupMessage
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            (context as? BaseActivity)?.finish()
        }
    }

    private lateinit var viewModel : LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViewModel()
        setupInteractions()

    }

    private fun setupInteractions(){
        login_button.setOnClickListener {
            validate()
        }

    }

    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val serverResponseObserver = Observer<ServerResponse>{
            login_button.stopLoading()
            if (it.isSuccessful!!) {
                API.initApis(ApiManager(MyApplication.appContext.resources.getString(R.string.base_url),ApiTokenType.PRIVATE))
                MainActivity.startActivity(this)
            } else {
                PopupMessage.createPopupMessage(this, it.reason ?: "")
            }
        }
        viewModel.serverResponse.observe(this,serverResponseObserver)
    }

    private fun validate(){
        var isValid = true
        if(login_username_edittext.text?.isEmpty()==true){
            login_username.error = "Please enter an email address!"
            isValid = false
        }else{
            login_username.error = null
        }
        if(login_password_edittext.text?.isEmpty()==true){
            login_password.error = "Please enter a password!"
            isValid = false
        }else{
            login_password.error = null
        }
        if(isValid){
            login_button.showLoading()
            viewModel.login(Login(login_username_edittext.text.toString(),login_password_edittext.text.toString()))
        }
    }



}
