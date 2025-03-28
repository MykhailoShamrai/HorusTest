import java.util.Optional;
import java.util.Stack;

public interface IConsumer {
    public int Count(Folder folder, Stack<Folder> stackForCount);
    public Optional<Folder> CheckName(Folder folder, String pattern, Stack<Folder> stackForNamesCheck);
}
