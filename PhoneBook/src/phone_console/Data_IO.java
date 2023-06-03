package phone_console;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Data_IO {
    private static final String FILE_NAME = "data.txt";
    //파일쓰기 
    public static void writeData(String [][]Data) {
    	if(Data.length<1) return;
    	try {
    		FileWriter fileWriter = new FileWriter(FILE_NAME, false);
    		fileWriter.write(Data[0][0]+"\n"+Data[0][1]);
    		fileWriter.close();
    	} catch (IOException e) {
    		System.out.println("데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
    	}
    	try {
    		FileWriter fileWriter = new FileWriter(FILE_NAME, true);
    		for(int i=1; i<Data.length; i++) {
    			fileWriter.write("\n" + Data[i][0] + "\n" + Data[i][1]);
    		}
    		fileWriter.close();
    	} catch (IOException e) {
    		System.out.println("데이터 저장 중 오류가 발생했습니다: " + e.getMessage());
    		}
    }
    //파일 읽어오기
    public static String[][] readData() {
        try {
            File file = new File(FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            ArrayList<String[]> dataList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String name = line.trim();
                String phoneNumber = reader.readLine().trim();
                String[] data = { name, phoneNumber };
                dataList.add(data);
            }
            
            String[][] result = new String[dataList.size()][2];
            for (int i = 0; i < dataList.size(); i++) {
                result[i] = dataList.get(i);
            }
            
            return result;
        } catch (IOException e) {
            System.out.println("데이터 읽기 중 오류가 발생했습니다: " + e.getMessage());
            return new String[0][0];
        }
    }

    public static boolean checkDataFormat() {
    	File file = new File(FILE_NAME);
    	
    	//파일 존재여부 확인 후 파일 생성
    	if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("파일 생성 중 오류가 발생했습니다: " + e.getMessage());
                return false;
            }
            return true;
        }
    	
    	//파일 양식 검사
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int lineNumber = 1;
            boolean nameExpected = true;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (nameExpected) {
                    if (line.isEmpty()) {
                        System.out.println("필수 데이터 없음: " + lineNumber);
                        return false;
                    }
                } else {
                    if (line.isEmpty()) {
                    } else if (!line.matches("\\d+")) {
                        System.out.println("데이터 조건 불충족: " + lineNumber);
                        return false;
                    }
                }

                nameExpected = !nameExpected;
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 에러가 생겼습니다: " + e.getMessage());
            return false;
        }
        return true;
    }
}