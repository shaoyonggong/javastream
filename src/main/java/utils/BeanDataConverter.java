package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class BeanDataConverter {
    public static void converterData(Object fromBean, Object toBean, String[] excludeProperties)
            throws InvocationTargetException, IllegalAccessException {

        Objects.requireNonNull(fromBean);
        Objects.requireNonNull(toBean);
        Objects.requireNonNull(excludeProperties);

        List<String> excludes = Arrays.stream(excludeProperties).map(String::toLowerCase).collect(Collectors.toList());

        Method[] methods = fromBean.getClass().getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (!methodName.startsWith("get") || "getClass".equals(methodName)
                    || excludes.contains(methodName.replaceFirst("get", "").toLowerCase())) {
                continue;
            }
            Class<?> returnType = method.getReturnType();
            Object value = method.invoke(fromBean, new Object[]{});
            String setMethodName = String.format("set%s", methodName.replaceFirst("get", ""));
            try {
                Method setMethod = toBean.getClass().getMethod(setMethodName, returnType);
                setMethod.invoke(toBean, value);
            } catch (NoSuchMethodException e) {
            }
        }
    }
}
