package com.lib.net.errortransformer;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.lib.net.exception.ApiException;
import com.lib.net.exception.ResultException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import retrofit2.HttpException;

/**
 * Created by shiyadong on 2019/8/23.
 * 处理异常信息.
 */
public final class HttpExceptionEngine {
  /**
   * http网络异常码.
   */
  private static final int ERROR_CODE_400 = 400;//错误请求
  private static final int ERROR_CODE_401 = 401;//未授权
  private static final int ERROR_CODE_403 = 403;//没有权限
  private static final int ERROR_CODE_404 = 404;//没有找到资源
  private static final int ERROR_CODE_405 = 405;//请求方法不被允许
  private static final int ERROR_CODE_406 = 406;//缺少请求头
  private static final int ERROR_CODE_408 = 408;//请求超时
  private static final int ERROR_CODE_410 = 410;//请求资源在服务器不再可用
  private static final int ERROR_CODE_415 = 415;//请求资源或方法或请求体服务器不支持
  private static final int ERROR_CODE_500 = 500;//服务器错误
  private static final int ERROR_CODE_501 = 501;//服务器无法识别请求
  private static final int ERROR_CODE_502 = 502;//服务器无响应
  private static final int ERROR_CODE_503 = 503;//服务器过载
  private static final int ERROR_CODE_504 = 504;//未获取响应
  private static final int ERROR_CODE_505 = 505;//服务器不支持
  private static final int ERROR_CODE_509 = 509;//服务器达到带宽限制

  /**
   * 约定异常.
   */
  public static final class ERROR {
    /**
     * 未知错误.
     */
    public static final int UNKNOWN = -1000;
    /**
     * 解析错误.
     */
    public static final int PARSE_ERROR = -1001;
    /**
     * 网络错误.
     */
    public static final int NETWORK_ERROR = -1002;
    /**
     * http协议错误.
     */
    public static final int HTTP_ERROR = -1003;
  }

  /**
   * 处理异常.
   */
  public static ApiException handleException(Throwable e) {
    if (e instanceof HttpException) {
      //http错误.
      return handleHttpException(e);
    } else if (e instanceof ResultException) {
      //服务端定制的错误.
      return handleResultException(e);
    } else if (e instanceof JsonParseException
        || e instanceof JSONException
        || e instanceof ParseException) {
      //json解析错误.
      return handleJsonException(e);
    } else if (e instanceof ConnectException
        || e instanceof SocketTimeoutException
        || e instanceof ConnectTimeoutException
        || e instanceof UnknownHostException) {
      //网络错误.
      return handleNetworkException(e);
    }
    //未知错误.
    return handleUnknownException(e);
  }

  //处理服务端定制错误.
  private static ApiException handleResultException(Throwable e) {
    ResultException resultException = (ResultException) e;
    return new ApiException(resultException, resultException.code(), resultException.message());
  }

  //未知错误.
  private static ApiException handleUnknownException(Throwable e) {
    return new ApiException(e, ERROR.UNKNOWN, "未知错误");
  }

  //处理数据解析错误.
  private static ApiException handleJsonException(Throwable e) {
    return new ApiException(e, ERROR.PARSE_ERROR, "数据解析错误");
  }

  //处理网络错误信息.
  private static ApiException handleNetworkException(Throwable e) {
    return new ApiException(e, ERROR.NETWORK_ERROR, "未检测到网络,请联网重试");
  }

  //处理http错误信息.
  private static ApiException handleHttpException(Throwable e) {
    HttpException httpException = (HttpException) e;
    switch (httpException.code()) {
      case ERROR_CODE_400:
        return new ApiException(e, ERROR.HTTP_ERROR, "错误请求");
      case ERROR_CODE_401:
        return new ApiException(e, ERROR.HTTP_ERROR, "未授权");
      case ERROR_CODE_403:
        return new ApiException(e, ERROR.HTTP_ERROR, "没有权限");
      case ERROR_CODE_404:
        return new ApiException(e, ERROR.HTTP_ERROR, "没有找到资源");
      case ERROR_CODE_405:
        return new ApiException(e, ERROR.HTTP_ERROR, "请求方法不被允许");
      case ERROR_CODE_406:
        return new ApiException(e, ERROR.HTTP_ERROR, "缺少请求头");
      case ERROR_CODE_408:
        return new ApiException(e, ERROR.HTTP_ERROR, "请求超时");
      case ERROR_CODE_410:
        return new ApiException(e, ERROR.HTTP_ERROR, "请求资源在服务器不再可用");
      case ERROR_CODE_415:
        return new ApiException(e, ERROR.HTTP_ERROR, "请求资源或方法或请求体服务器不支持");
      case ERROR_CODE_500:
        return new ApiException(e, ERROR.HTTP_ERROR, "服务器错误");
      case ERROR_CODE_501:
        return new ApiException(e, ERROR.HTTP_ERROR, "服务器无法识别请求");
      case ERROR_CODE_502:
        return new ApiException(e, ERROR.HTTP_ERROR, "服务器无响应");
      case ERROR_CODE_503:
        return new ApiException(e, ERROR.HTTP_ERROR, "服务器过载");
      case ERROR_CODE_504:
        return new ApiException(e, ERROR.HTTP_ERROR, "未获取响应");
      case ERROR_CODE_505:
        return new ApiException(e, ERROR.HTTP_ERROR, "服务器不支持");
      case ERROR_CODE_509:
        return new ApiException(e, ERROR.HTTP_ERROR, "服务器达到带宽限制");
      default:
        return new ApiException(e, ERROR.HTTP_ERROR, "未知错误");
    }
  }
}
