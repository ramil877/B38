package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;
import ru.stqa.b38.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test//(enabled = false)
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("test123").withLastname("test456").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
