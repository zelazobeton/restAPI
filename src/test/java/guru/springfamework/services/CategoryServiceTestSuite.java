package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.controllers.v1.CategoryController;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CategoryServiceTestSuite {
    public static final String CAT_NAME_1 = "CAT_NAME_1";
    public static final Category CAT_1 = new Category(CAT_NAME_1);
    public static final List<Category> CATEGORIES =
            Arrays.asList(CAT_1, new Category("CAT_2"), new Category("CAT_3"));
    CategoryService sut;

    @Mock
    public CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        sut = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void shouldGetAllCategories() throws Exception{
        when(categoryRepository.findAll()).thenReturn(CATEGORIES);
        List<CategoryDTO> categoryDTOS = sut.getAllCategories();
        assertEquals(CATEGORIES.size(), categoryDTOS.size());
    }

    @Test
    public void shouldGetCategoryByName() throws Exception{
        when(categoryRepository.findByName(CAT_NAME_1)).thenReturn(CAT_1);
        CategoryDTO categoryDTO = sut.getCategoryByName(CAT_NAME_1);
        assertEquals(CAT_1.getName(), categoryDTO.getName());
        assertEquals(CAT_1.getId(), categoryDTO.getId());
    }

}