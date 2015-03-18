package com.example.newapp.pages;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
// import org.hibernate.Session;

import com.example.newapp.entities.Address;



/**
 * Start page of application newapp.
 */
public class Index
{
    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;

    @InjectComponent
    private Zone zone;

    @Persist
    @Property
    private int clickCount;

    @Inject
    private AlertManager alertManager;

    public Date getCurrentTime()
    // public String getCurrentTime()
    {
        return new Date();
    	// return "A great day to learn Tapestry";
    }

    void onActionFromIncrement()
    {
        alertManager.info("Increment clicked");

        clickCount++;
    }

    Object onActionFromIncrementAjax()
    {
        clickCount++;

        alertManager.info("Increment (via Ajax) clicked");

        return zone;
    }
    
    
    
    //@Inject
    //private Session session;
    
    @Inject
    private EntityManager em;
    
    public List<Address> getAddresses()
    {
        // return session.createCriteria(Address.class).list();
    	 Query query = em.createQuery("SELECT e FROM Address e");
    	 return query.getResultList();
    }
    
}

