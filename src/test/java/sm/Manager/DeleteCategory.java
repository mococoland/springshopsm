package sm.Manager;

import edu.sm.service.CategoryService;

public class DeleteCategory {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        // 삭제할 카테고리 ID
        int categoryId = 20; // 삭제할 카테고리 ID를 지정합니다.

        try {
            boolean isDeleted = categoryService.remove(categoryId); // 카테고리 삭제
            if (isDeleted) {
                System.out.println("카테고리가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("해당 카테고리를 찾을 수 없거나 삭제에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
