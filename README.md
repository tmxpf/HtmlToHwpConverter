## <폴더 구조> 
 - root Storage
   - BinData
     - BIN001.jpg
     - ...
   - BodyText
     - Section0
     - ...
   - DocOptions
     - _LinkDoc
   - Scripts
     - DefaultJScript
     - JScriptVersion
   - DocInfo
   - FileHeader
   - HwpSummaryInfomation
   - PrvImage
   - PrvText

## 이미지 삽입방법 및 로직
1. rootStorage => BinData 스토리지에 넣을 스트림 오브젝트를 생성한다.
```java
streamIndex = hwpFile.getBinData().getEmbeddedBinaryDataList().size() + 1;
String streamName = ImageUtil.getStreamName(streamIndex, imgObj.getImgExt());
hwpFile.getBinData().addNewEmbeddedBinaryData(streamName, imgBytes, compressMethod);

public void addNewEmbeddedBinaryData(String name, byte[] data, BinDataCompress compressMethod) {
    EmbeddedBinaryData ebd = this.addNewEmbeddedBinaryData();
    ebd.setName(name);
    ebd.setData(data);
    ebd.setCompressMethod(compressMethod);
}
```

2. rootStorage => DocInfo 스트림에 BinData스토리지에 넣은 데이터 스트림(BIN0001.jpg와 같은 데이터)의 streamIndex값을 넣어서 관리한다.
```java
binDataID = addBinDataInDocInfo(streamIndex);

private int addBinDataInDocInfo(int streamIndex) {
    BinData bd = new BinData();
    bd.getProperty().setType(BinDataType.Embedding);
    bd.getProperty().setCompress(compressMethod);
    bd.getProperty().setState(BinDataState.NotAcceess);
    bd.setBinDataID(streamIndex);
    bd.setExtensionForEmbedding(imgObj.getImgExt());
    hwpFile.getDocInfo().getBinDataList().add(bd);
    return hwpFile.getDocInfo().getBinDataList().size();
}
```
3. BinData 스토리지에 BIN001.jpg(byte데이터)를 저장하고, DocInfo 스트림에 BIN001.jpg데이터에 대한 
정보를 저장했다면 한글파일에 이미지를 그려줘야합니다. 한글파일 텍스트 내용, 이미지, 도표 등의 데이터들은
BodyText => Section 스트림에 저장됩니다.



