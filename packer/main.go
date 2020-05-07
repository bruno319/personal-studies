package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"reflect"
	"runtime"
	"strconv"
	"strings"
)

var history []Result

type Result struct {
	Operation   string  `json:"operation"`
	FirstValue  float64 `json:"firstValue"`
	SecondValue float64 `json:"secondValue"`
	Result      float64 `json:"result"`
}

type Error struct {
	Error string `json:"error"`
}

func Sum(x float64, y float64) float64 {
	return x + y
}

func Subtraction(x float64, y float64) float64 {
	return x - y
}

func Multiplication(x float64, y float64) float64 {
	return x * y
}

func Division(x float64, y float64) float64 {
	if y == 0 {
		return 0
	}
	return x / y
}

func Operations() map[string]func(float64, float64) float64 {
	return map[string]func(float64, float64) float64{
		"sum": Sum,
		"sub": Subtraction,
		"mul": Multiplication,
		"div": Division,
	}
}

func ParsePathParameters(url string) map[string]string {
	parameters := make(map[string]string)
	split := strings.Split(url, "/")
	parameters["op"] = split[2]
	parameters["x"] = split[3]
	parameters["y"] = split[4]
	return parameters
}

func OperationHandler(w http.ResponseWriter, r *http.Request) {
	parameters := ParsePathParameters(r.URL.Path)
	x, xErr := strconv.ParseFloat(parameters["x"], 64)
	y, yErr := strconv.ParseFloat(parameters["y"], 64)
	if xErr == nil && yErr == nil {
		operation := Operations()[parameters["op"]]
		opName := runtime.FuncForPC(reflect.ValueOf(operation).Pointer()).Name()
		result := Result{
			Operation:   strings.TrimPrefix(opName, "main."),
			FirstValue:  x,
			SecondValue: y,
			Result:      operation(x, y),
		}
		history = append(history, result)
		json.NewEncoder(w).Encode(result)
	} else {
		if xErr != nil {
			json.NewEncoder(w).Encode(Error{Error: xErr.Error()})
		}
		if yErr != nil {
			json.NewEncoder(w).Encode(Error{Error: yErr.Error()})
		}
	}
}

func HistoryHandler(w http.ResponseWriter, r *http.Request) {
	json.NewEncoder(w).Encode(history)
}

func main() {
	fmt.Print("Calculator is running")
	http.HandleFunc("/calc/", OperationHandler)
	http.HandleFunc("/calc/history", HistoryHandler)
	http.ListenAndServe(":8282", nil)
}
