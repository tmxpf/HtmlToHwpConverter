import parser.htmlparser.HTMLForEduParser;

public class Main {

    public static void main(String[] args) throws Exception {

        //이미지 삽입예제
//      InsertImage.insertImageToHWP();

        //텍스트 삽입예제
//      InsertText.insertImageToHWP();

        //테이블 삽입예제
//      InsertTable.insertTableToHWP();

        String html = "<P style=\"margin-left:1.0pt;\"><font style='font-size:11pt;'>다음 글을 읽고 사건의 흐름을 정리하였을 때, 빈칸에 들어갈 사건으로 적절한 것에 ○표, 적절하지 않은 것에 X표 하시오.</font></P><P><TABLE fromhwp=\"1\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style='border-collapse:collapse;border:none;' width=\"100%\"><TR><TD valign=\"middle\"  style=\"width:29%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:solid #000000 1px;border-bottom:solid #000000 1px;padding:0pt 1pt 0pt 1pt\" ><P><font style='font-size:11pt;'>&nbsp;두 동네를 반으로 가르는 곳에 있던 사과나무에서 황금 사과가 열림.</font></P></TD><TD valign=\"middle\"  style=\"width:5%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:none;border-bottom:none;padding:0pt 1pt 0pt 1pt\" ><P style=\"text-align:center;\">→</P></TD><TD valign=\"middle\"  style=\"width:29%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:solid #000000 1px;border-bottom:solid #000000 1px;padding:0pt 1pt 0pt 1pt\" ><P><font style='font-size:11pt;'>&nbsp;</font></P></TD><TD valign=\"middle\"  style=\"width:5%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:none;border-bottom:none;padding:0pt 1pt 0pt 1pt\" ><P style=\"text-align:center;\">→</P></TD><TD valign=\"middle\"  style=\"width:29%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:solid #000000 1px;border-bottom:solid #000000 1px;padding:0pt 1pt 0pt 1pt\" ><P><font style='font-size:11pt;'>&nbsp;땅바닥에 금을 그어 황금 사과를 나누어 갖기로 함.</font></P></TD></TR></TABLE></P><P></P><P><font style='font-size:11pt;'>(1) 황금 사과를 서로 갖겠다고 두 동네 사람들이 싸움.&nbsp;</font><font style='font-size:11pt;'>(<input style=\"width:59px;height:27px;text-align:Center;border:1px none #000000;\" class=\"txtinput\" type=\"text\" value=\"\" />)</font></P><P><font style='font-size:11pt;'>(2) 황금 사과가 열린다는 소식이 온 동네에 퍼짐.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font style='font-size:11pt;'>(<input style=\"width:59px;height:27px;text-align:Center;border:1px none #000000;\" class=\"txtinput\" type=\"text\" value=\"\" />)</font></P><P><font style='font-size:11pt;'>(3) 아무도 나무를 눈여겨보지 않음.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font style='font-size:11pt;'>(<input style=\"width:59px;height:27px;text-align:Center;border:1px none #000000;\" class=\"txtinput\" type=\"text\" value=\"\" />)</font></P>";
        HTMLForEduParser test = new HTMLForEduParser();
        test.parsingTest(html);

//		String writePath = "C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/result-making-blankfile.hwp";
//		String html = "<P style=\"margin-left:1.0pt;\"><font style='font-size:11pt;'>다음 글을 읽고 사건의 흐름을 정리하였을 때, 빈칸에 들어갈 사건으로 적절한 것에 ○표, 적절하지 않은 것에 X표 하시오.</font></P><P><TABLE fromhwp=\"1\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style='border-collapse:collapse;border:none;' width=\"100%\"><TR><TD valign=\"middle\"  style=\"width:29%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:solid #000000 1px;border-bottom:solid #000000 1px;padding:0pt 1pt 0pt 1pt\" ><P><font style='font-size:11pt;'>&nbsp;두 동네를 반으로 가르는 곳에 있던 사과나무에서 황금 사과가 열림.</font></P></TD><TD valign=\"middle\"  style=\"width:5%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:none;border-bottom:none;padding:0pt 1pt 0pt 1pt\" ><P style=\"text-align:center;\">→</P></TD><TD valign=\"middle\"  style=\"width:29%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:solid #000000 1px;border-bottom:solid #000000 1px;padding:0pt 1pt 0pt 1pt\" ><P><font style='font-size:11pt;'>&nbsp;</font></P></TD><TD valign=\"middle\"  style=\"width:5%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:none;border-bottom:none;padding:0pt 1pt 0pt 1pt\" ><P style=\"text-align:center;\">→</P></TD><TD valign=\"middle\"  style=\"width:29%;height:90px;;border-left:solid #000000 1px;border-right:solid #000000 1px;border-top:solid #000000 1px;border-bottom:solid #000000 1px;padding:0pt 1pt 0pt 1pt\" ><P><font style='font-size:11pt;'>&nbsp;땅바닥에 금을 그어 황금 사과를 나누어 갖기로 함.</font></P></TD></TR></TABLE></P><P></P><P><font style='font-size:11pt;'>(1) 황금 사과를 서로 갖겠다고 두 동네 사람들이 싸움.&nbsp;</font><font style='font-size:11pt;'>(<input style=\"width:59px;height:27px;text-align:Center;border:1px none #000000;\" class=\"txtinput\" type=\"text\" value=\"\" />)</font></P><P><font style='font-size:11pt;'>(2) 황금 사과가 열린다는 소식이 온 동네에 퍼짐.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font style='font-size:11pt;'>(<input style=\"width:59px;height:27px;text-align:Center;border:1px none #000000;\" class=\"txtinput\" type=\"text\" value=\"\" />)</font></P><P><font style='font-size:11pt;'>(3) 아무도 나무를 눈여겨보지 않음.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font style='font-size:11pt;'>(<input style=\"width:59px;height:27px;text-align:Center;border:1px none #000000;\" class=\"txtinput\" type=\"text\" value=\"\" />)</font></P>";
//		HTMLToHwpParser hwpParser = new HTMLToHwpParser("C:/Users/Gyu/Downloads/테스트용 한글파일/실전 테스트/blank.hwp");
//		HWPFile hwpFile = hwpParser.HTMLToHwpParser(html);
//
//		HWPWriter.toFile(hwpFile, writePath);

        System.out.println("파일 생성 끝.");
    }
}
