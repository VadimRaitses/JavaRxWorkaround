/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import java.util.Scanner;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

public class RxRelaySubject {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        Subject<Integer> subject = ReplaySubject.create();

        subject.subscribe(item -> System.out.println("Student 1 received Item:" + item + ":" + Thread.currentThread().getName()));

        //topics
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);

        // getting all stream again
        subject.subscribe(item -> System.out.println("Student 2 received Item:" + item + ":" + Thread.currentThread().getName()));


        new Scanner(System.in).nextLine();

    }


}