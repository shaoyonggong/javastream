package myclass;

import java.util.function.Consumer;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class TestConsumerException {
    public static void main(String[] args) {
        //计算平方
        testConsumer(2);
        //异常测试
        testAndThen(3);
    }

    /**
     * 一个简单的平方计算
     */
    public static void testConsumer(Integer integer){
        Consumer<Integer> square = x -> System.out.println(x+"的平方是: " + x * x);
        square.accept(integer);
    }

    /**
     * 定义3个Consumer并按顺序进行调用andThen方法，其中consumer2抛出NullPointerException。
     */
    public static void testAndThen(Integer num){
        Consumer<Integer> consumer1 = x -> System.out.println("first x : " + x);
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second x : " + x);
            //抛出异常
            throw new NullPointerException("throw exception test");
        };
        Consumer<Integer> consumer3 = x -> System.out.println("third x : " + x);

        consumer1.andThen(consumer2).andThen(consumer3).accept(num);
    }
}
