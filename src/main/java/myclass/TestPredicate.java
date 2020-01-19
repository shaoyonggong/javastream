package myclass;

import java.util.function.Predicate;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class TestPredicate {

    public static void main(String[] args) {
        simpleDemo(new String());
    }

    private static void simpleDemo(String str) {
        Predicate<String> isNull = s -> null == s;
        Predicate<String> isLong = s ->  s.length() > 50;
        Predicate<String> isShort = s ->  s.length() < 50;

        System.out.println(isNull.and(isLong).test(str));
        System.out.println(isLong.or(isNull).test(str));
        System.out.println(isShort.test(str));
        Predicate<String> negate = isShort.negate();


    }
}
