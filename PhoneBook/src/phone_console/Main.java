package phone_console;
public class Main {
	public static void main(String[] args) {
		Member_ArrayList MemList = new Member_ArrayList();
		int n = 0;
		Boolean flag = true;
		//파일 입출력 설정
		if(Data_IO.checkDataFormat()) MemList.Data_in(Data_IO.readData());
		while (flag) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. 회원 추가     | 2. 회원 수정  | 3. 회원 삭제   | 4. 회원 검색  | 5. 회원 출력");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("                                         | 6. HELP    | 7. 종료");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("실행할 번호를 입력하세요:");
			n = Etc.NumberException();
			switch (n) {
			case 1: // 회원추가
				MemList.add();
				MemList.Data_Out();
				break;
			case 2: // 회원수정
				MemList.edit();
				MemList.Data_Out();
				break;
			case 3: // 회원삭제
				MemList.remove();
				MemList.Data_Out();
				break;
			case 4: // 회원검색
				MemList.prn_search();
				MemList.Data_Out();
				break;
			case 5: // 회원출력
				MemList.prn_all();
				MemList.Data_Out();
				break;
			case 6:
				Help();
				break;
			case 7: //종료
				flag=false;
				System.out.println("--프로그램을 종료합니다.--");
				break;
			default:
				Etc.ClearConsole();
				System.out.println("--1~7사이 정수를 입력해주세요.--");
			}
		}
	}
	static void Help() {
		Etc.ClearConsole();
		System.out.println("[회원추가]\n"
				+ "1. 이름과 전화번호를 입력합니다.\n"
				+ "2. 이름과 전화번호는 필수입력입니다.\n"
				+ "[회원수정]\n"
				+ "1. 수정하고 싶은 회원의 이름을 입력합니다.\n"
				+ "2. 동명이인이라면 수정할 회원목차를 입력합니다.\n"
				+ "3. 이름과 전화번호를 입력합니다.\n"
				+ "4. 이름은 필수입력, 전화번호는 숫자만 가능합니다.\n"
				+ "[회원삭제]"
				+ "1. 삭제하고 싶은 회원의 이름을 입력합니다.\n"
				+ "2. 동명이인이라면 삭제할 회원목차를 입력합니다.\n"
				+ "[회원검색]\n"
				+ "1. 검색할 회원의 이름을 입력합니다.\n"
				+ "[회원출력]\n"
				+ "1. 모든 회원의 정보를 출력합니다.\n"
				+ "[HELP]\n"
				+ "1. 본 프로그램의 사용법을 알려줍니다.\n"
				+ "[종료]\n"
				+ "1. 본 프로그램을 종료합니다.\n"
				+ "");
	}
}