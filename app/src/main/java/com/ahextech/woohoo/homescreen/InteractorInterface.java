package com.ahextech.woohoo.homescreen;

/**
 * Created by ahextech on 9/3/18.
 */

public interface InteractorInterface {
    void onLogOutButtonClicked(onLogOutCompleted listener);

    interface onLogOutCompleted {
        void onLogOutSuccess();

        void onLogOutFailure();
    }
}
