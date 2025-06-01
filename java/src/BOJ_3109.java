import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_3109
 * @date : 25. 6. 1.
 */
public class BOJ_3109 {
    private static int R, C;
    private static char[][] map;

    private static boolean dfs(int x, int y) {

        map[x][y] = '-';

        if(y == C - 1) return true;

        if(x > 0 && map[x - 1][y + 1] == '.') {
            if(dfs(x - 1, y + 1)) return true;
        }

        if(map[x][y + 1] == '.') {
            if(dfs(x, y + 1)) return true;
        }

        if(x + 1 < R && map[x + 1][y + 1] == '.') {
            if(dfs(x + 1, y + 1)) return true;
        }

        return false;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        int answer = 0;

        for(int i = 0; i < R; i++) {
            String row = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        for(int i = 0; i < R; i++) {
            if(dfs(i, 0)) answer++;
        }

        System.out.println(answer);
    }
}
