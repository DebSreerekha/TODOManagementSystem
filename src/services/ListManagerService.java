package services;

import com.sun.tools.internal.jxc.apt.Const;
import domain.Constants;
import domain.LineItem;
import domain.ToDoList;
import domain.ToDoListCollection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;


import java.sql.SQLException;
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

            loadContentsIntoLocalCollection();

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void loadContentsIntoLocalCollection()
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List lists = session.createQuery("from domain.ToDoList").list();
            for (Iterator iterator1 =
                 lists.iterator(); iterator1.hasNext(); ) {
                System.out.println("******************* Populating the local listCollection object ******************************");
                ToDoList list = (ToDoList) iterator1.next();
                listCollection.getToDoLists().put(list.getListName(),list);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public void reloadContentsIntoListCollection()
    {
        //flush the contents and reload contents ..
        System.out.println("**************Flushing the old contents ********************");

        listCollection.getToDoLists().clear();

        System.out.println("************* Reloading Fresh contents *********************");

        ListManagerService.loadContentsIntoLocalCollection();

    }

    /**
     * Default constructor
     */
    public ListManagerService() {

        cleanUpRecentlyFinishedList();

    }

    //create list
    public ToDoList createList(String listName) {
        Session session = factory.openSession();
        Transaction tx = null;

        ToDoList list = new ToDoList(listName);
        try {
            tx = session.beginTransaction();
            Map<String, LineItem> lineItemMap = new HashMap<String, LineItem>();
            list.setItems(lineItemMap);
            session.save(list);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        //return listId;
        System.out.println("list id :" + list.getId() + "listname" + listName);
        getListCollection().getToDoLists().put(listName, list);
        return list;
    }

    //update List
    public void updateList(String listName, String newListName) {
        ToDoList list = getListCollection().getToDoLists().get(listName);
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
        Hashtable<String, ToDoList> toDoLists = getListCollection().getToDoLists();
        toDoLists.put(newListName, list);
        toDoLists.remove(listName);

    }

    //read items
    public void ListTheListNames() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List lists = session.createQuery("from domain.ToDoList").list();
            for (Iterator iterator1 =
                 lists.iterator(); iterator1.hasNext(); ) {
                System.out.println("*********************** List contents*******************************");
                ToDoList list = (ToDoList) iterator1.next();
                System.out.println("ListName: " + list.getListName() + "   ");
                System.out.println("Creation date: " + list.getTimeStamp() + "    ");
                System.out.println("id: " + list.getId());
                System.out.println("----------------------------------------------------------------------");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //delete list
    public void deleteList(String listName) {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName);
        try {
            tx = session.beginTransaction();
            list = (ToDoList) session.get(ToDoList.class, list.getId());
            session.delete(list);
            tx.commit();
            getListCollection().getToDoLists().remove(listName);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    //add item to list
    public boolean addListItem(String listName, String itemName, String itemDescription, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean txnSuccessful = true;
        ToDoList list = getListCollection().getToDoLists().get(listName);

        try {
                tx = session.beginTransaction();

                list = (ToDoList) session.load(ToDoList.class, list.getId());

                Map<String, LineItem> lineItemMap = list.getItems();

                LineItem item = new LineItem();
                item.setStatus(status);
                item.setItemName(itemName);
                item.setItemDescription(itemDescription);

                lineItemMap.put(itemName, item);


                list.setItems(lineItemMap);

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

        if(txnSuccessful)
        {
           getListCollection().getToDoLists().put(list.getListName(),list);
        }
        return txnSuccessful;

    }


    //update list item decription
    public void updateLineItemDescription(String itemName, String itemDescription, String status, String listName) {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName);

        try {
            tx = session.beginTransaction();

            list = (ToDoList) session.load(ToDoList.class, list.getId());
            Map<String, LineItem> lineItemMap = list.getItems();

            LineItem item = lineItemMap.get(itemName);
            item.setItemDescription(itemDescription);
            item.setStatus(status);

            lineItemMap.put(itemName, item);
            System.out.println(item.getItemDescription());

            list.setItems(lineItemMap);

            session.save(list);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        getListCollection().getToDoLists().put(listName, list);

    }

    //update list item status
    public void updateLineItemStatus(String listName, String itemName, String status) {
        Session session = factory.openSession();
        Transaction tx = null;
        LineItem item = null ;
        ToDoList list = ListManagerService.getListCollection().getToDoLists().get(listName);

        try {
                tx = session.beginTransaction();

                list = (ToDoList) session.load(ToDoList.class, list.getId());
                Map<String, LineItem> lineItemMap = list.getItems();

                item = lineItemMap.get(itemName);

                item.setStatus(status);

                lineItemMap.put(itemName, item);

                list.setItems(lineItemMap);

                // if the status of the item is done , it needs to be removed
                //from the list and added to the recently finished list.
                if(!status.equals(Constants.STATUS_DONE)) {
                    session.save(list);
                }
                else {
                    //adding to the local collection
                    ToDoList RFIList = getListCollection().getRecentlyFinishedList();
                    RFIList.addLineItem(item);

                    //load the recently finished items list from the database
                    RFIList = (ToDoList) session.load(ToDoList.class,RFIList.getId());
                    Map<String, LineItem> RFILMap = RFIList.getItems();
                    String compositeKey = list.getListName()+item.getItemName();
                    RFILMap.put(compositeKey, item);
                    session.save(RFIList);
                   // session.delete(list);
                }
                tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        getListCollection().getToDoLists().put(listName, list);

    }

    //delete list item
    public void deleteLineItem(String listName, String itemName) {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName);

        try {
            tx = session.beginTransaction();

            Map<String, LineItem> lineItemMap = list.getItems();

            if(lineItemMap.containsKey(itemName)) {

                LineItem item = lineItemMap.get(itemName);
                item = (LineItem) session.get(LineItem.class, item.getId());
                lineItemMap.remove(itemName);
                list.setItems(lineItemMap);
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
        getListCollection().getToDoLists().put(listName, list);
    }

    //list the line item
    public void showListItem(String listName,String itemName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ToDoList list = getListCollection().getToDoLists().get(listName);
            list = (ToDoList) session.load(ToDoList.class, list.getId());
            Map<String, LineItem> lineItemMap = list.getItems();

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Displaying the line item..");
            LineItem item = lineItemMap.get(itemName);
            System.out.println("itemName        : " + item.getItemName());
            System.out.println("itemDescription : " + item.getItemDescription());
            System.out.println("Status          : " + item.getStatus());
            System.out.println("Time stamp      : " + item.getTimeStamp());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

    //list the list items
    public void listLineItems(String listName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ToDoList list = getListCollection().getToDoLists().get(listName);
            System.out.println("listName "+ list) ;
            list = (ToDoList) session.load(ToDoList.class, list.getId());
            Map<String, LineItem> lineItemMap = list.getItems();
            Collection<LineItem> Values = lineItemMap.values();
            Iterator<LineItem> iterator = Values.iterator();
            System.out.println("********************************************* listing the contents of the list " + listName + "***********************************************");
            while (iterator.hasNext()) {
                LineItem item = (LineItem) iterator.next();
                System.out.println("itemName        : " + item.getItemName());
                System.out.println("itemDescription : " + item.getItemDescription());
                System.out.println("Status          : " + item.getStatus());
                System.out.println("Time stamp      : " + item.getTimeStamp());
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //logic to clean up the recently finished list
    public void cleanUpRecentlyFinishedList() {
        //fetches the recently finished items list from the database
        ToDoList recentlyFinishedList = getListCollection().getToDoLists().get(Constants.RECENTLY_FINISHED_LIST);
        System.out.println("recentlyFinishedList " +recentlyFinishedList.getListName() ) ;
            Map<String, LineItem> items = recentlyFinishedList.getItems();
        if(items != null) {
            Collection<LineItem> Values = items.values();
            Iterator<LineItem> iterator = Values.iterator();
            while (iterator.hasNext()) {
                LineItem item = iterator.next();
                Date date = item.getTimeStamp();
                Date todaysDate = new Date();

                long noOfDays = (todaysDate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);

                if (noOfDays >= 3) {
                    //delete from the local list collection
                    getListCollection().getToDoLists().remove(item.getItemName());
                    //delete from the database table
                    deleteLineItem(Constants.RECENTLY_FINISHED_LIST, item.getItemName());
                }
            }
        }
    }

    // returns the local listCollection.
    public static ToDoListCollection getListCollection() {
        return listCollection;
    }

    //returns the list named recently finished list from the database.
    public  void getRecentlyFinishedList() {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(Constants.RECENTLY_FINISHED_LIST);
        try {
                tx = session.beginTransaction();
                list = (ToDoList) session.load(ToDoList.class, list.getId());
                getListCollection().getToDoLists().put(Constants.RECENTLY_FINISHED_LIST,list);
                tx.commit();
        }catch(HibernateException he){
        if (tx != null) tx.rollback();
        he.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Displays all the lists in the system..
     */
    public void displayAllTheLists()
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List lists = session.createQuery("from domain.ToDoList").list();
            for (Iterator iterator1 =
                 lists.iterator(); iterator1.hasNext(); ) {
                System.out.println("*********************** List contents*******************************");
                ToDoList list = (ToDoList) iterator1.next();
                System.out.print("ListName: " + list.getListName() + "   ");
                System.out.print("Creation date: " + list.getTimeStamp() + "    ");
                System.out.print("id: " + list.getId());
                System.out.println();

                Map<String,LineItem> itemsMap = list.getItems() ;
                Collection<LineItem> lineItemsMap = itemsMap.values();
                Iterator iter = lineItemsMap.iterator() ;
                while(iter.hasNext())
                {
                    LineItem item = (LineItem)iter.next();
                    System.out.println("************ Printing the line items *************");
                    System.out.println(item.getItemName());
                    System.out.println(item.getItemDescription()) ;
                    System.out.println(item.getTimeStamp());
                    System.out.println(item.getStatus());
                }
                System.out.println("----------------------------------------------------------------------");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}