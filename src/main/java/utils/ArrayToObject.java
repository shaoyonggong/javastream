package utils;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2019/9/16
 */
public class ArrayToObject {
    /**
     *@param obj 数据数组
     *@param classType 类字节码
     */
    public static Object arrayToObject(Object[] obj,Class<?> classType) {

        Object stu1= null;
        try {
            stu1 = classType.newInstance();

            for(int i=0;i< classType.getDeclaredFields().length;i++){

                String setMethodName="set"+classType.getDeclaredFields()[i].getName().substring(0,1).toUpperCase()+classType.getDeclaredFields()[i].getName().substring(1);

                Method setMethod=classType.getDeclaredMethod(setMethodName, new Class[]{classType.getDeclaredFields()[i].getType()});

                if (i>=obj.length||obj[i]==null||obj[i]==""){
                    continue;
                }
                setMethod.invoke(stu1,obj[i].toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stu1;
    }

}
