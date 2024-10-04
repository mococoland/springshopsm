package sm.Manager;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

import java.util.List;

public class SelectAllCategory {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        try {
            List<Category> categories = categoryService.get(); // 모든 카테고리 조회
            System.out.println("모든 카테고리 목록:");
            for (Category category : categories) {
                System.out.println("카테고리 ID: " + category.getCategoryId() +
                        ", 카테고리 이름: " + category.getCategoryName() +
                        ", 상위 카테고리 ID: " + category.getSuperCategory());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
