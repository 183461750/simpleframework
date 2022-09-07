package org.simpleframework.test;

/**
 * @author fa
 */
public class Test {

    /**
     * 单例类
     */
    static class Singleton {

        private static final Singleton SINGLETON = new Singleton();
        
    }

    /**
     * 单例枚举
     */
    enum SingletonHolder {
        /**
         * 单例
         */
        HOLDER;

        private final Singleton singleton;

        SingletonHolder() {
            this.singleton = new Singleton();
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton.SINGLETON);
        System.out.println(SingletonHolder.HOLDER.singleton);
    }

}
