import java.util.ArrayList;
import java.util.List;

public class TestMultiFolder implements MultiFolder
{
    private String name;
    private String size;
    private List<Folder> folders;

    
    public TestMultiFolder(String name, String size)
    {
        folders = new ArrayList<Folder>();
        this.name = name;
        this.size = size;
    }

    public void AddFolder(Folder folder)
    {
        folders.add(folder);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public List<Folder> getFolders() 
    {
        return folders;
    }
    
}
