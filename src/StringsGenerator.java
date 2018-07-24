import java.util.Random;

public class StringsGenerator {

    private int wordLength() {
        Random ran = new Random();
        int length = ran.nextInt(10)+3;
        return length;
    }

    public String generateWord() {
        char[] word = new char[wordLength()];
        Random randomLetter = new Random();
        for (int i = 0; i < word.length; i++) {
            word[i] = getAlphabet()[randomLetter.nextInt(getAlphabet().length)];
        }
        word = assignVowelInEveryThreeLetters(word);
        String wordString = new String(word);
        return wordString;
    }

    private char[] assignVowelInEveryThreeLetters(char[] word) {

        for (int i = 0; i < word.length; i++) {
            if ((i + 1) % 3 == 0 && !isVowel(word[i])) {
                word[i] = generateVowel();
            }
        }
        return word;
    }

    private char generateVowel() {
        char[] vowelsArray = getVowels();
        Random r = new Random();
        char vowel = vowelsArray[r.nextInt(vowelsArray.length)];
        return vowel;
    }

    private boolean isVowel(char c) {
        char[] vowels = getVowels();
        for (char ch : vowels) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    private char[] getAlphabet() {
        char[] alphabet = {'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i',
                'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r',
                'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z'};

        return alphabet;
    }

    private char[] getVowels(){

        char[] vowels = {'a', 'A', 'e','E', 'u','U', 'i','I', 'o','O'};

        return vowels;
    }
}
