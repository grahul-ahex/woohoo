package com.ahextech.woohoo.homescreen;


import com.ahextech.woohoo.MyApplication;
import com.ahextech.woohoo.POJO.RegistrationReponseModel;
import com.ahextech.woohoo.api.APIService;

/**
 * Created by ahextech on 9/3/18.
 */

public class HomeScreenInteractorImpl implements InteractorInterface {
    onLogOutCompleted listener;
    @Override
    public void onLogOutButtonClicked(onLogOutCompleted listener) {
        this.listener=listener;
        //Call API to delete user token
    }
}
