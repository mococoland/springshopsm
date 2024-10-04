package sm.Manager;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

public class UpdateCategory {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        // 업데이트할 카테고리 정보
        int categoryId = 20; // 업데이트할 카테고리 ID
        String newCategoryName = "업데이트된 후드티"; // 새 카테고리 이름
        int superCategory = 1; // 상위 카테고리 ID

        // 카테고리 객체 생성
        Category category = new Category(categoryId, superCategory, newCategoryName);

        try {
            categoryService.modify(category); // 카테고리 수정
            System.out.println("카테고리가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
