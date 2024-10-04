package edu.sm;

import edu.sm.dto.Cart;
import edu.sm.service.CartService;

public class CartInsert {
    public static void main(String[] args) {
        CartService cartService = new CartService();

        // 상품 ID와 수량을 설정하여 장바구니 객체를 생성합니다.
        // 예를 들어, 상품 ID가 1인 상품을 장바구니에 추가한다고 가정합니다.
        Cart cart = Cart.builder()
                .custKey(1)
                .itemKey(2)  // 장바구니에 추가할 상품의 ID
                .itemCnt(3)   // 장바구니에 추가할 상품의 수량
                .price(4)


                .build();

        try {
            // 장바구니에 상품을 추가합니다.
            cartService.add(cart);
            System.out.println("상품이 장바구니에 추가되었습니다: " + cart.getItemKey());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("장바구니에 상품을 추가하는데 오류가 발생했습니다.");
        }
    }
}