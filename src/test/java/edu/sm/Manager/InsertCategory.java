package edu.sm.Manager;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InsertCategory {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");
        CategoryService categoryService = new CategoryService();
        Category category = new Category(20,1 , "후드티"); // categoryId = 1, categoryName = "Electronics", superCategory = 0

        try {
            categoryService.add(category);
            System.out.println("카테고리가 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}