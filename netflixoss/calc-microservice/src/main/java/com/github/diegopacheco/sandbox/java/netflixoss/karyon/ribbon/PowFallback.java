package com.github.diegopacheco.sandbox.java.netflixoss.karyon.ribbon;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rx.Observable;

import java.util.Map;

public class PowFallback implements FallbackHandler<ByteBuf> {

    private Double firstValue;
    private Double secondValue;

    public PowFallback(Double firstValue, Double secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
        return Observable.just(Unpooled.wrappedBuffer(String.valueOf(Math.pow(firstValue, secondValue)).getBytes()));
    }
}
