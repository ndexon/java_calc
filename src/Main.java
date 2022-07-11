
import java.lang.Exception;
import java.util.Scanner;



public class Main {
    public static String calc (String input) throws Exception {


        String [] wordArr  = input.split(" "); // создаю массив из операндов и оператора по разделителю пробелу
        // данные в тз через пробел вбиваются, без пробела работать не будет (надеюсь, что и не должно, и я правильно понял тз)

        boolean romaninput = false; //изменится, если введем римские цифры

        if (wordArr.length != 3) throw new Exception("incorrect data"); //если длина массива не 3, значит данные неверные)
        if ( (wordArr[0].equals("10") || (wordArr[0].charAt(0) > '0' && wordArr[0].charAt(0) <= '9')) &&
                (wordArr[2].equals("10") || (wordArr[2].charAt(0) > '0' && wordArr[2].charAt(0) <= '9')) );
        //выше проверка на верность введенных арабских цифр согласно тз)
            else try {
                    Enum.valueOf(Roman.class, wordArr[0]); //иначе проверка на верность римских, если ввели что-то другое, то заканчиваем работу)
                    Enum.valueOf(Roman.class, wordArr[2]);
                    wordArr[0] = Enum.valueOf(Roman.class, wordArr[0]).getArabian(); //если все хорошо, то получаю значения из перечисления
                    wordArr[2] = Enum.valueOf(Roman.class, wordArr[2]).getArabian();
                    if ((wordArr[1].equals("-") || wordArr[1].equals("/"))  && Integer.parseInt(wordArr[0]) <= Integer.parseInt(wordArr[2]))
                        throw new Exception("incorrect data"); //тут проверка на корректность римских цифр (первое меньше второго, но операция тогда должна быть умножение или сложение)
                    romaninput = true; //если дошли до этого момента, то все хорошо и введенные данные в римских цифрах верны
                     }

                    catch (Exception e) {
                        throw new Exception("incorrect data"); //исключение о неверных данных
                    }

        int result = switch (wordArr[1].charAt(0)) { //проверяем оператор, если он введен верно, то меняем строки на числа и делаем соответствующую операцию)
            case '+' -> Integer.parseInt(wordArr[0]) + Integer.parseInt(wordArr[2]);
            case '-' -> Integer.parseInt(wordArr[0]) - Integer.parseInt(wordArr[2]);
            case '*' -> Integer.parseInt(wordArr[0]) * Integer.parseInt(wordArr[2]);
            case '/' -> Integer.parseInt(wordArr[0]) / Integer.parseInt(wordArr[2]);
            default -> throw new Exception("incorrect data"); // если оператор введен неверно, то исключение
        };
        String newResult = Integer.toString(result); //перевожу результат в строку
       if (!romaninput) // если считали в арабскких числах, то возвращаем его
        return newResult;
        else {
            StringBuilder romanResult = new StringBuilder(); //иначе результат нужно перевести в римское число
            char [] chars = String.valueOf(result).toCharArray(); //создаю массив символов числа-результата
            if (result % 10 == 0 || result <= 9) {
                romanResult.append(Roman.getEnum((String.valueOf(result)))); //если результат есть в перечислении то просто его вывожу
            }
            else for (int i=0; i < chars.length; i++) { //иначе создаю цикл и вывожу результат.
                if (i == 0)  romanResult.append(Roman.getEnum(String.valueOf(result / 10 * 10)));
                else romanResult.append(Roman.getEnum((String.valueOf(chars[i]))));

            }

           return romanResult.toString(); //возвращаю ответ

            }

       }

    public static void main (String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println(calc(in.nextLine()));
    }
}
