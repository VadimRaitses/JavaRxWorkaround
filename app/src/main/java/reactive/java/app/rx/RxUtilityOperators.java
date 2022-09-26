/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxUtilityOperators {
    public static void main(String[] args) throws InterruptedException {

        Observable
                .just(1, 2, 3)
                .delay(5, TimeUnit.SECONDS) //prints:  "1" ,"2", "3" after 5 seconds
                .subscribe(System.out::println);
        System.out.println("\n");

        System.out.println(Thread.currentThread().getName());

        Observable
                .just(1, 2, 3)
                .observeOn(Schedulers.newThread())
                .subscribe(item -> {
                    System.out.println(item + ":" + Thread.currentThread().getName());
                    System.out.println(item + ":" + Thread.currentThread().getName());
                });
        System.out.println("\n");

        Observable
                .just(1, 2, 3)
                .subscribeOn(Schedulers.newThread())
                .subscribe(item -> {
                    System.out.println(item + ":" + Thread.currentThread().getName());
                    System.out.println(item + ":" + Thread.currentThread().getName());
                });
        System.out.println("\n");

        Observable
                .just(1, 2, 3)
                .doOnNext(item -> System.out.println(item + ": log some info" + Thread.currentThread().getName()))
                .filter(item -> item == 2); //prints:  log, log ,2 log
        System.out.println("\n");

        Disposable disposable = Observable
                .timer(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Dispose Called"))
                .filter(item -> item == 2) //prints:  dispose called
                .subscribe(System.out::println);
        disposable.dispose();

        System.out.println("\n");

        Observable
                .just(2, 1)
                .map(item -> 2 / item)
                .retry(1)
                .subscribe(item -> {
                    System.out.println(item);
                }, throwable -> System.out.println(throwable.getMessage())); //prints:  log, log ,2 log
        System.out.println("\n");




        Observable
                .just(2, 1)
                .map(item -> 2 / item)
                .onErrorResumeWith(Observable.just(5,6,7))
                .subscribe(item -> {
                    System.out.println(item);
                }, throwable -> System.out.println(throwable.getMessage())); //prints:  log, log ,2 log
        System.out.println("\n");

        sleep(100000);

    }

}
