package phone_console;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Etc {
	//0~9만 받아서 String으로
	public static String StringonlyNumber() {
		Scanner sc = new Scanner(System.in);
        String str = "";
        while (true) {
        	str = sc.nextLine();
        	if (str.matches("[0-9]+")) {
                break;
            }else {
            	System.out.println("--숫자만 입력해주세요.--");
            	System.out.print("재입력: ");
            	}
        }
        return str;
	}
	//int 입력 예외처리
	public static int NumberException() {
		Scanner sc = new Scanner(System.in);
        int number = 0;
        while (true) {
            try {
                number = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("--정수를 입력해주세요.--");
                System.out.print("재입력: ");
                sc.nextLine(); // 버퍼를 비워줌
            }
        }
        return number;
	}
	
	//0을 포함한 자연수만 입력 (비디오 수량입력을 위해 작성)
	public static int NationalException() {
		int integer;
		while(true) {
			integer= NumberException();
			if(integer>=0)
				break;
			else {
				System.out.println("--0을 포함한 양수를 입력해주세요.");
				System.out.print("재입력: ");
			}
		}
		return integer;
	}
	public static void ClearConsole() {
		for (int i = 0; i < 10; i++)
		      System.out.println("\n\n\n\n\n\n\n");
	}
}
