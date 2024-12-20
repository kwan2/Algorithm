import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1283 {

    private static String makeWord(int targetIndex, String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(i == targetIndex) {
                sb.append("[").append(s.charAt(i)).append("]");
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    private static int findSecondWord(Set<String> preset, String s) {
        String[] splitWords = s.split(" ");
        int targetIndex = 0;

        for (String splitWord : splitWords) {
            for (int i = 0; i < splitWord.length(); i++) {
                String candidate = splitWord.substring(i, i + 1).toUpperCase();
                if (!preset.contains(candidate)) {
                    preset.add(candidate);
                    return targetIndex + i;
                }
            }
            targetIndex += splitWord.length() + 1;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Set<String> preset = new HashSet<>();
        String[] options = new String[N];

        for(int i = 0; i < N; i++) {
            String keyWord = br.readLine();
            String[] splitWords = keyWord.split(" ");
            boolean isSelected = false;
            int targetIndex = 0;

            for(String word : splitWords) {
                String tempWord = word.substring(0, 1).toUpperCase();
                if(!preset.contains(tempWord)) {
                    isSelected = true;
                    preset.add(tempWord);
                    break;
                }
                // 공백 포함
                targetIndex += word.length() + 1;
            }

            if(isSelected) {
                options[i] = makeWord(targetIndex, keyWord);
            } else {
                targetIndex = findSecondWord(preset, keyWord);
                if(targetIndex != -1) {
                    options[i] = makeWord(targetIndex, keyWord);
                } else {
                    options[i] = keyWord;
                }
            }
        }

        for(String option : options) {
            System.out.println(option);
        }
    }
}
