package com.lib.net.calladapter;

import androidx.annotation.Nullable;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public final class RxJavaCallAdapterFactory extends CallAdapter.Factory {

  private final RxJava2CallAdapterFactory callAdapterFactory;

  public static RxJavaCallAdapterFactory create(){
    return new RxJavaCallAdapterFactory();
  }

  private RxJavaCallAdapterFactory() {
    callAdapterFactory = RxJava2CallAdapterFactory.create();
  }

  @Nullable @Override public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
    return callAdapterFactory.get(returnType, annotations, retrofit);
  }
}
