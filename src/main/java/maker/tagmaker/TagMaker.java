package maker.tagmaker;

import kr.dogfoot.hwplib.object.HWPFile;
import maker.HwpContentMaker;
import org.jsoup.nodes.Node;

public class TagMaker implements HwpContentMaker {

    private HWPFile hwpFile;
    private Node node;

    public TagMaker(HWPFile hwpFile, Node node) {
        this.hwpFile = hwpFile;
        this.node = node;
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

    }

}
