package modules;

public class ImageForHWP {

    private long instanceId;

    private byte[] bytes;

    private String imgExt;

    private String binDataName;

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getImgExt() {
        return imgExt;
    }

    public void setImgExt(String imgExt) {
        this.imgExt = imgExt;
    }

    public String getBinDataName() {
        return binDataName;
    }

    public void setBinDataName(int streamIndex) {
        this.binDataName = "Bin" + String.format("%04X", streamIndex) + "." + imgExt;
    }
}
