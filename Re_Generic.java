package Reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;



/**
 * 通过反射获取泛型信息
 * @author dell
 *
 */
public class Re_Generic {
	
	public void test01(Map<String,User> map,List<User> list){
		System.out.println("test01()");
	}
	
	public Map<Integer,User> test02(){
		System.out.println("test02()");
		return null;
	}
	
	public static void main(String[] args) {
		try {
			//获得指定方法参数泛型信息
			Method m = Re_Generic.class.getMethod("test01", Map.class,List.class);
			Type[] t = m.getGenericParameterTypes();
			for (Type paramType : t) {
				System.out.println("#"+paramType);
				if(paramType instanceof ParameterizedType){
					Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();
					for (Type genericType : genericTypes) {
						System.out.println("泛型类型："+genericType);
					}
				}
			}
			
			//获得指定方法返回值泛型信息
			Method m2 = Re_Generic.class.getMethod("test02", null);
			Type returnType = m2.getGenericReturnType();
			System.out.println("#"+returnType);
			if(returnType instanceof ParameterizedType){
					Type[] genericTypes = ((ParameterizedType) returnType).getActualTypeArguments();

					for (Type genericType : genericTypes) {
						System.out.println("返回值，泛型类型："+genericType);
					}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
