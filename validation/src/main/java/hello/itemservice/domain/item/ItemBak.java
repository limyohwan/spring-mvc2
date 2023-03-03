package hello.itemservice.domain.item;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import lombok.Data;

@Data
// @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message="총합 10000 x") //java 11이상
public class ItemBak {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 10000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public ItemBak() {
    }

    public ItemBak(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
