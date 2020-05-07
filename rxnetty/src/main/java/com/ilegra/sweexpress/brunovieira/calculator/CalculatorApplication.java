package com.ilegra.sweexpress.brunovieira.calculator;

import com.ilegra.sweexpress.brunovieira.calculator.configuration.CalculatorConfiguration;
import com.ilegra.sweexpress.brunovieira.calculator.server.CalculatorRequestHandler;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.Karyon;
import netflix.karyon.ShutdownModule;
import netflix.karyon.servo.KaryonServoModule;
import netflix.karyon.transport.http.HttpRequestHandler;
import netflix.karyon.transport.http.SimpleUriRouter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculatorApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);
        RequestHandler requestHandler = new CalculatorRequestHandler(context);

        SimpleUriRouter<ByteBuf, ByteBuf> router = new SimpleUriRouter<>();
        router.addUri("/calc", requestHandler);

        Karyon.forRequestHandler(8080,
                new HttpRequestHandler<>(router),
                Karyon.toBootstrapModule(KaryonWebAdminModule.class),
                ShutdownModule.asBootstrapModule(),
                KaryonServoModule.asBootstrapModule()
        ).startAndWaitTillShutdown();
    }

}
