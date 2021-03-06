package com.mcit.kritth.bo.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import com.mcit.kritth.bo.TestService;
import com.mcit.kritth.bo.template.EmployeeBO;
import com.mcit.kritth.bo.template.StudentBO;
import com.mcit.kritth.bo.template.UserBO;
import com.mcit.kritth.dao.template.PersonDAO;
import com.mcit.kritth.model.data.Employee;
import com.mcit.kritth.model.data.Person;
import com.mcit.kritth.model.data.Student;
import com.mcit.kritth.model.data.User;
import com.mcit.kritth.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonBO implements TestService
{
	@Mock
	private Person instance;
	@Mock
	private Student student;
	@Mock
	private Employee employee;
	@Mock
	private User user;
	@Mock
	private PersonDAO dao;
	@Mock
	private StudentBO sservice;
	@Mock
	private EmployeeBO eservice;
	@Mock
	private UserBO uservice;
	@InjectMocks
	private PersonBOImpl service;
	
	@Before
	@Override
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Override
	public void testInsert()
	{
		service.insert(instance);
		verify(dao).insertBean(instance);
	}

	@Test
	@Override
	public void testUpdate() {
		service.update(instance);
		verify(dao).updateBean(instance);
	}

	@Test
	@Override
	public void testGet() {
		int id = TestUtil.generateRandomNumber();
		service.getById(id);
		verify(dao).getModelByPrimaryKey(id);
	}

	@Test
	@Override
	public void testGetAll() {
		service.getAll();
		verify(dao).getAllBeans();
	}

	@Test
	@Override
	public void testDelete() throws Exception {
		int id = TestUtil.generateRandomNumber();
		when(instance.getID()).thenReturn(id);
		when(sservice.getById(instance.getID())).thenReturn(student);
		when(eservice.getById(instance.getID())).thenReturn(employee);
		when(uservice.getByPersonId(instance.getID())).thenReturn(user);
		service.delete(instance);
		verify(sservice).delete(student);
		verify(eservice).delete(employee);
		verify(uservice).delete(user);
		verify(dao).removeBeanByPrimaryKey(instance.getID());
	}
	
}
