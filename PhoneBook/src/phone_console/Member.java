package phone_console;
//회원정보 DB
public class Member {
	private String Name;
	private String Phnum;

    public Member(String name, String Phnum) {
        this.Name = name;
        this.Phnum = Phnum;
    }
    //이름 저장
  	public void Name_get(String name) {this.Name=name;}
  	//이름 반환
  	public String Name_return(){return this.Name;}
  	//이름 출력
  	public void Name_prn() {
  		System.out.println("이름: "+Name);
  	}
    //전화번호 저장
    public void Pn_get(String Phnum) {this.Phnum = Phnum;}
    //전화번호 반환
    public String Pn_return() {return this.Phnum;}
    //전화번호 출력
    public void Pn_prn() {
    	System.out.println("전화번호: "+Phnum);
    }
    public void Mem_prn() {
    	Name_prn();
    	Pn_prn();
    }
}
