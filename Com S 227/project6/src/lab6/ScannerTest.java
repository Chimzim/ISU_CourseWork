package lab6;

import java.util.Scanner;

public class ScannerTest
{
  public static void main(String[] args)
  {
    int result = sumThree("42 237 5");
    System.out.println(result);
  }
  
  public static int sumThree(String text)
  {
    Scanner in = new Scanner(text);
    int total = 0;
    int num = in.nextInt();
    total = total + num;
    num = in.nextInt();
    total = total + num;
    num = in.nextInt();
    total = total + num;
    return total;
  }
}