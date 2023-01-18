package hello.springmvc.basic;

import lombok.Data;

@Data //getter, setter, constructor등 모두 만들줌
public class HelloData {
    private String username;
    private int age;
}
