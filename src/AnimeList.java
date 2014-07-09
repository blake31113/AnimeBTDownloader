import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Blake on 2014/7/6.
 */

public class AnimeList
{
    public static final String AnimeListPath="http://share.dmhy.org/cms/page/name/programme.html/";

    public static AnimeFansubList fansubList=new AnimeFansubList();
    private static AnimeList instance = null;
    public static AnimeList getInstance()
    {
        if (instance == null){
            synchronized(AnimeList.class)
            {
                if(instance == null)
                {
                    instance = new AnimeList();
                }
            }
        }
        return instance;
    }
    public static void loadAnimeListFromFile() throws IOException
    {
        fansubList.removeAllList();
        FileReader fr = new FileReader(Config.AnimeListFilePath);
        BufferedReader br = new BufferedReader(fr);
        while (br.ready())
        {
            String[] strs=br.readLine().split("\\|");
            for(int i=0;i<strs.length;i++)
            {
                if(i==0)
                {
                    fansubList.addFansubData(new AnimeFansubData());
                    fansubList.getFansubData(fansubList.fansubDataSize()-1).setAnimeName(strs[i]);
                }
                else
                {
                    fansubList.getFansubData(fansubList.fansubDataSize()-1).addFansub(strs[i]);
                }
            }
        }
//        printAnimeFansubList();
        fr.close();
    }
    public static void reloadAnimeListFromWeb()
    {
        fansubList.removeAllList();
        try
        {
            WebClient wc = new WebClient(BrowserVersion.CHROME);
            wc.getOptions().setUseInsecureSSL(true);
            wc.getOptions().setJavaScriptEnabled(true);             // 启用JS解释器，默认为true
            wc.getOptions().setCssEnabled(false);                   // 禁用css支持
            wc.getOptions().setThrowExceptionOnScriptError(false);  // js运行错误时，是否抛出异常
            wc.getOptions().setTimeout(100000);                     // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
            wc.getOptions().setDoNotTrackEnabled(false);
            HtmlPage page = wc.getPage(AnimeListPath);
            DomNodeList<DomElement> links = page.getElementsByTagName("div");
            AnimeFansubList fansubList=new AnimeFansubList();
            int iterator=0;
            for (int i=0;i<links.size();i++)
            {
                if(links.get(i).getAttribute("class").equals("weekly_list_title")&&!links.get(i).asText().equals(" "))
                {
                    fansubList.addFansubData(new AnimeFansubData());
                    fansubList.getFansubData(iterator).setAnimeName(links.get(i).asText());
                    iterator++;
                }
                if(links.get(i).getAttribute("class").equals("weekly_list_part")&&!links.get(i).asText().equals(" "))
                {
                    DomNodeList<DomNode> childs=links.get(i).getChildNodes();
                    for(DomNode child:childs)
                    {
                        fansubList.getFansubData(iterator-1).addFansub(child.asText());
                    }
                }
            }
            FileWriter fw = new FileWriter(Config.AnimeListFilePath);
            for(int i=0;i<fansubList.fansubDataSize();i++)
            {
                fw.write(fansubList.getFansubData(i).getAnimeName()+"|");
                for(int j=0;j<fansubList.getFansubData(i).getFansubSize();j++)
                {
                    fw.write(fansubList.getFansubData(i).getFansub(j)+"|");
//                    System.out.println(fansubList.getFansubData(i).getFansub(j));
                }
                fw.write("\r\n");
            }
            fw.flush();
            fw.close();
            System.out.println("Successful Reload");
        }
        catch (Exception e)
        {
            System.err.println( "Exception: " + e );
        }
    }
    public static void printAnimeFansubList()
    {
        for(int i=0;i<fansubList.fansubDataSize();i++)
        {
            System.out.println("AnimeName: " + fansubList.getFansubData(i).getAnimeName());
            for(int j=0;j<fansubList.getFansubData(i).getFansubSize();j++)
            {
                System.out.println("FanSub: " + fansubList.getFansubData(i).getFansub(j));
            }
        }
    }
}

class AnimeFansubList
{
    private ArrayList<AnimeFansubData> animeFansubData=new ArrayList<AnimeFansubData>();
    public AnimeFansubData getFansubData(int index)
    {
        return animeFansubData.get(index);
    }
    public void addFansubData(AnimeFansubData fansubdata)
    {
        this.animeFansubData.add(fansubdata);
    }
    public int fansubDataSize()
    {
        return animeFansubData.size();
    }
    public void removeAllList()
    {
        animeFansubData.clear();
    }
}
class AnimeFansubData
{
    private String animeName="";
    private ArrayList<String> fansubs=new ArrayList<String>();
    public String getAnimeName()
    {
        return animeName;
    }
    public int getFansubSize()
    {
        return fansubs.size();
    }
    public String getFansub(int index)
    {
        return fansubs.get(index);
    }
    public void setAnimeName(String animeName)
    {
        this.animeName=animeName;
    }
    public void addFansub(String fansub)
    {
        this.fansubs.add(fansub);
    }
}
