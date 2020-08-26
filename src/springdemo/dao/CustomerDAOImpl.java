package springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory 
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public List<Customer> getCustomers() {
		// get current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession(); 
		
		// create a query .. sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class); 		
		
		// execute query and get result list 
		List<Customer> customers = theQuery.getResultList(); 		
		
		// return the results 		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate sesion 
		Session currentSession = sessionFactory.getCurrentSession(); 
		
		
		// save or update the customer 
		currentSession.saveOrUpdate(theCustomer); 
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession(); 
		
		Customer theCustomer = currentSession.get(Customer.class, theId); 
		
		return theCustomer; 
		
	}

	@Override
	public void deleteCustomer(Customer theCustomer) {
		// get current hibernate sesion 
		Session currentSession = sessionFactory.getCurrentSession(); 
				
				
		// delete the customer 
		currentSession.delete(theCustomer);
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current Hibernate session 
		Session currentSession = sessionFactory.getCurrentSession(); 
		Query theQuery = null; 
		
		// Only search by name if the searchName is not empty
		if(theSearchName!= null && theSearchName.trim().length() >0) {
			// Search for lastName or firstName
			theQuery = currentSession.createQuery("from Customer where "
												+ "lower(firstName) like:theName or lower(lastName) like:theName"
												,Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");			
		} else {
			// theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);
		}
		// execute query and get result list
        List<Customer> customers = theQuery.getResultList();                
        // return the results        
        return customers;
	}

}
