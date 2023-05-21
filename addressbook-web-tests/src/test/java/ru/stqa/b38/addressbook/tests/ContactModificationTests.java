package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  private void ensurePreconditions() {
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData()
              .withFirstname("test123").withLastname("test456").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru"));
    }
  }
  @Test//(enabled = false)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = 0;
    app.contact().initModification();
    ContactData contact = new ContactData().withId(before.get(index).getId())
            .withFirstname("test124").withLastname("test457").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru");
    app.contact().fillForm(contact);
    app.contact().submitModification();
    app.contact().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> Byid = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(Byid);
    after.sort(Byid);
    Assert.assertEquals(after, before);
  }
}
