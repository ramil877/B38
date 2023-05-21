package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test//(enabled = false)
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData> Byid = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(Byid);
    after.sort(Byid);
    Assert.assertEquals(after, before);
  }

}
