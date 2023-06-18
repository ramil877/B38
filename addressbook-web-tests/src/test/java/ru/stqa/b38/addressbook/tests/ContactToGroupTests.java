package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;
import ru.stqa.b38.addressbook.model.GroupData;
import ru.stqa.b38.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ContactToGroupTests extends TestBase {
    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("test123").withLastname("test456").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru"));
        }
        if (freeGroups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name1").withHeader("header1").withFooter("footer1"));
            app.goTo().homePage();
        }
    }

    @Test//(enabled = false)
    public void testContactToGroup() {

        ContactData contact = app.db().contacts().iterator().next();
        Groups before = contact.getGroups();
        int usedGroup = freeGroups().iterator().next().getId();
        app.contact().addingToGroup(contact.getId(), usedGroup);
        ContactData contactData = app.db().contacts().iterator().next();
        Groups after = contactData.getGroups();

        assertThat(after.size(), equalTo(before.size() + 1));
    }
}
