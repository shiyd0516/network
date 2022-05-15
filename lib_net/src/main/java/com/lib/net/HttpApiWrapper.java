package com.lib.net;

import retrofit2.Retrofit;

public final class HttpApiWrapper {

  private final Retrofit mRetrofit;

  HttpApiWrapper(Retrofit retrofit) {
    this.mRetrofit = retrofit;
  }

  public <T> T createServer(Class<T> serviceClazz) {
    return mRetrofit.create(serviceClazz);
  }
}
