package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;
import ru.stqa.b38.addressbook.model.GroupData;
import ru.stqa.b38.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ContactToGroupTests extends TestBase {
    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("test123").withLastname("test456").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru"));
        }
    }

    @Test//(enabled = false)
    public void testContactToGroup() {

        ContactData contact = app.db().contacts().iterator().next();
        if (freeGroups().size() == 0) {
            Groups groupsBefore = app.db().groups();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name1").withHeader("header1").withFooter("footer1"));
            Groups groupsAfter = app.db().groups();
            List<GroupData> newGroup = new ArrayList<>(groupsAfter);
            newGroup.removeAll(groupsBefore);
            int freeGroup = newGroup.iterator().next().getId();
            app.goTo().homePage();
            app.contact().addingToGroup(contact.getId(), freeGroup);

            assertThat(freeGroups().size(), equalTo(0));
        } else {
            Groups groupsBefore = contact.getGroups();
            int freeGroup = freeGroups().iterator().next().getId();
            app.contact().addingToGroup(contact.getId(), freeGroup);
            ContactData contactData = app.db().contacts().iterator().next();
            Groups groupsAfter = contactData.getGroups();

            assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        }
    }

    public List<GroupData> freeGroups() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups groupsWithContact = contact.getGroups();
        Groups allGroups = app.db().groups();
        List<GroupData> groups = new ArrayList<>(allGroups);
        groups.removeAll(groupsWithContact);
        return groups;
    }
}
