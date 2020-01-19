package myclass;

import com.alibaba.fastjson.JSONObject;
import dto.UserDTO;
import po.User;
import utils.BeanDataConverter;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description
 * @Author shaoyonggong
 * @Date 2020/1/19
 */
public class TestConverter {
    public static void main(String[] args) {
        User user = new User();
        user.setName("张三");
        user.setAge(15);
        user.setGendle("男");

        UserDTO userDto = new UserDTO();
        try {
            String[] excludeProperties = {"grade"};
            BeanDataConverter.converterData(user,userDto,excludeProperties);

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSONString(userDto));


    }
}
