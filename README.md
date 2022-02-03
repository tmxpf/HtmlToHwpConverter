## 폴더 구조
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

## 이미지 삽입로직
1. `BinData`스토리지는 이미지, 음성파일과 같은 바이너리 데이터를 저장하는 저장소입니다. 이곳에
EmbeddedBinaryData 클래스를 생성하여 `바이너리 데이터 객체의 이름(ex. BIN0001.jpg)`과 `바이너리 데이터 객체의 데이터`, `암축 방법`에 대한
설정값을 삽입하여 저장합니다.
```java
int streamIndex = hwpFile.getBinData().getEmbeddedBinaryDataList().size() + 1;
String streamName = getStreamName();
byte[] fileBinary = loadFile();

HWPFile hwpFile.getBinData().addNewEmbeddedBinaryData(streamName, imgObj.getBytes(), compressMethod);

/**
 * 새로운 첨부된 바이너리 데이터 객체를 생성하여 list에 추가합니다.
 *
 * @param name           새로운 첨부된 바이너리 데이터 객체의 이름
 * @param data           새로운 첨부된 바이너리 데이터 객체의 데이터
 * @param compressMethod 암축 방법
 */
public void addNewEmbeddedBinaryData(String name, byte[] data, BinDataCompress compressMethod) {
        EmbeddedBinaryData ebd = addNewEmbeddedBinaryData();
        ebd.setName(name);
        ebd.setData(data);
        ebd.setCompressMethod(compressMethod);
}
```

2. `BinData`스토리지에 바이너리 데이터를 저장하였다면, `DocInfo`스트림 파일에 `바이너리 데이터(ex. BIN0001.jpg)`에 대한
정보를 저장해야 합니다. 바이너리 데이터가 저장된 `streamIndex`값을 `DocInfo`스트림에 저장합니다.
```java
private int addBinDataInDocInfo(int streamIndex) {
    //아래 BinData는 1번에서 설명하는 BinData스토리지와는 다른 객체입니다.
    //위 BinData는 바이너리 데이터를 저장하는 스토리지이고, 아래 BinData는 DocInfo에 저장할 스트림 파일입니다.
    BinData bd = new BinData();
    bd.getProperty().setType(BinDataType.Embedding);
    bd.getProperty().setCompress(compressMethod);
    bd.getProperty().setState(BinDataState.NotAcceess);
    bd.setBinDataID(streamIndex);
    bd.setExtensionForEmbedding(imageFileExt);
    hwpFile.getDocInfo().getBinDataList().add(bd);
    return hwpFile.getDocInfo().getBinDataList().size();
}
```

3. 이미지 바이너리 데이터를 저장하고 `DocInfo`스트림에도 저장을 했다면 이제 본문에 직접 이미지를 그려줘야합니다.
본문은 `BodyText`스토리지안 `Section`스트림에 저장한 값을 출력합니다.
```java
Section firstSection = hwpFile.getBodyText().getSectionList().get(0);
Paragraph firstParagraph = firstSection.getParagraph(0);
```
`Paragraph`객체는 `Section`스트림 내에 있는 하나의 `문단`을 의미합니다.
그리고 생성한 문단에 텍스트가 아닌 이미지가 삽입될 것 이라는 표시를 합니다.
```java
// 문단에서 사각형 컨트롤의 위치를 표현하기 위한 확장 문자를 넣는다.
firstParagraph.getText().addExtendCharForGSO();
```
이미지 삽입 표시를 했다면 직접 문단에 사각형 컨트롤러(`ControlRectangle`)를 추가합니다.
```java
rectangle = (ControlRectangle) firstParagraph.addNewGsoControl(GsoControlType.Rectangle);
```

4. 이제 생성한 사각형 컨트롤러의 `Header`객체와 `property`객체의 속성을 지정합니다.
```java
CtrlHeaderGso hdr = rectangle.getHeader();
GsoHeaderProperty prop = hdr.getProperty();
prop.setLikeWord(false);
prop.setApplyLineSpace(false);
        .
        .
        .
hdr.setyOffset(fromMM(shapePosition.y));
hdr.setxOffset(fromMM(shapePosition.x));
hdr.setWidth(fromMM(shapePosition.width));
hdr.setHeight(fromMM(shapePosition.height));
hdr.setzOrder(0);
hdr.setOutterMarginLeft(0);
```

5. 사각형 컨트롤러(`ControlRectangle`)의 일반설정이 완료되었다면 사각형 자체의 설정(ex. 테두리 선 정보, 그림자 정보 등)을  
작성합니다.
```java
ShapeComponentNormal sc = (ShapeComponentNormal) rectangle.getShapeComponent();
sc.setOffsetX(0);
sc.setOffsetY(0);
sc.setGroupingCount(0);
        .
        .
        .
sc.createLineInfo();
LineInfo li = sc.getLineInfo();
li.getProperty().setLineEndShape(LineEndShape.Flat);
li.getProperty().setStartArrowShape(LineArrowShape.None);
li.getProperty().setStartArrowSize(LineArrowSize.MiddleMiddle);
        .
        .
        .
sc.createFillInfo();
FillInfo fi = sc.getFillInfo();
fi.getType().setPatternFill(false);
        .
        .
        .
ImageFill imgF = fi.getImageFill();
imgF.setImageFillType(ImageFillType.FitSize);
imgF.getPictureInfo().setBrightness((byte) 0);
        .
        .
        .
sc.createShadowInfo();
ShadowInfo si = sc.getShadowInfo();
si.setType(ShadowType.None);
si.getColor().setValue(0xc4c4c4);
        .
        .
        .
```
>`ShapeComponentNormal`객체가 일반 컨트롤을 위한 객체 공통 속성 레코드이며,
> 내부에는 `LineInfo(선 설정)`, `FillInfo(채우기 설정)`, `ImageFill(이미지 채우기 설정)`,
> `ShadowInfo(그림자 정보 설정)`의 설정 객체들을 포함하고 있습니다.

6. 마지막으로 `사각형 객체 속성 레코드(ShapeComponentRectangle)`를 가져와 이미지를 draw합니다.
```java
ShapeComponentRectangle scr = rectangle.getShapeComponentRectangle();
scr.setRoundRate((byte) 0);
scr.setX1(0);
scr.setY1(0);
scr.setX2(fromMM(shapePosition.width));
scr.setY2(0);
scr.setX3(fromMM(shapePosition.width));
scr.setY3(fromMM(shapePosition.height));
scr.setX4(0);
scr.setY4(fromMM(shapePosition.height));
```

## 텍스트 삽입로직
1. 텍스트 글꼴 설정
2. 텍스트 글자 모양 설정
3. BodyText -> Section -> Paragraph(문단)가져와서 `ParaHeader`입력
4. `ParaText`입력
5. `ParaCharShape(글자 모양)`입력
6. `ParaLineSeg(문단 레이아웃)`입력
7. `LineSegItem(각 중의 align 정보에 대한 객체)`입력

