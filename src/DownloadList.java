/**
 * Created by Blake on 2014/7/7.
 */
public class DownloadList
{
    private int numberOfTorrent=0;
    private String downloadURL;
    public DownloadList(String input)
    {
        downloadURL=input;
    }
    public String getURL()
    {
        return downloadURL;
    }
    public void setNumberOfTorrent(int input)
    {
        numberOfTorrent=input;
    }
    public int getNumberOfTorrent()
    {
        return numberOfTorrent;
    }
}
