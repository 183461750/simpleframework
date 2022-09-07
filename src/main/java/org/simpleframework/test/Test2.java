package org.simpleframework.test;

import java.lang.reflect.Constructor;

/**
 * @author fa
 */
public class Test2 {

    public static class StarvingSingleton {

        private static final StarvingSingleton INSTANCE = new StarvingSingleton();

        private StarvingSingleton() {
        }

        public static StarvingSingleton getInstance() {
            return INSTANCE;
        }
    }

    static class EnumStarvingSingleton {
        private EnumStarvingSingleton() {
        }

        public static EnumStarvingSingleton getInstance() {
            return ContainerHolder.HOLDER.instance;
        }

        // 枚举类型是在类加载的时候就被创建出来
        private enum ContainerHolder {
            HOLDER;
            private EnumStarvingSingleton instance;

            ContainerHolder() {
                instance = new EnumStarvingSingleton();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        {
            Class<?> starvingSingleton = Class.forName("org.simpleframework.test.Test2$StarvingSingleton");
            // Class<?> starvingSingleton = StarvingSingleton.class;
            Constructor<?> declaredConstructor = starvingSingleton.getDeclaredConstructor();
            // 通过该设置我们打破了私有构造函数
            declaredConstructor.setAccessible(true);
            Object o = declaredConstructor.newInstance();
            // 结果为false
            System.out.println(o == StarvingSingleton.getInstance());
        }

        System.out.println("=======================================");

        {
            // 通过类名调用getInstance方法
            System.out.println(EnumStarvingSingleton.getInstance());
            // 通过反射获取到EnumStarvingSingleton的实例
            Class<?> aClass = EnumStarvingSingleton.class;
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            // 将获取到的私有构造函数,设置为强制允许外部访问
            declaredConstructor.setAccessible(true);
            EnumStarvingSingleton enumStarvingSingleton = (EnumStarvingSingleton) declaredConstructor.newInstance();
            // 通过EnumStarvingSingleton实例来调用getInstance方法
            System.out.println(enumStarvingSingleton);
            System.out.println(enumStarvingSingleton.getInstance());

        }

        System.out.println("=======================================");

        {
            // 获取枚举类对象
            Class<EnumStarvingSingleton.ContainerHolder> containerHolderClass = EnumStarvingSingleton.ContainerHolder.class;
            // 获取枚举类的构造函数
            Constructor<EnumStarvingSingleton.ContainerHolder> declaredConstructor = containerHolderClass.getDeclaredConstructor(EnumStarvingSingleton.class);
            declaredConstructor.setAccessible(true);
            // 创建对象
            System.out.println(declaredConstructor.newInstance(new EnumStarvingSingleton()));
        }


    }

}
