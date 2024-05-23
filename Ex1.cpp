//Дано число m. Требуется в последовательности цифр 1 2 3 4 … 9 расставить знаки
//«+» и «-» так, чтобы значением получившегося выражения было число m. Например,
//m=122. Знаки расставляются как 12+34-5-6+78+9

#include <iostream>
#include <string>
#include <vector>

using namespace std;

// функция для вычисления значения выражения
int evaluateExpression(const string &expression) {
    int total = 0; // инициализируем итоговую сумму
    int currentNumber = 0; // инициализируем текущее число
    int sign = 1; // 1 для '+', -1 для '-' (по умолчанию положительное число)

    // проходим по каждому символу в строке выражения
    for (char c : expression) {
        if (isdigit(c)) { // если символ - цифра
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
void findExpressions(string currentExpression, int currentIndex, int target) {
    if (currentIndex > 9) {
        if (evaluateExpression(currentExpression) == target) {
            cout << currentExpression << endl; // если значение выражения равно цели, печатаем выражение
        }
        return;
    }

    // добавляем текущую цифру к текущему выражению
    findExpressions(currentExpression + to_string(currentIndex), currentIndex + 1, target);

    // добавляем текущую цифру с плюсом
    findExpressions(currentExpression + "+" + to_string(currentIndex), currentIndex + 1, target);

    // добавляем текущую цифру с минусом
    findExpressions(currentExpression + "-" + to_string(currentIndex), currentIndex + 1, target);
}

int main() {
    int m;
    cout << "Введите число m: ";
    cin >> m;

    // начинаем с первой цифры
    findExpressions("1", 2, m);

    return 0;
}
