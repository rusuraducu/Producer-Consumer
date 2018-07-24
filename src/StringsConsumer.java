import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class StringsConsumer implements Runnable, StringsProcessor {
    private ArrayBlockingQueue<Object> arrayBlockingQueue;
    private boolean run = true;

    public StringsConsumer(ArrayBlockingQueue<Object> arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (run) {
            try {

                String generatedWord = (String) arrayBlockingQueue.poll(1, TimeUnit.SECONDS);
                ProcessingMethods method = (ProcessingMethods) arrayBlockingQueue.poll(1, TimeUnit.SECONDS);

                if (generatedWord == null) {
                    continue;
                }

                System.out.printf("Generated word: " + generatedWord);
                System.out.println(" | " + applyAlgorithm(generatedWord, method));

                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String applyAlgorithm(String generatedWord, ProcessingMethods algorithm) {
        String result = new String();

        switch (algorithm) {

            case VOWELS_COUNTER:
                result = "Vowels Counter: " + String.valueOf(VowelsCounter(generatedWord)) + " vowels.";
                break;
            case CONSONANTS_COUNTER:
                result = "Consonants Counter: " + String.valueOf(ConsonantsCounter(generatedWord)) + " consonants.";
                break;
            case STRINGS_LENGTH:
                result = "Strings Length: " + String.valueOf(StringsLength(generatedWord)) + " letters.";
                break;
            case VOWELS_REMOVER:
                result = "Vowels Remover: " + VowelsRemover(generatedWord);
                break;

        }

        return result;
    }

    @Override
    public void StringsPrinter(String generatedWord) {
        System.out.println(generatedWord);
    }

    @Override
    public int VowelsCounter(String generatedWord){
        char[] generatedWordArr = generatedWord.toCharArray();
        int vowel = 0;
        for (int i = 0; i < generatedWordArr.length; i++) {
            if (isVowel(generatedWordArr[i])) {
                vowel++;
            }
        }
        return vowel;
    }

    @Override
    public int ConsonantsCounter(String generatedWord) {
        char[] generatedWordArr = generatedWord.toCharArray();
        int consonants = 0;
        for (int i = 0; i < generatedWordArr.length; i++) {
            if (!isVowel(generatedWordArr[i])) {
                consonants++;
            }
        }
        return consonants;
    }

    @Override
    public int StringsLength(String generatedWords) {
        return generatedWords.length();
    }

    @Override
    public String VowelsRemover(String generatedWord) {
        char[] generatedWordArr = generatedWord.toCharArray();
        StringBuilder wordWithoutVowels = new StringBuilder();
        for (int i = 0; i < generatedWord.toCharArray().length; i++) {
            if (!isVowel(generatedWordArr[i])) {
                wordWithoutVowels.append(generatedWordArr[i]);
            }
        }
        return wordWithoutVowels.toString();
    }

    private boolean isVowel(char c) {
        char[] vowels = {'a', 'A', 'e', 'E', 'u', 'U', 'i', 'I', 'o', 'O'};
        for (char ch : vowels) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public void stopWork() {
        this.run = false;
    }

}

