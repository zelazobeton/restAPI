package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {
    public static final String CAT_NAME_1 = "CAT_NAME_1";
    public static final CategoryDTO CAT_1 = new CategoryDTO(CAT_NAME_1);
    public static final List<CategoryDTO> CATEGORIES =
            Arrays.asList(CAT_1, new CategoryDTO("CAT_2"), new CategoryDTO("CAT_3"));

    @Mock
    CategoryService categoryServiceMock;

    @InjectMocks
    CategoryController sut;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    public void testListCategories() throws Exception {
        when(categoryServiceMock.getAllCategories()).thenReturn(CATEGORIES);
        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //$ stands for root object
                .andExpect(jsonPath("$.categories", hasSize(CATEGORIES.size())));
    }

    @Test
    public void testGetCategoryByName() throws Exception {
        when(categoryServiceMock.getCategoryByName(CAT_NAME_1)).thenReturn(CAT_1);

        mockMvc.perform(get("/api/v1/categories/" + CAT_NAME_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(CAT_NAME_1)));
    }

}