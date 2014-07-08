import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Blake on 2014/7/7.
 */
public class DownloadBT_Thread extends Thread
{

    private String fileURL;
    private String fileName;
    public DownloadBT_Thread(String input) throws MalformedURLException
    {
        fileURL =input;
        fileName=input.substring(input.lastIndexOf("/")+1,input.length());
    }
    public void run()
    {
        try
        {
            boolean ifhasdownloaded=false;
            TorrentLog.readList();
            for(int i=0;i<TorrentLog.TorrentlogList.size();i++)
            {
                if(TorrentLog.TorrentlogList.get(i).equals(fileURL))
                {
                    ifhasdownloaded=true;
                    System.out.println("This torrent has downloaded!");
                    break;
                }
            }
            if(!ifhasdownloaded)
            {
                downloadURLFile(fileURL, fileName);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void downloadURLFile(String urlPath,String fileName)
    {
        try
        {
            System.out.println("File is downloading: "+fileName);
            URL zeroFile=new URL(urlPath);
            BufferedInputStream bs=new BufferedInputStream(zeroFile.openStream());
            byte[] b=new byte[1024];
            FileOutputStream fs=new FileOutputStream(Config.DownloadTorrentPath +fileName);
            int len;
            while((len=bs.read(b,0,b.length))!=-1)
            {
                fs.write(b,0,len);
                fs.flush();
            }
            System.out.println("Finish Downloading: "+fileName);
            TorrentLog.addList(urlPath);
            bs.close();
            fs.close();
            if(Config.useutorrentFlag)
            {
                System.out.println("Use torrent to Download File!");
                if(new File(Config.torrentPath+fileName).exists())
                    useDownloadClientToDownload(Config.utorrentPath,Config.torrentPath+fileName);
                else
                    System.out.println(fileName+"  not exist in "+Config.torrentPath);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void useDownloadClientToDownload(String downloadClientPath,String torrentPath) throws IOException
    {
        System.out.println("Execute Command: "+downloadClientPath+" "+torrentPath);
        Process p = Runtime.getRuntime().exec(downloadClientPath+" "+torrentPath);
    }
}
