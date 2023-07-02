import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pro수식최대화 {
    private static ArrayList<String> op;
    private static ArrayList<String> strList;
    private static long maxValue = 0;
    // str to list
    private static void listGenerate (String regex, String str){
        ArrayList<String> li = new ArrayList<>();
        strList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String match = matcher.group();
            if (match.matches("[*\\-+]"))
                li.add(match);
            strList.add(match);
        }
        HashSet<String> set = new HashSet<>(li);
        op = new ArrayList<>(set);

    }
    private static void priorityGenerate(ArrayList<String> exp, int cnt, boolean[] visited){
        if(cnt == op.size()){
            maxValueFind(exp);
            return;
        }
        for(int i =0; i<op.size();i++){
            if(!visited[i]){
                visited[i] = true;
                exp.add(op.get(i));
                priorityGenerate(exp,cnt+1,visited);
                visited[i]= false;
                exp.remove(op.get(i));
            }
        }
    }
    private static void maxValueFind(ArrayList<String> exp){
        ArrayList<String> temp = new ArrayList<>();
        temp.addAll(strList);
        int c = 0;
        while(c < exp.size()){
            for(int i =0; i< temp.size();i++){
                String tmp = "";
                if(exp.get(c).equals(temp.get(i))){
                    switch (exp.get(c)){
                        case "+":
                            tmp = Long.toString(Long.parseLong(temp.get(i-1)) + Long.parseLong(temp.get(i+1)));
                            break;
                        case "-":
                            tmp = Long.toString(Long.parseLong(temp.get(i-1)) - Long.parseLong(temp.get(i+1)));
                            break;
                        case "*":
                            tmp = Long.toString(Long.parseLong(temp.get(i-1)) * Long.parseLong(temp.get(i+1)));
                            break;
                    }
                    temp.remove(i-1);
                    temp.remove(i-1);
                    temp.remove(i-1);
                    temp.add(i-1,tmp);
                    break;
                }
            }
            if (!temp.contains(exp.get(c))) c++;
        }
        if (maxValue < Math.abs(Long.parseLong(temp.get(0)))) maxValue = Math.abs(Long.parseLong(temp.get(0)));
    }
    public static long solution(String expression) {
        listGenerate("\\d+|[-*+]", expression);
        for(int i =0; i<op.size();i++){
            ArrayList<String> temp = new ArrayList<>();
            boolean[] visited = new boolean[op.size()];
            temp.add(op.get(i));
            visited[i] = true;
            priorityGenerate(temp, 1, visited);
        }
        return maxValue;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        long answer = solution(str);
        System.out.println(answer);
    }
}
