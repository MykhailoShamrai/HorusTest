import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
public class Tests
{
    @Test
    void Count_TwoNormalFolder_2()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        cabinet.AddFolderToCabinet(new TestFolder("first", "medium"));
        cabinet.AddFolderToCabinet(new TestFolder("second", "medium"));

        assertEquals(cabinet.count(), 2);
    }

    @Test
    void Count_OneMultiFolderWithTwoTestFoldersInside_3()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        TestMultiFolder f1 = new TestMultiFolder("first", "medium");
        f1.AddFolder(new TestFolder("second", "small"));
        f1.AddFolder(new TestFolder("third", "large"));

        cabinet.AddFolderToCabinet(f1);

        assertEquals(cabinet.count(), 3);
    }

    @Test
    void Count_ThreeMultiFoldersOneInEachOtherAndTwoNormalFoldersInFlatStructure_5()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        TestMultiFolder f1 = new TestMultiFolder("first", "medium");
        TestMultiFolder f2 = new TestMultiFolder("second", "small");
        TestMultiFolder f3 = new TestMultiFolder("third", "large");
        f1.AddFolder(f2);
        f2.AddFolder(f3);

        cabinet.AddFolderToCabinet(f1);
        cabinet.AddFolderToCabinet(new TestFolder("fourth", "small"));
        cabinet.AddFolderToCabinet(new TestFolder("fifth", "large"));

        assertEquals(cabinet.count(), 5);
    }
}
    
