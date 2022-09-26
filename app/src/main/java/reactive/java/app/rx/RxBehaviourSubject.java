/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class RxBehaviourSubject {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        Subject<Integer> subject = BehaviorSubject.create();

        subject.subscribe(item -> System.out.println("Person 1 listening song Item:" + item + ":" + Thread.currentThread().getName()));
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);

        // receiving only last next imitted value.
        subject.subscribe(item -> System.out.println("Person 2 listening song Item:" + item + ":" + Thread.currentThread().getName()));


        new Scanner(System.in).nextLine();

    }


}
