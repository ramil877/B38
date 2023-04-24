package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.alertAccept();
  }
}
