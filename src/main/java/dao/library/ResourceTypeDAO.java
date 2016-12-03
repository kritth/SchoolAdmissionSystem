package dao.library;

import java.util.List;

import bean.library.ResourceType;
import template.DAO;
import util.HibernateUtil;

public class ResourceTypeDAO implements DAO<ResourceType>
{
	@Override
	public void insert(ResourceType o) { HibernateUtil.insert(o); }

	@Override
	public void update(ResourceType o) { HibernateUtil.update(o); }

	@Override
	public void removeByPrimaryKey(int id) { HibernateUtil.deleteById(ResourceType.class, id); }

	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceType> getAll() { return (List<ResourceType>) HibernateUtil.loadAll(ResourceType.class); }

	@Override
	public ResourceType getByPrimaryKey(int id) { return (ResourceType) HibernateUtil.load(ResourceType.class, id); }
	
	// Singleton
	private static ResourceTypeDAO instance;
	
	private ResourceTypeDAO() { }
	public static ResourceTypeDAO getInstance()
	{
		if (instance == null) instance = new ResourceTypeDAO();
		return instance;
	}
}
