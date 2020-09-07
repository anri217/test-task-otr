package service;

import domain.TreeNode;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TreeNodeService {
    void save(TreeNode treeNode);
    void update(TreeNode treeNode);
    TreeNode getById(Integer id);
    void delete(Integer id);
    List<TreeNode> getAllWhereParentNode(Integer parentNodeId, Integer pageId);
    List<TreeNode> getFilteredByPattern(String pattern);
}
