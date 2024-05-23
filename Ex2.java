import java.util.Arrays;

public class Main {
    
    public static boolean isMatch(String s, String p) {
        int m = s.length(); // инициализируем переменную m длиной строки s
        int n = p.length(); // инициализируем переменную n длиной строки p
        
        // создаем двумерный массив для хранения результатов сопоставления
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // пустая строка и пустой шаблон сопоставляются
        dp[0][0] = true;
        
        // обрабатываем случай, когда шаблон начинается с '*'
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') { // если текущий символ шаблона - '*'
                dp[0][j] = dp[0][j - 1]; // устанавливаем значение в соответствии с предыдущим символом шаблона
            }
        }
        
        // заполняем таблицу сопоставления
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) { // если текущий символ шаблона - '?' или совпадает с текущим символом строки
                    dp[i][j] = dp[i - 1][j - 1]; // устанавливаем значение в соответствии с предыдущими символами
                } else if (p.charAt(j - 1) == '*') { // если текущий символ шаблона - '*'
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // устанавливаем значение в соответствии с предыдущими символами и их комбинациями
                }
            }
        }
        
        // возвращаем результат сопоставления для всей строки
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        String s1 = "aa", p1 = "a";
        String s2 = "aa", p2 = "*";
        String s3 = "cb", p3 = "?a";

        System.out.println("Пример 1: " + (isMatch(s1, p1) ? "true" : "false"));
        System.out.println("Пример 2: " + (isMatch(s2, p2) ? "true" : "false"));
        System.out.println("Пример 3: " + (isMatch(s3, p3) ? "true" : "false"));
    }
}
