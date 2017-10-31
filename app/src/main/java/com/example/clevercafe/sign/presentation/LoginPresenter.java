package com.example.clevercafe.sign.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.example.clevercafe.App;
import com.example.clevercafe.base.BasePresenter;
import com.example.clevercafe.sign.domain.ILoginInteractor;
import com.google.firebase.FirebaseNetworkException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chudofom on 29.10.17.
 */
@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {
    @Inject
    public ILoginInteractor interactor;
    LoginView loginView = getViewState();

    public LoginPresenter() {
        App.getLoginComponent().inject(this);
    }

    public void loginButtonClicked(String login, String password) {
        interactor.login(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> loginView.startApp(), throwable -> {
                    throwable.printStackTrace();
                    loginView.showProgress(false);
                    if (throwable instanceof FirebaseNetworkException) {
                        loginView.showMessage("Нет соединения с интернетом");
                    } else {
                        loginView.showMessage("Ошибка входа");
                    }
                });
    }
}
