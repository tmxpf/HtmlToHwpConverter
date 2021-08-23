package modules;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.docinfo.bindata.BinDataCompress;

import java.io.*;
import java.util.HashSet;
import java.util.Random;

public class ImageMaker {

    private int streamIndex;

    public ImageMaker() {
    }

    public void insertImageToHwp(HWPFile hwpFile, ImageForHWP imgObj) {
        streamIndex = hwpFile.getBinData().getEmbeddedBinaryDataList().size() + 1;
        imgObj.setBinDataName(streamIndex);
        BinDataCompress compressMethod = BinDataCompress.ByStroageDefault;

        hwpFile.getBinData().addNewEmbeddedBinaryData(imgObj.getBinDataName(), imgObj.getBytes(), compressMethod);
    }

    public ImageForHWP loadImage(String filePath) {
        ImageForHWP imageForHWP = new ImageForHWP();
        String ext = filePath.substring(filePath.lastIndexOf(".") + 1);
        File file = new File(filePath);
        InputStream ios = null;

        if(file.exists() && file.isFile()) {
            try {
                byte[] buffer = new byte[(int) file.length()];
                ios = new FileInputStream(file);
                ios.read(buffer);

                imageForHWP.setBytes(buffer);
                imageForHWP.setImgExt(ext);
                imageForHWP.setInstanceId(ImageUtil.getInstanceId());

            } catch (IOException ioException) {

            } finally {
                try {
                    if (ios != null)
                        ios.close();
                } catch (IOException ioException2) {

                }
            }
        }

        return imageForHWP;
    }

    public String getBinDataName(int streamIndex) {
        String imageFileExt = "jpg";
        return "Bin" + String.format("%04X", streamIndex) + "." + imageFileExt;
    }

}
