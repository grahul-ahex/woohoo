package com.ahextech.woohoo.homescreen;

/**
 * Created by ahextech on 9/3/18.
 */

public class HomeScreenPresenterImpl implements PresenterInterface, HomeScreenInteractorImpl.onLogOutCompleted {
    private HomeScreenView view;
    private HomeScreenInteractorImpl interactor;

    public HomeScreenPresenterImpl(HomeScreenView homeScreenView) {
        this.view = homeScreenView;
        interactor = new HomeScreenInteractorImpl();
    }

    @Override
    public void onLogOutButtonClicked() {
        interactor.onLogOutButtonClicked(this);
    }

    @Override
    public void onLogOutSuccess() {

    }

    @Override
    public void onLogOutFailure() {

    }
}
