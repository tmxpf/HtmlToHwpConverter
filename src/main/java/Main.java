import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;
import writer.textwriter.TextMaker;

public class Main {

    public static void main(String[] args) throws Exception {

        //이미지 삽입예제
//        InsertImage.insertImageToHWP();

        String filename = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/blank.hwp";
        HWPFile hwpFile = HWPReader.fromFile(filename);
        String writePath = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/result-making-blankfile.hwp";

        if (hwpFile != null) {
            TextMaker tmcs = new TextMaker();
            tmcs.test(hwpFile);

            HWPWriter.toFile(hwpFile, writePath);
        }

        System.out.println("파일 생성 끝.");
    }
}
