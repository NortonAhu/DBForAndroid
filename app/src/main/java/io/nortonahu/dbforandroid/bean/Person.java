package io.nortonahu.dbforandroid.bean;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/24 11:31.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class Person extends BaseData{
    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static final String KEY_INFO = "info";

    public String name;
    public int age;
    public String info;

    public Person(String name, int age, String info) {
        this.name = name;
        this.age = age;
        this.info = info;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return info;
    }


    @Override
    public String toString() {
        return "id is:" + getid() + " name:" + getName() + " | age is:" + getAge() + " | info is:" + getInfo();
    }
}
