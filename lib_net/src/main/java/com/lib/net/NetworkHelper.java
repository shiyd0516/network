package com.lib.net;

import com.lib.net.calladapter.RxJavaCallAdapterFactory;
import com.lib.net.converter.BuildConverterFactory;
import com.lib.net.interceptor.HttpLogInterceptor;
import com.lib.net.interceptor.CustomInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class NetworkHelper {

  private final NetworkConfig netConfig;
  private final Retrofit retrofit;

  public static NetworkHelper create(NetworkConfig netConfig) {
    return new NetworkHelper(netConfig);
  }

  private NetworkHelper(NetworkConfig netConfig) {
    this.netConfig = netConfig;
    retrofit = createRetrofit();
  }

  private OkHttpClient createOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(netConfig.getConnectTimeout(), TimeUnit.MILLISECONDS);
    builder.writeTimeout(netConfig.getWriteTimeout(), TimeUnit.MILLISECONDS);
    builder.readTimeout(netConfig.getReadTimeout(), TimeUnit.MILLISECONDS);
    builder.addInterceptor(new CustomInterceptor(netConfig.getHttpInterceptor()));
    builder.addNetworkInterceptor(HttpLogInterceptor.create());
    return builder.build();
  }

  private Retrofit createRetrofit() {
    return new Retrofit.Builder().baseUrl(netConfig.getBaseUrl())
        .client(createOkHttpClient())
        .addConverterFactory(BuildConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  public HttpApiWrapper createAPIWrapper() {
    return new HttpApiWrapper(retrofit);
  }
}
