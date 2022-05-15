package com.lib.net.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public final class CustomInterceptor implements Interceptor {

  private final HttpInterceptor interceptor;

  public CustomInterceptor(HttpInterceptor interceptor) {
    this.interceptor = interceptor;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    return interceptor.intercept(chain);
  }
}
