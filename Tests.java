import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
public class Tests
{
    @Test
    void Count_TwoNormalFolder_2()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        cabinet.AddFolderToCabinet(new TestFolder("first", "MEDIUM"));
        cabinet.AddFolderToCabinet(new TestFolder("second", "MEDIUM"));

        assertEquals(cabinet.count(), 2);
    }

    @Test
    void Count_OneMultiFolderWithTwoTestFoldersInside_3()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        TestMultiFolder f1 = new TestMultiFolder("first", "MEDIUM");
        f1.AddFolder(new TestFolder("second", "SMALL"));
        f1.AddFolder(new TestFolder("third", "LARGE"));

        cabinet.AddFolderToCabinet(f1);

        assertEquals(cabinet.count(), 3);
    }

    @Test
    void Count_ThreeMultiFoldersOneInEachOtherAndTwoNormalFoldersInFlatStructure_5()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        TestMultiFolder f1 = new TestMultiFolder("first", "MEDIUM");
        TestMultiFolder f2 = new TestMultiFolder("second", "SMALL");
        TestMultiFolder f3 = new TestMultiFolder("third", "LARGE");
        f1.AddFolder(f2);
        f2.AddFolder(f3);

        cabinet.AddFolderToCabinet(f1);
        cabinet.AddFolderToCabinet(new TestFolder("fourth", "SMALL"));
        cabinet.AddFolderToCabinet(new TestFolder("fifth", "LARGE"));

        assertEquals(cabinet.count(), 5);
    }

    @Test
    void FindFolderByName_TwoNormalFolders_ExistingName_True()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        Folder f1 = new TestFolder("first", "MEDIUM");
        Folder f2 = new TestFolder("second", "MEDIUM");

        cabinet.AddFolderToCabinet(f1);
        cabinet.AddFolderToCabinet(f2);

        assertEquals(cabinet.findFolderByName("first").get(), f1);
    }

    @Test
    void FindFolderByName_TwoNormalFolders_NotExistingName_True()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        Folder f1 = new TestFolder("first", "MEDIUM");
        Folder f2 = new TestFolder("second", "MEDIUM");

        cabinet.AddFolderToCabinet(f1);
        cabinet.AddFolderToCabinet(f2);

        assert(!cabinet.findFolderByName("First").isPresent());
    }

    @Test
    void FindFolderByName_ThreeMultiFoldersOneInEachOtherAndTwoNormalFoldersInFlatStructure_ExistingName_True()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        TestMultiFolder f1 = new TestMultiFolder("first", "MEDIUM");
        TestMultiFolder f2 = new TestMultiFolder("second", "SMALL");
        TestMultiFolder f3 = new TestMultiFolder("third", "LARGE");
        f1.AddFolder(f2);
        f2.AddFolder(f3);

        cabinet.AddFolderToCabinet(f1);
        cabinet.AddFolderToCabinet(new TestFolder("fourth", "SMALL"));
        cabinet.AddFolderToCabinet(new TestFolder("fifth", "LARGE"));

        assertEquals(cabinet.findFolderByName("third").get(), f3);
    }

    @Test
    void FindFolderBySize_ThreeMultiFoldersOneInEachOtherAndTwoNormalFoldersInFlatStructure_SMALL_TwoFolder()
    {
        FileCabinet cabinet = new FileCabinet();

        cabinet.AddConsumerToHashMap("TestFolder", new FolderConsumer());
        cabinet.AddConsumerToHashMap("TestMultiFolder", new MultiFolderConsumer());

        TestMultiFolder f1 = new TestMultiFolder("first", "MEDIUM");
        TestMultiFolder f2 = new TestMultiFolder("second", "SMALL");
        TestMultiFolder f3 = new TestMultiFolder("third", "LARGE");
        f1.AddFolder(f2);
        f2.AddFolder(f3);

        TestFolder f4 = new TestFolder("fourth", "SMALL");
        TestFolder f5 = new TestFolder("fifth", "LARGE");

        List<Folder> test_res = new ArrayList<Folder>();
        test_res.add(f2);
        test_res.add(f4);

        cabinet.AddFolderToCabinet(f1);
        cabinet.AddFolderToCabinet(f4);
        cabinet.AddFolderToCabinet(f5);

        assertEquals(cabinet.findFoldersBySize("SMALL"), test_res);
    }

}
    
