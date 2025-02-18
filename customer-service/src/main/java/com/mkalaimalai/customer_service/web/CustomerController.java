package com.mkalaimalai.customer_service.web;

import io.swagger.v3.oas.annotations.Operation;
import com.example.common.dto.PageResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.mkalaimalai.customer_service.application.dto.CustomerDTO;
import com.mkalaimalai.customer_service.application.service.CustomerService;

import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(
            summary = "Create a new customer",
            description = "Creates a new customer with the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @Operation(
            summary = "Get a customer by ID",
            description = "Returns a customer based on the provided ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer found",
                    content = @Content(schema = @Schema(implementation = CustomerDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public Mono<CustomerDTO> getCustomer(@PathVariable UUID id) {
        return customerService.getCustomer(id);
    }

    @GetMapping
    @Operation(summary = "Get all customers with pagination")
    public Mono<ResponseEntity<PageResponse<CustomerDTO>>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {
        
        return customerService.getAllCustomers(PageRequest.of(page, size, Sort.by(sort)))
            .map(ResponseEntity::ok);
    }

    @Operation(
            summary = "Update a customer",
            description = "Updates an existing customer's details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public Mono<CustomerDTO> updateCustomer(
            @Parameter(description = "Customer ID", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Updated customer details", required = true)
            @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @Operation(
            summary = "Delete a customer",
            description = "Deletes a customer based on the provided ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCustomer(@PathVariable UUID id) {
        return customerService.deleteCustomer(id);
    }
} 