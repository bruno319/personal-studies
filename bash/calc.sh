#!/bin/bash

echo "Enter two values"
read firstValue
read secondValue

echo "Choose an operation: + - / x ^"
read operation

SUM_OPERATOR="+"
SUBTRACTION_OPERATOR="-"
MULTIPLICATION_OPERATOR="x"
DIVISION_OPERATOR="/"
POWER_OPERATOR="^"

sum() {
  echo "$(($1 + $2))"
}

subtract() {
  echo "$(($1 - $2))"
}

multiply() {
  echo "$(($1 * $2))"
}

divide() {
  echo "$(($1 / $2))"
}

power() {
  echo "$(($1 ** $2))"
}

case $operation in
  $SUM_OPERATOR)
    sum firstValue secondValue ;;
  $SUBTRACTION_OPERATOR)
    subtract firstValue secondValue ;;
  $MULTIPLICATION_OPERATOR)
    multiply firstValue secondValue ;;
  $DIVISION_OPERATOR)
    divide firstValue secondValue;;
  $POWER_OPERATOR)
    power firstValue secondValue ;;
  *)
    echo "Invalid option" ;;
esac