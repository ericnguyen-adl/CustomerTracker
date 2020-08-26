package springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdemo.dao.CustomerDAO;
import springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired
	private CustomerDAO customerDAO; 

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers(); 
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer); 
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		Customer theCustomer = customerDAO.getCustomer(theId); 
		return theCustomer; 
	}

	@Override
	@Transactional
	public void delete(Customer theCustomer) {
		customerDAO.deleteCustomer(theCustomer); 
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		return customerDAO.searchCustomers(theSearchName); 
	}

}