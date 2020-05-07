package com.ilegra.sweexpress.brunovieira.calculator.server;

import com.ilegra.sweexpress.brunovieira.calculator.domain.Division;
import com.ilegra.sweexpress.brunovieira.calculator.domain.History;
import com.ilegra.sweexpress.brunovieira.calculator.domain.Sum;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorRequestHandlerTest {

    @Mock
    private ApplicationContext context;

    private CalculatorRequestHandler requestHandler;

    @Mock
    private HttpServerRequest<ByteBuf> request;

    @Mock
    private HttpServerResponse<ByteBuf> response;

    @Before
    public void setup() {
        when(response.writeStringAndFlush(anyString())).thenReturn(null);

        when(context.getBean(eq("history"))).thenReturn(new History());
        requestHandler = new CalculatorRequestHandler(context);
    }

    @Test
    public void shouldCalculateAndReturnResult() {
        Map<String, List<String>> params = createRequestParams("sum", "20", "15");
        when(request.getQueryParameters()).thenReturn(params);
        when(context.getBean(eq("sum"))).thenReturn(new Sum());

        requestHandler.handle(request, response);

        verify(context, times(1)).getBean(eq("sum"));
        verify(response, times(1)).writeStringAndFlush(eq("35.0"));
    }

    @Test
    public void shouldNotifyWhenDivisionByZeroOccurs() {
        Map<String, List<String>> params = createRequestParams("division", "15", "0");
        when(request.getQueryParameters()).thenReturn(params);
        when(context.getBean(eq("division"))).thenReturn(new Division());

        requestHandler.handle(request, response);

        verify(context, times(1)).getBean(eq("division"));
        verify(response, times(1)).writeStringAndFlush(eq("Can't divide by zero"));
    }

    private Map<String, List<String>> createRequestParams(String function, String x, String y) {
        Map<String, List<String>> params = new HashMap<>();
        params.put("function", Collections.singletonList(function));
        params.put("x", Collections.singletonList(x));
        params.put("y", Collections.singletonList(y));
        return params;
    }
}
