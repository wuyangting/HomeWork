package com.example.adapterfeng;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableError;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
String BASE_URL="https://www.wanandroid.com/";
@GET("project/list/1/json")
Observable<Bean> getDta(@Query("cid") String cid);
}
