/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import reactive.java.app.model.Item;

public class RxColdHot {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start");
      //  coldObservableExample();
        hotObservableExample();
        //  System.out.println(Thread.currentThread().getName());
        new Scanner(System.in).nextLine();
    }

    private static void coldObservableExample() {
        Observable observable = Observable.just("a", "b", "c");
        observable.subscribe((item) -> System.out.println("Observer 1 - " + item));
        observable.subscribe((item) -> System.out.println("Observer 2 - " + item));
        observable.subscribe((item) -> System.out.println("Observer 3 - " + item));

    }

    private static void hotObservableExample() throws InterruptedException {
        ConnectableObservable observable = Observable.interval(1, TimeUnit.SECONDS).map(item->new Item(item.intValue())).publish();
        observable.connect();

        observable.subscribe((item) -> System.out.println("Observer 1 - " + item));
        Thread.sleep(5000);

        observable.subscribe((item) -> System.out.println("Observer 2 - " + item));
      //  Thread.sleep(500);

        //  observable.subscribe((item) -> System.out.println("Observer 2 - " + item));
        //  observable.subscribe((item) -> System.out.println("Observer 3 - " + item));

    }

}