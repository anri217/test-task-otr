package dao.impl;

import dao.TreeNodeDao;
import dao.util.HibernateSessionFactoryUtil;
import domain.TreeNode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TreeNodeDaoImpl implements TreeNodeDao {
    private final SessionFactory sessionFactory;

    public TreeNodeDaoImpl(){
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public void save(TreeNode treeNode) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(treeNode);
        transaction.commit();
        session.close();
    }
    @Override
    public void update(TreeNode treeNode) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(treeNode);
        transaction.commit();
        session.close();
    }

    @Override
    public TreeNode getById(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TreeNode treeNode = (TreeNode) session.createQuery("from TreeNode where id = :id").setParameter("id", id).
                uniqueResult();
        transaction.commit();
        session.close();
        return treeNode;
    }

    @Override
    public void delete(Integer id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from TreeNode where id = :id").setParameter("id", id).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TreeNode> getAllWhereParentNode(Integer parentNodeId, Integer pageId) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer limit = 20;
        List<TreeNode> treeNodeList;
        if (parentNodeId == null){
            treeNodeList = session.createQuery("from TreeNode where parentNode is null order by id")
                    .setFirstResult(pageId * limit - limit)
                    .setMaxResults(limit).list();
        } else {
            treeNodeList = session.createQuery("from TreeNode where parentNode.id = :parentNodeId order by id")
                    .setFirstResult(pageId * limit - limit)
                    .setMaxResults(limit + 1)
                    .setParameter("parentNodeId", parentNodeId).list();
        }
        transaction.commit();
        session.close();
        return treeNodeList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TreeNode> getFilteredByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<TreeNode> treeNodeList = session.createQuery("from TreeNode where " +
                "description like concat('%', :pattern, '%') order by id").setParameter("pattern", pattern).
                list();
        transaction.commit();
        session.close();
        return treeNodeList;
    }
}
