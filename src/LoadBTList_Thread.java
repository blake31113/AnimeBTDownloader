import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Blake on 2014/7/6.
 */
public class LoadBTList_Thread extends Thread
{
    public static final String KxtpKeywordPath="http://bt.ktxp.com/search.php?keyword=";
    private BTdataList bTdataList=new BTdataList();
    private int downloadListIndex;
    public LoadBTList_Thread(int index)
    {
        downloadListIndex=index;
    }
    public void run()
    {
        LoadTorrentList(MissionList.downList.get(downloadListIndex).getURL());
        MissionList.downList.get(downloadListIndex).setNumberOfTorrent(bTdataList.getBTdataSize());
    }
    public void LoadTorrentList(String input)
    {
        try
        {
            WebClient wc = new WebClient(BrowserVersion.CHROME);
            wc.getOptions().setUseInsecureSSL(true);
            wc.getOptions().setJavaScriptEnabled(false); // 启用JS解释器，默认为true
            wc.getOptions().setCssEnabled(false); // 禁用css支持
            wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
            wc.getOptions().setTimeout(10000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
            wc.getOptions().setDoNotTrackEnabled(false);
            String keyword=encodeToURL(input);
            HtmlPage page = wc.getPage(KxtpKeywordPath+keyword);
            DomNodeList<DomElement> links = page.getElementsByTagName("a");

            for (int i=0;i<links.size();i++)
            {
                String temp=links.get(i).getAttribute("href");
                if(links.get(i).hasAttribute("href"))
                {
                    if(temp.length()>10&&temp.substring(temp.length()-7,temp.length()).equals("torrent"))
                    {
                        bTdataList.addBTdata("http://bt.ktxp.com"+temp,links.get(i+1).asText());
//                        System.out.println(bTdataList.getBTdata(bTdataList.getBTdataSize()-1).getBTname());
                        Thread t2=new DownloadBT_Thread(bTdataList.getBTdata(bTdataList.getBTdataSize()-1).getBTname());
                        t2.start();
                    }

                }
            }
        }
        catch (Exception e) {
            System.err.println( "Exception: " + e );
        }
    }
    public String encodeToURL(String input) throws UnsupportedEncodingException
    {
        String str=java.net.URLEncoder.encode(input, "UTF-8");
        return str;
    }
    public BTdataList getbTdataList()
    {
        return bTdataList;
    }
}
class BTdataList
{
    private ArrayList<BTdata> btdatas=new ArrayList<BTdata>();
    public void addBTdata()
    {
        btdatas.add(new BTdata());
    }
    public void addBTdata(String BTname,String BTtorrentName)
    {
        btdatas.add(new BTdata(BTname,BTtorrentName));
    }
    public BTdata getBTdata(int i)
    {
        return btdatas.get(i);
    }
    public int getBTdataSize()
    {
        return btdatas.size();
    }
}
class BTdata
{
    private String BTname;
    private String BTtorrentName;
    public BTdata()
    {

    }
    public BTdata(String BTname,String BTtorrentName)
    {
        this.BTname=BTname;
        this.BTtorrentName=BTtorrentName;
    }
    public void setBTname(String input)
    {
        BTname=input;
    }
    public void setBTtorrentName(String input)
    {
        BTtorrentName=input;
    }
    public String getBTname()
    {
        return BTname;
    }
    public String getBTtorrentName()
    {
        return BTtorrentName;
    }
}