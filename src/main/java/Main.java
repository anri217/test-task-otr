import domain.TreeNode;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {
    public static void main(String[] args){
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget service = client.target(getBaseURI());

        TreeNode parentTreeNode = new TreeNode();
        TreeNode treeNode = new TreeNode();

        for (int i = 1; i < 16670; i += 4){
            parentTreeNode.setDescription("" + i);
            parentTreeNode.setParentNode(null);
            System.out.println("" + i);
            i += 1;
            for (int j = i; j < i + 4; ++j){
                treeNode.setDescription("" + j);
                treeNode.setParentNode(parentTreeNode);
                parentTreeNode = treeNode;
                treeNode = new TreeNode();
                System.out.println("" + j);
            }
            service.request(MediaType.APPLICATION_JSON).
                    put(Entity.entity(parentTreeNode, MediaType.APPLICATION_JSON));
            parentTreeNode = new TreeNode();
        }

        for (int i = 16671; i < 33340; ++i){
            parentTreeNode.setDescription("" + i);
            parentTreeNode.setParentNode(null);
            service.request(MediaType.APPLICATION_JSON).
                    put(Entity.entity(parentTreeNode, MediaType.APPLICATION_JSON));
            parentTreeNode = new TreeNode();
            System.out.println("" + i);
        }
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/treeNodes").build();
    }
}
