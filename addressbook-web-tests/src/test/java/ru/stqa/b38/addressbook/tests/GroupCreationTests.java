package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", "null", "null");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getLogout();

    before.add(group);
    Comparator<? super GroupData> Byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(Byid);
    after.sort(Byid);
    Assert.assertEquals(after, before);
  }

}
