package myclass;

import java.util.function.Function;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class TestFunction {

    public static void main(String[] args) {

        Function<Integer, Integer> squareFun = s -> s * s;
        Function<Integer, Integer> cubicFun = s -> s * s * s;

        //函数式编程定义函数及执行
        System.out.println("3的平方：" + squareFun.apply(3));
        System.out.println("3的立方方：" + cubicFun.apply(3));

        /**
         * andThen()方法
         * 程序语句：function1.andThen(function2).apply(T t)
         * 先执行function1，再执行function2
         */
        System.out.println(squareFun.andThen(s -> s - 2).apply(6));

        /**
         * compose()方法
         * 程序语句是：function1.compose(function2).apply(T t)
         * 先执行function2，再执行function1
         *
         * 注意：compose()方法中参数为Function<? super V, ? extends T> before
         * function2最好先定义再传入compose中
         */
        Function<Integer, Integer> function2 = s -> s -1;
        System.out.println(squareFun.compose(function2).apply(6));

    }

}
