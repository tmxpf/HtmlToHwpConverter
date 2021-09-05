package parser.htmlparser;

import kr.dogfoot.hwplib.object.HWPFile;
import maker.HwpContentMaker;
import maker.object.TagGroup;
import maker.object.TagOptions;
import maker.tagmaker.TagMaker;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

public class NodeTreeSearch implements NodeVisitor{

	private HWPFile hwpFile;
	private HwpContentMaker hwpContentMaker;

	public NodeTreeSearch(HWPFile hwpFile) {
		this.hwpFile = hwpFile;
	}

	@Override
	public void head(Node node, int depth) {
		// 1. 태그 추출
		// 2. 태그 내용, 스타일 tagOptions에 모두 저장
		// 3. hwp docInfo에 설정값 저장
		// 4. hwp bodyText에 실제 값 적용

		String nodeName = node.nodeName();
		TagOptions tagOptions = new TagOptions(node.clone());

		switch(nodeName) {
			case TagGroup.P : {
				hwpContentMaker = new TagMaker(hwpFile, tagOptions);
				break;
			}
			case TagGroup.FONT : {
				hwpContentMaker = new TagMaker(hwpFile, tagOptions);
				break;
			}
			case TagGroup.TEXT : {

				break;
			}
			case TagGroup.DIV : {

				break;
			}
			default : {
				hwpContentMaker.make();
			}
		}

		node.attributes();
		node.nodeName();
	}

	@Override
	public void tail(Node node, int depth) {
		
	}

}
