package phone_console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Member_ArrayList {
	private ArrayList<Member> member_list;
	//생성자 :: 메인함수에서 LinkedList <Member>객체를 참조받는다.
	public Member_ArrayList() {
		this.member_list= new ArrayList<Member>();
	}
	//출력
	public void prn_all() {
		int i=1;
		Member MemTemp;
		Iterator<Member> Mitr = member_list.iterator();
		Etc.ClearConsole();
		System.out.println("==회원 출력==");
		while(Mitr.hasNext()) {
			MemTemp = Mitr.next();
			System.out.println("-"+i+"-");
			MemTemp.Mem_prn();
			System.out.println();
			i++;
		}
	}
	//검색
	public void prn_search() {
		Scanner sc = new Scanner(System.in);
		Iterator<Member> Mitr = member_list.iterator();
		Member MemTemp;//Iterator 값 받아줄 변수
		System.out.println("==회원 검색==");
		int i=1;//인덱스 카운트
		System.out.println("검색할 회원의 이름 입력");
		String name = sc.next();
		Etc.ClearConsole();
		while(Mitr.hasNext()) {//다음값이 있으면
			MemTemp = Mitr.next();//받아서
			if(MemTemp.Name_return().equals(name)) {//이름비교하고
				System.out.println("\n-"+i+"-");//인덱스 출력
				MemTemp.Mem_prn();//데이터 출력
				System.out.println();
			}
			i++;//카운트 세기
		}
	}
	//추가
	public void add() {
		Scanner sc = new Scanner(System.in);
		Member MemTemp;
		System.out.println("==회원 추가==");
		System.out.print("이름 입력:");
		String name = sc.next();
		System.out.print("전화번호 입력:");
		String phnum = Etc.StringonlyNumber();
		MemTemp = new Member(name,phnum);
		member_list.add(MemTemp);
		Etc.ClearConsole();
		System.out.println("추가되었습니다.");
	}
	//삭제
	public void remove() {
		Scanner sc = new Scanner(System.in);
		Iterator<Member> Mitr = member_list.iterator();
		long []remove_index= new long[member_list.size()];
		int i=1;//인덱스 카운트
		int same=0;//같은놈 나오면 카운트
		Member MemTemp;//Iterator 값 받아줄 변수
		System.out.println("==회원 삭제==");
		//remove_index 0으로 초기화
		for (int j = 0; j < remove_index.length; j++) {
		    remove_index[j] = 0;
		}
		System.out.println("삭제할 회원의 이름 입력");
		String name = sc.next();
		while(Mitr.hasNext()) {//다음값이 있으면
			MemTemp = Mitr.next();//받아서
			if(MemTemp.Name_return().equals(name)) {//이름비교하고
				System.out.println("\n-"+i+"-");//인덱스 출력
				MemTemp.Mem_prn();//데이터 출력
				same++;
				remove_index[i-1]=1;
			}
			i++;//카운트 세기
		}
		//삭제할 데이터 없음
		if(same==0) {
			System.out.println("해당되는 회원이 없습니다.");
			return;
		}
		//삭제할 데이터 유일
		if(same==1) {
			for(int j=0;j<member_list.size();j++) {//리스트 전체 반복
				if(remove_index[j]==1)//1이들어있는인덱스가 삭제할 인덱스
					member_list.remove(j);//삭제
			}
			Etc.ClearConsole();
			System.out.println("삭제되었습니다.");
			return;
		}
		//삭제할 데이터가 2개 이상일 때
		if(same>1) {
			while(true) {
				while (true) {
				    System.out.println("동일한 회원이 " + same + "명 있습니다.");
				    System.out.println("삭제할 회원의 목차번호를 입력해주세요.");
				    i = Etc.NumberException();
				    if (0 < i && i <= remove_index.length) {
				        break;
				    }
				    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				}
				if(remove_index[i-1]==1) {//입력된 숫자가 검색된 넘버와 같으면
					member_list.remove(i-1);
					Etc.ClearConsole();
					System.out.println("삭제되었습니다.");
					break;
				}else {
					System.out.println("검색된 회원의 목차번호만 입력해주세요.");
				}
			}
			return;
		}
	}
	//수정
	public void edit() {
		Scanner sc = new Scanner(System.in);
		Iterator<Member> Mitr = member_list.iterator();
		int []edit_index= new int[member_list.size()];
		int final_index = 0;//최종 수정할 인덱스
		int i=1;//인덱스 카운트
		int same=0;//같은놈 나오면 카운트
		Member MemTemp;//Iterator 값 받아줄 변수
		System.out.println("==회원 수정==");
		//remove_index 0으로 초기화
		for (int j = 0; j < edit_index.length; j++) {
		    edit_index[j] = 0;
		}
		System.out.print("수정할 회원의 이름입력:");
		String name = sc.next();
		while(Mitr.hasNext()) {//다음값이 있으면
			MemTemp = Mitr.next();//받아서
			if(MemTemp.Name_return().equals(name)) {//이름비교하고
				System.out.println("-"+i+"-");//인덱스 출력
				MemTemp.Mem_prn();//데이터 출력
				same++;
				edit_index[i-1]=1;
			}
			i++;//카운트 세기
		}
		//수정할 데이터가 없거나 하나 일 때
		if(same==0) {
			System.out.println("해당되는 회원이 없습니다.");
			return;
		}
		//수정할 데이터 유일
		if(same==1) {
			for(int j=0;j<edit_index.length;j++) {//리스트 전체 반복
				if(edit_index[j]==1) {//1이들어있는인덱스가 삭제할 인덱스
					final_index=j;
				}
			}
		}
		//수정할 데이터가 2개 이상일 때
		if(same>1) {
			System.out.println("동일한 회원이 " + same + "명 있습니다.");
			System.out.println("수정할 회원의 목차번호를 입력해주세요.\n입력:");
			while(true) {
				i = Etc.NumberException();
			    if ((0 < i) && (i <= edit_index.length) && (edit_index[i-1] == 1)) {
			    	final_index=i-1;
			        break;
			    }
			    System.out.println("--해당되는 목차번호만 입력해주세요--");
			    System.out.print("재입력: ");
			}
		}
		//수정할 데이터 입력
		System.out.print("이름 입력: ");
		String edit_name = sc.next();
		System.out.print("전화번호 입력: ");
		String phnum = Etc.StringonlyNumber();
		MemTemp = new Member(edit_name,phnum);
		member_list.set(final_index, MemTemp);
		Etc.ClearConsole();
		System.out.println("수정되었습니다.");
	}
	//데이터 형식 변환
	public String[][] Data_change(){
		String[][] Data= new String[member_list.size()][2];
		Member MemTemp;
		Iterator<Member> Mitr = member_list.iterator();
		int i=0;
		while(Mitr.hasNext()) {
			MemTemp = Mitr.next();
			Data[i][0]=MemTemp.Name_return();
			Data[i][1]=MemTemp.Pn_return();
			i++;
		}
		return Data;
	}
	//파일출력
	public void Data_in(String [][] Data) {
		Member MemTemp;
		for(String[] row : Data) {
			MemTemp = new Member(row[0],row[1]);
			member_list.add(MemTemp);
		}
	}
	//파일입력
	public void Data_Out() {
		Data_IO.writeData(Data_change());
	}
}