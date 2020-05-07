package main

import (
	"net/http"
	"net/http/httptest"
	"strings"
	"testing"
)

func TestSum(t *testing.T) {
	sum := Sum(5, 10)
	if sum != 15 {
		t.Errorf("Sum failed. Expected <%d>, but was <%f>!", 15, sum)
	}
}

func TestSubtraction(t *testing.T) {
	subtraction := Subtraction(30, 17)
	if subtraction != 13 {
		t.Errorf("Subtraction failed. Expected <%d>, but was <%f>!", 13, subtraction)
	}
}

func TestMultiplication(t *testing.T) {
	multiplication := Multiplication(4, 5)
	if multiplication != 20 {
		t.Errorf("Multiplication failed. Expected <%d>, but was <%f>!", 20, multiplication)
	}
}

func TestDivision(t *testing.T) {
	division := Division(15, 3)
	if division != 5 {
		t.Errorf("Division failed. Expected <%d>, but was <%f>!", 5, division)
	}
}

func TestDivisionByZero(t *testing.T)  {
	division := Division(15, 0)
	if division != 0 {
		t.Errorf("Division by zero should return zero but was <%f>!", division)
	}
}

func TestOperations(t *testing.T) {
	operations := Operations()
	if len(operations) != 4 {
		t.Errorf("Unexpected number of operations. Expected <%d>, but was <%d>!", 4, len(operations))
	}
}

func TestParsePathParameters(t *testing.T) {
	pathParameters := ParsePathParameters("/calc/sum/13/27")
	if pathParameters["op"] != "sum" {
		t.Errorf("Unexpected value in first parameter. Expected <%s>, but was <%s>!", "sum", pathParameters["op"])
	}
	if pathParameters["x"] != "13" {
		t.Errorf("Unexpected value in second parameter. Expected <%s>, but was <%s>!", "13", pathParameters["x"])
	}
	if pathParameters["y"] != "27" {
		t.Errorf("Unexpected value in third parameter. Expected <%s>, but was <%s>!", "27", pathParameters["y"])
	}
}

func TestOperationHandler(t *testing.T) {
	req, err := http.NewRequest("GET", "/calc/sum/10/15", nil)
	if err != nil {
		t.Fatal(err)
	}
	w := httptest.NewRecorder()
	OperationHandler(w, req)

	if w.Code != 200 {
		t.Errorf("Unexpected status code Expected <%d>, but was <%d>!", 200, w.Code)
	}

	if !strings.Contains(w.Body.String(), "\"result\":25") {
		t.Errorf("Unexpected response body  %s", w.Body.String())
	}
}

func TestHistoryHandler(t *testing.T) {
	sumReq, err := http.NewRequest("GET", "/calc/sum/70/5", nil)
	if err != nil {
		t.Fatal(err)
	}
	OperationHandler(httptest.NewRecorder(), sumReq)

	historyReq, err := http.NewRequest("GET", "/calc/history", nil)
	if err != nil {
		t.Fatal(err)
	}
	w := httptest.NewRecorder()
	HistoryHandler(w, historyReq)

	if w.Code != 200 {
		t.Errorf("Unexpected status code Expected <%d>, but was <%d>!", 200, w.Code)
	}
	if !strings.Contains(w.Body.String(), "\"result\":75") {
		t.Errorf("Unexpected response body  %s", w.Body.String())
	}
}
