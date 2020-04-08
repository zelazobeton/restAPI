package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest{
    public static final String CAT_NAME_1 = "CAT_NAME_1";


    @Mock
    CustomerService customerServiceMock;

    @InjectMocks
    CategoryController sut;
    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

//    @Test
//    public void testCreateNewCustomer() throws Exception {
//        CustomerDTO newCustomerDTO = new CustomerDTO("Jane", "Fonda");
//        CustomerDTO returnCustomerDTO = new CustomerDTO("Jane", "Fonda");
//        returnCustomerDTO.setUrl("/api/v1/customers/1");
//
//        when(customerServiceMock.createNewCustomer(newCustomerDTO)).thenReturn(returnCustomerDTO);
//        mockMvc.perform(post("api/v1/customers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(newCustomerDTO)))
//                    .andExpect(status().isCreated())
//                    .andExpect(jsonPath("$.firstname", equalTo("Jane")))
//                    .andExpect(jsonPath("$.lastname", equalTo("Fonda")))
//                    .andExpect(jsonPath("$.url", equalTo("/api/v1/customers/1")));
//    }

}