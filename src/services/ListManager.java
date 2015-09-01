package services;

import domain.Constants;
import domain.LineItem;
import domain.ToDoList;
import domain.ToDoListCollection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.*;

/**
 * Created by sreerekhadeb on 31/08/15.
 */
public class ListManager {

    private static ToDoListCollection listCollection ;
    private static SessionFactory factory;

    public ListManager()
    {
        listCollection = new ToDoListCollection();
    }

    public static void main(String []args)
    {
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
        String newListName1 = "New TODO List 1";


        manager.createList(listname1);
        manager.createList(listname2);
        manager.createList(listname3);


        /* List down all the List names  */
        manager.ListTheListNames();

        /* Update list name */
        manager.updateList(listname1, newListName1);

        /* Delete a list from the database */
        manager.deleteList(listname3);

        /* List down all the ListNames  */
        manager.ListTheListNames();



        //create linetitems and add them to the todolist
        manager.addListItem("item2","item description 2", Constants.STATUS_ADDED,listname2);
        manager.addListItem("item20","item description 20", Constants.STATUS_ADDED,listname2);
        manager.addListItem("item21","item description 21", Constants.STATUS_ADDED,listname2);
        manager.addListItem("item22","item description 22", Constants.STATUS_ADDED,listname2);
        manager.addListItem("item1","item description 1", Constants.STATUS_ADDED,newListName1);

        //update the line item description
        manager.updateLineItemDescription("item2","updated description",Constants.STATUS_UPDATE,listname2);

        //list the contents of  a particular list
        manager.listLineItems(listname2);


        //delete a line item
        manager.deleteLineItem(listname2,"item2");

        //listing the contents after delete
        manager.listLineItems(listname2);

    }
    //create list
    public ToDoList createList(String listName)
    {
        Session session = factory.openSession();
        Transaction tx = null;

        ToDoList list = new ToDoList(listName);
        try{
            tx = session.beginTransaction();
            Map<String, LineItem> lineItemMap = new HashMap<String ,LineItem>();
            list.setItems(lineItemMap);
            session.save(list);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        //return listId;
        System.out.println("list id :"+list.getId() +"listname"+ listName);
        getListCollection().getToDoLists().put(listName,list);
        return list;
    }
    //update List
    public void updateList(String listName, String newListName)
    {
        ToDoList list = getListCollection().getToDoLists().get(listName);
        Session session = factory.openSession();
        Transaction tx = null;
        try{

            tx = session.beginTransaction();
             list = (ToDoList)session.get(ToDoList.class, list.getId());
            list.setListName(newListName);
            session.update(list);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        Hashtable<String,ToDoList> toDoLists = getListCollection().getToDoLists();
        toDoLists.put(newListName,list);
        toDoLists.remove(listName);

    }

    //read items
    public void ListTheListNames()
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List lists = session.createQuery("from domain.ToDoList").list();
            for (Iterator iterator1 =
                 lists.iterator(); iterator1.hasNext();) {
                System.out.println("*********************** List contents*******************************");
                ToDoList list = (ToDoList) iterator1.next();
                System.out.println("ListName: " + list.getListName()+"   ");
                System.out.println("Creation date: " + list.getTimeStamp()+"    ");
                System.out.println("id: " + list.getId());
                System.out.println("----------------------------------------------------------------------");
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    //delete list
    public void deleteList(String listName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName) ;
        try{
            tx = session.beginTransaction();
            list = (ToDoList)session.get(ToDoList.class, list.getId());
            session.delete(list);
            tx.commit();
            getListCollection().getToDoLists().remove(listName);
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }


    //add item to list
    public void addListItem(String itemName, String itemDescription,String status,String listName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName) ;


        try{
            tx = session.beginTransaction();

             list = (ToDoList) session.load(ToDoList.class,list.getId());
             Map<String, LineItem> lineItemMap = list.getItems();

            LineItem item = new LineItem();
            item.setStatus(status);
            item.setItemName(itemName);
            item.setItemDescription(itemDescription);

            lineItemMap.put(itemName, item);
            System.out.println(itemName);

            list.setItems(lineItemMap);

            session.save(list);

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        getListCollection().getToDoLists().put(listName,list) ;

    }
    //update list item
    public void updateLineItemDescription(String itemName, String itemDescription,String status,String listName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName) ;

        try{
            tx = session.beginTransaction();


            list = (ToDoList) session.load(ToDoList.class,list.getId());
            Map<String, LineItem> lineItemMap = list.getItems();

            LineItem item = lineItemMap.get(itemName);
            item.setItemDescription(itemDescription);
            item.setStatus(status);

            lineItemMap.put(itemName, item);
            System.out.println( item.getItemDescription());

            list.setItems(lineItemMap);

            session.save(list);

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        getListCollection().getToDoLists().put(listName,list);

    }

    //delete list item
    public void deleteLineItem(String listName,String itemName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        ToDoList list = getListCollection().getToDoLists().get(listName) ;

        try{
            tx = session.beginTransaction();


            Map<String, LineItem> lineItemMap = list.getItems();

            LineItem item = lineItemMap.get(itemName);

            System.out.println(item.getId());
             item = (LineItem) session.get(LineItem.class,item.getId());

            session.delete(item);

            tx.commit();
            lineItemMap.remove(itemName);

            list.setItems(lineItemMap);

        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
         getListCollection().getToDoLists().put(listName, list) ;
    }
    //list the list items
    public void listLineItems(String listName)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ToDoList list = getListCollection().getToDoLists().get(listName);
            list = (ToDoList) session.load(ToDoList.class,list.getId());
            Map<String, LineItem> lineItemMap = list.getItems();


            Collection<LineItem> Values = lineItemMap.values();
            Iterator<LineItem> iterator = Values.iterator() ;
            System.out.println("********************************************* listing the contents of the list " + listName + "***********************************************");
            while(iterator.hasNext())
            {
                LineItem item = (LineItem)iterator.next();
                System.out.println("itemName        : "+item.getItemName());
                System.out.println("itemDescription : " + item.getItemDescription());
                System.out.println("Status          : "+item.getStatus());
                System.out.println("Time stamp      : " + item.getTimeStamp());
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            }

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    public static ToDoListCollection getListCollection() {
        return listCollection ;
    }
}
