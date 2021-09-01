package maker.tagmaker;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.object.bodytext.paragraph.Paragraph;
import kr.dogfoot.hwplib.object.bodytext.paragraph.header.ParaHeader;
import kr.dogfoot.hwplib.object.bodytext.paragraph.lineseg.LineSegItem;
import kr.dogfoot.hwplib.object.bodytext.paragraph.lineseg.ParaLineSeg;
import maker.HwpContentMaker;
import maker.object.TagOptions;

public class TagMaker implements HwpContentMaker {

    private HWPFile hwpFile;
    private TagOptions tagOptions;

    public TagMaker(HWPFile hwpFile, TagOptions tagOptions) {
        this.hwpFile = hwpFile;
        this.tagOptions = tagOptions;
    }

    @Override
    public void make() {
        setCommon();
        setDocInfo();
        setBodyText();
    }

    private void setCommon() {

    }

    private void setDocInfo() {

    }

    private void setBodyText() {
    	Paragraph p = hwpFile.getBodyText().getSectionList().get(0).addNewParagraph();
        setParaHeader(p);
        setParaLineSeg(p);
    }

    private void setParaHeader(Paragraph p) {
        ParaHeader ph = p.getHeader();
        ph.setLastInList(true);
        // 문단 모양을 이미 만들어진 문단 모양으로 사용함
        ph.setParaShapeId(1);
        // 이미 만들어진 스타일으로 사용함
        ph.setStyleId((short) 1);
        ph.getDivideSort().setDivideSection(false);
        ph.getDivideSort().setDivideMultiColumn(false);
        ph.getDivideSort().setDividePage(false);
        ph.getDivideSort().setDivideColumn(false);
        ph.setCharShapeCount(1);
        ph.setRangeTagCount(0);
        ph.setLineAlignCount(1);
        ph.setInstanceID(0);
        ph.setIsMergedByTrack(0);
    }

    private void setParaLineSeg(Paragraph p) {
        p.createLineSeg();

        ParaLineSeg pls = p.getLineSeg();
        LineSegItem lsi = pls.addNewLineSegItem();

        lsi.setTextStartPosition(0);
        lsi.setLineVerticalPosition(0);
        lsi.setLineHeight(ptToLineHeight(11.0));
        lsi.setTextPartHeight(ptToLineHeight(11.0));
        lsi.setDistanceBaseLineToLineVerticalPosition(ptToLineHeight(11.0 * 0.85));
        lsi.setLineSpace(ptToLineHeight(4.0));
        lsi.setStartPositionFromColumn(0);
        lsi.setSegmentWidth((int) mmToHwp(50.0));
        lsi.getTag().setFirstSegmentAtLine(true);
        lsi.getTag().setLastSegmentAtLine(true);
    }

    private int ptToLineHeight(double pt) {
        return (int) (pt * 100.0f);
    }

    private long mmToHwp(double mm) {
        return (long) (mm * 72000.0f / 254.0f + 0.5f);
    }

}
