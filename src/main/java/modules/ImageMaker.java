package modules;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.Section;
import kr.dogfoot.hwplib.object.bodytext.control.ctrlheader.CtrlHeaderGso;
import kr.dogfoot.hwplib.object.bodytext.control.ctrlheader.gso.*;
import kr.dogfoot.hwplib.object.bodytext.control.gso.ControlRectangle;
import kr.dogfoot.hwplib.object.bodytext.control.gso.GsoControlType;
import kr.dogfoot.hwplib.object.bodytext.control.gso.shapecomponent.ShapeComponentNormal;
import kr.dogfoot.hwplib.object.bodytext.control.gso.shapecomponent.lineinfo.*;
import kr.dogfoot.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo.ShadowInfo;
import kr.dogfoot.hwplib.object.bodytext.control.gso.shapecomponent.shadowinfo.ShadowType;
import kr.dogfoot.hwplib.object.bodytext.control.gso.shapecomponenteach.ShapeComponentRectangle;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.docinfo.BinData;
import kr.dogfoot.hwplib.object.docinfo.bindata.BinDataCompress;
import kr.dogfoot.hwplib.object.docinfo.bindata.BinDataState;
import kr.dogfoot.hwplib.object.docinfo.bindata.BinDataType;
import kr.dogfoot.hwplib.object.docinfo.borderfill.fillinfo.FillInfo;
import kr.dogfoot.hwplib.object.docinfo.borderfill.fillinfo.ImageFill;
import kr.dogfoot.hwplib.object.docinfo.borderfill.fillinfo.ImageFillType;
import kr.dogfoot.hwplib.object.docinfo.borderfill.fillinfo.PictureEffect;

import java.awt.*;
import java.io.*;

public class ImageMaker {

	private final BinDataCompress compressMethod = BinDataCompress.ByStroageDefault;
    private int streamIndex;
    private HWPFile hwpFile;
    private ImageForHWP imgObj;
    private int binDataID;

    private ControlRectangle rectangle;
    private Rectangle shapePosition = new Rectangle(25, 25, 100, 100);

    public ImageMaker() {
    }

    public void insertImageToHwp(HWPFile hwpFile, ImageForHWP imgObj) {
        this.hwpFile = hwpFile;
        this.imgObj = imgObj;

        addBinData();
        binDataID = addBinDataInDocInfo(streamIndex);
        addGsoControl();
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

                BufferedImage bi = ImageIO.read(file);
                
                imageForHWP.setBytes(buffer);
                imageForHWP.setImgExt(ext);
                imageForHWP.setInstanceId(ImageUtil.getInstanceId());
                imageForHWP.setImageInfo(bi);

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
    
    private void addBinData() {
        streamIndex = hwpFile.getBinData().getEmbeddedBinaryDataList().size() + 1;
        String streamName = ImageUtil.getStreamName(streamIndex, imgObj.getImgExt());
        hwpFile.getBinData().addNewEmbeddedBinaryData(streamName, imgObj.getBytes(), compressMethod);
        binDataID = addBinDataInDocInfo(streamIndex);
    }

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

    private void addGsoControl() {
        createRectangleControlAtFirstParagraph();

        setCtrlHeaderGso();
        setShapeComponent();
        setShapeComponentRectangle();
    }

    private void createRectangleControlAtFirstParagraph() {
        Section firstSection = hwpFile.getBodyText().getSectionList().get(0);
        Paragraph firstParagraph = firstSection.getParagraph(0);

        // 문단에서 사각형 컨트롤의 위치를 표현하기 위한 확장 문자를 넣는다.
        firstParagraph.getText().addExtendCharForGSO();

        // 문단에 사각형 컨트롤 추가한다.
        rectangle = (ControlRectangle) firstParagraph.addNewGsoControl(GsoControlType.Rectangle);
    }

    private void setCtrlHeaderGso() {
        CtrlHeaderGso hdr = rectangle.getHeader();
        GsoHeaderProperty prop = hdr.getProperty();
        prop.setLikeWord(false);
        prop.setApplyLineSpace(false);
        prop.setVertRelTo(VertRelTo.Para);
        prop.setVertRelativeArrange(RelativeArrange.TopOrLeft);
        prop.setHorzRelTo(HorzRelTo.Para);
        prop.setHorzRelativeArrange(RelativeArrange.TopOrLeft);
        prop.setVertRelToParaLimit(true);
        prop.setAllowOverlap(true);
        prop.setWidthCriterion(WidthCriterion.Absolute);
        prop.setHeightCriterion(HeightCriterion.Absolute);
        prop.setProtectSize(false);
        prop.setTextFlowMethod(TextFlowMethod.FitWithText);
        prop.setTextHorzArrange(TextHorzArrange.BothSides);
        prop.setObjectNumberSort(ObjectNumberSort.Figure);

        hdr.setyOffset(fromMM(shapePosition.y));
        hdr.setxOffset(fromMM(shapePosition.x));
        hdr.setWidth(fromMM(shapePosition.width));
        hdr.setHeight(fromMM(shapePosition.height));
        hdr.setzOrder(0);
        hdr.setOutterMarginLeft(0);
        hdr.setOutterMarginRight(0);
        hdr.setOutterMarginTop(0);
        hdr.setOutterMarginBottom(0);
        hdr.setInstanceId(imgObj.getInstanceId());
        hdr.setPreventPageDivide(false);
        hdr.getExplanation().setBytes(null);
    }

    private int fromMM(int mm) {
        if (mm == 0) {
            return 1;
        }

        return (int) ((double) mm * 72000.0f / 254.0f + 0.5f);
    }

    private void setShapeComponent() {
        ShapeComponentNormal sc = (ShapeComponentNormal) rectangle.getShapeComponent();
        sc.setOffsetX(0);
        sc.setOffsetY(0);
        sc.setGroupingCount(0);
        sc.setLocalFileVersion(1);
        sc.setWidthAtCreate(fromMM(shapePosition.width));
        sc.setHeightAtCreate(fromMM(shapePosition.height));
        sc.setWidthAtCurrent(fromMM(shapePosition.width));
        sc.setHeightAtCurrent(fromMM(shapePosition.height));
        sc.setRotateAngle(0);
        sc.setRotateXCenter(fromMM(shapePosition.width / 2));
        sc.setRotateYCenter(fromMM(shapePosition.height / 2));

        sc.createLineInfo();
        LineInfo li = sc.getLineInfo();
        li.getProperty().setLineEndShape(LineEndShape.Flat);
        li.getProperty().setStartArrowShape(LineArrowShape.None);
        li.getProperty().setStartArrowSize(LineArrowSize.MiddleMiddle);
        li.getProperty().setEndArrowShape(LineArrowShape.None);
        li.getProperty().setEndArrowSize(LineArrowSize.MiddleMiddle);
        li.getProperty().setFillStartArrow(true);
        li.getProperty().setFillEndArrow(true);
        li.getProperty().setLineType(LineType.None);
        li.setOutlineStyle(OutlineStyle.Normal);
        li.setThickness(0);
        li.getColor().setValue(0);

        sc.createFillInfo();
        FillInfo fi = sc.getFillInfo();
        fi.getType().setPatternFill(false);
        fi.getType().setImageFill(true);
        fi.getType().setGradientFill(false);
        fi.createImageFill();
        ImageFill imgF = fi.getImageFill();
        imgF.setImageFillType(ImageFillType.FitSize);
        imgF.getPictureInfo().setBrightness((byte) 0);
        imgF.getPictureInfo().setContrast((byte) 0);
        imgF.getPictureInfo().setEffect(PictureEffect.RealPicture);
        imgF.getPictureInfo().setBinItemID(binDataID);

        sc.createShadowInfo();
        ShadowInfo si = sc.getShadowInfo();
        si.setType(ShadowType.None);
        si.getColor().setValue(0xc4c4c4);
        si.setOffsetX(283);
        si.setOffsetY(283);
        si.setTransparent((short) 0);

        sc.setMatrixsNormal();
    }

    private void setShapeComponentRectangle() {
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
    }
}
