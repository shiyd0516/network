package com.lib.net.errortransformer;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 处理OnError事件的拦截器，在这个拦截器中将异常信息转化为友好的提示.
 */
public final class HttpErrorFunction<T> implements Function<Throwable, Flowable<T>> {
  @NonNull @Override
  public Flowable<T> apply(@NonNull Throwable throwable) throws Exception {
    //使用ExceptionEngine异常处理驱动器处理异常异常信息.
    return Flowable.error(HttpExceptionEngine.handleException(throwable));
  }
}
