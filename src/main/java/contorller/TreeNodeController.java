package contorller;

import domain.TreeNode;
import service.TreeNodeService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/treeNodes")
public class TreeNodeController {

    @EJB
    private TreeNodeService treeNodeService;

    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTreeNode(TreeNode treeNode){
        treeNodeService.save(treeNode);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTreeNode(TreeNode treeNode){
        treeNodeService.update(treeNode);
    }

    @GET
    @Path("/{id}")
    public TreeNode getTreeNodeById(@PathParam("id") Integer id){
        return treeNodeService.getById(id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTreeNode(@PathParam("id") Integer id){
        System.out.println(id);
        treeNodeService.delete(id);
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreeNode> getAllWhereParentNode(@QueryParam("pageId") Integer pageId,
                                                @QueryParam("parentNodeId") Integer parentNodeId){
        //StartScriptExecutor.executeSqlStartScript("database/start_script.sql");
        if(pageId == null) { pageId = 1; }
        return treeNodeService.getAllWhereParentNode(parentNodeId, pageId);
    }

    @GET
    @Path("/pattern/{pattern}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreeNode> getFilteredByPattern(@PathParam("pattern") String pattern){
        return treeNodeService.getFilteredByPattern(pattern);
    }
}
