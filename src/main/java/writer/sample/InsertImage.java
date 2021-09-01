package writer.sample;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.writer.HWPWriter;
import writer.imagewriter.ImageForHWP;
import writer.imagewriter.ImageWriter;

public class InsertImage {

    public static void insertImageToHWP() throws Exception {
        String filename = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/blank.hwp";
        HWPFile hwpFile = HWPReader.fromFile(filename);

        if (hwpFile != null) {
            String writePath = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/result-making-blankfile.hwp";
            String imagePath = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/ocean-3605547_1920.jpg";

            ImageWriter imageMaker = new ImageWriter();
            ImageForHWP imageForHWP = imageMaker.loadImage(imagePath);
            imageMaker.insertImageToHwp(hwpFile, imageForHWP);

            HWPWriter.toFile(hwpFile, writePath);
        }
    }

}
