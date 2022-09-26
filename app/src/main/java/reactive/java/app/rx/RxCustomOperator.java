/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package reactive.java.app.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOperator;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class RxCustomOperator {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        Observable
                .just(1, 2, 3, 4)
                .lift(takeEven())
                //.filter(item -> item%2==0) //prints:  Hello World
                .subscribe(System.out::println);
    }

    private static ObservableOperator<Integer, Integer> takeEven() {
        return new ObservableOperator<Integer, Integer>() {
            @Override
            public @NonNull Observer<? super Integer> apply(@NonNull Observer<? super Integer> observer) throws Throwable {
                return new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(@NonNull Integer item) {
                        if (item % 2 == 0)
                            observer.onNext(item);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
            }
        };
    }

}
