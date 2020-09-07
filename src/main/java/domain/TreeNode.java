package domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "nodes")
public class TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_node_id", referencedColumnName = "id")
    private TreeNode parentNode;
}
