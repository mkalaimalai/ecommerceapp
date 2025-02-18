Feature: Customer API Tests

  Background:
    * url baseUrl
    * def customerRequest = read('classpath:karate/requests/create-customer.json')
    * def random = function(max){ return Math.floor(Math.random() * max) }
    * def randomEmail = function(){ return 'john.doe' + random(10000) + '@example.com' }
    * set customerRequest.email = randomEmail()

  Scenario: Create a new customer
    Given path '/api/customers'
    And request customerRequest
    When method POST
    Then status 201
    And match response.id == '#uuid'
    And match response.email == customerRequest.email
    And match response.addresses[0].streetAddress == customerRequest.addresses[0].streetAddress
    And match response.addresses[0].city == customerRequest.addresses[0].city

  Scenario: Get customer by ID
    # First create a customer
    * set customerRequest.email = randomEmail()
    Given path '/api/customers'
    And request customerRequest
    When method POST
    Then status 201
    * def customerId = response.id

    # Then get the customer
    Given path '/api/customers/', customerId
    When method GET
    Then status 200
    And match response.id == customerId
    And match response.email == customerRequest.email
    And match response.addresses == '#[1]'

  Scenario: Get all customers with pagination
    Given path '/api/customers'
    And param page = 0
    And param size = 10
    And param sort = 'createdAt,desc'
    When method GET
    Then status 200
    And match $ == { content: '#array', page: '#number', size: '#number', total_elements: '#number', total_pages: '#number' }
    And match each $.content contains { id: '#uuid', email: '#string' } 