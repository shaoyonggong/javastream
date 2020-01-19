package myclass;

import com.alibaba.fastjson.JSONObject;
import po.User;
import utils.ArrayToObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class TestConsumerAboutObject {

    /**
     * 将指定的数组转换为对象
     *
     * @param classType
     * @param arr
     * @return
     */
    public static <T> List<T> arrayConversionToObject(Class<?> classType, String[] arr) {

        List<T> resultList = new ArrayList<>();
        //循环list
        for (String str : arr) {

            Object[] obj = str.split("，");
            //创建实例对象
            Object instance = null;
            try {
                instance = classType.newInstance();//getSuperclass
                for (int i = 0; i < classType.getDeclaredFields().length; i++) {
                    //获取set方法名
                    String originalName = classType.getDeclaredFields()[i].getName();
                    String methodName = "set" + originalName.substring(0, 1).toUpperCase() + originalName.substring(1);
                    Method method = classType.getDeclaredMethod(methodName, classType.getDeclaredFields()[i].getType());
                    if (i >= obj.length || obj[i] == null || obj[i] == "") {
                        continue;
                    }
                    //赋值
                    method.invoke(instance, obj[i]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            resultList.add((T) instance);
        }

        return resultList;
    }

    public static void main(String[] args) {

        String[] arr = {"张三，男，15", "李四，女，16", "王五，女，17", "麻六，男，18", "幺鸡，女"};

        List<User> users = arrayConversionToObject(User.class, arr);
        System.out.println(JSONObject.toJSONString(users));

        List<Object> objList = new ArrayList<>();
        Collections.addAll(objList,arr);

        objList.forEach(s-> {
            User user = (User) ArrayToObject.arrayToObject(s.toString().split("，"), User.class);
        });
    }
}
