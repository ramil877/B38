package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteContact();
    app.alertAccept();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(0);
    Assert.assertEquals(before, after);
  }
}
