package services;

import domain.Constants;
import domain.ListItem;
import domain.ToDoList;
import domain.ToDoListCollection;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;


import java.util.*;

/**
 * Created by sreerekhadeb on 31/08/15.
 */
public class ListManagerService {

    private static ToDoListCollection listCollection = new ToDoListCollection();

    private static SessionFactory factory;

    static {
        try {

            factory = HibernateUtil.getSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public ListManagerService() {

        cleanUpRecentlyFinishedList();

    }


    public void createList(String listName) {

        Session session = factory.openSession();
        Transaction tx = null;

        ToDoList list = new ToDoList(listName);
        try {
            tx = session.beginTransaction();
            Map<String, ListItem> ListItemMap = new HashMap<String, ListItem>();
            list.setItems(ListItemMap);
            session.save(list);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }


    public void updateList(String listName, String newListName) {

        ToDoList list = fetchTheList(listName);
        Session session = factory.openSession();
        Transaction tx = null;
        try {

                tx = session.beginTransaction();
                list = (ToDoList) session.get(ToDoList.class, list.getId());
                list.setListName(newListName);
                session.update(list);
                tx.commit();
            } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally {
            session.close();
            }


    }


    public ToDoList fetchTheList(String listName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = null ;
        try
        {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(ToDoList.class);
            // Add restriction.
            cr.add(Restrictions.eq("listName", listName));
            List toDoLists = cr.list();

            for (Iterator iterator1 =
                 toDoLists.iterator(); iterator1.hasNext(); ) {
                list = (ToDoList) iterator1.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public ListItem fetchTheListItem(String itemName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        ListItem listItem = null ;
        try {
            tx = session.beginTransaction();

            Criteria cr = session.createCriteria(ListItem.class,itemName);
            // Add restriction.
            cr.add(Restrictions.eq("itemName", itemName));
            List listItems = cr.list();

            for (Iterator iterator1 =
                 listItems.iterator(); iterator1.hasNext(); ) {
                listItem = (ListItem) iterator1.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listItem;

    }

    public ToDoListCollection fetchAllTheLists(){
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoListCollection listCollection = new ToDoListCollection();
        try {
            tx = session.beginTransaction();
            List lists = session.createQuery("from domain.ToDoList").list();
            for (Iterator iterator1 =
                 lists.iterator(); iterator1.hasNext(); ) {
                ToDoList list = (ToDoList) iterator1.next();
                listCollection.getToDoLists().add(list) ;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return listCollection;
    }

    public void deleteList(String listName) {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = fetchTheList(listName);
        try
        {
             if(list != null)
             {
                tx = session.beginTransaction();
                list = (ToDoList) session.get(ToDoList.class, list.getId());
                session.delete(list);
                tx.commit();
            }
            else
             {
                 System.err.println("List Is already deleted !!");
             }
        } catch (HibernateException e)
        {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }

    }

    public void deleteAllListsExceptRFIL()
    {
        Session session = factory.openSession();
        Transaction tx = null ;

        try {
                tx = session.beginTransaction();
                Criteria cr = session.createCriteria(ToDoList.class);
                // Add restriction.
                cr.add(Restrictions.ne("listName", "RFIL"));
                List lists = cr.list();
                for (Iterator iterator1 =
                     lists.iterator(); iterator1.hasNext(); ) {
                    ToDoList list = (ToDoList) iterator1.next();
                    System.out.println("Names " +list.getListName());
                    session.delete(list);
                }
                tx.commit();

        }catch(HibernateException he)
        {
            if (tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally
        {
            session.close();
        }

    }


    public boolean addListItem(String listName, String itemName, String itemDescription, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean txnSuccessful = true;
        ToDoList list = fetchTheList(listName);

        try {
                tx = session.beginTransaction();

                list = (ToDoList) session.load(ToDoList.class, list.getId());

                Map<String, ListItem> ListItemMap = list.getItems();

                ListItem item = new ListItem();
                item.setStatus(status);
                item.setItemName(itemName);
                item.setItemDescription(itemDescription);

                ListItemMap.put(itemName, item);

                list.setItems(ListItemMap);

                session.save(list);

                tx.commit();


        }catch(ConstraintViolationException cve)
        {
            txnSuccessful = false;
            if (tx != null) tx.rollback();
            cve.printStackTrace();
        }
        catch(HibernateException he)
        {
            txnSuccessful = false;
            he.printStackTrace();
        }
        finally {
            session.close();
        }


        return txnSuccessful;

    }

    public void updateListItemDescription(String itemName, String itemDescription, String status, String listName) {
        Session session = factory.openSession();
        Transaction tx = null;

        ToDoList list = fetchTheList(listName);

        try {
            tx = session.beginTransaction();

            list = (ToDoList) session.load(ToDoList.class, list.getId());
            Map<String, ListItem> ListItemMap = list.getItems();

            ListItem item = ListItemMap.get(itemName);
            item.setItemDescription(itemDescription);
            item.setStatus(status);

            ListItemMap.put(itemName, item);
            System.out.println(item.getItemDescription());

            list.setItems(ListItemMap);

            session.save(list);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    public void updateListItemStatus(String listName, String itemName, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        ListItem item = null ;
        ToDoList list = fetchTheList(listName) ;


        try {
                tx = session.beginTransaction();

                list = (ToDoList) session.load(ToDoList.class, list.getId());

                Map<String, ListItem> ListItemMap = list.getItems();

                item = ListItemMap.get(itemName);

                item.setStatus(status);

                ListItemMap.put(itemName, item);

                list.setItems(ListItemMap);


                if(!status.equals(Constants.STATUS_DONE)) {
                    session.save(list);
                }
                else
                {

                    ToDoList RFIList = getRecentlyFinishedList();
                    RFIList.addListItem(item);

                    RFIList = (ToDoList) session.load(ToDoList.class,RFIList.getId());
                    Map<String, ListItem> RFILMap = RFIList.getItems();
                    String compositeKey = list.getListName()+item.getItemName();
                    RFILMap.put(compositeKey, item);
                    session.save(RFIList);
                   // deleteListItem(listName,itemName);

                }
                tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public void deleteListItem(String listName, String itemName) {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list =fetchTheList(listName);
        try
        {
            tx = session.beginTransaction();

            Map<String, ListItem> ListItemMap = list.getItems();

            if(ListItemMap.containsKey(itemName)) {

                ListItem item = ListItemMap.get(itemName);
                item = (ListItem) session.get(ListItem.class, item.getId());
                ListItemMap.remove(itemName);
                list.setItems(ListItemMap);
                session.delete(item);
                tx.commit();
            }
            else
            {
                System.err.println("The specified item does not exist in the list");
            }

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void cleanUpRecentlyFinishedList() {
        ToDoList recentlyFinishedList = fetchTheList(Constants.RECENTLY_FINISHED_LIST);
        if(recentlyFinishedList != null) {
            System.out.println("recentlyFinishedList " + recentlyFinishedList.getListName());
            Map<String, ListItem> items = recentlyFinishedList.getItems();
            if (items != null) {
                Collection<ListItem> Values = items.values();
                Iterator<ListItem> iterator = Values.iterator();
                while (iterator.hasNext()) {
                    ListItem item = iterator.next();
                    Date date = item.getLastUpdatedAt();
                    Date todaysDate = new Date();

                    long noOfDays = (todaysDate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);

                    if (noOfDays >= 3) {

                        //delete from the database table
                        deleteListItem(Constants.RECENTLY_FINISHED_LIST, item.getItemName());
                    }
                }
            }
        }
    }
    public  ToDoList getRecentlyFinishedList() {
        ToDoList list = fetchTheList(Constants.RECENTLY_FINISHED_LIST);
        return list;
    }
    public static void main(String []args)
    {
        ListManagerService service = new ListManagerService();
        service.deleteAllListsExceptRFIL();
    }

}