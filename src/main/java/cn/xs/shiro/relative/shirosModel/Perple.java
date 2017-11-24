package cn.xs.shiro.relative.shirosModel;

/**
 * Created by uwayxs on 2017/11/23.
 */
public class Perple {

    private String name;
    private int age;
    private String sex;

    private Perple(Builder builder){
        this.age = builder.age;
        this.name = builder.name;
        this.sex = builder.sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static class Builder{
        private String name;
        private int age;
        private String sex;
        public Builder age(int age){
            this.age = age;
            return this;
        }
        public Builder sex(String sex){
            this.sex = sex;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Perple buildPerple(){
            return new Perple(this);
        }
    }

    @Override
    public String toString() {
        return "Perple{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
