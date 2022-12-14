/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.AllArgsConstructor;

public class RxThreading {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());
        Flowable.create(new ApiCall("https://www.gutenberg.org/cache/epub/2600/pg2600.txt"), BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .subscribe(item -> {
                    System.out.println(Thread.currentThread().getName() + ": " + item);
                });

        Observable.fromCallable(new ApiCallAction("https://www.gutenberg.org/cache/epub/2600/pg2600.txt"))
                .observeOn(Schedulers.io())
                .flatMap(item -> Observable
                        .fromAction(new FileWriteAction("test.txt", item))
                        .observeOn(Schedulers.io()))
                .subscribe(item -> {
                    System.out.println(Thread.currentThread().getName() + ": " + item);
                }, throwable -> {
                    System.out.println(throwable.getMessage());
                }, () -> {
                    System.out.println("done");
                });

        //    new Scanner(System.in).nextLine();

    }

    @AllArgsConstructor
    static class ApiCall implements FlowableOnSubscribe<String> {
        private final String uri;

        @Override
        public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {
            System.out.println(Thread.currentThread().getName());
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200)
                response.body().lines().forEach(emitter::onNext);

            else emitter.onError(new Exception("hell"));
        }

    }

    @AllArgsConstructor
    static class FileWriteAction implements Action {
        private final String fileName;
        private final String line;

        @Override
        public void run() throws Throwable {
            System.out.println(Thread.currentThread().getName());
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            Files.write(
                    Paths.get(fileName),
                    line.getBytes(),
                    StandardOpenOption.APPEND);

        }
    }

    @AllArgsConstructor
    static class ApiCallAction implements Callable<String> {
        private final String uri;

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }

}
