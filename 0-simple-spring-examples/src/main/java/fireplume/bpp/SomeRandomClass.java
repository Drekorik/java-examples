package fireplume.bpp;

import org.springframework.stereotype.Component;

@Component
public class SomeRandomClass {

    @CustomAnnotation(value = 5)
    private int data;

    public void printData() {
        System.out.println("Data value is " + data);
    }
}
