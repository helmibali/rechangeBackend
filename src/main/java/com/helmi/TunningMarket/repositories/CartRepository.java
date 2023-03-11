package com.helmi.TunningMarket.repositories;
import com.helmi.TunningMarket.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("Select cart  FROM Cart cart WHERE cart.user.username=:username and cart.commands.size=0")
    List<Cart> getCartByusername(@Param("username")String username);

    @Query("Select cart  FROM Cart cart WHERE cart.user.username=:username and cart.commands.size <> 0")
    List<Cart> getCartByusernameEnCours(@Param("username")String username);

    @Query("Select cart  FROM Cart cart WHERE cart.produit.user.username=:username and cart.commands.size <> 0")
    List<Cart> getCartByusernameInCmd(@Param("username")String username);
/*
    @Query("Select sum(cart.prix) FROM Cart cart WHERE cart.user.username=:username and cart.prix > 0")
    double getTotalAmountByUsername(@Param("username")String username);


    @Query("Select cart  FROM Cart cart WHERE cart.user.username=:username")
    List<Cart> getCartByusername(@Param("username")String username);


    @Query("Select addCart  FROM Cart addCart WHERE addCart.produit.idProduit= :product_id and addCart.user_id=:user_id")
    Optional<Cart> getCartByProductIdAnduserId(@Param("user_id")Long user_id, @Param("product_id")Long product_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM Cart addCart WHERE addCart.id =:cart_id   and addCart.user_id=:user_id")
    void deleteCartByIdAndUserId(@Param("user_id")Long user_id,@Param("cart_id")Long cart_id);
    @Modifying
    @Transactional
    @Query("DELETE  FROM Cart addCart WHERE   addCart.user_id=:user_id")
    void deleteAllCartByUserId(@Param("user_id")Long user_id);

    @Modifying
    @Transactional
    @Query("DELETE  FROM Cart addCart WHERE addCart.user_id=:user_id")
    void deleteAllCartUserId(@Param("user_id")Long user_id);
    @Modifying
    @Transactional
    @Query("update Cart addCart set addCart.qty=:qty,addCart.prix=:price WHERE addCart.id=:cart_id")
    void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("price")double price,@Param("qty")Integer qty);
*/

}
