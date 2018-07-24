import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args) {

        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        StringsProducer stringsProducer = new StringsProducer(arrayBlockingQueue);
        StringsConsumer stringsConsumer = new StringsConsumer(arrayBlockingQueue);

        Thread t01 = new Thread(stringsProducer);
        Thread t02 = new Thread(stringsConsumer);

        t01.start();
        t02.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
