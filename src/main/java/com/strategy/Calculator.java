package com.strategy;

/**
 * @Description:  策略【strategy】模式
 * @Author: franky
 * @CreateDate: 2019-08-09 13:40
 * @Version: 1.0
 */
public enum Calculator {
    ADD("+") {
        /**
         * 变量名的后的代码块相当于是实现抽象方法的匿名内部类。写法如同：变量类型 变量名 =new 枚举类类名（参数）{@Override
         * 　　　　　　　　　　　　　　　　　　　　　　　　public 枚举类类型 抽象类类名() {
         *         　　　　　　　　　　　　　　　　　　　　　　　　return 变量名; }
         * 　　　　　　　　　　　　　　　　　　　　　　　}
         * @param a
         * @param b
         * @return
         */
        public int exec(int a, int b) {
            return a + b;
        }
    },
    SUB("-") {
        public int exec(int a, int b) {
            return a - b;
        }
    };

    String value = "";

    Calculator(String _value) {
        this.value = _value;
    }

    public String getValue() {
        return value;
    }

    //为枚举类定义一个抽象方法
    //这个抽象方法由不同的枚举值进行实现
    /**
     * 枚举里定义抽象方法时，不能用abstract将枚举类定义成抽象类【因为系统会自动为他添加abstract关键字】
     * 但因为枚举类需要显式的创建枚举值，而不是作为父类，所以定义枚举值时必须对抽象方法进行时间，否则会编译报错
     * @param a
     * @param b
     * @return
     */
    public abstract int exec(int a, int b);

}
