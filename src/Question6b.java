public class Question6b {
    private char[] letters = {'S', 'I', 'X', 'E', 'V', 'N', 'T', 'W', 'Y'};
    private int[] values = {6, 5, 0, 8, 7, 2, 1, 3, 4};


    public boolean showResult(String[] words, String result) {
        return getWordsValue(words) == Integer.parseInt(getEachValue(result));
    }

    int getWordsValue(String[] words) {
        int wordValue = 0;

        for (String word : words) {

            wordValue += Integer.parseInt(getEachValue(word));
        }
        return wordValue;
    }

    String getEachValue(String word) {
        String letterValue = "";
        char letter;
        for (int i = 0; i < word.length(); i++) {
            // get each letter of the word
            letter = word.charAt(i);
            letterValue += getValueOf(letter);
        }
        return letterValue;
    }

    int getValueOf(char letter) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == letter) {
                return values[i];
            }
        }
        throw new IllegalAccessError();
    }

    public static void main(String[] args) {
        Question6b answer = new Question6b();
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";

        System.out.println(answer.showResult(words, result));
        int wordsValue = answer.getWordsValue(words);

        System.out.println("Words value : " + wordsValue);
        String resultValue = answer.getEachValue(result);
        System.out.println("Result value : " + resultValue);
    }
}