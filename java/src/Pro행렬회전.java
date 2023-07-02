import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pro행렬회전 {
    int[][] grid;

    public void initialGrid(int rows, int columns){
        int cnt = 1;
        for (int i = 0; i < rows; i++){
            for(int j = 0; j <columns; j++){
                this.grid[i][j] = i * columns + j + 1;
            }
        }
    }
    public int simulate(int[] query){
        List<Integer> before = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for(int i = query[0] - 1; i < query[2]; i++){
            if (i == query[0] - 1){
                for(int j = query[1] - 1; j < query[3]; j++){
                    before.add(this.grid[i][j]);
                }
            }
            else if (i == query[2] - 1){
                for(int j = query[3] -1; j >= query[1]-1; j--){
                    before.add(this.grid[i][j]);
                }
            }else{
                before.add(this.grid[i][query[3]-1]);
                temp.add(this.grid[i][query[1]-1]);
            }
        }
        for(int i = temp.size() - 1; i >= 0; i--){
            before.add(temp.get(i));
        }
        before.add(0,before.get(before.size()-1));
        before.remove(before.size()-1);

        int cnt = 0;
        for(int i = query[0] - 1; i < query[2]; i++){
            if (i == query[0] - 1){
                for(int j = query[1] - 1; j < query[3]; j++){
                    this.grid[i][j] = before.get(cnt++);
                }
            }
            else if (i == query[2] - 1){
                for(int j = query[3] -1; j >= query[1] - 1; j--){
                    this.grid[i][j] = before.get(cnt++);
                }
            }else{
                this.grid[i][query[3]-1] = before.get(cnt++);
            }
        }
        int x = query[2] - 2;
        for(int i = cnt; i < before.size(); i++){
            this.grid[x--][query[1]-1] = before.get(cnt++);
        }
        return Collections.min(before);
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        this.grid = new int[rows][columns];
        initialGrid(rows,columns);
        for (int i =0; i< answer.length; i++){
            answer[i] = simulate(queries[i]);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Pro행렬회전 test = new Pro행렬회전();
        int[] answer;
        int[][] queries = {{1,1,10,9}};
        answer = test.solution(10,9,queries);
        for (int i = 0; i<answer.length;i++){
            System.out.println(answer[i]);
        }
    }
}
