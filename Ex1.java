import java.util.Scanner;

public class Main {

    // функция для вычисления значения выражения
    public static int evaluateExpression(String expression) {
        int total = 0; // инициализируем итоговую сумму
        int currentNumber = 0; // инициализируем текущее число
        int sign = 1; // 1 для '+', -1 для '-' (по умолчанию положительное число)

        // проходим по каждому символу в строке выражения
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) { // если символ - цифра
                currentNumber = currentNumber * 10 + (c - '0'); // добавляем цифру к текущему числу
            } else { // если символ - знак '+' или '-'
                total += sign * currentNumber; // добавляем текущее число с учетом знака к общей сумме
                currentNumber = 0; // сбрасываем текущее число
                sign = (c == '+') ? 1 : -1; // определяем знак для следующего числа
            }
        }
        total += sign * currentNumber; // добавляем последнее число к общей сумме
        return total; // возвращаем итоговое значение выражения
    }

    // рекурсивная функция для поиска всех выражений
    public static void findExpressions(String currentExpression, int currentIndex, int target) {
        if (currentIndex > 9) {
            if (evaluateExpression(currentExpression) == target) {
                System.out.println(currentExpression); // если значение выражения равно цели, печатаем выражение
            }
            return;
        }

        // добавляем текущую цифру к текущему выражению
        findExpressions(currentExpression + currentIndex, currentIndex + 1, target);

        // добавляем текущую цифру с плюсом
        findExpressions(currentExpression + "+" + currentIndex, currentIndex + 1, target);

        // добавляем текущую цифру с минусом
        findExpressions(currentExpression + "-" + currentIndex, currentIndex + 1, target);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число m: ");
        int m = scanner.nextInt();

        // начинаем с первой цифры
        findExpressions("1", 2, m);

        scanner.close();
    }
}
