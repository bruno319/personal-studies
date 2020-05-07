package com.github.diegopacheco.sandbox.java.netflixoss.karyon.ribbon;

import java.nio.charset.Charset;

import com.google.inject.Injector;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.guice.LifecycleInjectorBuilder;
import com.netflix.ribbon.ClientOptions;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonResponse;
import com.netflix.ribbon.http.HttpRequestTemplate;
import com.netflix.ribbon.http.HttpResourceGroup;

import com.netflix.ribbon.hystrix.FallbackHandler;
import io.netty.buffer.ByteBuf;
import rx.Observable;

@SuppressWarnings("unchecked")
public class RibbonMathClient {

    private EurekaClient eurekaClient;

    private EurekaClient getEurekaClient() {
        if (eurekaClient == null) {
            LifecycleInjectorBuilder builder = LifecycleInjector.builder();
            Injector injector = builder.build().createInjector();
            eurekaClient = injector.getInstance(EurekaClient.class);
        }
        return eurekaClient;
    }

    private String getServerIP(String microservice) {
        try {
            InstanceInfo info = getEurekaClient().getApplication(microservice.toUpperCase()).getInstances().get(0);
            String serverPort = "http://" + info.getVIPAddress() + ":" + info.getPort();
            return serverPort;
        } catch (Exception e) {
            throw new RuntimeException("Could not get Microservice IP:PORT. EX: " + e);
        }
    }

    private String callService(
            String microservice,
            String path,
            Double firstValue,
            Double secondValue,
            FallbackHandler fallbackHandler
    ) {
        HttpResourceGroup httpRG = Ribbon.createHttpResourceGroup("apiGroup",
                ClientOptions.create()
                        .withMaxAutoRetriesNextServer(1)
                        .withConfigurationBasedServerList(getServerIP(microservice)));

        HttpRequestTemplate<ByteBuf> apiTemplate = httpRG.newTemplateBuilder("apiCall", ByteBuf.class)
                .withMethod("GET")
                .withUriTemplate(path + firstValue + "/" + secondValue)
                .withFallbackProvider(fallbackHandler)
                .withResponseValidator(new SimpleResponseValidator())
                .build();

        RibbonResponse<ByteBuf> result = apiTemplate.requestBuilder()
                .withHeader("client", "calc-microservice")
                .build()
                .withMetadata().execute();
        ByteBuf buf = result.content();
        String json = buf.toString(Charset.forName("UTF-8"));
        return json;
    }

    public Observable<Double> sum(Double firstValue, Double secondValue) {
        return Observable.just(new Double(
                callService(
                        "sum-service",
                        "/math/sum/", firstValue, secondValue,
                        new DefaultFallback()
                )
        ));
    }

    public Observable<Double> sub(Double firstValue, Double secondValue) {
        return Observable.just(new Double(
                callService(
                        "sub-service",
                        "/math/sub/", firstValue, secondValue,
                        new DefaultFallback()
                )
        ));
    }

    public Observable<Double> mul(Double firstValue, Double secondValue) {
		return Observable.just(new Double(
				callService(
						"mul-service",
						"/math/mul/", firstValue, secondValue,
						new DefaultFallback()
				)
		));
    }

    public Observable<Double> div(Double firstValue, Double secondValue) {
		return Observable.just(new Double(
				callService(
						"div-service",
						"/math/div/", firstValue, secondValue,
						new DefaultFallback()
				)
		));
    }

    public Observable<Double> pow(Double firstValue, Double secondValue) {
		return Observable.just(new Double(
				callService(
						"pow-service",
						"/math/pow/", firstValue, secondValue,
						new PowFallback(firstValue, secondValue)
				)
		));
    }
}
