package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "null", "null"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
