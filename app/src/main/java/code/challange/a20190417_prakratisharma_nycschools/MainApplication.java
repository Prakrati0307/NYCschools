package code.challange.a20190417_prakratisharma_nycschools;

import android.app.Application;

import code.challange.a20190417_prakratisharma_nycschools.network.ApiManager;


public class MainApplication extends Application {

    public static ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        apiManager = ApiManager.getInstance();
    }
}
