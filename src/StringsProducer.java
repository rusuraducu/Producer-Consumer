import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class StringsProducer implements Runnable {
    private ArrayBlockingQueue<Object> arrayBlockingQueue;
    private boolean run = true;

    public StringsProducer(ArrayBlockingQueue<Object> arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {

        while (run) {

            try {

                StringsGenerator stringsGenerator = new StringsGenerator();
                String generatedWord = stringsGenerator.generateWord();
                ProcessingMethods algorithm = randomAlgorithm();

                arrayBlockingQueue.offer(generatedWord, 1, TimeUnit.SECONDS);
                arrayBlockingQueue.offer(algorithm, 1, TimeUnit.SECONDS);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ProcessingMethods randomAlgorithm() {
        Random r = new Random();
        int randomAlgorithm = r.nextInt(ProcessingMethods.values().length);
        int i = 0;
        for (ProcessingMethods algorithm : ProcessingMethods.values()) {
            if( i == randomAlgorithm){
                return algorithm;
            }
            i++;
        }
        return null;
    }
}
