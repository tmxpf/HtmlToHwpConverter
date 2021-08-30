package parser.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class HTMLForEduParser {

    public void parsingTest(String htmlString) {
    	List<String> childList = new ArrayList<>();
    	NodeVisitor myNodeVisitor = new MyNodeVisitor(childList);
    	NodeTraversor traversor = new NodeTraversor();
    	Document doc = Jsoup.parse(htmlString);
    	
    	Element body = doc.getElementsByTag("body").first();
    	if(body == null) {
    		System.out.println("unable to find any body.");
    	} else {
    		traversor.traverse(myNodeVisitor, body);
    		
    		for(String child : childList) {
    			System.out.println(child);
    		}
    	}
    }

}

class MyNodeVisitor implements NodeVisitor {
	private List<String> childList;
	
	public MyNodeVisitor(List<String> childList) {
		if(childList == null) {
			throw new NullPointerException();
		}
		
		this.childList = childList;
	}

	@Override
	public void head(Node node, int depth) {
		if(node.childNodeSize() == 0) {
			childList.add(node.toString());
		}
	}

	@Override
	public void tail(Node node, int dpth) {
		
	}
}