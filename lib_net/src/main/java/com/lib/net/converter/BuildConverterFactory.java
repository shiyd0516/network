package com.lib.net.converter;

import androidx.annotation.Nullable;
import com.google.gson.GsonBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public final class BuildConverterFactory extends Converter.Factory {

  private final Converter.Factory jsonFactory;
  private final Converter.Factory xmlFactory;
  private final Converter.Factory stringFactory;

  public static BuildConverterFactory create() {
    return new BuildConverterFactory();
  }

  private BuildConverterFactory() {
    this.jsonFactory = GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create());
    this.xmlFactory = SimpleXmlConverterFactory.create();
    this.stringFactory = ScalarsConverterFactory.create();
  }

  @Nullable @Override
  public Converter<?, RequestBody> requestBodyConverter(Type type,
      Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
    super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    for (Annotation annotation : methodAnnotations) {
      if (annotation instanceof RequestConverter) {
        ConverterFormat format = ((RequestConverter) annotation).format();
        if (ConverterFormat.JSON == format) {
          return jsonFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
        if (ConverterFormat.XML == format) {
          return xmlFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
        if (ConverterFormat.TEXT == format) {
          return stringFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
      }
    }
    //没有添加注解，默认采用Json方式解析
    return jsonFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
  }

  @Nullable @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
      Retrofit retrofit) {
    super.responseBodyConverter(type, annotations, retrofit);
    for (Annotation annotation : annotations) {
      if (annotation instanceof ResponseConverter) {
        ConverterFormat format = ((ResponseConverter) annotation).format();
        if (ConverterFormat.JSON == format) {
          return jsonFactory.responseBodyConverter(type, annotations, retrofit);
        }
        if (ConverterFormat.XML == format) {
          return xmlFactory.responseBodyConverter(type, annotations, retrofit);
        }
        if (ConverterFormat.TEXT == format) {
          return stringFactory.responseBodyConverter(type, annotations, retrofit);
        }
      }
    }
    //没有添加ConverterFormat，默认采用json注解.
    return jsonFactory.responseBodyConverter(type, annotations, retrofit);
  }
}
