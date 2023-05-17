package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    app.getLogout();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> Byid = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(Byid);
    after.sort(Byid);
    Assert.assertEquals(after, before);
  }
}
