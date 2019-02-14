package jwd.wafepa.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		Activity swimming = new Activity("Swimming");
		Activity running = new Activity("Running");
		
		activityService.save(swimming);
		activityService.save(running);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(2L);
		
		Assert.assertEquals("Running", a.getName());
	}
	
	@Test
	public void testFindOneNonexisting() {
		Activity a = activityService.findOne(3L);
		
		Assert.assertNull(a);
	}
	
	@Test
	public void testFindAll() {
		List<Activity> ret = activityService.findAll();
		
		Assert.assertEquals(2, ret.size());
	}
	
	@Test
	public void testDelete() {
		Activity a = activityService.delete(2L);
		
		Assert.assertEquals("Running", a.getName());
		
		List<Activity> ret = activityService.findAll();
		Assert.assertEquals(1, ret.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteNonexisting() {
		activityService.delete(3L);
	}
}
