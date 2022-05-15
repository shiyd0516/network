package com.lib.net.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor {

  private final Map<String, String> headers = new HashMap<>();

  public final Response intercept(Interceptor.Chain chain) throws IOException {
    Request request = chain.request();
    //处理请求头
    onRequestHeader(request.headers().toMultimap());

    Request.Builder builder = request.newBuilder();
    Set<Map.Entry<String, String>> entries = headers.entrySet();
    for (Map.Entry<String, String> entry : entries) {
      builder.addHeader(entry.getKey(), entry.getValue());
    }

    return chain.proceed(builder.build());
  }

  public void onRequestHeader(Map<String, List<String>> headers) {

  }

  protected void addHeader(String name, String value) {
    headers.put(name, value);
  }
}
