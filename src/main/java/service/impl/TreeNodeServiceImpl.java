package service.impl;

import dao.TreeNodeDao;
import domain.TreeNode;
import service.TreeNodeService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TreeNodeServiceImpl implements TreeNodeService {
    @EJB
    private TreeNodeDao treeNodeDao;

    @Override
    public void save(TreeNode treeNode) {
        treeNodeDao.save(treeNode);
    }

    @Override
    public void update(TreeNode treeNode) {
        treeNodeDao.update(treeNode);
    }

    @Override
    public TreeNode getById(Integer id) {
        return treeNodeDao.getById(id);
    }

    @Override
    public void delete(Integer id) {
        treeNodeDao.delete(id);
    }

    @Override
    public List<TreeNode> getAllWhereParentNode(Integer parentNodeId, Integer pageId) {
        return treeNodeDao.getAllWhereParentNode(parentNodeId, pageId);
    }

    @Override
    public List<TreeNode> getFilteredByPattern(String pattern) {
        return treeNodeDao.getFilteredByPattern(pattern);
    }
}
