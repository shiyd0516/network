package com.lib.net.errortransformer;

import com.lib.net.BaseResult;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.annotations.NonNull;
import org.reactivestreams.Publisher;

/**
 * 数据解析层拦截器.
 */
public final class HttpTransformer<T> implements FlowableTransformer<BaseResult<T>, T> {
  @NonNull @Override
  public Publisher<T> apply(@NonNull Flowable<BaseResult<T>> upstream) {
    return upstream.map(new HttpResultFunction<>()).onErrorResumeNext(new HttpErrorFunction<>());
  }
}
