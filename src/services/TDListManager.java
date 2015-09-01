package services;
import controller.ListManagement;

import domain.ToDoList;
import domain.LineItem;
import domain.ToDoListCollection;
import domain.Constants;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created by sreerekhadeb on 27/08/15.
 */
public class TDListManager implements ListManagement {

    private ToDoListCollection listCollection;
    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public TDListManager()
    {
       // HibernateUtil.getSessionFactory();
        listCollection = new ToDoListCollection();

    }
    public ToDoListCollection getListCollection()
    {
        return listCollection ;
    }
    @Override
    public void updateStatus(String listName, String itemName, String status) {
        Session session = factory.getCurrentSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            LineItem item =
                    (LineItem)session.get(LineItem.class, itemName);
            item.setStatus(status);
            session.update(item);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                if (tx != null)
                    tx.commit();
                session.flush();
                session.close();
            }
        }


    }

    @Override
    public void cleanUpRecentlyFinishedList() {

    }

    @Override
    public void viewListItem(String listName, String itemName) {

    }

    @Override
    public boolean addItemToList(String listName, String itemName, String description) {
        Session session= factory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {

            LineItem item = new LineItem();
            item.setItemName(itemName);
           // item.setListName(listName);
            item.setItemDescription(description);
            item.setTimeStamp(new Date());
            item.setStatus(Constants.STATUS_ADDED);
            Map<String,LineItem> items = new HashMap<String,LineItem>();
            items.put(itemName,item);
            List lists =  session.createQuery("from domain.ToDoList ").list();

            Iterator iterator1;
            for (iterator1 = lists.iterator(); iterator1.hasNext();) {
                ToDoList list = (ToDoList) iterator1.next();
                list.setItems(items);
                list.addLineItem(item);
                session.save(list);
            }

            tx.commit();
        }catch(HibernateException he)
        {
            System.err.println(he.getMessage());
            System.exit(0);
            return false;
        }
        finally {
            if (session != null && session.isOpen()) {
                if (tx != null)
                    tx.commit();
                session.flush();
                session.close();
            }
        }
        return true;
    }

    @Override
    public ToDoList getList(String listName) {
        return null;
    }

    @Override
    public void viewList(String listName) {
        ToDoList list = listCollection.getToDoLists().get(listName);
        list.viewItemsInTheList(listName);

    }

    @Override
    public void deleteItemFromTheList(String listName, String itemName) {

    }

    @Override
    public void updateItemInTheList(String listName, String itemName, String itemDescription) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ToDoList list =
                    (ToDoList)session.get(ToDoList.class, listName);
            System.out.println("***********" +listName+"*****"+list);
           // LineItem item = list.getItems().get(itemName);
            LineItem item =(LineItem)list.getLineItem(itemName);
            item.setItemDescription(itemDescription);
            session.update(item);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                if (tx != null)
                    tx.commit();
                session.flush();
                session.close();
            }
        }

    }

    @Override
    public ToDoList createList(String listName) {
        ToDoList list = new ToDoList();
        list.setListName(listName);
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            Integer listid = (Integer) session.save(list);
            tx.commit();
          //  HibernateUtil.getSessionFactory().close();
        }catch(HibernateException he)
        {
            System.err.println(he.getMessage());
            System.exit(0);

        }
        finally {
            if (session != null && session.isOpen()) {
                if (tx != null)
                    tx.commit();
                session.flush();
                session.close();
            }
        }
        System.out.println("the list id is :"+list.getId());
        getListCollection().getToDoLists().put(listName, list);
        return list;
    }

    @Override
    public ToDoList getRecentlyFinishedList() {
        return listCollection.getRecentlyFinishedList();
    }

    @Override
    public void setItemInTheRecentlyFinishedList(LineItem completed_item) {
        listCollection.getRecentlyFinishedList().addLineItem(completed_item);

    }

    public static void main(String []args)
    {

        TDListManager tdm = new TDListManager();

        try{

            factory = HibernateUtil.getSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ListManager manager = new ListManager();

        String listname1 = "TODO List 1";
        String listname2 = "TODO List 2";
        String listname3 = "TODO List 3";

        /* Create the list */
//        Integer listId1 = manager.createList(listname1);
//        Integer listId2 = manager.createList(listname2);
//        Integer listId3 = manager.createList(listname3);


//        getListCollection().getToDoLists().put(listname2,new ToDoList(listname2));


        /* List down all the List names  */
//        manager.ListTheListNames();
//
//        /* Update list name */
//        manager.updateList(listId2, "New TODO List 1");
//
//        /* Delete a list from the database */
//        manager.deleteList(listId1);
//
//        /* List down all the ListNames  */
//        manager.ListTheListNames();
//
//
//
//        //create linetitems and add them to the todolist
//        manager.addListItem("item1","item description 1", Constants.STATUS_ADDED,listId2);


    }
}
