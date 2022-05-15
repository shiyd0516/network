package com.lib.net.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public final class HttpLogInterceptor implements Interceptor {

  private final HttpLoggingInterceptor httpLoggingInterceptor;

  public static HttpLogInterceptor create() {
    return new HttpLogInterceptor();
  }

  private HttpLogInterceptor() {
    httpLoggingInterceptor = new HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Override public Response intercept(Chain chain) throws IOException {
    return httpLoggingInterceptor.intercept(chain);
  }
}
