package com.sidecarhealth.services.storage

import alimojarrad.rivaltest.MyApplication
import alimojarrad.rivaltest.models.Authentication
import android.content.Context

/**
 * Created by amojarrad on 2/5/18.
 */
object AuthStorage {

    fun saveAuthenticationInfo(authentication: Authentication) {
        val context = MyApplication.appContext
        val mPrefs = context.getSharedPreferences("authInfo", Context.MODE_PRIVATE)
        val prefsEditor = mPrefs?.edit()
        prefsEditor?.putString("token", authentication.user_token)
        prefsEditor?.commit()
    }


    fun getToken(): String? {
        val mPrefs = MyApplication.appContext.getSharedPreferences("authInfo", Context.MODE_PRIVATE)
        return mPrefs?.getString("token", null)
    }

}