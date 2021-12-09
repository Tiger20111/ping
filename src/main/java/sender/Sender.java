package sender;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class Sender {
    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @RequestMapping(value = "/requests/{number}", method = RequestMethod.POST)
    public void sendRequest(@PathVariable("number") int number) {
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            requests.add(new Request(i));
        }
        ArrayList<Future<?>> futures = new ArrayList<>();
        for (Request request:
                requests) {
            futures.add(executorService.submit(request));
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
