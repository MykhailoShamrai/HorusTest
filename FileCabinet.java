import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class FileCabinet implements Cabinet 
{
    private List<Folder> folders = new ArrayList<Folder>();
    private Map<String, IConsumer> consumersMap = new HashMap<String, IConsumer>();
    

    public void AddFolderToCabinet(Folder folder)
    {
        folders.add(folder);
    }
    
    public void AddConsumerToHashMap(String key, IConsumer consumer)
    {
        consumersMap.put(key, consumer);
    }

    @Override
    public Optional<Folder> findFolderByName(String name) 
    {
        Stack<Folder> stackForNames = new Stack<Folder>();
        Optional<Folder> opt;
        for (Folder folder : folders)
        {
            opt = consumersMap.get(folder.getClass().getName()).CheckName(folder, name, stackForNames);
            if (opt.isPresent())
                return Optional.ofNullable(folder);
        }
        while (!stackForNames.empty())
        {
            Folder folderFromStack = stackForNames.pop();
            opt = consumersMap.get(folderFromStack.getClass().getName()).CheckName(folderFromStack, name, stackForNames);
            if (opt.isPresent())
                return Optional.ofNullable(folderFromStack);
        }       
        return Optional.empty(); 
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        Stack<Folder> stackForSizes = new Stack<Folder>();
        List<Folder> res = new ArrayList<Folder>();
        for (Folder folder : folders)
        {
            consumersMap.get(folder.getClass().getName()).FindBySizes(folder, size, res, stackForSizes);;
        }
        while (!stackForSizes.empty())
        {
            Folder folderFromStack = stackForSizes.pop();
            consumersMap.get(folderFromStack.getClass().getName()).FindBySizes(folderFromStack, size, res, stackForSizes);
        }       
        return res;
    }

    @Override
    public int count() {
        Stack<Folder> stackForCount = new Stack<Folder>();
        int counter = 0;
        for (Folder folder : folders)
        {
            counter += consumersMap.get(folder.getClass().getName()).Count(folder, stackForCount);
        }
        while (!stackForCount.empty())
        {
            Folder folderFromStack = stackForCount.pop();
            counter += consumersMap.get(folderFromStack.getClass().getName()).Count(folderFromStack, stackForCount);
        }
        return counter;
    }
}

interface Folder {
    String getName();
    String getSize();
}

interface MultiFolder extends Folder {
     List<Folder> getFolders();
}

