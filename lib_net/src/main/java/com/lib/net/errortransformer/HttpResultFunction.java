package com.lib.net.errortransformer;

import com.lib.net.BaseResult;
import com.lib.net.exception.ResultException;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 拦截固定格式的公共数据类型{@link BaseResult} BaseResult<T>,判断里面的状态码.
 */
public final class HttpResultFunction<T> implements Function<BaseResult<T>, T> {
  @NonNull @Override
  public T apply(@NonNull BaseResult<T> result) throws Exception {
    //对请求结果进行判断，
    // 如果请求码是失败，则证明服务端返回了错误信息;可以根据与服务端约定好的错误码去解析异常.
    if (result.isSuccess()) {
      //服务器请求成功，返回服务器返回的数据.
      return result.getData();
    }
    //如果服务器端有错误信息返回，那么抛出ResultException异常，让下面的方法去捕获异常做统一处理.
    throw new ResultException(result.getCode(), result.getMsg());
  }
}
