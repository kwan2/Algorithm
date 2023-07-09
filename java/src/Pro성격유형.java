import java.util.*;

public class Pro성격유형 {
    HashMap<String,Integer> hm;
    static String[] element = {"R", "T", "C", "F", "J", "M", "A", "N"};

    private HashMap<String,Integer> init (){
        for(int i = 0; i < 8; i++){
            hm.put(element[i],0);
        }
        return hm;
    }

    public String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder();
        hm = new HashMap<>();
        init();

        for(int i = 0; i < survey.length; i++){
            String t1 = survey[i].substring(0,1);
            String t2 = survey[i].substring(1);
            if(choices[i] > 4) hm.replace(t2, choices[i] - 4+ hm.get(t2));
            else if(choices[i] < 4) hm.replace(t1, 4 - choices[i] + hm.get(t1));
        }
        for (int i = 0; i < element.length; i += 2){
            String t1 = element[i];
            String t2 = element[i+1];
            if(hm.get(t1) - hm.get(t2) >= 0) sb.append(t1);
            else sb.append(t2);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Pro성격유형 test = new Pro성격유형();
        System.out.println(test.solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3}));
    }
}