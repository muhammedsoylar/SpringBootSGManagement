package tr.com.teb.dw.product_app.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@SequenceGenerator(name = "PRODUCTS_SEQUENCE", sequenceName = "TEB_PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@Entity
@Table(name = "PRODUCTS")
public class Product
{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTS_SEQUENCE")
    @Id
    @Column(name = "PRODUCT_ID")
    private Integer productID;

    @Column(length = 120)
    private String name;

    @Column(length = 80)
    private String category;

    private String price;

    private String description;

    private Date created;
}
