package com.imashevdt11.store.unit.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imashevdt11.store.commons.EndpointConstants;
import com.imashevdt11.store.controllers.ProductController;
import com.imashevdt11.store.dtos.ProductDto;
import com.imashevdt11.store.services.ProductService;
import com.imashevdt11.store.services.auth.JwtService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    private ProductDto productDto;

    @BeforeEach
    public void init() {
        productDto = ProductDto.builder().name("knife").price(25.0).build();
    }

    @Test
    public void ProductController_AddProduct_ReturnCreated() throws Exception {
        given(productService.createProduct(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post(EndpointConstants.PRODUCTS_ENDPOINT + "/create")
                .with(anonymous())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(productDto.getPrice())));
    }

    @Test
    public void ProductController_GetAllProducts_ReturnListOfProductsDto() throws Exception {

        List<ProductDto> productDtoList = new ArrayList<>();

        productDtoList.add(productDto);
        productDtoList.add(productDto);

        when(productService.getAllProducts()).thenReturn(productDtoList);

        ResultActions response = mockMvc.perform(get(EndpointConstants.PRODUCTS_ENDPOINT).contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(productDtoList.size())));
    }

    @Test
    public void ProductController_GetProductById_ReturnProductDto() throws Exception {

        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(productDto);

        ResultActions response = mockMvc.perform(get(EndpointConstants.PRODUCTS_ENDPOINT + "/" + productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(productDto.getPrice())));
    }

    @Test
    public void ProductController_UpdateProduct_ReturnProductDto() throws Exception {

        Long productId = 1L;
        when(productService.updateProduct(productId, productDto)).thenReturn(productDto);

        ResultActions response = mockMvc.perform(put(EndpointConstants.PRODUCTS_ENDPOINT + "/update/" + productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(productDto.getPrice())));
    }

    @Test
    public void ProductController_DeleteProduct_ReturnNoContent() throws Exception {

        Long productId = 1L;
        doNothing().when(productService).deleteProductById(productId);

        ResultActions response = mockMvc.perform(delete(EndpointConstants.PRODUCTS_ENDPOINT + "/delete/" + productId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}