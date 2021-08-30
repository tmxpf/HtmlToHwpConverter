import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import parser.htmlparser.NodeTreeSearch;

public class HTMLToHwpParser {

	private String blankHwpPath;
	private HWPFile hwpFile;
	
	public HTMLToHwpParser(String blankHwpPath) {
		this.blankHwpPath = blankHwpPath;
		this.hwpFile = new HWPFile();
	}
	
	public HWPFile HTMLToHwpParser(String htmlString) throws Exception {
		// 빈 hwp파일 읽어들이기
		readBlankHwp();
        
        if (hwpFile != null) {
        	Document doc = Jsoup.parse(htmlString);
        	Element body = doc.body();
        	Elements pTags = body.getElementsByTag("p");
        	
        	for(Element ele : pTags) {
        		paragraphTraversor(ele);
        	}
        }
        
        return hwpFile;
	}
	
	private void readBlankHwp() throws Exception {
        hwpFile = HWPReader.fromFile(blankHwpPath);
	}
	
	private void paragraphTraversor(Element ele) {
		NodeTreeSearch nodeTreeSearch = new NodeTreeSearch(hwpFile);
		NodeTraversor traversor = new NodeTraversor();
		
		traversor.traverse(nodeTreeSearch, ele);
	}
	
}
