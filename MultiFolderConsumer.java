import java.util.Optional;
import java.util.Stack;

public class MultiFolderConsumer implements IConsumer
{
    @Override
    public int Count(Folder folder, Stack<Folder> stackForCount) 
    {
        if (folder instanceof MultiFolder)
        {   
            for (Folder f : ((MultiFolder)folder).getFolders()) 
            {
                stackForCount.add(f);
            }
            return 1;
        }
        throw new IllegalArgumentException("Folder for this Consumer implementation must have class implemented MultiFolder as its argument!");
    }

    @Override
    public Optional<Folder> CheckName(Folder folder, String pattern, Stack<Folder> stackForNamesCheck)
    {
        if (folder instanceof MultiFolder)
        { 
            if (folder.getName().equals(pattern))
            {
                return Optional.ofNullable(folder);
            }
            else
            {
                for (Folder f : ((MultiFolder)folder).getFolders())
                {
                    stackForNamesCheck.add(f);
                }
                return Optional.empty();
            }
        }
        throw new IllegalArgumentException("Folder for this Consumer implementation must have class implemented MultiFolder as its argument!");
    }
}
