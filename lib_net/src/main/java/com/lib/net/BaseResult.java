package com.lib.net;

/**
 * 服务端返回数据的标准格式.
 */
public interface BaseResult<T> {
  /**
   * 服务端返回的结果码.
   *
   * @return 结果码.
   */
  public int getCode();

  /**
   * 服务端返回的结果描述信息.
   *
   * @return 结果描述信息.
   */
  public String getMsg();

  /**
   * 服务端返回数据的实际数据.
   *
   * @return 实际数据.
   */
  public T getData();

  /**
   * 返回的数据是否成功.
   *
   * @return 成功  {@code  true};反之  {@code false}.
   */
  public boolean isSuccess();
}
