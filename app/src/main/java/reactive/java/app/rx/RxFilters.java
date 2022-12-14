/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import io.reactivex.rxjava3.core.Observable;

public class RxFilters {
    public static void main(String[] args) {

        Observable
                .just("Hello", "my", "World")
                .filter(item -> item.length() != 2) //prints:  Hello World
                .subscribe(System.out::println);

        Observable
                .just("Hello", "my", "World")
                .take(2)// prints: Hello my
                .subscribe(System.out::println);

        Observable
                .just("Hello", "my", "World")
                .skip(2) // prints: World
                .subscribe(System.out::println);

        Observable
                .just("Hello", "my", "my", "World")
                .distinct()//remove duplicates Hello my World
                .subscribe(System.out::println);

        Observable
                .just("Hello", "my", "World")
                .first("default")//prints: Hello
                .subscribe(System.out::println);

        Observable
                .just("Hello", "my", "World")
                .last("default")//prints: World
                .subscribe(System.out::println);

        Observable
                .just(2, 3, 4, 5, 6, 7)
                .takeWhile(item -> item <= 3)//prints: 2,3
                .subscribe(System.out::println);

        Observable
                .just(2, 3, 4, 5, 6, 7)
                .skipWhile(item -> item <= 4)//prints: 4,5
                .subscribe(System.out::println);

        Observable
                .just("abc", "def", "ghk")
                .all(item -> item.length() == 3)//prints: true
                .subscribe(System.out::println);

        Observable
                .just("abc", "def", "ghk")
                .any(item -> item.length() == 4)//prints: false
                .subscribe(System.out::println);

        Observable
                .just("abc", "def", "ghk")
                .filter(item -> item.length() == 4)
                .defaultIfEmpty("ABC")//prints: ABC
                .subscribe(System.out::println);


        Observable
                .just("abc", "def", "ghk")
                .filter(item -> item.length() == 4)
                .switchIfEmpty(Observable.just("Hello", "World"))//prints: Hello World
                .subscribe(System.out::println);


    }

}
