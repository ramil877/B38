package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    app.getLogout();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> Byid = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(Byid);
    after.sort(Byid);
    Assert.assertEquals(after, before);
  }
}
