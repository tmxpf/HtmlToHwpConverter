package writer.sample;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;
import writer.textwriter.TextMaker;

public class InsertText {

    public static void insertImageToHWP() throws Exception {
        String filename = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/blank.hwp";
        HWPFile hwpFile = HWPReader.fromFile(filename);
        String writePath = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/result-making-blankfile.hwp";

        if (hwpFile != null) {
            TextMaker tmcs = new TextMaker();
            tmcs.test(hwpFile);

            HWPWriter.toFile(hwpFile, writePath);
        }
    }

}
