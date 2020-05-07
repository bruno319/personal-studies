package com.ilegra.sweexpress.brunovieira.calculator.server;

import com.ilegra.sweexpress.brunovieira.calculator.domain.Function;
import com.ilegra.sweexpress.brunovieira.calculator.domain.History;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import org.springframework.context.ApplicationContext;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class CalculatorRequestHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private ApplicationContext context;
    private History history;

    public CalculatorRequestHandler(ApplicationContext context) {
        this.context = context;
        this.history = (History) context.getBean("history");
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        Map<String, List<String>> params = request.getQueryParameters();

        Function function = (Function) context.getBean(params.get("function").get(0));
        Double x = Double.valueOf(params.get("x").get(0));
        Double y = Double.valueOf(params.get("y").get(0));

        try {
            double result = function.execute(x, y);
            history.addOperation(function.getClass().getSimpleName(), x, y);
            return response.writeStringAndFlush(String.valueOf(result));
        } catch (IllegalArgumentException e) {
            return response.writeStringAndFlush(e.getMessage());
        }
    }
}
