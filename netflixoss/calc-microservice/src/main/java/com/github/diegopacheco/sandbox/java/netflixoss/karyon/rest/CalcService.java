package com.github.diegopacheco.sandbox.java.netflixoss.karyon.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.diegopacheco.sandbox.java.netflixoss.karyon.ribbon.RibbonMathClient;

@Singleton
public class CalcService {

    private RibbonMathClient client;
    private List<String> operations;

    @Inject
    public CalcService(RibbonMathClient client) {
        this.client = client;
        this.operations = Arrays.asList("+", "-", "*", "/", "^");
    }

    public Double calc(String expr) {
        if (expr == null || ("".equals(expr)))
            throw new IllegalArgumentException("You must pass a valid expression. ");

        Stack<String> stack = new Stack<>();
        Double firstValue;
        Double secondValue;
        Double result = 0.0;

        for (int i = 0; i < expr.length(); i++) {
            String charExpression = expr.charAt(i) + "";

            if (" ".equals(charExpression)) continue;

            if (operations.contains(charExpression)) {
                firstValue = new Double(stack.pop());
                secondValue = new Double(stack.pop());

                switch (charExpression) {
                    case "+":
                        result = client.sum(firstValue, secondValue).toBlocking().first();
                        break;
                    case "-":
                        result = client.sub(firstValue, secondValue).toBlocking().first();
                        break;
                    case "/":
                        result = client.div(firstValue, secondValue).toBlocking().first();
                        break;
                    case "*":
                        result = client.mul(firstValue, secondValue).toBlocking().first();
                        break;
                    case "^":
                        result = client.pow(secondValue, firstValue).toBlocking().first();
                        break;
                }
            } else {
                stack.push(charExpression);
            }
        }
        return result;
    }

}
