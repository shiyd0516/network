package com.lib.net;

import com.lib.net.interceptor.HttpInterceptor;

public final class NetworkConfig {
  private String baseUrl;
  private long connectTimeout = 60 * 1000;
  private long writeTimeout = 60 * 1000;
  private long readTimeout = 60 * 1000;
  private HttpInterceptor httpInterceptor;

  public NetworkConfig setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
    return this;
  }

  public NetworkConfig setConnectTimeout(long connectTimeout) {
    this.connectTimeout = connectTimeout;
    return this;
  }

  public NetworkConfig setWriteTimeout(long writeTimeout) {
    this.writeTimeout = writeTimeout;
    return this;
  }

  public NetworkConfig setReadTimeout(long readTimeout) {
    this.readTimeout = readTimeout;
    return this;
  }

  public NetworkConfig setHttpInterceptor(HttpInterceptor httpInterceptor) {
    this.httpInterceptor = httpInterceptor;
    return this;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public long getConnectTimeout() {
    return connectTimeout;
  }

  public long getWriteTimeout() {
    return writeTimeout;
  }

  public long getReadTimeout() {
    return readTimeout;
  }

  public HttpInterceptor getHttpInterceptor() {
    return httpInterceptor;
  }
}
