package fireplume.multithreading;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GlobalVariables {
    private List<String> global;

    public void run() {
        print("has started");
        final int random = ThreadLocalRandom.current().nextInt(1, 10);

        //Initializing some global variable
        global = new ArrayList<>(random);
        List<String> local = new ArrayList<>();
        print("Setting global");
        for (int i = 0; i < random; i++) {
            local.add("String " + i);
        }
        global.addAll(local);

        // first access to variable
        print("Local has been set: " + local);
        print("Global has been set: " + global);

        // Some work imitation, for example work with DB
        print("Performing some work...");
        ThreadsHelper.pause(1000 * random);
        print("Work done");

        // Second access to variable
        print("Let's check our global variable: " + global);
    }

    private void print(String data) {
        System.out.println(Thread.currentThread().getName() + " : " + data);
    }
}
