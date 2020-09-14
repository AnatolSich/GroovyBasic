class Calculator {

    Double addition(Double a, Double b){
        a+b
    }

    Double substraction(Double a, Double b){
        a-b
    }

    Double multiplication(Double a, Double b){
        a*b
    }

    Double division(Double a, Double b){
        try {
            a/b
        } catch (ArithmeticException e) {
            if("Division by zero".equals(e.getMessage())){
                println("Division by zero")
            }
        }
    }
}
