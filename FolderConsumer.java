import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class FolderConsumer implements IConsumer
{
    @Override
    public int Count(Folder folder, Stack<Folder> stackForCount) 
    {
        return 1;
    }

    @Override
    public Optional<Folder> CheckName(Folder folder, String pattern, Stack<Folder> stackForNamesCheck)
    {
        if (folder.getName().equals(pattern))
            return Optional.ofNullable(folder);
        return Optional.empty();
    }

    @Override
    public void FindBySizes(Folder folder, String size, List<Folder> res, Stack<Folder> stackForFindingBySize)
    {
        if (folder.getSize().equals(size))
        {
            res.add(folder);
        }
    }
}