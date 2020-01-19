package myclass;

import java.util.function.Consumer;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class TestConsumerAfterAndThen {

    public static void print(String[] arr, Consumer<String> con1) {
        //遍历字符串数组
        for (String message : arr) {
            con1.accept(message);
        }
    }

    //定义一个方法，参数传递String类型的数组和两个Consumer接口，泛型使用String
    public static void printInfo(String[] arr, Consumer<String> con1, Consumer<String> con2) {
        //遍历字符串数组
        for (String message : arr) {
            //使用andThen方法连接两个Consumer接口，消费字符串
            con1.andThen(con2).accept(message);
        }
    }

    public static void main(String[] args) {
        //定义一个字符串类型的数组
        String[] arr = {"张三，男", "李四，女", "王五，女", "麻六，男", "幺鸡，女"};

        //输出每一个姓名
        print(arr, (s) -> System.out.println("姓名:" + s.split("，")[0]));
        System.out.println("------------分割线----------");
        /**
         * 调用printInfo方法，传递字符串数组，和两个Lambda表达式
         * 将该数组输出为指定格式：姓名:张三性别：男。
         */
        printInfo(arr, (message1) -> {
            //消费方式：对message进行切割，获取姓名，按照指定的格式输出
            String name = message1.split("，")[0];
            System.out.print("姓名：" + name + "，");
        }, (message2) -> {
            //消费方式：对message进行切割，获取性别，按照指定的格式输出
            String gender = message2.split("，")[1];
            System.out.println("性别：" + gender + "。");
        });
        System.out.println("------------分割线----------");
        //上述写法可以简化为
        printInfo(arr, (s) -> System.out.print("姓名:" + s.split("，")[0] + "，"), s -> System.out.println("性别：" + s.split("，")[1] + "。"));

    }
}
