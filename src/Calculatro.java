/* Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
        Реализуй класс Main с методом public static String calc(String input). Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения. Ты можешь добавлять свои импорты, классы и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие)
        Требования:
        1.	Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)! Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
        2.	Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
        3.	Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
        4.	Калькулятор умеет работать только с целыми числами.
        5.	Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
        6.	При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.
        7.	При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
        8.	При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
        9.	Результатом операции деления является целое число, остаток отбрасывается.
        10.	Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль. Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
*/

        import java.util.Scanner;

public  class Calculatro{
    public static void main(String[] args) throws Exception {                                                           //требование 7 (throws и while)
        while (true){
        Scanner input=new Scanner(System.in);
        System.out.println("Input:");
        String s=input.nextLine();
        System.out.println("Output:\n"+Main.calc(s));
        }
    }
}
class Main{
    public static String calc(String input) throws Exception{
        boolean romOrAra=false;
        int result, num1, num2;                                                                                         //требование 4 и 9
        String output;
        String oper=detectOper(input);
        Main arabToRom= new Main();
        String[] inputdata=input.split("[+/*\\-]");
        if(inputdata.length>2){
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)"); //требование 8
        } else if (inputdata.length<2){
            throw new Exception("Cтрока не является математической операцией");
        }
        inputdata[0]=inputdata[0].trim();
        inputdata[1]=inputdata[1].trim();
        if (Roman.isRom(inputdata[0])&&(Roman.isRom(inputdata[1]))){
            num1=romToAr(inputdata[0]);                                                                                 //требование 2
            num2=romToAr(inputdata[1]);
            romOrAra=true;
        }else if(Roman.isRom(inputdata[0])||(Roman.isRom(inputdata[1]))) {                                              //требование 5
            throw new Exception("Используются одновременно разные системы счисления");
        }else{
            try {
                num1 = Integer.parseInt(inputdata[0]);                                                                  //требование 2
                num2 = Integer.parseInt(inputdata[1]);
            } catch (NumberFormatException e) {
                throw new Exception("Cтрока не является математической операцией");
            }  
        }
        if (num1 > 10 || num2 > 10) {                                                                                   //требование 3
            throw new Exception("Числа должны находиться в диапазоне от 1 до 10");
        }
        switch (oper){                                                                                                  //требование 1
            case "+"->result=num1+num2;
            case "-"->result=num1-num2;
            case "*"->result=num1*num2;
            default -> result=num1/num2;
        }
   if(romOrAra){                                                                                                        //требование 6 b 10
       if(result<1){
           throw new Exception("В римской системе нет отрицательных чисел");
       }
       output = arabToRom.arToRom(result);
   }else{
       output = Integer.toString(result);
   }
   return output;
    }
        static Integer romToAr(String romInput){
        int relust=0;
        int[] ar={10,9,5,4,1};
        String[] roman={"X", "IX", "V", "IV", "I"};
                for (int i=0;i<ar.length;i++){
                    while (romInput.indexOf(roman[i])==0){
                        relust+=ar[i];
                        romInput=romInput.substring((roman[i].length()));
                    }
                }
                return relust;
    }
    String arToRom(int arInput){
        String result="";
        int value;
        int[] ar={100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rom={"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i=0; i<ar.length;i++){
            value=arInput/ar[i];
            for (int j=0; j<value; j++){
                result=result.concat(rom[i]);
            }
            arInput=arInput%ar[i];
        }
        return result;
    }
    static String detectOper(String operation){
        if (operation.contains("+")) return "+";
        else if (operation.contains("-")) return "-";
        else if (operation.contains("*")) return "*";
        else return "/";
    }
    static class Roman{
        static String[] arrRom=new String[]{"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        public static boolean isRom(String val){
            for (String s : arrRom) {
                if (val.equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }
}